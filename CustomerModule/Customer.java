package CustomerModule;
import Database.*;
import java.sql.*;
import java.util.*;

class food extends Customer
{

    public  void  MenuDisplay()
    {
        System.out.println("Enter the food type(veg/nonveg)");
        String Food_Type=s.next();
        if(Food_Type.trim().toLowerCase().compareTo("veg")==0)
            setQuery("select * from food_items where Food_Type='veg' ");
        else if(Food_Type.trim().toLowerCase().compareTo("nonveg")==0)
            setQuery("select * from food_items where Food_Type='nonveg'");
        else
            setQuery("select * from food_items");
        try {
            Establish();
            ResultSet rs;
            rs=Execute();
            while(rs.next()){
                System.out.println(rs.getInt("Id")+ " ------  " + rs.getString("Food_name")+ "  --------  " + rs.getInt("price"));}
        }
        catch (Exception E){
            System.out.println(E);
        }
        System.out.println("Do you want to order food(Yes/No)");
        String order=s.next();
        if(order.toLowerCase().compareTo("yes")==0){
            Food_Order();}
    }

    public void Food_Order(){

        int cost = 0;
        System.out.println(("please tell the food_id of order you want in menu :"));

        while (true) {
            //Enter 0 for no further order
            int a=s.nextInt();
            if (a==0) {
                break;
            }
            setQuery(String.format("select price from food_items where Id = %d",a));
            ResultSet r1;
            try{
                r1=Execute();
                r1.next();
                cost = cost + r1.getInt(1);
            }
            catch (SQLException e) {
                System.out.println(e);
            }
        }
        System.out.println("please be patient ,your order is received.");
        System.out.println("Your bill is " + cost);

    }

}
// end of food class**************>

class Vehicle extends Customer{

    final static private double Train_Cost=100;

    public void Available_Vehicle()
    {

        setQuery("select * from vehicles ");

        try {
            Establish();
            ResultSet r;
            r= Execute();
            while (r.next()) {
                System.out.println(r.getInt("Id") + " --------- " + r.getString("vehicle_name") + "  --------  " + r.getInt("price") + " -------  " + r.getInt("capacity"));
            }
        }
        catch(Exception E){
            System.out.println(E);
        }

        System.out.println("Do you want to rent Vehicle(Yes/No)");
        String Rent=s.next();
        if(Rent.toLowerCase().compareTo("yes")==0){
            Rent_Vehicle();}


    }

    public void Rent_Vehicle()
    {
        System.out.print("Enter the vehicle Id you want : ");
        System.out.println("Enter 0 if you don't want a vehicle ");
        while(true)
        {
            int a = s.nextInt();
            if(a==0)
            {
                break;
            }

            setQuery(String.format("select price from vehicles where Id = %d", a));
            ResultSet r;
            try{
                r=Execute();
                r.next();
                System.out.println("Your cost of Vehicle = " + r.getInt("price"));
            }
            catch (Exception E){
                System.out.println(E);
            }
        }

    }

    public void Train_Schedule()
    {

        setQuery("select *  from trains");
        try{
            Establish();
            ResultSet rs;
            rs = Execute();
            while (rs.next()) {
                System.out.println(rs.getInt("Id")+ " --------- " + rs.getString("train_name") + "  --------  " + rs.getInt("price") + " -------  " + rs.getString("time"));
            }
        }
        catch (Exception E){
            System.out.println(E);
        }
        System.out.println("Please enter 1 if you want a train ride ? ");
        int a = s.nextInt();
        if(a==1)
        {
            train_booking();
        }
        //Enter 1 if you want ride...
    }

    ArrayList<Integer> train_id = new ArrayList<>();
    public void train_booking()
    {
        System.out.println("Please enter the train number you want to book : ");
        int a = s.nextInt();
        train_id.add(a);
        setQuery(String.format("select price from trains where Id =  %d",train_id.get(0)));

        try{
            Establish();
            ResultSet r1;
            r1 = Execute();
            r1.next();
            System.out.println("Please enter the no of tickets : ");
            int no = s.nextInt();
            System.out.println(" ****Please go to the train point**** ");
            System.out.println(" Train cost = " +no*(r1.getInt("price")) );}

        catch (Exception E){
            System.out.println(E);
        }
    }
}
class AvgRating extends Customer
{
    private int rating = s.nextInt();
    ArrayList<Integer> store = new ArrayList<>();
    public void Give_rating()
    {
        store.add(this.rating);
    }
}

class Timetable
{
    public void Timings()
    {
        System.out.println(" Opening time : 8.30 AM ");
        System.out.println(" Closed time  : 6.30 PM ");
    }
}

class Customer extends Database
{

    static  Scanner s=new Scanner(System.in);

}
