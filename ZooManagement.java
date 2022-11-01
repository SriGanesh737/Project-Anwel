import java.util.*;

class Animal {
    private String Id;
    private String location;
    private String HealthStatus;
    private int HungryPercentage;
    private String Breed;
    private String FoodType;
    private String Name;

    public Animal()
    {

    }

    String healthissues[] = { "good", "leg injured", "skin infection", "heart problem", "not taking food", "fever" };

    public Animal(String id, String name, String location, int hungryPercentage, String breed,
            String Food) {
        Id = id;
        Name = name;
        this.location = location;
        HungryPercentage = hungryPercentage;
        Breed = breed;
        FoodType = Food;
        this.set_healthstatus();
        
    }

    void set_healthstatus()
    {
        Random rand = new Random();
        int temp = rand.nextInt(healthissues.length);
        this.HealthStatus = healthissues[temp];
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
    public void setHealthStatus(String healthStatus) {
        HealthStatus = healthStatus;
    }
    public int getHungryPercentage() {
        return HungryPercentage;
    }
    public void setHungryPercentage(int hungryPercentage) {
        HungryPercentage = hungryPercentage;
    }
    public String getBreed() {
        return Breed;
    }

    public void setBreed(String breed) {
        Breed = breed;
    }



    public void giveFood()
    {
        this.setHungryPercentage(0);
        System.out.printf("given food for %s\n", Name);
    }

    public String toString()
    {

        return String.format("Id : %s\n Name: %s\n location : %s\n HealthStatus : %s\n HungryPercentage: %d\n Breed :%s\n Food :%s\n",this.getId(),this.getName(),this.getLocation(),this.getHealthStatus(),this.getHungryPercentage(),this.getBreed(),this.getFoodType());
    }

}


public class ZooManagement {
    public static ArrayList<Animal> al=new ArrayList<>();
    public static ArrayList<employee> el=new ArrayList<>();
    public static ArrayList<sponcers> sl=new ArrayList<>();
    public static ArrayList<message> ml=new ArrayList<>();

    public static void StoreAnimal()
    {

        Animal Anm1 = new Animal("100", "Lion", "b-25", "Good", 20, "Carnivore", "meat");
        Animal Anm2 = new Animal("103", "Rabbit", "b-27", "Average", 10, "Herbivore", "Carrot");
        Animal Anm3 = new Animal("102", "Bear", "a-30", "Bad", 50, "Omnivore", "meat");

        al.add(Anm1);
        al.add(Anm2);
        al.add(Anm3);
    }

    public static void PrintAllAnimalDetails()
    {
        for (Animal x : al) {
            System.out.println(x.toString());
        }
    }

    public static void storeemployees()
    {
        el = new ArrayList<>();

        employee e1 = new manager("ram", 100, 500000);
        employee e2 = new manager("sam", 103, 200000);
        employee e3 = new caretaker("kam", 104);
        employee e4 = new caretaker("fam", 105);

        el.add(e1);
        el.add(e2);
        el.add(e3);
        el.add(e4);
    }

    public static void demofunc(int id)
    {

        for (employee x : el) {
            if (x.getId() == id) {
                caretaker y = (caretaker) x;
                y.showInbox();
            }
        }
    }

    public static void displaySponcers()
    {
        System.out.println("A very special thanks to all our sponcers !!!\n");
        for (sponcers s : sl) {
            System.out.println(s);
        }
    }

    public static void displayAllMessages()
    {
        
    }

}
