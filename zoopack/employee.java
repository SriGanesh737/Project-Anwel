package zoopack;
import java.util.*;



import java.sql.*;
import Database.*;
import Message.*;
import zoopack.employees.*;

interface   Salary_Calculation {

    void salary();
}

public abstract class employee implements Salary_Calculation {

    private String name;
    private int salary = 0;
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

        if (this instanceof Manager)
            type = 1;
        else if (this instanceof Caretaker)
            type = 2;
        else if (this instanceof Doctor)
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

        int rep = 0;
        for (employee e : ZooManagement.el) {
            if (e.getId() == this.getId())
                rep = 1;
        }

        switch (type) {
            case 1:
                Manager y1 = (Manager) this;
                db.setQuery(String.format("insert into manager values(%d,%s,%d)", y1.getId(), y1.getName(),
                        y1.getSalary()));
                if (rep == 1)
                    db.setQuery(String.format("update manager set id=%d,name=%s,salary=%d where id=%d", y1.getId(),
                            y1.getName(),
                            y1.getSalary(), y1.getId()));

                try {
                    db.Update();
                } catch (SQLException e1) {
                    System.out.println("while updating common table.");
                    e1.printStackTrace();
                }
                db.setQuery(String.format("insert into empIdType values(%d,\"%s\",%s)", this.getId(), "manager",
                        this.getName()));
                break;
            case 2:
                Caretaker y2 = (Caretaker) this;
                db.setQuery(String.format("insert into caretaker values(%d,%s,%d)", y2.getId(), y2.getName(),
                        y2.getSalary()));
                if (rep == 1)
                    db.setQuery(String.format("update caretaker set id=%d,name=%s,salary=%d where id=%d", y2.getId(),
                            y2.getName(),
                            y2.getSalary(), y2.getId()));

                try {
                    db.Update();
                } catch (SQLException e1) {
                    System.out.println("while updating common table.");
                    e1.printStackTrace();
                }
                db.setQuery(String.format("insert into empIdType values(%d,\"%s\",%s)", this.getId(), "caretaker",
                        this.getName()));
                break;
            case 4:
                ChartedAccountant y4 = (ChartedAccountant) this;
                db.setQuery(String.format("insert into chartedaccountant values(%d,%s,%d,%d)", y4.getId(), y4.getName(),
                        y4.getSalary(), y4.currentFinancialPosition));
                if (rep == 1)
                    db.setQuery(String.format(
                            "update chartedaccountant set id=%d,name=%s,salary=%d,currentFinancialPosition=%d where id=%d",
                            y4.getId(), y4.getName(),
                            y4.getSalary(), y4.currentFinancialPosition, y4.getId()));

                try {
                    db.Update();
                } catch (SQLException e1) {
                    System.out.println("while updating common table.");
                    e1.printStackTrace();
                }

                db.setQuery(String.format("insert into empIdType values(%d,\"%s\",%s)", this.getId(),
                        "chartedaccountant", this.getName()));
                break;
            case 3:
                Doctor y3 = (Doctor) this;
                db.setQuery(String.format("insert into doctor values(%d,%s,%d,%d)", y3.getId(), y3.getName(),
                        y3.getSalary(), y3.getCasesTaken()));

                if (rep == 1)
                    db.setQuery(String.format("update doctor set id=%d,name=%s,salary=%d,casesTaken=%d where id=%d",
                            y3.getId(), y3.getName(),
                            y3.getSalary(), y3.getCasesTaken(), y3.getId()));

                try {
                    db.Update();
                } catch (SQLException e1) {
                    System.out.println("while updating common table.");
                    e1.printStackTrace();
                }
                db.setQuery(String.format("insert into empIdType values(%d,\"%s\",%s)", this.getId(), "doctor",
                        this.getName()));
                break;
            case 5:
                ZooGuide y5 = (ZooGuide) this;
                db.setQuery(String.format("insert into zooguide values(%d,%s,%d,%f)", y5.getId(), y5.getName(),
                        y5.getSalary(), y5.getAvgrating()));

                if (rep == 1)
                    db.setQuery(
                            String.format("update zooguide set id=%d,name=%s,salary=%d,Average_rating=%f where id=%d",
                                    y5.getId(), y5.getName(),
                                    y5.getSalary(), y5.getAvgrating(), y5.getId()));
                //Customer.ids.add(y5.getId());

                try {
                    db.Update();
                } catch (SQLException e1) {
                    System.out.println("while updating common table.");
                    e1.printStackTrace();
                }

                db.setQuery(String.format("insert into empIdType values(%d,\"%s\",%s)", this.getId(), "zooguide",
                        this.getName()));
                break;
            default:
                System.out.println("In the Default Statement");
        }

        try {
            int rs = db.Update();
        } catch (SQLException e) {
            System.out.println("Error while adding message to Database");
            e.printStackTrace();
        }

        try {
            db.Close(db.getSt(), db.getCon());
        } catch (SQLException e) {
            System.out.println("Error in closing database");
            e.printStackTrace();
        }
    }

    public void update_salary_IN_Database() {

        String s = "employee";

        if (this instanceof Manager)
            s = "manager";
        else if (this instanceof Caretaker)
            s = "caretaker";
        else if (this instanceof Doctor)
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

        db.setQuery(String.format("update %s set salary=%d where id= %d", s, salary, id));

        try {
            int rs = db.Update();
            //System.out.println(rs);
        } catch (SQLException e) {

            System.out.println("while updating salary...");
            e.printStackTrace();
        }

        try {
            db.Close(db.getSt(), db.getCon());
        } catch (SQLException e) {
            System.out.println("Error in closing database");
            e.printStackTrace();
        }
    }

}




