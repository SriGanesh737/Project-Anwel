package zoopack.employees;
import Message.*;
import zoopack.*;

import java.util.*;

public class ZooGuide extends zoopack.employee {
    private int baseSalary;
    // give bonus based on the ratings they got.
    ArrayList<Integer> ratings = new ArrayList<>();
    ArrayList<message> inbox = new ArrayList<message>();
    private double avgrating = 0;

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

    public void sendmessage(int recieversid, String msgid, String body) {
        // id is for whom we want to send
        message m = new message(msgid, getId(), body);
        m.setReceiversId(recieversid);
        ZooManagement.ml.add(m);
        m.storeMessage();
    }

    public void salary() {
        double sal = baseSalary + avgrating * 5000;
        super.setSalary((int) Math.round(sal));
        this.update_salary_IN_Database();
    }
}
