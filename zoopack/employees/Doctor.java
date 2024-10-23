package zoopack.employees;

import zoopack.*;
import java.sql.*;
import java.util.*;
import Message.*;
import Database.*;


public class Doctor extends employee {

    ArrayList<message> inbox = new ArrayList<message>();
    ArrayList<String> patientList = new ArrayList<>();
    private int CasesTaken = 3;

    public Doctor() {

    }

    public Doctor(String name, int id, int salary) {
        super(name, id);
        super.setSalary(salary);
    }

    public int getCasesTaken() {
        return CasesTaken;
    }

    public void setCasesTaken(int casesTaken) {
        CasesTaken = casesTaken;
    }

    public void sendmessage(int recieversid, String msgid, String body) {
        // id is for whom we want to send
        message m = new message(msgid, getId(), body);
        m.setReceiversId(recieversid);
        ZooManagement.ml.add(m);
        m.storeMessage();
    }

    public void animalCheckup() {
        Database db = new Database();

        try {
            db.Establish();
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        db.setQuery("select id from animals");

        try {
            ResultSet rs = db.Execute();
            while (rs.next()) {
                String id = rs.getString(1);
                boolean b = new Animal().check_health_of_animal(id);
                if (b)
                    new Animal().animal_treatment(id);
            }
            System.out.println("All animals are checked and treated");
        } catch (SQLException e) {
            // TODO Auto-generated catch block

            e.printStackTrace();
        }
    }

    public void salary() {
        setSalary(getSalary() + 1000 * CasesTaken);
        //System.out.println("Your salary is :" + getSalary());
        this.update_salary_IN_Database();
    }
}
