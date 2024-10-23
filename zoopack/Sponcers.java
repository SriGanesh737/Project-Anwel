package zoopack;

import Database.*;
import java.sql.*;

public class Sponcers {
    private String Name;
    private int donatedAmount;
    private int id;

    public Sponcers() {

    }

    public Sponcers(String name, int donatedAmount, int id) {
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
        return String.format("\t\t******************\nName: %s \nDonated Amount: %d\n \t\t******************\n", this.getName(), this.getDonatedAmount());
    }

}
