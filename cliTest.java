import java.util.*;
public class cliTest{
    public static void main(String args[])
    {
        Scanner sin = new Scanner(System.in);
        int key=0;
        key =  Integer.parseInt(args[0]);
        if (key == 1)
        {
            System.out.println("This is function-1");
            ZooManagement.StoreAnimal();
             ZooManagement.PrintAllAnimalDetails();
        }
        else if (key == 2)
        {
            System.out.println("This is function-2");
            ZooManagement.storeemployees();
            //employee e2 = new manager("suresh", 1030, 70000);
            manager m1 = new manager();
            m1.sendmessage(105, "demo message sent", "m-1");
            ZooManagement.demofunc(105);
        }
        else if (key == 3)
        {
            System.out.println("This is function-3");
        }
        else
            System.out.println("This is function-default");
    }
}