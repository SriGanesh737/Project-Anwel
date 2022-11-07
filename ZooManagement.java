import java.sql.*;
import java.util.*;

import com.mysql.cj.protocol.Resultset;

class Animal {
    private String Id;
    private String location;
    private String HealthStatus;
    private int HungryPercentage;
    private String Breed;
    private String FoodType;
    private String Name;

    public Animal() {

    }

    String healthissues[] = { "good", "leg injured", "skin infection", "heart problem", "not taking food", "fever",
            "moody" };

    public Animal(String id, String name, String location, String breed, String Food) {
        Id = id;
        Name = name;
        this.location = location;
        this.setHungryPercentage();
        Breed = breed;
        FoodType = Food;
        this.setHealthStatus();

    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getFoodType() {
        return FoodType;
    }

    public void setFoodType(String foodType)
    {
        FoodType = foodType;
    }

    public String getId() {
        return Id;
    }

    public void setId(String id) {
        Id = id;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getHealthStatus() {
        return HealthStatus;
    }

    public void setHealthStatus() {
        Random rand = new Random();
        int temp = rand.nextInt(healthissues.length);
        this.HealthStatus = healthissues[temp];
    }

    public int getHungryPercentage() {
        return HungryPercentage;
    }

    public void setHungryPercentage() {
        Random rand = new Random();
        int temp = rand.nextInt(100);
        this.HungryPercentage = temp;
    }

    public String getBreed() {
        return Breed;
    }

    public void setBreed(String breed)
    {
        Breed = breed;
    }

    public void giveFood(String uid) 
    {
        Database db = new Database();
        int test = 0;
        String type = "";
        try {
            db.Establish();
            db.setQuery(String.format("SELECT hungry_percentage,food_type FROM animals where id=\"%s\"", uid));
            ResultSet rs = db.Execute();
            while (rs.next()) {
                test = rs.getInt(1);
                type = rs.getString(2);
            }

            System.out.println(test);
            System.out.println(type);
            if (test < 50) {
                if (Food.availability(type)) {
                    Food.consumed_food(type);
                    db.setQuery(String.format("UPDATE animals SET hungry_percentage=%d WHERE id=\"%s\"", 0, uid));
                    db.Update();

                }
            }
            db.Close(db.getSt(), db.getCon());
        } 
          catch (Exception e) {
             // TODO Auto-generated catch block
             e.printStackTrace();
         }
        



    }

    public void insert_animal(int i, String uname,String uhealth, String ulocation, int perc, String ubreed, String ufood)
    {
        Database db = new Database();
        try {
            db.Establish();
            db.setQuery(String.format("insert into animals values(\"%s\",\"%s\",\"%s\",\"%s\",%d,\"%s\",\"%s\")", i, uname,uhealth,ulocation, perc, ubreed, ufood));
            db.Update();
            db.Close(db.getSt(), db.getCon());

        } 
        catch (Exception e) 
        {
           
            e.printStackTrace();                    
        }


    }

    public String toString() {

        return String.format(
                "Id : %s\n Name: %s\n location : %s\n HealthStatus : %s\n HungryPercentage: %d\n Breed :%s\n Food :%s\n",
                this.getId(), this.getName(), this.getLocation(), this.getHealthStatus(), this.getHungryPercentage(),
                this.getBreed(), this.getFoodType());
    }

}

public class ZooManagement {
    // public static ArrayList<Animal> al = new ArrayList<>();
    // public static ArrayList<employee> el = new ArrayList<>();
    // public static ArrayList<sponcers> sl = new ArrayList<>();
    // public static ArrayList<message> ml = new ArrayList<>();
    // public static ArrayList<Food> fl = new ArrayList<>();

   
    

    // public static void PrintAllAnimalDetails() {
    //     for (Animal x : al) {
    //         System.out.println(x.toString());
    //     }
    // }

    public static void storeemployees() {

        // employee e1 = new manager("ram", 100, 500000);
        // employee e3 = new caretaker("kam", 104);
        // el.add(e1);
        // el.add(e3);

        /* Manager- name,id,salary
         * Caretaker- name,id,salary
         * Doctor- name,id,basesalary(salary),casesTaken
         * Charted Accountant- name,id,salary,static(CurrentFinancialPosition)
         * ZooGuide-name,id,salary(basesalary),avgrating
         * sponcers- name,donatedAmount
         * Messages-SendersId,ReceiversId,messageId,MarkasRead,Body
         */

    }

    // public static void displaySponcers() {
    //     System.out.println("A very special thanks to all our sponcers !!!\n");
    //     for (sponcers s : sl) {
    //         System.out.println(s);
    //     }
    // }

    //This method is only for the highest authority for checking all the messages sent from anyone to anyone.
    // public static void displayAllMessages() {
    //     System.out.println("The messages are :\n");
    //     for (message m : ml) {
    //         System.out.println(m);
    //     }
    // }
    public static void main(String args[])
    {
        Animal an = new Animal();
        // an.insert_animal(4, "Girrafe", "Good", "B-17", 80, "reticulated", "twigs");
        an.giveFood("1");

    }
}


