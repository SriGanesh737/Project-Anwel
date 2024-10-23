package zoopack.employees;

import zoopack.*;
import Message.*;
import java.util.*;

public class Manager extends employee {
    ArrayList<message> inbox = new ArrayList<message>();

    public Manager() {

    }

    public Manager(String name, int id, int salary) {
        super(name, id);
    super.setSalary(salary);
    }

    public void sendmessage(int recieversid, String msgid, String body) {
        // id is for whom we want to send
        message m = new message(msgid, getId(), body);
        m.setReceiversId(recieversid);
        ZooManagement.ml.add(m);
        m.storeMessage();
    }

    public void salary() {
        super.setSalary(100000);
        this.update_salary_IN_Database();
    }
}
