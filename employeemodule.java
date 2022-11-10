import java.util.*;
import java.sql.*;

interface duties_and_salaries {

    void salary();
}

abstract class employee implements duties_and_salaries {

    private String name;
    private int salary=0;
    private int id;

    public employee() {

    }

    public employee(String name, int id) {

        this.name = name;
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public int getSalary() {
        return salary;
    }

    public void setSalary(int salary) {
        this.salary = salary;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public void addEmployee() {

        // we should execute differnt queries based on the type
        int type = -1;
        /*
         * type=1-->manager
         * type=2-->Caretaker
         * type=3-->Doctor
         * type=4-->ChartedAccountant
         * type=5-->ZooGuide
         */

        if (this instanceof manager)
            type = 1;
        else if (this instanceof caretaker)
            type = 2;
        else if (this instanceof doctor)
            type = 3;
        else if (this instanceof ChartedAccountant)
            type = 4;
        else if (this instanceof ZooGuide)
            type = 5;


        Database db = new Database();

        try {
            db.Establish();
        } catch (Exception e1) {
            e1.printStackTrace();
        }

        switch (type) {
            case 1:
                manager y1 = (manager) this;
                db.setQuery(String.format("insert into manager values(%d,\"%s\",%d)",y1.getId(),y1.getName(),y1.getSalary()));
                break;
            case 2:
                caretaker y2 = (caretaker) this;
                db.setQuery(String.format("insert into caretaker values(%d,\"%s\",%d)", y2.getId(), y2.getName(),
                        y2.getSalary()));
                break;
            case 4:
                ChartedAccountant y4 = (ChartedAccountant) this;
                db.setQuery(String.format("insert into chartedaccountant values(%d,\"%s\",%d,%d)", y4.getId(), y4.getName(),
                        y4.getSalary(),y4.currentFinancialPosition));
                break;
            case 3:
                doctor y3 = (doctor) this;
                db.setQuery(String.format("insert into doctor values(%d,\"%s\",%d,%d)", y3.getId(), y3.getName(),
                        y3.getSalary(),y3.CasesTaken));
                break;
            case 5:
                ZooGuide y5 = (ZooGuide) this;
                db.setQuery(String.format("insert into zooguide values(%d,\"%s\",%d,%f)", y5.getId(), y5.getName(),
                        y5.getSalary(),y5.getAvgrating()));
                break;
            default:
                System.out.println("In the Default Statement");
        }



        try {
            int rs = db.Update();
        } catch (SQLException e) {
            System.out.println("here bro while adding message to database error occurred!!+_+ O_O");
            e.printStackTrace();
        }

        try {
            db.Close(db.getSt(), db.getCon());
        } catch (SQLException e) {
            System.out.println("Error in closing ¬_¬");
            e.printStackTrace();
        }
    }

    public void update_salary_IN_Database() {

        String s = "employee";


        if (this instanceof manager)
            s = "manager";
        else if (this instanceof caretaker)
            s = "caretaker";
        else if (this instanceof doctor)
            s = "doctor";
        else if (this instanceof ChartedAccountant)
            s = "chartedaccountant";
        else if (this instanceof ZooGuide)
            s = "zooguide";

        Database db = new Database();

        try {
            db.Establish();
        } catch (Exception e1) {
            // TODO Auto-generated catch block
            e1.printStackTrace();
        }

        // need to test this line of code .



        db.setQuery(String.format("update %s set salary=%d where id= %d",s, salary, id));

        try {
            int rs = db.Update();
            System.out.println(rs);
        } catch (SQLException e) {

            System.out.println("while updating salary...");
            e.printStackTrace();
        }

        try {
            db.Close(db.getSt(), db.getCon());
        } catch (SQLException e) {
            System.out.println("Error in closing ¬_¬");
            e.printStackTrace();
        }
    }

}

class manager extends employee {

    public manager() {

    }

    public manager(String name, int id, int salary) {
        super(name, id);
    super.setSalary(salary);
    }

    public void sendmessage(int recieversid, String msgid, String body) {
        // id is for whom we want to send
        message m = new message(msgid, getId(), body);
        m.setReceiversId(recieversid);
        m.storeMessage();
    }

    public void salary() {
        super.setSalary(100000);
        this.update_salary_IN_Database();
    }

}

class caretaker extends employee {

    ArrayList<message> inbox = new ArrayList<message>();

    public caretaker(String name, int id) {
        super.setName(name);
        super.setId(id);
    }


    public void sendMessage(int receiversId, String msgId, String body) {

        message m = new message(msgId, getId(), body);
        m.setReceiversId(receiversId);
        m.storeMessage();
    }


    public void salary() {
        super.setSalary(50000);
        this.update_salary_IN_Database();
    }

}

class doctor extends employee {

    ArrayList<message> inbox = new ArrayList<message>();
    ArrayList<String> patientList = new ArrayList<>();
     int CasesTaken = 3;

    public doctor() {

    }

    public doctor(String name, int id, int salary) {
        super(name, id);
        super.setSalary(salary);
    }


    public void salary() {
        setSalary(getSalary() + 1000 * CasesTaken);
        System.out.println("Your salary is :" + getSalary());
        this.update_salary_IN_Database();
    }
}

class ChartedAccountant extends employee {
    ArrayList<message> inbox = new ArrayList<message>();
    static int currentFinancialPosition = 0;

    public ChartedAccountant() {
    }

    public ChartedAccountant(String name, int id) {
        super(name, id);
    }


    public void salary() {
        this.update_salary_IN_Database();
    }

    public void addsponcer(String Name, int donatedAmount,int id) {
        sponcers s = new sponcers(Name, donatedAmount,id);
        currentFinancialPosition += donatedAmount;
        s.storeSponcer();
        // need to add in transaction log if we maintain one
        ZooManagement.sl.add(s);
    }

    public void showFinancialStatus() {
        System.out.printf("The current Financial status is : %d\n", currentFinancialPosition);
    }

}

class ZooGuide extends employee {

    private int baseSalary;
    // give bonus based on the ratings they got.
    ArrayList<Integer> ratings = new ArrayList<>();
    ArrayList<message> inbox = new ArrayList<message>();
    private double avgrating=0;

    public ZooGuide(int baseSalary) {
        this.baseSalary = baseSalary;
    }

    public ZooGuide(String name, int id, int baseSalary) {
        super(name, id);
        this.baseSalary = baseSalary;
    }

    public double getAvgrating() {
        return avgrating;
    }

    public void setAvgrating(double avgrating) {
        this.avgrating = avgrating;
    }

    public int getBaseSalary() {
        return baseSalary;
    }

    public void setBaseSalary(int baseSalary) {
        this.baseSalary = baseSalary;
    }

    public void calculateAvgRating() {
        int total = 0;
        int avg;
        for (int i = 0; i < ratings.size(); i++) {
            total += ratings.get(i);
            avg = total / ratings.size();
            System.out.println("The Average IS:" + avg);
            setAvgrating(avg);
        }
    }

    public void addRating(int rating) {
        ratings.add(rating);
        System.out.println("Rating added succesfully");
    }


    public void salary() {
        double sal = baseSalary + avgrating * 5000;
        super.setSalary((int) Math.round(sal));
        this.update_salary_IN_Database();
    }

}

class sponcers extends Database {

    String Name;
    int donatedAmount;
    int id;

    public sponcers() {

    }

    public sponcers(String name, int donatedAmount, int id) {
        Name = name;
        this.donatedAmount = donatedAmount;
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public int getDonatedAmount() {
        return donatedAmount;
    }

    public void setDonatedAmount(int donatedAmount) {
        this.donatedAmount = donatedAmount;
    }

    public void storeSponcer() {
        Database db = new Database();

        try {
            db.Establish();
        } catch (Exception e1) {
            e1.printStackTrace();
        }

        db.setQuery(String.format("insert into sponcers values(%d,\"%s\",%d)", id, Name, donatedAmount));

        try {
            db.Update();
        } catch (SQLException e) {
            System.out.println("here bro while adding message to database error occurred!!+_+ O_O");
            e.printStackTrace();
        }

        try {
            db.Close(db.getSt(), db.getCon());
        } catch (SQLException e) {
            System.out.println("Error in closing ¬_¬");
            e.printStackTrace();
        }
    }

    @Override
    public String toString() {
        return String.format("Name: %s ,Donated Amount: %d\n", this.getName(), this.getDonatedAmount());
    }

}


public  class employeemodule {

    public static void main(String args[]) {

        employee doc1 = new doctor("vikram", 508, 1000);
        employee ct1 = new caretaker("raj", 106);
        employee m1 = new manager("hari", 101, 300000);
        employee ca1 = new ChartedAccountant("Ramesh", 333);
        employee zg1 = new ZooGuide("harry", 205, 30000);

        // new message().showUnreadMessages(108);

        // m1.sendmessage(106, "c-150", "Assemble here at hall");
        // ct1.sendMessage(108, "S-200", "A unread message for the second time ");
        // // ZooManagement.displayAllMessages();
        // System.out.println("here");
        // m1.sendmessage(106, "c-157", "Assemble here at room");
        // new message().showUnreadMessages(108);

        // doc1.addEmployee();
        // doc1.salary();
        // doc1.salary();

        // ct1.addEmployee();

        // m1.addEmployee();

        // ca1.addEmployee();

        // zg1.addEmployee();

        // new ChartedAccountant().addsponcer("gokul", 300000, 980);
        // new ChartedAccountant().addsponcer("rishi", 70000, 549);

        // ZooManagement.update_employee_detail("doctor", 508, "name", "vikram");
        // ZooManagement.update_employee_detail("doctor", 508, "salary", "667744");
        // ZooManagement.update_employee_detail("zooguide", 205, "Average_Rating", "4.7");
        ZooManagement.update_employee_detail("zooguide", 205, "salary", "50000");
         zg1.salary();
    }
}