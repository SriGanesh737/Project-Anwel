package loginpage;

import java.lang.reflect.Method;
import java.util.*;
import  java.sql.*;
import static java.lang.Integer.parseInt;
import Database.*;
import BirdLifeSanc.*;


abstract class Database_Abstract extends Database
{
    abstract  public boolean Login_Check(String userId,String password,String Table_Name,String Profession) throws Exception;
}


abstract class General_Authentication 
{
    static Scanner s=new Scanner(System.in);

     static String[]  return_Id=new String[1];

     static String[]  prof=new String[1];

     private String G_Authentication;

     private boolean Limit_flag;


    public void setLimit_flag(boolean limit_flag) 
    {
        Limit_flag = limit_flag;
    }

    public boolean getLimit_flag() 
    {
        return Limit_flag;
    }







    public void setG_Authentication(String g_Authentication) 
    {
        G_Authentication = g_Authentication;
    }

    public String getG_Authentication() 
    {
        return G_Authentication;
    }

    abstract public void Return_Details(String userId, String prof);
    abstract public void return_customer();
    public void  Job_Chooser(String L_Name) throws Exception
    {    HyperLink H=new HyperLink();
        H.loginTitle(L_Name);
        System.out.println("--***--JOBS--***--");
          System.out.println("1.)Director\n2.)Employee\n3.)Customer");
        System.out.println("Enter your Job choice.....");
        int Job=-1;
        int k=0;
        while(k==0)
        {    try
            {    
                Job=s.nextInt();
                if(Job==1 || Job==2 || Job==3)
                  k=1;
                else
                 {
                    System.out.println("Invalid Job Choice.....!");
                    System.out.println("Enter a valid choice..!!!!");
                 } 
            }
    
             catch(Exception e)
             {  System.out.println("Enter a valid Integer..!!"); 
                 s.nextLine();
             }        
        }

        switch (Job) 
        {
            case 1 -> {
                        Director();
                      }

            case 2 -> {
                       Employee();
                      }

            case 3 -> {
                       Customer();
                      }
        }

    }

    public  void Director() throws Exception 
    {
        Director_Login A=new Director_Login();

        boolean Flag=A.Authentication_Details(return_Id,prof,getG_Authentication());
        if(Flag) 
        {
            System.out.println("Login Successful......!!");
            Return_Details(return_Id[0],prof[0]);
        }

        else
        {
            System.out.println("Incorrect Login Credentials!!");
            System.exit(0);
        }
    }

    public void Employee() throws Exception 
    {
         System.out.println("--EMPLOYEE-->");
         if(getLimit_flag())
             System.out.println("01)Care Taker\n02)Doctor\n03)ChartedAccountant\n04) ZooGuide\n");
             else
         System.out.println("01)Care Taker\n02)Doctor\n03)Manager\n");
        System.out.println("Enter the Octal Number for Specific Employement");
        String Emp_Number=s.next();

        int Emp_No=parseInt(Emp_Number,8);
        if(!getLimit_flag())
        {
            if(Emp_No>3)
            {
                System.out.println("Choose the correct number");
                System.exit(0);
            }
        }

        if(getLimit_flag()&&Emp_No>2)
        {
            Emp_No++;
        }

        Employee_Login E=new Employee_Login();
        boolean Flag=E.Employee_Details(Emp_No,return_Id,prof,getG_Authentication());
        if(Flag)
        {
            System.out.println("Login Successful......!!");
            Return_Details(return_Id[0],prof[0]);
        }
        else
        {
            System.out.println("Incorrect Login Credentials..!!!");
            System.exit(0);
        }

    }

    public void Customer() throws Exception 
    {

        Customer_Login C=new Customer_Login();
        boolean Flag=C.Customer_Details(getG_Authentication());
       if(Flag)
        {
            System.out.println("Login Successful......!!");
            return_customer();

        }
        else
       {
            System.out.println("Incorrect Credentials..!");
            System.exit(0);
       }

    }

}

class Director_Login extends Database_Abstract
{
  public boolean Authentication_Details(String[] a,String[] b,String Table_Name) throws Exception 
  {
      Scanner s=new Scanner(System.in);
      System.out.println("****-----LOGIN-----***");
      System.out.println("Enter the LoginId");

      String userId=s.next();
      System.out.println("Enter the password");
      String password=s.next();

      setQuery("select * from "+Table_Name);
      a[0]=userId;
      b[0]="Director";
      return  Login_Check(userId,password,Table_Name,b[0]);
  }

@Override
  public boolean Login_Check(String userId,String password,String Table_Name,String Profession) throws Exception 
  {
        Establish();
    ResultSet r= Execute();
      boolean Flag=false;

      while(r.next())
      {
       if((r.getString(1).compareTo(userId)==0)&&(r.getString(2).compareTo(password)==0)&&(r.getString(3).compareTo(Profession)==0))
       {
        Flag=true;
        break;
       }
      }

      Close(getSt(),getCon());
    return Flag;
  }

 //class ends
}

class Employee_Login extends Database_Abstract 
{
    public boolean Employee_Details(int Emp_No,String []a,String []b,String Table_Name) throws Exception 
    {
        Scanner s = new Scanner(System.in);

        switch (Emp_No) 
        {
            case 1 -> 
            {
                setQuery("select * from "+Table_Name);

                b[0]="Care Taker";
            }
            case 2 -> 
            {
                setQuery("select * from "+Table_Name);

                b[0]="Doctor";
            }
            case 3->
            {
                setQuery("select * from "+Table_Name);
                b[0]="Manager";
            }
            case 4 -> 
            {
                setQuery("select * from "+Table_Name);

                b[0]="ChartedAccountant";
            }
            case 5 -> 
            {
                setQuery("select * from "+Table_Name);

                b[0]="ZooGuide";
            }

            default -> {System.out.println("ENTER THE VALID EMPLOYEE NUMBER");
                        System.exit(0);
                       }
        }
        System.out.println("****-----LOGIN-----***");
         System.out.println("Enter the LoginId");
         String userId=s.next();
         System.out.println("Enter the password");
         String password=s.next();

        a[0]=userId;
        return   Login_Check(userId,password,Table_Name,b[0]);
    }

    @Override
    public boolean Login_Check(String userId,String password,String Table_Name,String Profession) throws Exception 
    {
        Establish();
        ResultSet r= Execute();
        boolean Flag=false;
        while(r.next())
        {
          if((r.getString(1).compareTo(userId))==0&&(r.getString(2).compareTo(password)==0)&&(r.getString(3).compareTo(Profession)==0)){
              Flag=true;
              break;
          }
        }
       Close(getSt(),getCon());
          return Flag;
    }

}
//customer
 class Customer_Login extends Database_Abstract
 {
    public boolean Customer_Details(String Table_Name ) throws Exception 
    {
        Scanner s=new Scanner(System.in);

        System.out.println("Enter the Name");
        String Name=s.next();
        System.out.println("Enter the Ticket_Reference Number");
        String Reference_No=s.next();
        String Profession="Customer";
        setQuery("select * from "+Table_Name);
        return  Login_Check(Name,Reference_No,Table_Name,Profession);
    }
  @Override
    public boolean Login_Check(String Name,String Reference_No,String Table_Name,String Profession) throws Exception
    {
        Establish();
        ResultSet r= Execute();
        boolean Flag=false;

        while(r.next())
        {
            if((r.getString(1).compareTo(Name)==0)&&(r.getString(2).compareTo(Reference_No)==0))
            {
                Flag=true;
                break;
            }
        }

         Close(getSt(),getCon());
        return Flag;
    }

}

//Main class
public class Authentication 
{
    static Scanner s=new Scanner(System.in);

    public static void main(String[] args) throws Exception 
    {
        HyperLink H=new HyperLink();
        H.appTitle();

            mainfunctions2 mf2=new mainfunctions2();
                mf2.populateTables();

/*zooooo{                                                              /*bird{                                          /*{
     1 for Director//manager;                                                   1 for Director;
     2  employee;{                                                     2  employee;{
    01   caretaker;                                                    01   caretaker;
    02   doctors                                                       02   doctors
    03   ChartedAccountant                                             03   Manager
    04   ZooGuide                                                        }
    }

     //3    costumer                                                     //3    costumer
    }*/                                                                    //}

        //0--zoo;
        //1--bird;
        int k=0;
        int Sector=-1;
        System.out.println("  *******************************************************************");
        System.out.println("  *                 1.ZOO MANAGEMENT                                *");
        System.out.println("  *                 2.BIRD LIFE SANCTUARY                           *");
        System.out.println("  ********************************************************************");
        System.out.println();
        System.out.println("Enter respective number:");
                 while(k==0)
       {   try
        {    
            Sector=s.nextInt();
            if(Sector==1 || Sector==2)
              k=1;
            else
              System.out.println("Enter a valid choice..!!!!");
        }

         catch(Exception e)
         {  System.out.println("Enter a valid Integer..!!"); 
             s.nextLine();
         }
      }

      switch (Sector)
      {
          case 1 ->{

              new ZooManagement_Authentication().Job_Chooser("Z00");
          }
          case 2 ->{
              H.welcome("BIRD");
              new BirdLifeSanctuary_Authentication().Job_Chooser("BIRD");
          }
      }

     mf2.truncatetables();
 
  //end of main
 }



}


//login redundancy;
//replace last;
//establish ---method overloaded(prepared statement method not used);
//close statements chooduuuu