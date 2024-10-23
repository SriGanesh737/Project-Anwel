package zoopack.employees;

import zoopack.*;
import Database.*;
import java.util.*;
import Message.*;
import java.sql.*;

public class Caretaker extends employee {

    ArrayList<message> inbox = new ArrayList<message>();

    public Caretaker(String name, int id) {
        super.setName(name);
        super.setId(id);
    }

    public void sendMessage(int receiversId, String msgId, String body) {

        message m = new message(msgId, getId(), body);
        m.setReceiversId(receiversId);
        ZooManagement.ml.add(m);
        m.storeMessage();
    }

    // give food not yet tested
    public void giveFood() {
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
                new Animal().giveFood(id);
            }
            System.out.println("All animals are feeded");
        } catch (SQLException e) {
            // TODO Auto-generated catch block

            e.printStackTrace();
        }
    }

    public void salary() {
        super.setSalary(50000);
        this.update_salary_IN_Database();
    }

}
