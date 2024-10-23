package zoopack;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

import Database.*;


import java.io.*;

public class Animal {
    private String Id;
    private String location;
    private String HealthStatus;
    private int HungryPercentage;
    private String Breed;
    private String FoodType;
    private String Name;
    private int count;

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public Animal() {

    }

    String healthissues[] = { "good", "leg injured", "skin infection", "heart problem", "not taking food", "fever",
            "chronic disease", "moody" };

    public Animal(String id, String name, String location, String breed, String Food) {
        Id = id;
        Name = name;
        this.location = location;
        Breed = breed;
        FoodType = Food;

    }

    public Animal(String id, String location, String healthStatus, int hungryPercentage, String breed, String foodType,
            String name, int count) {
        Id = id;
        this.location = location;
        HealthStatus = healthStatus;
        HungryPercentage = hungryPercentage;
        Breed = breed;
        FoodType = foodType;
        Name = name;
        this.count = count;
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

    public void setFoodType(String foodType) {
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

    public void setHealth_Status() {
        Random rand = new Random();
        int temp = rand.nextInt(healthissues.length);
        this.HealthStatus = healthissues[temp];
    }

    public int getHungryPercentage() {
        return HungryPercentage;
    }

    public void setHealthStatus(String healthStatus) {
        HealthStatus = healthStatus;
    }

    public void setHungryPercentage(int hungryPercentage) {
        HungryPercentage = hungryPercentage;
    }

    public void setHungry_Percentage() {
        Random rand = new Random();
        int temp = rand.nextInt(100);
        this.HungryPercentage = temp;
    }

    public String getBreed() {
        return Breed;
    }

    public void setBreed(String breed) {
        Breed = breed;
    }

    // Here caretaker calls this function to feed animals if the animal is hungry
    public void giveFood(String uid) {
        Database db = new Database();
        int test = 0;
        String type = "";
        int count = 0;
        try {
            db.Establish();
            db.setQuery(String.format("SELECT hungry_percentage,food_type,count(*) FROM animals where id=\"%s\"", uid));
            ResultSet rs = db.Execute();
            while (rs.next()) {
                test = rs.getInt(1);
                type = rs.getString(2);
                count = rs.getInt(3);

            }
            if (count == 0) {
                System.out.println("***************************************************");
                System.out.println("The animal with given ID is not available");
                System.out.println("***************************************************");
            }

            else if (test > 50) // if test>50 then animal is hungry
            {
                System.out.println(" feeding animal as it has " + test + "% hungry percentage");
                if (Food.availability(type)) // checks food availability
                {
                    Food.consumed_food(type);
                    db.setQuery(String.format("UPDATE animals SET hungry_percentage=%d WHERE id=\"%s\"", 0, uid));
                    db.Update();

                } else { // here buy the food
                    Food.buy_food(type);
                    Food.consumed_food(type);
                    db.setQuery(String.format("UPDATE animals SET hungry_percentage=%d WHERE id=\"%s\"", 0, uid));
                    db.Update();
                }
            }
            db.Close(db.getSt(), db.getCon());

        } catch (SQLException e) {
            System.out.println("Check your SQL syntax as there is a SQL exception");
            e.printStackTrace();
        } catch (Exception e) {

            e.printStackTrace();
        }

    }

    // this function is called first during execution of program
    public static void populateTables() {
        FileReader fr1;
        Scanner sin = null;

        try {
            fr1 = new FileReader("src/JAVA CSV FILES/animals.csv");
            sin = new Scanner(fr1);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        sin.nextLine();
        String row;
        while (sin.hasNext()) {
            row = sin.nextLine();
            String[] tokens = row.split(",");
            int hungry_percentage = Integer.parseInt(tokens[4]);
            Animal.insert_animal(tokens[0], tokens[1], tokens[2], tokens[3], hungry_percentage, tokens[5], tokens[6]);

        }

        System.out.println("Tables have been populated\n");
    }

    // updation of animal database by taking the colujmn of updation and id for
    // updating that value
    public static void update_animal_details(String uid, String ucol, String upd) {
        Database db = new Database();
        try {
            db.Establish();
            db.setQuery(String.format("UPDATE animals SET %s=\"%s\" WHERE id=\"%s\"", ucol, upd, uid));
            db.Update();
            db.Close(db.getSt(), db.getCon());
            System.out.println("The query is successfully updated");

        } catch (SQLException e) {
            System.out.println("Check your SQL syntax as there is a SQL exception");
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    // bulk updation
    public static void bulk_updation() {
        FileReader fr1;
        Scanner sin = null;

        try {
            fr1 = new FileReader("src/JAVA CSV FILES/temp.csv");
            sin = new Scanner(fr1);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        sin.nextLine();
        String row;
        while (sin.hasNext()) {
            row = sin.nextLine();
            String[] tokens = row.split(",");
            int hungry_percentage = Integer.parseInt(tokens[4]);
            Animal.insert_animal(tokens[0], tokens[1], tokens[2], tokens[3], hungry_percentage, tokens[5], tokens[6]);

        }
        System.out.println("Bulk updation is completed successfully\n");
    }

    // animals available in the zoo (useful for visitors to know animals in the zoo)
    public static void animals_currently_available() {
        System.out.println("Currently available animals in the zoo are :");
        Database db = new Database();
        try {
            db.Establish();
            db.setQuery(String.format("SELECT id,name FROM animals"));
            ResultSet rs = db.Execute();
            while (rs.next()) {
                System.out.println("***************************************************");
                System.out.println("ID:" + rs.getString(1));
                System.out.println("NAME:" + rs.getString(2));
                System.out.println("***************************************************");
            }
        } catch (SQLException e) {
            System.out.println("Check your SQL syntax as there is a SQL exception");
            e.printStackTrace();
        } catch (Exception e) {

            e.printStackTrace();
        }

    }

    // all details of all animals in the database
    public static void find_details_of_all_animals() {
        Database db = new Database();
        try {
            db.Establish();
            db.setQuery(String.format("SELECT *  FROM animals"));
            ResultSet rs = db.Execute();
            System.out.println("Details are:");
            while (rs.next()) {
                System.out.println("***************************************************");
                System.out.println("ID:" + rs.getString(1));
                System.out.println("NAME:" + rs.getString(2));
                System.out.println("LOCATION:" + rs.getString(3));
                System.out.println("BREED:" + rs.getString(6));
                System.out.println("FOOD:" + rs.getString(7));
                System.out.println("***************************************************");
            }
        } catch (SQLException e) {
            System.out.println("Check your SQL syntax as there is a SQL exception");
            e.printStackTrace();
        }

        catch (Exception e) {
            e.printStackTrace();
        }

    }

    public static void find_type_based_on_location() // it gives user type of animals in that location based on his/her
                                              // location
    {
        System.out.println("Enetr the location you are currently in (A OR B):");
        Scanner sin = new Scanner(System.in);
        String ch = sin.nextLine();
        System.out.println("***************************************************");
        if (ch.compareTo("A") == 0)
            System.out.println("Be cautious as you are sorrounded by wild animals");
        else {
            System.out.println("You can feed animals in  this location as these are friendly to you");
        }
        System.out.println("***************************************************");
    }

    // inserting a new animal into the database or updating the existing animal in
    // the database
    public static void insert_animal(String i, String uname, String ulocation, String uhealth, int perc, String ubreed,
            String ufood) {
        Database db = new Database();
        int count = 0;
        try {

            db.Establish();
            db.setQuery(String.format("SELECT count(*) FROM animals where id=\'%s\' ", i));
            ResultSet rs = db.Execute();
            if (rs.next()) {
                count = rs.getInt(1);
            }
            if (count == 0) {
                db.setQuery(String.format("insert into animals values(\"%s\",\"%s\",\"%s\",\"%s\",%d,\"%s\",\"%s\")", i,
                        uname, ulocation, uhealth, perc, ubreed, ufood));
                db.Update();
                db.Close(db.getSt(), db.getCon());
                System.out.println("The animal is successfully inserted");
            } else {
                db.setQuery(String.format(
                        "UPDATE animals SET name=\'%s',location=\'%s\',health_status=\'%s\',hungry_percentage=\'%s\',breed=\'%s\',food_type=\'%s\' where id=\'%s'",
                        uname, ulocation, uhealth, perc, ubreed, ufood, i));
                db.Update();
                db.Close(db.getSt(), db.getCon());
                //System.out.println("The animal is successfully updated as the id is already present");
            }

        } catch (SQLException e) {
            System.out.println("Check your SQL syntax as there is a SQL exception");
            e.printStackTrace();
        } catch (Exception e) {

            e.printStackTrace();
        }

    }

    // search animal based on attribute and value (can use partial strings too)
    public static void search(String str1, String str2) {
        Database db = new Database();
        try {
            db.Establish();

            db.setQuery("SELECT * FROM animals where " + str1 + " like \'%" + str2 + "%\'");
            ResultSet rs = db.Execute();
            if (!rs.next())
                System.out.println("No details available with your current details");
            else {
                System.out.println("These are details available for your search type " + str1);
                System.out.println("***************************************************");
                System.out.println("ID:" + rs.getString(1));
                System.out.println("NAME:" + rs.getString(2));
                System.out.println("LOCATION:" + rs.getString(3));
                System.out.println("BREED:" + rs.getString(6));
                System.out.println("FOOD:" + rs.getString(7));
                System.out.println("***************************************************");
                while (rs.next()) {
                    System.out.println("***************************************************");
                    System.out.println("ID:" + rs.getString(1));
                    System.out.println("NAME:" + rs.getString(2));
                    System.out.println("LOCATION:" + rs.getString(3));
                    System.out.println("BREED:" + rs.getString(6));
                    System.out.println("FOOD:" + rs.getString(7));
                    System.out.println("***************************************************");
                }
            }
            db.Close(db.getSt(), db.getCon());
            System.out.println("The query is successfully executed");

        } catch (SQLException e) {
            System.out.println("Check your SQL syntax as there is a SQL exception");
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void delete_animal(String str1, String str2)// delete animal based on attribute and value
    {
        Database db = new Database();
        int count = 0;
        try {
            db.Establish();
            db.setQuery(String.format("SELECT count(*) FROM animals WHERE %s=\"%s\"", str1, str2));
            ResultSet rs = db.Execute();
            while (rs.next()) {
                count = rs.getInt(1);
                if (count == 0)
                    System.out.println("The animal you trying to remove is currently unavailable");
                else {
                    Database db1 = new Database();

                    db1.Establish();
                    db1.setQuery(String.format("DELETE FROM animals WHERE %s=\"%s\"", str1, str2));
                    db1.Update();
                    db1.Close(db1.getSt(), db1.getCon());
                }
                System.out.println("The Data you have entered is deleted succesfully");
            }
            db.Close(db.getSt(), db.getCon());

        } catch (SQLException e) {
            System.out.println("Check your SQL syntax as there is a SQL exception");
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // useful to check animal health status
    public boolean check_health_of_animal(String uid) {
        Database db = new Database();
        String health = "";
        try {
            db.Establish();
            db.setQuery(String.format("SELECT health_status FROM animals WHERE id=\"%s\"", uid));
            ResultSet rs = db.Execute();

            while (rs.next()) {
                health = rs.getString(1);
            }
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        if (health.compareTo("good") == 0)
            return false;

        return true;
    }

    // treat the animal if the animal is not in the good condition
    public void animal_treatment(String uid) {
        Database db = new Database();
        try {
            db.Establish();
            db.setQuery(String.format("UPDATE animals SET health_status=\"good\" WHERE id=\"%s\"", uid));
            db.Update();
        } catch (SQLException e) {
            System.out.println("Check your SQL syntax as there is a SQL exception");
            e.printStackTrace();
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        //System.out.println("Animal with id " + uid + " health status updated");
    }

    // tostring method
    public String toString() {

        return String.format(
                "Id : %s\n Name: %s\n location : %s\n HealthStatus : %s\n HungryPercentage: %d\n Breed :%s\n Food_Type :%s\n",
                this.getId(), this.getName(), this.getLocation(), this.getHealthStatus(), this.getHungryPercentage(),
                this.getBreed(), this.getFoodType());
    }



}
