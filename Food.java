import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Iterator;

public class Food {
    private String type;
    private int quantity;
    private float cost;

    public float getCost() {
        return cost;
    }

    public void setCost(float cost) {
        this.cost = cost;
    }

    public Food() {
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public static void consumed_food(String type) {
        Database db = new Database();
        
        try {
            db.Establish();
        db.setQuery(String.format("UPDATE food set quantity=quantity-%d where type=\"%s\"",10,type));
            db.Update();
            db.Close(db.getSt(), db.getCon());
        } catch (Exception e) {
            
            e.printStackTrace();
        }

    }

    public static void add_food(String type) 
    {
        Database db = new Database();
       
        try {
            db.Establish();
            db.setQuery(String.format("UPDATE food set quantity=quantity+10 where type=\"%s\"", type));
            db.Execute();
            db.Close(db.getSt(), db.getCon());
        } catch (Exception e) {
            
            e.printStackTrace();
        }
        
      
    }

    public static boolean availability(String ftype) 
    {
        int test = 0;
        System.out.println(ftype);
        Database db = new Database();
        ResultSet rs;
        try {
            db.Establish();
            // db.getCon();
            db.setQuery(String.format("SELECT quantity from food where type=\"%s\"", ftype));
            rs = db.Execute();
            System.out.println(test);
            while (rs.next())
                test = rs.getInt(1);
            System.out.println(test);
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (test > 50)
            return true;
        else
            return false;

    }
    public void get_details_of_food()
    {
        System.out.println("Food type of different animals:");
        Database db = new Database();
        try{
            db.Establish();
            db.setQuery(String.format("SELECT type FROM food"));
            ResultSet rs=db.Execute();
            while(rs.next())
            System.out.println(rs.getString(1));


        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String args[])
    {
        Food f = new Food();
        f.get_details_of_food();
    }

}
