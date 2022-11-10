import java.sql.*;
import java.util.*;

// import com.mysql.cj.protocol.Resultset;

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

    String healthissues[] = { "good", "leg injured", "skin infection", "heart problem", "not taking food", "fever","chronic disease","moody" };

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

    public void setBreed(String breed) {
        Breed = breed;
    }

    public void giveFood(String uid) {
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
        } catch (Exception e) {

            e.printStackTrace();
        }

    }

   

    public void update_animal_details() {
        Scanner sin = new Scanner(System.in);
        System.out.println("Enter the id of animal you want update in database");
        String uid = sin.nextLine();
        // sin.nextLine();
        System.out.println("Enter the column name you want to update the database");
        String ucol = sin.nextLine();
        System.out.println("Entered the updated detail");
        String upd = sin.nextLine();
        Database db = new Database();
        try {
            db.Establish();
            db.setQuery(String.format("UPDATE animals SET %s=\"%s\" WHERE id=\"%s\"", ucol, upd, uid));
            db.Update();
            db.Close(db.getSt(), db.getCon());
            System.out.println("The query is successfully updated");

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public void animals_currently_available() {
        System.out.println("Currently available animals in the zoo are :");
        Database db = new Database();
        try {
            db.Establish();
            db.setQuery(String.format("SELECT name FROM animals"));
            ResultSet rs = db.Execute();
            while (rs.next()) {
                System.out.println(rs.getString(1));
            }
        } catch (Exception e) {

            e.printStackTrace();
        }

    }

    public void find_all_details_of_specific_animal()
    {
        Scanner sin = new Scanner(System.in);
        System.out.println();
        String uname = sin.nextLine();
        Database db = new Database();
        try {
            db.Establish();
            db.setQuery(String.format("SELECT *  FROM animals WHERE name=\"%s\"", uname));
            ResultSet rs = db.Execute();
            System.out.println("Details are:");
            while (rs.next()) {
                System.out.println("***************************************************");
                System.out.println("ID:" + rs.getString(1));
                System.out.println("NAME:" + rs.getString(2));
                System.out.println("LOCATION:" + rs.getString(3));
                System.out.println("BREED:" + rs.getString(6));
                System.out.println("FOOD:" + rs.getString(7));
                System.out.println("COUNT :"+rs.getInt(8));
                System.out.println("***************************************************");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
    public void find_details_of_all_animals()
    {
        Database db=new Database();
        try {
            db.Establish();
            db.setQuery(String.format("SELECT *  FROM animals"));
            ResultSet rs = db.Execute();
            System.out.println("Details are:");
            while (rs.next())
            {
                System.out.println("***************************************************");
                System.out.println("ID:" + rs.getString(1));
                System.out.println("NAME:" + rs.getString(2));
                System.out.println("LOCATION:" + rs.getString(3));
                System.out.println("BREED:" + rs.getString(6));
                System.out.println("FOOD:" + rs.getString(7));
                System.out.println("COUNT :"+rs.getInt(8));
                System.out.println("***************************************************");
            }
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }

     }

     public void find_location_using_animalName() {

         System.out.println("Enter the name of animal you want to find");
         Scanner sin = new Scanner(System.in);
         String uname = sin.nextLine();
         Database db = new Database();
         try {
             db.Establish();
             db.setQuery(String.format("SELECT location  FROM animals WHERE name=\"%s\"", uname));
             ResultSet rs = db.Execute();
             while (rs.next())
                 System.out.println("Location :" + rs.getString(1));
         } catch (Exception e) {
             e.printStackTrace();
         }

     }

     public void find_type_based_on_location()
     {
         System.out.println("Enetr the location you are currently in (A OR B):");
         Scanner sin = new Scanner(System.in);
         String ch = sin.nextLine();
         System.out.println("***************************************************");
             if(ch.compareTo("A")==0)
             System.out.println("Be cautious as you are sorrounded by wild animals");
            else
            {
                System.out.println("You can feed animals in  this location as these are friendly to you");
            }
            System.out.println("***************************************************");
    }


    public void insert_animal(int i, String uname, String uhealth, String ulocation, int perc, String ubreed,
            String ufood) {
        Database db = new Database();
        try {
            db.Establish();
            db.setQuery(String.format("insert into animals values(\"%s\",\"%s\",\"%s\",\"%s\",%d,\"%s\",\"%s\")", i,
                    uname, uhealth, ulocation, perc, ubreed, ufood));
            db.Update();
            db.Close(db.getSt(), db.getCon());

        } catch (Exception e) {

            e.printStackTrace();
        }

    }

    public void get_details_based_on_food(String str)
    {
        Database db = new Database();
        try {
            db.Establish();
            db.setQuery(String.format("SELECT * FROM animals WHERE food_type=\"%s\" ", str));
            ResultSet rs = db.Execute();
            System.out.println("The details of animals are:");
            while (rs.next()) {
                System.out.println("***************************************************");
                System.out.println("ID:" + rs.getString(1));
                System.out.println("NAME:" + rs.getString(2));
                System.out.println("LOCATION:" + rs.getString(3));
                System.out.println("BREED:" + rs.getString(6));
                // System.out.println("FOOD:" + rs.getString(7));
                System.out.println("COUNT :" + rs.getInt(8));
                System.out.println("***************************************************");
            }
        } catch (Exception e) {

            e.printStackTrace();
        }

    }
    public void search(String str1)
    {
        Database db = new Database();
        try {
            db.Establish();
            db.setQuery(String.format("SELECT %s FROM animals", str1));
            ResultSet rs = db.Execute();
            while (rs.next())
                System.out.println(rs.getString(1));
            System.out.println("These are details available for your search type "+str1);
            Scanner sin = new Scanner(System.in);
            String str2 = sin.nextLine();
            db.setQuery(String.format("SELECT * FROM animals where %s=\"%s\"",str1,str2));
            rs = db.Execute();
            while (rs.next())
            {
                System.out.println("***************************************************");
                System.out.println("ID:" + rs.getString(1));
                System.out.println("NAME:" + rs.getString(2));
                System.out.println("LOCATION:" + rs.getString(3));
                System.out.println("BREED:" + rs.getString(6));
                System.out.println("FOOD:" + rs.getString(7));
                System.out.println("COUNT :"+rs.getInt(8));
                System.out.println("***************************************************");
            }

            db.Close(db.getSt(), db.getCon());
            System.out.println("The query is successfully updated");

        } catch (Exception e) {
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

    public static void storeemployees() {

        /*
         * Manager- name,id,salary
         * Caretaker- name,id,salary
         * Doctor- name,id,basesalary(salary),casesTaken
         * Charted Accountant- name,id,salary,static(CurrentFinancialPosition)
         * ZooGuide-name,id,salary(basesalary),avgrating
         * sponcers- name,donatedAmount
         * Messages-SendersId,ReceiversId,messageId,MarkasRead,Body
         */

    }

    // public static void displaySponcers() {
    // System.out.println("A very special thanks to all our sponcers !!!\n");
    // for (sponcers s : sl) {
    // System.out.println(s);
    // }
    // }

    // This method is only for the highest authority for checking all the messages
    // sent from anyone to anyone.
    // public static void displayAllMessages() {
    // System.out.println("The messages are :\n");
    // for (message m : ml) {
    // System.out.println(m);
    // }
 //This method is only for the highest authority for checking all the messages sent from anyone to anyone.
//  public static void displayAllMessages() {
//     System.out.println("The messages are :\n");
//     for (message m : ml) {
//         System.out.println(m);
//     }
// }

// public static void update_employee_detail(String table_name,int empid,String column_name,String newVal)
// {
//     String type, attr, val;int id;
//     type = table_name;
//     attr = column_name;
//     val = newVal;
//     id = empid;
//     int intval=0;
//     double dblval=3.7;
//      String[] intList={"salary","id","casesTaken","currentFinancialPosition","donatedAmount"};

//     int qtype = 1;
//   for(int i=0;i<intList.length;i++)
//   {
//       if (attr.compareTo(intList[i]) == 0)
//       {
//           qtype = 2;
//           intval = Integer.parseInt(newVal);
//       }

//   }

//   if(attr.compareTo("Average_Rating")==0)
//   {
//       qtype = 3;
//       dblval = Double.parseDouble(newVal);
//   }



//     Database db = new Database();

//     try {
//         db.Establish();
//     } catch (Exception e1) {
//         // TODO Auto-generated catch block
//         e1.printStackTrace();
//     }

//     // need to test this line of code .
//    if(qtype==1)
//     db.setQuery(String.format("update %s set %s=\"%s\" where id= %d",type,attr,newVal,id));
//     else if (qtype == 2)
//     db.setQuery(String.format("update %s set %s=%d where id= %d", type, attr, intval, id));
//     else
//         db.setQuery(String.format("update %s set %s=%f where id= %d", type, attr, dblval, id));
//     try {
//         int rs = db.Update();
//         System.out.println(rs);
//     } catch (SQLException e) {

//         System.out.println("while updating salary...");
//         e.printStackTrace();
//     }

//     try {
//         db.Close(db.getSt(), db.getCon());
//     } catch (SQLException e) {
//         System.out.println("Error in closing ¬_¬");
//         e.printStackTrace();
//     }

// }
    
    
    
    
    
    // }
    public static void main(String args[]) {
        Animal an = new Animal();
        Food f = new Food();
        System.out.println("Enter 1 to insert an Animal");
        System.out.println("Enter 2 to feed animal by id based on hungry percentage");
        System.out.println("Enter 3 to view the currently available animals");
        System.out.println("Enter 4 to  update details of animal");
        System.out.println("Enter 5 to  find location of animal");
        System.out.println("Enter 6 to  find details of an animal");
        System.out.println("Enter 7 to find details of all animals");
        System.out.println("Enter 8 to find if you can feed animals");
        System.out.println("Enter 9 to get details of all animals based on food type");
        System.out.println("Enter 10 to find details of animals based on your choice");

        System.out.println();
        int choice = 1;
        Scanner sin = new Scanner(System.in);
        while (choice != 0)
        {
            System.out.print("Enter your choice:");
            choice = sin.nextInt();
            System.out.println();
            switch (choice) {
                case 1:
                    an.insert_animal(4, "Girrafe", "Good", "B-17", 80, "reticulated", "twigs");
                    break;
                case 2:
                    an.giveFood("1");
                    break;
                case 3:
                    an.animals_currently_available();
                    break;
                case 4:
                    an.update_animal_details();
                    break;
                case 5: {
                    System.out.println("***************************************************");
                    an.animals_currently_available();
                    System.out.print("Select the animal name you want from above list:");
                    System.out.println();
                    an.find_location_using_animalName();
                    System.out.println("***************************************************");
                }
                    break;
                case 6: {
                    System.out.println("***************************************************");
                    an.animals_currently_available();
                    System.out.print("Select the animal name you want from above list:");
                    an.find_all_details_of_specific_animal();
                    System.out.println("***************************************************");

                }
                    break;
                case 7: {
                    System.out.println("***************************************************");
                    an.find_details_of_all_animals();
                    System.out.println("***************************************************");
                }
                    break;
                case 8: {
                    System.out.println("***************************************************");
                    an.find_type_based_on_location();
                    System.out.println("***************************************************");
                }
                    break;
                case 9: {
                    f.get_details_of_food();
                    System.out.println("Enter food type to search animals animals based on the food");
                    sin.nextLine();
                    String st = sin.nextLine();
                    an.get_details_based_on_food(st);
                }
                case 10: {
                    System.out.println("These are feilds available for animals ");
                    System.out.println("ID\nname\nlocation\nbreed\nfood_type\n");
                    sin.nextLine();
                    System.out.println("enter the string to search on that basis:");
                    String str = sin.nextLine();
                    an.search(str);

                }
            }
            System.out.print("Enter specified number to know more information or enter 0 to exit:");
            choice = sin.nextInt();
        }
        System.out.println();
        System.out.println("------------THANK YOU FOR VISTING OUR ZOO----------------");
    }
}
