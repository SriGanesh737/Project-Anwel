package CustomerModule;
import java.sql.SQLException;
import java.util.Scanner;
import zoopack.*;
import zoopack.employees.*;
import BirdLifeSanc.*;
import zoopack.Animal;



public class choice
{ public void facilities(boolean flag)  {   Scanner s=new Scanner(System.in);
    boolean check = true;
    if(flag)
    {
        System.out.println("****************************************************");
        System.out.println("   1.Timetable display    					 ");
        System.out.println("   2.Menu display          					 ");
        System.out.println("   3.Vehicle availability  					 ");
        System.out.println("   4.Seeing train schedule 					 ");
        System.out.println("   5.Finding animals in your location               ");
        System.out.println("   6.Knowing the animals currently available 		 ");
        System.out.println("  -1 For coming out of facility window			 ");
        System.out.println("****************************************************");

        while (check)
        {
            System.out.println("Please enter your choice");
            int choice = s.nextInt();
            while(choice>6){
                System.out.println("Please enter your valid choice");
                 choice = s.nextInt();
            }
            switch (choice) {
                case 1:
                    Timetable t1 = new Timetable();
                    t1.Timings();
                    break;
                case 2:
                    food f = new food();
                    f.MenuDisplay();
                    break;
                case 3:
                    Vehicle v = new Vehicle();
                    v.Available_Vehicle();
                    break;
                case 4:
                    Vehicle v1 = new Vehicle();
                    v1.Train_Schedule();
                    break;
                case 5:
                    Animal.find_type_based_on_location();
                    break;
                case 6:
                     Animal.animals_currently_available();
                    break;
                case -1:
                    check = false;
                    break;
            }

        }


    }

    else
    {   System.out.println("****************************************************");
        System.out.println("\nChoose Any one of Below Options...!!");
        System.out.println("1.)Display Timetable ");
        System.out.println("2.)Display Menucard to order the Food");
        System.out.println("3.)Vehicle Availability");
        System.out.println("4.)Bird Statistics...");
        System.out.println("5.)Types of birds based on Current Season\n6.)Types of Birds Based on Season");
        System.out.println("If you want to Exit Enter -1.....!!");
        System.out.println("****************************************************");
        while (check)
        {    int k=0;
            int choice=-1;
            System.out.println("\nPlease Enter your choice");

            while(k==0)
            {   try
            {
                choice = s.nextInt();
                if(choice==1 || choice==2 || choice==3 || choice==4 || choice==5 || choice==6 ||choice==-1)
                    k=1;
                else
                    System.out.println("Enter a valid choice..!!!!");
            }

            catch(Exception e)
            {  System.out.println("Enter a valid Integer..!!");
                s.nextLine();
            }
            }

            switch (choice)
            {
                case 1:  {  Timetable t1 = new Timetable();
                    t1.Timings();
                    break;
                }

                case 2: {
                    food f = new food();
                    f.MenuDisplay();
                    break;
                }

                case 3:   {  Vehicle v = new Vehicle();
                    v.Available_Vehicle();
                    break;
                }

                case 4: {  mainfunctions2 mf2=new mainfunctions2();
                    mf2.callbirdstatistics();
                    break;

                }

                case 5: {    mainfunctions2 mf2=new mainfunctions2();
                    try {
                        mf2.callgetbirdbyseason();
                    }
                    catch(Exception e)
                    {
                        System.out.println(e);
                    }
                    break;
                }


                case 6:
                {     mainfunctions mf=new mainfunctions();
                    mf.getdatabyseason();
                    break;
                }

                case -1 : check=false;
            }
        }

    }
}

}
