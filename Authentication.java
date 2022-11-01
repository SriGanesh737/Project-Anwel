import com.sun.source.tree.SwitchTree;

import java.util.*;
import  java.sql.*;
import static java.lang.Integer.parseInt;

abstract class database {
    final static private String url="jdbc:mysql://localhost:3306/authentication";
    final static private String uname="root";
    final static private String pass="rabbit@1504";
    private  String query;
    private Statement st;
    private PreparedStatement pst;
    private Connection con;

    public void setCon(Connection con) {
        this.con = con;
    }

    public Connection getCon() {
        return con;
    }

    public void setSt(Statement st) {
        this.st = st;
    }

    public Statement getSt() {
        return st;
    }

    public void setPst(PreparedStatement pst) {
        this.pst = pst;
    }

    public PreparedStatement getPst() {
        return pst;
    }


    public void setQuery(String query) {
        this.query = query;
    }

    public String getQuery() {
        return query;
    }

    final public ResultSet Establish() throws Exception{

 //Class.forName("com.mysql.jdbc.Driver");
  setCon(DriverManager.getConnection(url,uname,pass));
  setSt(getCon().createStatement());
  return getSt().executeQuery(getQuery());
    }
    //method overload for prepared statement
    final public ResultSet Establish(String Table_Name) throws Exception{

        //Class.forName("com.mysql.jdbc.Driver");
        setCon(DriverManager.getConnection(url,uname,pass));
        setPst(getCon().prepareStatement(getQuery()));
        complete_prepared_statement(Table_Name);
        return getPst().executeQuery();
    }

    public void complete_prepared_statement(String Table_Name) throws SQLException {
        getPst().setString(1,Table_Name);
    }
   final public void Close(Statement st,Connection con) throws SQLException {
        st.close();

        con.close();
    }


    abstract  public boolean Login_Check(String userId,String password,String Table_Name) throws Exception;





}

//login admin class
class Administrator_Login extends database{



  public boolean Authentication_Details() throws Exception {


      Scanner s=new Scanner(System.in);

      System.out.println("enter the userId");
      String userId=s.next();
      System.out.println("enter the password");
      String password=s.next();
      String Table_Name="Administrator";
      setQuery("select * from Administrator");
      return  Login_Check(userId,password,Table_Name);


  }
@Override
  public boolean Login_Check(String userId,String password,String Table_Name) throws Exception {

      ResultSet r=Establish();
      boolean Flag=false;

      while(r.next()){
       if((r.getString(1).compareTo(userId)==0)&&(r.getString(2).compareTo(password)==0)){
        Flag=true;
        break;
      }}

    Close(getSt(),getCon());
    return Flag;
  }

 //class ends
}
class Employee_Login extends database{



    public boolean Employee_Details(int Emp_No) throws Exception {
        Scanner s = new Scanner(System.in);
        String Table_Name="";
        switch (Emp_No){

            case 1:setQuery("select * from CareTaker");
                   Table_Name="CareTaker";
                   break;
            case 2:setQuery("select * from Doctor");
                   Table_Name="Doctor";
                   break;
            case 3:setQuery("select * from ChartedAccountant");
                   Table_Name="ChartedAccountant";
                   break;
            case 4:setQuery("select * from ZooGuide");
                   Table_Name="ZooGuide";
                   break;
            default: System.out.println("ENTER THE VALID EMPLOYEE NUMBER");
        }

         System.out.println("enter the userId");
         String userId=s.next();
         System.out.println("enter the password");
         String password=s.next();


        return   Login_Check(userId,password,Table_Name);

    }
    @Override
    public boolean Login_Check(String userId,String password,String Table_Name) throws Exception {

        ResultSet r=Establish();
        boolean Flag=false;
        while(r.next()){
          if((r.getString(1).compareTo(userId))==0&&(r.getString(2).compareTo(password)==0)){
              Flag=true;
              break;
          }
        }
        Close(getSt(),getCon());
   return Flag;
    }





}
//customer
class Customer_Login extends database{

    public boolean Customer_Details() throws Exception {


        Scanner s=new Scanner(System.in);

        System.out.println("enter the Name");
        String Name=s.next();
        System.out.println("enter the Ticket_Reference.NO");
        String Reference_No=s.next();
        String Table_Name="Customer";
        setQuery("select * from Customer");
        return  Login_Check(Name,Reference_No,Table_Name);


    }
    @Override
    public boolean Login_Check(String Name,String Reference_No,String Table_Name) throws Exception {

        ResultSet r=Establish();
        boolean Flag=false;

        while(r.next()){
            if((r.getString(1).compareTo(Name)==0)&&(r.getString(2).compareTo(Reference_No)==0)){
                Flag=true;
                break;
            }}

        Close(getSt(),getCon());
        return Flag;
    }





}
//Main class
public class Authentication {

    static Scanner s=new Scanner(System.in);

    public static void main(String[] args) throws Exception {


  //1 for admin;
/*//2  employee;{
    01   caretaker;
    02   doctors
    03   ChartedAccountant
    04   ZooGuide*/
  //3    costumer



   System.out.println("enter you Job number");

   int Job=s.nextInt();

   switch(Job) {

           case 1:Administrator();
           break;
           case 2:Employee();
           break;
           case 3:Customer();
           break;

           default: System.out.println("ENTER THE VALID JOB NUMBER");
   }
  //end of main
 }
    public static void Administrator() throws Exception {

        Administrator_Login A=new Administrator_Login();
        boolean Flag=A.Authentication_Details();
        if(Flag)
            System.out.println("Login Successful");
        else
            System.out.println("incorrect details");

    }

    public static void Employee() throws Exception {
       System.out.println("enter the octal number for spefic employement");
        String Emp_Number=s.next();

        int Emp_No=parseInt(Emp_Number,8);

       Employee_Login E=new Employee_Login();
       boolean Flag=E.Employee_Details(Emp_No);
        if(Flag)
            System.out.println("Login Successful");
        else
            System.out.println("incorrect details");

      }
    public static void Customer() throws Exception {

       Customer_Login C=new Customer_Login();
        boolean Flag=C.Customer_Details();
        if(Flag)
            System.out.println("Login Successful");
        else
            System.out.println("incorrect details");


    }


}


//login redundancy;
//replace last;
//establish ---method overloaded(prepared statement method not used);
