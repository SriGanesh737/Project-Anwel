import java.util.*;

interface duties_and_salaries {
    void duty();
    void salary();
}



abstract class employee implements duties_and_salaries {

     String name;
     int salary;
     int id;


    public employee()
     {

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

    public boolean login_for_employee(employee e) {
        if (this.getName().compareTo(e.name) == 0 && this.id == e.id)
            return true;
        return false;
    }

}

class manager extends employee {

    public manager()
    {

    }
    public manager(String name, int id, int salary) {
        super(name, id);
        super.salary = salary;
    }

    public void duty() {

    }

    public void sendmessage(int recieversid, String msgid,String body)
    {
        //id is for whom we want to send
        message m = new message(msgid, getId(), body);
        m.setReceiversId(recieversid);
        for(employee x: ZooManagement.el)
        {
            if(x.getId()==recieversid)
            {
                caretaker y = (caretaker) x;
                y.inbox.add(m);
                System.out.println("Message Sent");
            }
        }
    }

    public void salary() {
        this.salary = 100000;

    }



}

class caretaker extends employee {

    ArrayList<message> inbox = new ArrayList<message>();

    public caretaker(String name, int id) {
        super.setName(name);
        super.setId(id);
    }

    public void showInbox() {
        System.out.println("The messages in the inbox are :");
        for (message x : inbox) {
            if (x.getMarkAsRead() == 0) {
                System.out.println(x.getBody());
                x.setMarkAsRead(1);
            }
        }
    }

    public void sendMessage(int receiversId,String msgId,String body)
    {
        message m = new message(msgId, getId(), body);
        m.setReceiversId(receiversId);
        for (employee x : ZooManagement.el) {
            if (x.getId() == receiversId) {
                doctor y = (doctor) x;
                y.inbox.add(m);
                System.out.println("Message Sent to doctor");
            }
        }

    }

    
    public void duty() {

    }

    public void salary() {

    }

}

class doctor extends employee {

    ArrayList<message> inbox = new ArrayList<message>();
    ArrayList<String> patientList = new ArrayList<>();
    private int CasesTaken=0;

    public doctor() {

    }

    public doctor(String name, int id,int salary) {
        super(name, id);
        super.setSalary(salary);
    }

    public void duty() {

    }

    public void salary() {
        setSalary(getSalary()+1000*CasesTaken);
        System.out.println("Your salary is :" + getSalary());
    }
}

class ChartedAccountant extends employee {
    ArrayList<message> inbox = new ArrayList<message>();
    static int currentFinancialPosition=0;


    public ChartedAccountant() {
    }

    public ChartedAccountant(String name, int id) {
        super(name, id);
    }

    public void duty()
    {

    }

    public void salary()
    {

    }

    public void addsponcer(String Name, int donatedAmount) {
        sponcers s = new sponcers(Name, donatedAmount);
        currentFinancialPosition += donatedAmount;
        //need to add in transaction log if we maintain one
        ZooManagement.sl.add(s);
    }

    public void showFinancialStatus()
    {
        System.out.printf("The current Financial status is : %d\n", currentFinancialPosition);
    }

}

class ZooGuide extends employee {

    private int baseSalary;
    //give bonus based on the ratings they got.
    ArrayList<Integer> ratings = new ArrayList<>();
    ArrayList<message> inbox = new ArrayList<message>();
    private double avgrating;

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


    public void calculateAvgRating()
    {
        int total = 0;
        int avg;
        for (int i = 0; i < ratings.size(); i++) {
            total += ratings.get(i);
            avg = total / ratings.size();
            System.out.println("The Average IS:" + avg);
            setAvgrating(avg);
        }
    }


    public void addRating(int rating)
    {
        ratings.add(rating);
        System.out.println("Rating added succesfully");
    }


    public void duty ()
    {

    }

    public void salary()
    {
        double sal = baseSalary + avgrating * 5000;
        super.setSalary((int) Math.round(sal));
    }


}

class sponcers {

    String Name;
    int donatedAmount;

    public sponcers() {

    }

    public sponcers(String name, int donatedAmount) {
        Name = name;
        this.donatedAmount = donatedAmount;
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

    @Override
    public String toString()
    {
        return String.format("Name: %s ,Donated Amount: %d\n", this.getName(),this.getDonatedAmount());
    }

}


public class employeemodule {

    public static void main(String args[])
    {



    }
}