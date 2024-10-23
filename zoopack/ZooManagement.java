package zoopack;

import java.sql.*;
import java.util.*;
import java.io.*;
import Database.*;
import Message.*;
import zoopack.employees.*;



//comparison classes to sort animals
class compared_based_on_name implements Comparator<Animal> {
    public int compare(Animal a1, Animal a2) {
        return a1.getName().compareTo(a2.getName());
    }
}

class compared_based_on_breed implements Comparator<Animal> {
    public int compare(Animal a1, Animal a2) {
        return a1.getBreed().compareTo(a2.getBreed());
    }
}

class compared_based_on_id implements Comparator<Animal> {
    public int compare(Animal a1, Animal a2) {
        return a1.getId().compareTo(a2.getId());
    }
}

public class ZooManagement {
    public static ArrayList<Animal> al = new ArrayList<>();
    public static ArrayList<employee> el = new ArrayList<>();
    public static ArrayList<Sponcers> sl = new ArrayList<>();
    public static ArrayList<message> ml = new ArrayList<>();
    public static ArrayList<Food> fl = new ArrayList<>();

    // used to store animals in the arraylist
    public static void store_animals_in_arraylist() {
        Database db = new Database();
        al.clear();
        try {
            db.Establish();
            db.setQuery(String.format("SELECT * FROM animals"));
            ResultSet rs = db.Execute();
            while (rs.next()) {
                Animal an = new Animal();
                an.setId(rs.getString(1));
                an.setName(rs.getString(2));
                an.setLocation(rs.getString(3));
                an.setHealthStatus(rs.getString(4));
                an.setHungryPercentage(rs.getInt(5));
                an.setBreed(rs.getString(6));
                an.setFoodType(rs.getString(7));
                al.add(an);

            }
        } catch (SQLException e) {
            System.out.println("There is an SQL Exception");
            e.printStackTrace();
        } catch (InputMismatchException e) {
            System.out.println("There is an SQL Exception");
            e.printStackTrace();
        }

        catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    public static void sort_animal_by_name() {
        ZooManagement.store_animals_in_arraylist();
        Collections.sort(al, new compared_based_on_name());
        for (Animal a : al) {
            System.out.println("***************************************************");
            System.out.println(a);
            System.out.println("***************************************************");
        }
    }

    public static void sort_animal_by_breed() {
        ZooManagement.store_animals_in_arraylist();
        Collections.sort(al, new compared_based_on_breed());
        for (Animal a : al) {
            System.out.println("***************************************************");
            System.out.println(a);
            System.out.println("***************************************************");
        }
    }

    public static void sort_animal_by_id() {
        ZooManagement.store_animals_in_arraylist();
        Collections.sort(al, new compared_based_on_id());
        for (Animal a : al) {
            System.out.println("***************************************************");
            System.out.println(a);
            System.out.println("***************************************************");
        }
    }


    public static void displaySponcers() {
    System.out.println("A very special thanks to all our sponcers !!!\n");
    for (Sponcers s : sl) {
    System.out.println(s);
    }
    }

    // This method is only for the highest authority for checking all the messages sent from anyone to anyone.
    public static void displayAllMessages() {
        System.out.println("The messages are :\n");
        for (message m : ml) {
            System.out.println(m);
        }
    }

    public static void update_employee_detail(String table_name, int empid, String column_name, String newVal) {
        String type, attr, val;
        int id;
        type = table_name;
        attr = column_name;
        val = newVal;
        id = empid;
        int intval = 0;
        double dblval = 3.7;
        String[] intList = { "salary", "id", "casesTaken", "currentFinancialPosition", "donatedAmount" };

        int qtype = 1;
        for (int i = 0; i < intList.length; i++) {
            if (attr.compareTo(intList[i]) == 0) {
                qtype = 2;
                intval = Integer.parseInt(newVal);
            }

        }

        if (attr.compareTo("Average_Rating") == 0) {
            qtype = 3;
            dblval = Double.parseDouble(newVal);
        }

        Database db = new Database();

        try {
            db.Establish();
        } catch (Exception e1) {
            // TODO Auto-generated catch block
            e1.printStackTrace();
        }

        // need to test this line of code .
        if (qtype == 1)
            db.setQuery(String.format("update %s set %s=\"%s\" where id= %d", type, attr, newVal, id));
        else if (qtype == 2)
            db.setQuery(String.format("update %s set %s=%d where id= %d", type, attr, intval, id));
        else
            db.setQuery(String.format("update %s set %s=%f where id= %d", type, attr, dblval, id));
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
            System.out.println("Error in closing ¬_¬");
            e.printStackTrace();
        }

    }

    public static void printResult(ResultSet rs, String type) throws SQLException {
        // System.out.println("here");
        if (type.compareTo("manager") == 0) {
            while (rs.next()) {
                System.out.printf("Id: %d\n Name:%s\n salary:%d\n", rs.getInt(1), rs.getString(2), rs.getInt(3));
            }

        } else if (type.compareTo("doctor") == 0) {
            while (rs.next()) {
                System.out.printf("Id: %d\n Name:%s\n salary:%d\n Cases Taken:%d\n", rs.getInt(1), rs.getString(2),
                        rs.getInt(3), rs.getInt(4));
            }
        } else if (type.compareTo("caretaker") == 0) {
            while (rs.next()) {
                System.out.printf("Id: %d\n Name:%s\n salary:%d\n", rs.getInt(1), rs.getString(2), rs.getInt(3));
            }
        } else if (type.compareTo("chartedaccountant") == 0) {
            while (rs.next()) {
                System.out.printf("Id: %d\n Name:%s\n salary:%d\n CurrentFinancialPosition: %d\n", rs.getInt(1),
                        rs.getString(2), rs.getInt(3), rs.getInt(4));
            }
        } else if (type.compareTo("zooguide") == 0) {
            while (rs.next()) {
                System.out.printf("Id: %d\n Name:%s\n salary:%d\n AverageRating: %f", rs.getInt(1), rs.getString(2),
                        rs.getInt(3), rs.getDouble(4));
            }
        }
    }

    public static void search_employee_detail(String column_name, String search_value) {

        String type = "&";
        Database db = new Database();

        try {
            db.Establish();
        } catch (Exception e) {
            System.out.println("While searching using id..");
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        if (column_name.compareTo("id") == 0) {
            int id = Integer.parseInt(search_value);
            db.setQuery(String.format("select type from empIdType  where id=%d", id));

            try {
                ResultSet rs = db.Execute();
                while (rs.next()) {
                    type = rs.getString("type");
                    System.out.println("type: " + type);

                    Database db2 = new Database();

                    try {
                        db2.Establish();
                    } catch (Exception e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                    }

                    db2.setQuery(String.format("select * from %s where id= %d", type, id));

                    ResultSet res = db2.Execute();
                    printResult(res, type);
                    while (res.next()) {
                        //System.out.println(res.getString("name"));
                        printResult(res, type);
                    }
                    db2.Close(db2.getSt(), db2.getCon());

                }

                db.Close(db.getSt(), db.getCon());
            } catch (SQLException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }

        } else if (column_name.compareTo("name") == 0) {
            db.setQuery("select type from empIdType where name like \'%" + search_value + "%\'");

            try {
                ResultSet rs = db.Execute();
                while (rs.next()) {
                    type = rs.getString("type");
                    System.out.println("type: " + type);

                    Database db2 = new Database();

                    try {
                        db2.Establish();
                    } catch (Exception e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                    }

                    db2.setQuery("select * from " + type + " where name like \'%" + search_value + "%\'");
                    ResultSet res = db2.Execute();
                    while (res.next()) {
                        printResult(res, type);
                    }
                    db2.Close(db2.getSt(), db2.getCon());
                }
                db.Close(db.getSt(), db.getCon());
            } catch (SQLException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }

        }

    }

    public static void sort_searched_result(String TableName, String column_name, String search_value,
            String sort_using) {
        String type = "&";
        Database db = new Database();

        try {
            db.Establish();
        } catch (Exception e) {
            System.out.println("While searching using id..");
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        if (column_name.compareTo("id") == 0) {
            int id = Integer.parseInt(search_value);
            db.setQuery(String.format("select * from %s where id=%d order by %s", TableName, id, sort_using));

            try {
                ResultSet rs = db.Execute();
                printResult(rs, TableName);

                db.Close(db.getSt(), db.getCon());
            } catch (SQLException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }

        } else if (column_name.compareTo("name") == 0) {
            db.setQuery("select * from " + TableName + " where name like \'%" + search_value + "%\' order by "
                    + sort_using);

            try {
                ResultSet rs = db.Execute();
                printResult(rs, TableName);
                db.Close(db.getSt(), db.getCon());
            } catch (SQLException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }

        }
    }

    public static void delete_employee_detail(String column_name, String search_value) {
        String type = "&";
        Database db = new Database();

        try {
            db.Establish();
        } catch (Exception e) {
            System.out.println("While searching using id..");
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        if (column_name.compareTo("id") == 0) {
            int id = Integer.parseInt(search_value);
            db.setQuery(String.format("select type from empIdType  where id=%d", id));

            try {
                ResultSet rs = db.Execute();
                while (rs.next()) {
                    type = rs.getString("type");
                    System.out.println("type: " + type);

                    Database db2 = new Database();

                    try {
                        db2.Establish();
                    } catch (Exception e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                    }

                    db2.setQuery(String.format("delete from %s where id= %d", type, id));
                    int delcol = db2.Update();
                    if(delcol>0)
                    System.out.println("deleted columns using id: " + delcol);
                    db2.Close(db2.getSt(), db2.getCon());

                }

                db.Close(db.getSt(), db.getCon());
            } catch (SQLException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }

        } else if (column_name.compareTo("name") == 0) {
            db.setQuery("select type from empIdType where name =\"" + search_value + "\"");

            try {
                ResultSet rs = db.Execute();
                while (rs.next()) {
                    type = rs.getString("type");
                    System.out.println("type: " + type);

                    Database db2 = new Database();

                    try {
                        db2.Establish();
                    } catch (Exception e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                    }

                    db2.setQuery("delete from " + type + " where name = \"" + search_value + "\"");
                    int delcol = db2.Update();
                    System.out.println("DElted columns: " + delcol);
                    db2.Close(db2.getSt(), db2.getCon());
                }
                db.Close(db.getSt(), db.getCon());
            } catch (SQLException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }

        }
    }

    public static void search_gt_lt_employee(String type, String column_name, int search_value, String operator) {

        Database db = new Database();

        try {
            db.Establish();
        } catch (Exception e) {
            System.out.println("While searching using id..");
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        db.setQuery(String.format("select * from %s where %s%s%d", type, column_name, operator, search_value));

        try {
            ResultSet rs = db.Execute();
            printResult(rs, type);

            db.Close(db.getSt(), db.getCon());
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

    }

    public static void perform_aggregate(String Tablename, String columnName, String operation) {
        Database db = new Database();

        try {
            db.Establish();
        } catch (Exception e) {
            System.out.println("While searching using id..");
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        db.setQuery(String.format("select %s(%s) from %s", operation, columnName, Tablename));

        try {
            ResultSet rs = db.Execute();
            rs.next();
            System.out.printf("The resultant %s is : %d\n", operation, rs.getInt(1));
            db.Close(db.getSt(), db.getCon());
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

    }



    public static void populateTables() {
        FileReader fr1;
        Scanner sin = null;

        // caretaker table
        try {
            fr1 = new FileReader("src/JAVA CSV FILES/caretaker.csv");
            sin = new Scanner(fr1);
        } catch (FileNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        sin.nextLine();
        String row;
        while (sin.hasNext()) {
            row = sin.nextLine();
            String[] tokens = row.split(",");
            String s = tokens[1];
            int id = Integer.parseInt(tokens[0]);
            Caretaker c = new Caretaker(s, id);
            c.setSalary(Integer.parseInt(tokens[2]));
            c.addEmployee();
            ZooManagement.el.add(c);
            //System.out.println(c);

        }

        // doctor table

        try {
            fr1 = new FileReader("src/JAVA CSV FILES/doctor.csv");
            sin = new Scanner(fr1);
        } catch (FileNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        sin.nextLine();
        while (sin.hasNext()) {
            row = sin.nextLine();
            String[] tokens = row.split(",");
            String name = tokens[1];
            int id = Integer.parseInt(tokens[0]);
            int salary = Integer.parseInt(tokens[2]);
            int casesTaken = Integer.parseInt(tokens[3]);
            Doctor d = new Doctor(name, id, salary);
            d.setCasesTaken(casesTaken);
            d.addEmployee();
            ZooManagement.el.add(d);
            //System.out.println(d);

        }

        //manager table

        try {
            fr1 = new FileReader("src/JAVA CSV FILES/manager.csv");
            sin = new Scanner(fr1);
        } catch (FileNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        sin.nextLine();
        while (sin.hasNext()) {
            row = sin.nextLine();
            String[] tokens = row.split(",");
            String name = tokens[1];
            int id = Integer.parseInt(tokens[0]);
            int salary = Integer.parseInt(tokens[2]);
            Manager m = new Manager(name, id, salary);
            m.addEmployee();
            ZooManagement.el.add(m);
            //System.out.println(m);

        }

        //charted accountant table

        try {
            fr1 = new FileReader("src/JAVA CSV FILES/chartedaccountant.csv");
            sin = new Scanner(fr1);
        } catch (FileNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        sin.nextLine();
        while (sin.hasNext()) {
            row = sin.nextLine();
            String[] tokens = row.split(",");
            String name = tokens[1];
            int id = Integer.parseInt(tokens[0]);
            int salary = Integer.parseInt(tokens[2]);
            int currentFinancialPosition = Integer.parseInt(tokens[3]);
            ChartedAccountant c = new ChartedAccountant(name, id);
            c.setSalary(salary);
            c.setCurrentFinancialPosition(currentFinancialPosition);
            c.addEmployee();
            ZooManagement.el.add(c);
            //System.out.println(c);

        }

        //zooguide table

        try {
            fr1 = new FileReader("src/JAVA CSV FILES/zooguide.csv");
            sin = new Scanner(fr1);
        } catch (FileNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        sin.nextLine();
        while (sin.hasNext()) {
            row = sin.nextLine();
            String[] tokens = row.split(",");
            String name = tokens[1];
            int id = Integer.parseInt(tokens[0]);
            int salary = Integer.parseInt(tokens[2]);
            double avgrating = Double.parseDouble(tokens[3]);
            ZooGuide z = new ZooGuide(name, id, salary);
            z.setAvgrating(avgrating);
            z.addEmployee();
            ZooManagement.el.add(z);
            //System.out.println(z);

        }

        //System.out.println("TAbles have been populated\n");
    }

    public static void truncateTable(String tablename) {
        Database db = new Database();

        try {
            db.Establish();
        } catch (Exception e) {
            System.out.println("While searching using id..");
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        db.setQuery(String.format("truncate table %s", tablename));

        try {
            db.Update();
            db.Close(db.getSt(), db.getCon());
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

    }

    private static void bulk_updation() {

        FileReader fr1;
        Scanner sin = null;

        // caretaker table
        try {
            fr1 = new FileReader("src/JAVA CSV FILES/updation.csv");
            sin = new Scanner(fr1);
        } catch (FileNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        sin.nextLine();
        String row;
        while (sin.hasNext()) {
            row = sin.nextLine();
            String[] tokens = row.split(",");
            String table_name, column_name, newVal;
            int empid;
            table_name = tokens[0];
            // System.out.println("here1");
            empid = Integer.parseInt(tokens[1]);
            column_name = tokens[2];
            newVal = tokens[3];
            // System.out.println("here2");
            update_employee_detail(table_name, empid, column_name, newVal);
        }

    }

    public static void driverFunc(String id, String profession) {
        Scanner sin = new Scanner(System.in);
        int key = -1;

        truncateTable("doctor");
        truncateTable("caretaker");
        truncateTable("zooguide");
        truncateTable("chartedaccountant");
        truncateTable("manager");
        truncateTable("empIdType");
        truncateTable("message");
        truncateTable("sponcers");
        populateTables();
        Animal.populateTables();

        if (profession.compareTo("Director") == 0) {
            System.out.printf("Logged in as Manager\n");
            Manager_funcs(Integer.parseInt(id));
        } else if (profession.compareTo("Doctor") == 0) {
            System.out.printf("Logged in as doctor\n");
            doctor_funcs(Integer.parseInt(id));
        } else if (profession.compareTo("ZooGuide") == 0) {
            System.out.printf("Logged in as zooguide\n");
            zooguide_funcs(Integer.parseInt(id));
        } else if (profession.compareTo("Care Taker") == 0) {
            System.out.printf("Logged in as caretaker\n");
            caretaker_funcs(Integer.parseInt(id));
        } else if (profession.compareTo("ChartedAccountant") == 0) {
            System.out.printf("Logged in as chartedaccountant\n");
            chartedaccountant_funcs(Integer.parseInt(id));
        }
        while (key != 0)
        {
            System.out.println("Enter 21 if you want to see your unseen messages");

            System.out.println("Enter 22 if you want to see your inbox");

            System.out.println("\nEnter 0 if u want to exit");
            key = sin.nextInt();

            if(key==21)
            {
               message.showUnreadMessages(Integer.parseInt(id));
            }
            else if(key==22)
            {
               message.showInbox(Integer.parseInt(id));
            }
        }



    }

    private static String generatemsgid(int id) {
        int rand = (int) Math.random() * (100);
        String res = Integer.toString(rand) + new Manager().hashCode();
        //System.out.println("msgid is" + res);
        return res;
    }

    private static void Manager_funcs(int id) {
        int key = -1;
        Scanner sin = new Scanner(System.in);
        while (key != 0) {

            System.out.println(" *****************************");
            System.out.println("             0. exit");
            System.out.println("             1. add new employee");
            System.out.println("             2 . send any message");
            System.out.println("             3 . update salary in the database");
            System.out.println("             4 . update employee detail");
            System.out.println("             5 . search employee detail");
            System.out.println("             6 . search employee detail and show them in sorted order ");
            System.out.println("             7 . delete employee detail");
            System.out.println("             8 . perform numeric search on employees details");
            System.out.println("             9 . perform aggregate operations on employees details");
            System.out.println("            10 . perform bulk updations on employees details");
            System.out.println("*****************************");

            key = sin.nextInt();
            Manager m = new Manager();
            for (employee x : ZooManagement.el) {
                if (x.getId() == id)
                    m = (Manager) x;
            }

            if (key == 1) {
              populateTables();
              System.out.println("ADDED SUCCESFULLY");
            }else if (key == 2) {
                int receiversid;
                String msgid, body;
                System.out.println("Enter the id to whom u want to send");
                receiversid = sin.nextInt();
                System.out.println("Enter the msg you want to send");
                sin.nextLine();
                body = sin.nextLine();
                msgid = generatemsgid(id);
                m.sendmessage(receiversid, msgid, body);
                System.out.println("Message sent successfully\n");
            } else if (key == 3) {
                m.salary();
                m.update_salary_IN_Database();
                System.out.printf("Your Salary is: %d\n",m.getSalary());
            } else if (key == 4) {
                String table_name, column_name, newVal;
                int empid;
                System.out.println("Enter the table name u wanted to update");
                sin.nextLine();
                table_name = sin.nextLine();
                System.out.println("Enter the  column name u wanted to update");
                column_name = sin.nextLine();
                System.out.println("Enter the  employee id whose details u wanted to update");
                empid = sin.nextInt();
                System.out.println("Enter the  new value to be changed");
                sin.nextLine();
                newVal = sin.nextLine();

                ZooManagement.update_employee_detail(table_name, empid, column_name, newVal);


            } else if (key == 5) {

                String column_name, newVal;
                System.out.println("Enter the  column name u wanted to search based on");
                sin.nextLine();
                column_name = sin.nextLine();
                System.out.println("Enter the value to be searched");
                // sin.nextLine();
                newVal = sin.nextLine();
                ZooManagement.search_employee_detail(column_name, newVal);

            } else if (key == 6) {
                String table_name, column_name, sortusing, searchvalue;

                System.out.println("Enter the table name u wanted to search in");
                sin.nextLine();
                table_name = sin.nextLine();
                System.out.println("Enter the  column name u wanted to search for");
                column_name = sin.nextLine();
                System.out.println("Enter the  value u wanted to search");
                searchvalue = sin.nextLine();
                System.out.println("Enter the column name which should be used for sorting");
                sortusing = sin.nextLine();
                ZooManagement.sort_searched_result(table_name, column_name, searchvalue, sortusing);
                //System.out.println("sorted results displayed");

            } else if (key == 7) {
                String column_name, searchVal;
                System.out.println("Enter the column name u wanted to delete");
                sin.nextLine();
                column_name = sin.nextLine();
                System.out.println("Enter the value u wanted to delete");
                searchVal = sin.nextLine();
                delete_employee_detail(column_name, searchVal);

            } else if (key == 8) {
                String table_name, column_name, operator, searchvalue;

                System.out.println("Enter the table name u wanted to search in");
                sin.nextLine();
                table_name = sin.nextLine();
                System.out.println("Enter the  column name u wanted to search for");
                column_name = sin.nextLine();
                System.out.println("Enter the numeric value to be used");
                searchvalue = sin.nextLine();
                System.out.println("Enter the operator u wanted to perform");
                operator = sin.nextLine();
                ZooManagement.search_gt_lt_employee(table_name, column_name, Integer.parseInt(searchvalue), operator);
                //System.out.println("sorted results displayed");
            } else if (key == 9) {
                String table_name, column_name, operation;

                System.out.println("Enter the table name in which u wanted to perform operation");
                sin.nextLine();
                table_name = sin.nextLine();
                System.out.println("Enter the  column name u wanted to perform operation on  (Enter column only whose values are numeric)");
                column_name = sin.nextLine();
                System.out.println("Enter the aggregate operation u wanted to perform\n Available aggregate operations are:\n count\n sum\n min\n max\n avg");
                operation = sin.nextLine();
                ZooManagement.perform_aggregate(table_name, column_name, operation);
                //System.out.println("Aggregate operation performed");
            } else if (key == 10) {
                bulk_updation();
                System.out.println("UPDATED SUCCESFULLY");
            }

        }

    }

    private static void doctor_funcs(int id) {
        int key = -1;
        Scanner sin = new Scanner(System.in);
        while (key != 0) {

            System.out.println("*****************************************************");
            System.out.println("      0.if u want to exit						");
            System.out.println("      1.if you want to send any message			");
            System.out.println("      2.if you want to check your salary ");
            System.out.println("      3.if you want to perform health check		");
            System.out.println("*****************************************************");

            key = sin.nextInt();

            Doctor d = new Doctor();
            for (employee x : ZooManagement.el) {
                if (x.getId() == id)
                    d = (Doctor) x;
            }

            if (key == 1) {
                int receiversid;
                String msgid, body;
                System.out.println("Enter the id to whom u want to send");
                receiversid = sin.nextInt();
                System.out.println("Enter the msg you want to send");
                sin.nextLine();
                body = sin.nextLine();
                msgid = generatemsgid(id);
                d.sendmessage(receiversid, msgid, body);
                System.out.println("Message sent successfully\n");
            } else if (key == 2) {
                d.salary();
                d.update_salary_IN_Database();
                System.out.printf("Your salary is :%d\n",d.getSalary());
            } else if (key == 3) {
                d.animalCheckup();
            }
        }

    }

    private static void zooguide_funcs(int id) {
        int key = -1;
        Scanner sin = new Scanner(System.in);
        while (key != 0) {
            System.out.println("**********************************************************");
            System.out.println("   0.if u want to exit								");
            System.out.println("   1.if you want to send any message					");
            System.out.println("   2.if you want to check your salary");
            System.out.println("	3.if you want to find the details of all animals		");
            System.out.println("	4.if you want to search animal					");
            System.out.println("**********************************************************");

            key = sin.nextInt();
            ZooGuide z = null;
            for (employee x : ZooManagement.el) {
                if (x.getId() == id)
                    z = (ZooGuide) x;
            }

            if (key == 1) {
                int receiversid;
                String msgid, body;
                System.out.println("Enter the id to whom u want to send");
                receiversid = sin.nextInt();
                System.out.println("Enter the msg you want to send");
                sin.nextLine();
                body = sin.nextLine();
                msgid = generatemsgid(id);
                z.sendmessage(receiversid, msgid, body);
                System.out.println("Message sent successfully\n");
            } else if (key == 2) {
                z.salary();
                z.update_salary_IN_Database();
                System.out.printf("Your salary is :%d\n", z.getSalary());
            } else if (key == 3) {
                Animal.find_details_of_all_animals();
            } else if (key == 4) {
                System.out.println("These are feilds available for animals ");
                System.out.println("ID\nname\nlocation\nbreed\nfood_type\n");
                sin.nextLine();
                System.out.println("Enter the string to search on that basis:");
                String str = sin.nextLine();
                System.out.println("Enter the serach key");
                // sin.nextLine();
                String str2 = sin.nextLine();
                Animal.search(str, str2);
            }
        }
    }

    private static void chartedaccountant_funcs(int id) {
        int key = -1;
        Scanner sin = new Scanner(System.in);
        while (key != 0) {
            System.out.println("**********************************************************");
            System.out.println("	0.if u want to exit								");
            System.out.println("	1.if you want to send any message					");
            System.out.println("   2.if you want to check your salary");
            System.out.println("	3.if you want to add a sponcer ");
            System.out.println("	4.if you want to see the current financial position	");
            System.out.println("**********************************************************");

            key = sin.nextInt();

            ChartedAccountant c = null;
            for (employee x : ZooManagement.el) {
                if (x.getId() == id)
                    c = (ChartedAccountant) x;
            }

            if (key == 1) {
                int receiversid;
                String msgid, body;
                System.out.println("Enter the id to whom u want to send");
                receiversid = sin.nextInt();
                sin.nextLine();
                System.out.println("Enter the msg you want to send");
                body = sin.nextLine();
                msgid = generatemsgid(id);
                c.sendmessage(receiversid, msgid, body);
                System.out.println("Message sent successfully\n");
            } else if (key == 2) {
                c.salary();
                c.update_salary_IN_Database();
                System.out.printf("Your salary is :%d\n", c.getSalary());
            } else if (key == 3) {
                String name;
                int spid, donatedAmount;
                System.out.println("Enter the sponcer name ");
                sin.nextLine();
                name = sin.nextLine();
                System.out.println("Enter the sponcerid");
                spid = sin.nextInt();
                System.out.println("Enter the donated amount");
                donatedAmount = sin.nextInt();
                c.addsponcer(name, donatedAmount, spid);

            } else if (key == 4) {
                c.showFinancialStatus();
            }
            else if(key==5)
            {
                displaySponcers();
            }

        }
    }

    private static void caretaker_funcs(int id) {

        int key = -1;
        Scanner sin = new Scanner(System.in);
        while (key != 0) {

            System.out.println("**********************************************************");
            System.out.println("       0.If you want to exit						");
            System.out.println("         1.if you want to send any message    			");
            System.out.println("	     2.if you want to check your salary");
            System.out.println("         3.if you want to feed animals					");
            System.out.println("         4.if you want to update animal details based on id	");
            System.out.println("         5.if you want to perform bulk updation			");
            System.out.println("         6.if you want to insert an animal				");
            System.out.println("         7.if you want to remove an animal				");
            System.out.println("**********************************************************");

            key = sin.nextInt();

            Caretaker ct = null;
            for (employee x : ZooManagement.el) {
                if (x.getId() == id)
                    ct = (Caretaker) x;
            }

            if (key == 1) {
                int receiversid;
                String msgid, body;
                System.out.println("Enter the id to whom u want to send");
                receiversid = sin.nextInt();
                System.out.println("Enter the msg you want to send");
                sin.nextLine();
                body = sin.nextLine();
                msgid = generatemsgid(id);
                ct.sendMessage(receiversid, msgid, body);
                System.out.println("Message sent successfully\n");
            } else if (key == 2) {
                ct.salary();
                ct.update_salary_IN_Database();
                System.out.printf("Your salary is :%d\n", ct.getSalary());
            } else if (key == 3) {
                ct.giveFood();
                System.out.println("food given succesfully");
            } else if (key == 4) {
                System.out.println("Enter the id of animal you want update in database");
                sin.nextLine();
                String uid = sin.nextLine();
                System.out.println("Enter the column name you want to update the database");
                String ucol = sin.nextLine();
                System.out.println("Entered the updated detail");
                String upd = sin.nextLine();
                Animal.update_animal_details(uid, ucol, upd);
            } else if (key == 5) {
                Animal.bulk_updation();
            } else if (key == 6) {
                String aid, name, location, health, breed, food;
                int hungry_percentage;
                System.out.println("Enter the id ");
                aid = sin.next();
                System.out.println("Enter the name");
                name = sin.next();
                System.out.println("Enter the location");
                location = sin.next();
                System.out.println("Enter the health status");
                health = sin.next();
                System.out.println("Enter hungry percentage");
                hungry_percentage = sin.nextInt();
                System.out.println("Enter breed");
                breed = sin.next();
                System.out.println("Enter food type");
                food = sin.next();
                Animal.insert_animal(aid, name, location, health, hungry_percentage, breed, food);
            } else if (key == 7) {
                System.out.println("Enter column of animal to delete on that basis");

                String str = sin.next();
                System.out.println("Enter the value");
                String str2 = sin.next();
                Animal.delete_animal(str, str2);
            }
        }

    }


}

