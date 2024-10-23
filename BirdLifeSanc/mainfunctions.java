package BirdLifeSanc;
import Database.*;
import java.util.*;
import java.sql.*;

public class mainfunctions 
{    public void checkpurityquality(String loginid,allbirdsancemployees e)
    {   // check water purity and quality
        check chk=new check();
        System.out.println(chk.Qualitycheck());
         System.out.println(chk.puritycheck());
        if(chk.Qualitycheck().contains("bad")) 
        {                   
         String msg="The Quality of water is Bad ,Please take Nessesary Action";
            e.sendMessage(loginid,e.getreceiversid(loginid,"Manager"),e.generatemsgid(),msg);
        } 

        if(chk.puritycheck().contains("bad")) 
        {                   
         String msg="The Purity of water is Bad ,Please take Nessesary Action";
            e.sendMessage(loginid,e.getreceiversid(loginid,"Manager"),e.generatemsgid(),msg);
        } 

    }

    public void righttoseeallmsgs(String loginid,allbirdsancemployees e) throws Exception
    {  // Have right to see all  sanctuary employees inbox's.... i.e for director
    Scanner sc= new Scanner(System.in);
    System.out.println("Enter the id of the person whose inbox you want to see:");  
    String id=sc.nextLine();
   
     Database db=new Database();
     db.Establish();
     String query1="select sanctuary from sancallemployees where empid=\""+id+"\"";
     db.setQuery(query1);
     ResultSet rs1= db.Execute();
     rs1.next();
     String st1=rs1.getString(1);

     String query2="select sanctuary from sancallemployees where empid=\""+loginid+"\"";
     db.setQuery(query2);
     db.Execute();
     ResultSet rs2= db.Execute();
     rs2.next();
     String st2=rs2.getString(1);

 if(st1.equals(st2)==true)
 {   // k==1 to see inbox of an employee
     // k==2 to see all msgs of an employee
       System.out.printf("Enter the choice:");
        int k=sc.nextInt();
         if(k==1)
   {      
        try
    {     String query3="select count(msgsent) from allmsgs where receiversid=\""+id+"\" and markasread=0";
           db.setQuery(query3);
          ResultSet rs3=db.Execute();
          rs3.next();
          if(rs3.getInt(1)!=0)
       { 
          System.out.printf("The messages in Inbox of employee with Id %s are\n",id);
          String query4="select msgsent from allmsgs where receiversid=\""+id+"\" and markasread=0";
          db.setQuery(query4);
          
          ResultSet rs=db.Execute();
          while(rs.next())
          {
              System.out.println(rs.getString(1));
          }
       }
       else
        System.out.printf("Inbox of %s is Empty\n",id);
          
    }
    catch(Exception ex)
    {   System.out.println("Unsuccesful Connection");
           System.out.println(ex);
    }
   }

   else
   {    e.seeallmsgsbydirector(id);

   }
 }
 else 
 System.out.printf("The employee with Id %s doesnot belong to this Sanctuary\n",id);

    }

public void updatedb(String loginid,allbirdsancemployees e) throws Exception
    {   // update or adding new employees...have rights only to director of a sanctuary can add employees to same sanc
        Scanner sc=new Scanner(System.in);
       
         System.out.println("Enter the Name of new Employee:");
         String name=sc.nextLine();
         System.out.println("Enter the profession:");
         String profession=sc.nextLine();
         System.out.println("Enter the Gender:");
         String gender=sc.nextLine();
         System.out.println("Enter the age:");
         int age=sc.nextInt();
         System.out.println("Enter Salary:");
         int salary=sc.nextInt();
         sc.nextLine();
         System.out.println("Enter the Sanctuary Name:");
         String sanc=sc.nextLine();
          sc.close();
     if(profession=="Doctor" || profession=="Manager"||profession=="Care Taker")     
     {    Random random = new Random(); 
         String idval=String.format("%d",random.nextInt(999));
         String id="";
         switch(profession)
         {   case "Doctor" : id="DOC"+idval;
                                break;
             case "Care Taker" : id="CRT"+idval;
                                break;                   
             case "Manager" : id="MNG"+idval;
                                break;                                               
         }
         
         Database db=new Database();
         db.Establish();
         String query1="select sanctuary from sancallemployees where profession=\"Director\" and empid=\""+loginid+"\"";
         db.setQuery(query1);
         ResultSet rs1= db.Execute();
           rs1.next();
          String drtsanc=rs1.getString(1);
          
          
    if(drtsanc.equals(sanc)==true)
     {   
          db.Establish();
        String query2="insert into sancallemployees values(\""+id+"\",\""+name+"\",\""+gender+"\",\""+age+"\",\""+salary+"\",\""+profession+"\",\""+sanc+"\");";
        //  String query2=String.format("insert into sancallemployees values(\"%s\",\"%s\",\"%s\",\"%d\",\"%d\",\"%s\"\"%s\");",id,name,gender,age,salary,profession,sanc);
          db.setQuery(query2);
         int numofrowseffected=db.Update(); 
     }

     else
       {
          System.out.println("You are trying to recruit/add employee to another Sanctuary.....Not possible to do that Operation");
       }
    } 
    else
    {
       System.out.println("Invalid Profession");
    }   

    }

  public void getdatabyseason()
  {  // Getting data of birds based on season entered by User
    System.out.printf("Enter the season to get bird data:");
    Scanner sc=new Scanner(System.in);
    String season=sc.nextLine();
        
        Database db=new Database();
      try
      {
       db.Establish();
       String query="select birdbreed from birdseason where season=\""+season+"\"";
       db.setQuery(query);
        ResultSet rs= db.Execute();
        while(rs.next())
              {   System.out.println(rs.getString(1));
                  
              } 
     }
       catch(Exception ex)
       {
        System.out.println("Unsuccesful Connection");
        System.out.println(ex);
       }   
  }

  public void removeemp(String loginid) throws Exception
  {  // Remove an employee which is only possible by Director
    Scanner sc=new Scanner(System.in);
    System.out.println("Enter the employee Id you want to delete:");
    String id=sc.nextLine();
     Database db=new Database();
         db.Establish();
         String query2="select sanctuary from sancallemployees where empid=\""+loginid+"\"";
         db.setQuery(query2);
         ResultSet rs=db.Execute();
         rs.next();
         String sanc=rs.getString(1);
         String query="delete from sancallemployees where empid=\""+id+"\" and sanctuary=\""+sanc+"\"";
          db.Establish();
          db.setQuery(query);
          int numrowseffected=db.Update();
          if(numrowseffected==0)
            System.out.println("Employee doesnot Exist in this Sanctuary");
         else  
           System.out.println("Removed the employee Successfully");

  }
  
  public void searchbasedon(String basedon) throws Exception
  {      ResultSet rs=null;
      switch(basedon)
    {   case "name":  rs=searchbasedonname();
                              break;
        case "empid":  rs=searchbasedonid(); 
                              break;
        case "gender" : rs=searchbasedongender();
                             break;
        case "profession" : rs=searchbasedonprofession();
                               break;
        case "sanctuary":  rs=searchbasedonsanc();
                              break;                                                                
    }

    while(rs.next())
    {  System.out.printf("\nEmployee Id: %s\n",rs.getString(1));
       System.out.printf("Employee Name: %s\n",rs.getString(2));
       System.out.printf("Gender: %s\n",rs.getString(3));
       System.out.printf("Age: %d\n",rs.getInt(4));
       System.out.printf("Salary: %d\n",rs.getInt(5));
       System.out.printf("Profession: %s\n",rs.getString(6));
       System.out.printf("Sanctuary: %s\n",rs.getString(7));
             System.out.printf("\n");
    }   
    
  }

  public void searchemployeecomparisions(String tablename,String basedon,int comparevaluewith,String operator)
    {
       Database db = new Database();
        try 
        {
          db.Establish();
        } 

        catch (Exception e) 
        {
            System.out.println("While searching using id..");
            e.printStackTrace();
        }

        db.setQuery(String.format("select * from %s where %s%s%d",tablename,basedon,operator,comparevaluewith ));
        ResultSet rs=null; 
            try 
            {   rs = db.Execute(); 
                if(tablename=="sancallemployees")
                {    while(rs.next())
                    {  System.out.printf("Employee Id: %s\n",rs.getString(1));
                       System.out.printf("Employee Name: %s\n",rs.getString(2));
                       System.out.printf("Gender: %s\n",rs.getString(3));
                       System.out.printf("Age: %d\n",rs.getInt(4));
                       System.out.printf("Salary: %d\n",rs.getInt(5));
                       System.out.printf("Profession: %s\n",rs.getString(6));
                       System.out.printf("Sanctuary: %s\n",rs.getString(7));
                             System.out.printf("\n");
                    }   
    
                } 
                else
                {  while(rs.next())
                    {  System.out.printf("Bird Id: %s\n",rs.getString(1));
                       System.out.printf("Bird Breed Name: %s\n",rs.getString(2));
                       System.out.printf("Sanctuary:%s\n",rs.getString(6));
                       System.out.printf("Count: %d\n",rs.getInt(7));
                             System.out.printf("\n");
                    }   
                } 

            } 
            catch (Exception e) 
            {
                System.out.println("Unsuccessful Connection\n");
                System.out.println(e);    
            }
        }


  public ResultSet searchbasedonname() throws Exception
  {       Scanner sc=new Scanner(System.in);
          System.out.println("Enter the name of the employee you want to search:");                  
          String name=sc.nextLine();
          String query="select *from sancallemployees where name like \'%"+name+"%\'";
 
      Database db=new Database();
      db.Establish();
      db.setQuery(query);
      ResultSet rs=db.Execute();
       return rs;
  }

  public ResultSet searchbasedonid() throws Exception
  {       Scanner sc=new Scanner(System.in);
          System.out.println("Enter the id of the employee you want to search:");                  
          String id=sc.nextLine();
          String query="select *from sancallemployees where empid like \'%"+id+"%\'";
 
      Database db=new Database();
      db.Establish();
      db.setQuery(query);
      ResultSet rs=db.Execute();
       return rs;
  }

  public ResultSet searchbasedongender() throws Exception
  {       Scanner sc=new Scanner(System.in);
          System.out.println("Enter the Gender of the employee you want to search:");                  
          String gender=sc.nextLine();
          String query="select *from sancallemployees where gender like \'%"+gender+"%\'";
 
      Database db=new Database();
      db.Establish();
      db.setQuery(query);
      ResultSet rs=db.Execute();
       return rs;
  }

  public ResultSet searchbasedonprofession() throws Exception
  {       Scanner sc=new Scanner(System.in);
          System.out.println("Enter the Profession of the employee you want to search:");                  
          String profession=sc.nextLine();
          String query="select *from sancallemployees where profession like \'%"+profession+"%\'";
          
      Database db=new Database();
      db.Establish();
      db.setQuery(query);
      ResultSet rs=db.Execute();
       return rs;
  }

  public ResultSet searchbasedonsanc() throws Exception
  {       Scanner sc=new Scanner(System.in);
          System.out.println("Enter the sanctuary of the employee you want to search:");                  
          String sanc=sc.nextLine();
          String query="select *from sancallemployees where sanctuary like \'%"+sanc+"%\'";
          
      Database db=new Database();
      db.Establish();
      db.setQuery(query);
      ResultSet rs=db.Execute();
       return rs;
  }
 
}


