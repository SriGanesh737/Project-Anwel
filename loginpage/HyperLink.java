package loginpage;
import java.util.ArrayList;
import BirdLifeSanc.*;
import Database.*;
import CustomerModule.*;
import zoopack.ZooManagement;

//1 for Director;
/*//2  employee;{
    01   caretaker;
    02   doctors
    03   ChartedAccountant
    04   ZooGuide*/
//3    costumer
class ZooManagement_Authentication extends General_Authentication 
{


   public ZooManagement_Authentication(){
       setG_Authentication("Z_Authentication");
       setLimit_flag(true);

   }

  public void return_customer(){
        new choice().facilities(true);
    }
    @Override
   public void Return_Details(String userId,String prof) {

        ZooManagement.driverFunc(userId,prof);
   }





}
class BirdLifeSanctuary_Authentication extends General_Authentication 
{


     public BirdLifeSanctuary_Authentication(){
         setG_Authentication("B_Authentication");
         setLimit_flag(false);
         System.out.println("Tables have been populated\n");

     }
    public void return_customer(){
        new choice().facilities(false);
    }
    @Override
    public void Return_Details(String userId,String prof){

        mainfunctions2 mf2=new mainfunctions2();
       mf2.maininsidecode(userId);

    }

}

public class HyperLink 
{

 public void appTitle(){
     System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
     System.out.println("                         *        *       *  *       *  * * * * *  *                                                                 ");
     System.out.println("                        * *       * *     *  *       *  *          *                                                                 ");
     System.out.println("                       *   *      *  *    *  *       *  *          *                                                                 ");
     System.out.println("                      *     *     *   *   *  *   *   *  * * * * *  *                                                                 ");
     System.out.println("                     * * * * *    *    *  *  *  * *  *  *          *                                                                 ");
     System.out.println("                    *         *   *     * *  * *   * *  *          *                                                                 ");
     System.out.println("                   *           *  *       *  *       *  * * * * *  * * * * * *                                                       ");
     System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
     System.out.println();

 }

 public void welcome(String W_Name){
     System.out.println("WELCOME TO "+W_Name+"MANAGEMENT SYSTEM");
 }

    public void loginTitle(String L_Name){


        System.out.println(L_Name+"MANAGEMENT SYSTEM LOGIN PAGE");
        System.out.println("-------------------------------------------------");
    }


}


