import java.sql.*;
import java.util.*;

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

    public void giveFood() {

        if (this.HungryPercentage > 50)
            System.out.printf("given food for %s\n", Name);
    }

    public String toString() {

        return String.format(
                "Id : %s\n Name: %s\n location : %s\n HealthStatus : %s\n HungryPercentage: %d\n Breed :%s\n Food :%s\n",
                this.getId(), this.getName(), this.getLocation(), this.getHealthStatus(), this.getHungryPercentage(),
                this.getBreed(), this.getFoodType());
    }

}

public class ZooManagement {
    public static ArrayList<Animal> al = new ArrayList<>();
    public static ArrayList<employee> el = new ArrayList<>();
    public static ArrayList<sponcers> sl = new ArrayList<>();
    public static ArrayList<message> ml = new ArrayList<>();
    public static ArrayList<Food> fl = new ArrayList<>();

    public void StoreAnimal_in_database(String a, String b, String c, String d, int e, String f, String g) {

        // Animal Anm1 = new Animal("100", "Lion", "b-25", "Good", 20, "Carnivore",
        // "meat");
        // Animal Anm2 = new Animal("103", "Rabbit", "b-27", "Average", 10, "Herbivore",
        // "Carrot");
        // Animal Anm3 = new Animal("102", "Bear", "a-30", "Bad", 50, "Omnivore",
        // "meat");

        // al.add(Anm1);
        // al.add(Anm2);
        // al.add(Anm3);

    }

    public static void PrintAllAnimalDetails() {
        for (Animal x : al) {
            System.out.println(x.toString());
        }
    }

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


    public static void displaySponcers() {
        System.out.println("A very special thanks to all our sponcers !!!\n");
        for (sponcers s : sl) {
            System.out.println(s);
        }
    }

    //This method is only for the highest authority for checking all the messages sent from anyone to anyone.
    public static void displayAllMessages() {
        System.out.println("The messages are :\n");
        for (message m : ml) {
            System.out.println(m);
        }
    }

    public static void update_employee_detail(String table_name,int empid,String column_name,String newVal)
    {
        String type, attr, val;int id;
        type = table_name;
        attr = column_name;
        val = newVal;
        id = empid;
        int intval=0;
        double dblval=3.7;
         String[] intList={"salary","id","casesTaken","currentFinancialPosition","donatedAmount"};

        int qtype = 1;
      for(int i=0;i<intList.length;i++)
      {
          if (attr.compareTo(intList[i]) == 0)
          {
              qtype = 2;
              intval = Integer.parseInt(newVal);
          }

      }

      if(attr.compareTo("Average_Rating")==0)
      {
          qtype = 3;
          dblval = Double.parseDouble(newVal);
      }



        Database db = new Database();

        try {
            db.Establish();
        } catch (Exception e1) {
            // TODO Auto-generated catch block
            e1.printStackTrace();
        }

        // need to test this line of code .
       if(qtype==1)
        db.setQuery(String.format("update %s set %s=\"%s\" where id= %d",type,attr,newVal,id));
        else if (qtype == 2)
        db.setQuery(String.format("update %s set %s=%d where id= %d", type, attr, intval, id));
        else
            db.setQuery(String.format("update %s set %s=%f where id= %d", type, attr, dblval, id));
        try {
            int rs = db.Update();
            System.out.println(rs);
        } catch (SQLException e) {

            System.out.println("while updating salary...");
            e.printStackTrace();
        }

        try {
            db.Close(db.getSt(), db.getCon());
        } catch (SQLException e) {
            System.out.println("Error in closing ¬_¬");
            e.printStackTrace();
        }

    }

}

