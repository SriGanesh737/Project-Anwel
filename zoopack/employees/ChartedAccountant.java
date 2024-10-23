package zoopack.employees;

import zoopack.*;
import java.util.*;
import Message.*;


public class ChartedAccountant extends employee {

    ArrayList<message> inbox = new ArrayList<message>();
    public static int currentFinancialPosition = 1000000;

    public ChartedAccountant() {
    }

    public ChartedAccountant(String name, int id) {
        super(name, id);
    }

    public static void setCurrentFinancialPosition(int currentFinancialPosition) {
        ChartedAccountant.currentFinancialPosition = currentFinancialPosition;
    }

    public void salary() {
        this.update_salary_IN_Database();
    }

    public void addsponcer(String Name, int donatedAmount, int id) {
        Sponcers s = new Sponcers(Name, donatedAmount, id);
        currentFinancialPosition += donatedAmount;
        s.storeSponcer();
        // need to add in transaction log if we maintain one
        ZooManagement.sl.add(s);
    }

    public void sendmessage(int recieversid, String msgid, String body) {
        // id is for whom we want to send
        message m = new message(msgid, getId(), body);
        m.setReceiversId(recieversid);
        ZooManagement.ml.add(m);
        m.storeMessage();
    }

    public void showFinancialStatus() {
        System.out.printf("The current Financial status is : %d\n", currentFinancialPosition);
    }

}
