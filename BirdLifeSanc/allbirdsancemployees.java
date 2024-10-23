package BirdLifeSanc;

import java.sql.*;
import java.util.*;
import Database.*;

public class allbirdsancemployees extends Database
{
    private String name;
    private int salary;
    private String id;
    private String sanc;
    private int age;
    
    public allbirdsancemployees() 
    {

    }
    
    public allbirdsancemployees(String name, String id,int Sal,String sanc,int age) 
    {   this.salary=Sal;
        this.sanc=sanc;
        this.name = name;
        this.id = id;
        this.age=age;
    }

    public int getAge() 
    {
        return age;
    }

    public void setAge(int age)
     {
        this.age = age;
     }

    public String getName() 
    {
        return name;
    }

    public int getSalary() 
    {
        return salary;
    }

    public void setSalary(int salary) 
    {
        this.salary = salary;
    }

    public void setName(String name) 
    {
        this.name = name;
    }

    public void setId(String id) 
    {
        this.id = id;
    }

    public String getId() 
    {
        return id;
    }

    public void setsanc(String sanc)
    {
         this.sanc=sanc;
    }
    public String getsanc()
    {
        return this.sanc;
    }

    // public void pushmsgintodb(String sendersid,String receiversId, String msgId, String body)
    // {

    // }

    // public void sendMessage(String loginid,String receiversId, String msgId, String body) 
    // {
       
    // }

    public void sendMessage(String sendersid,String receiversId, String msgId, String body)
    {    pushmsgintodb(sendersid,receiversId, msgId, body); 
       
    }



public void pushmsgintodb(String sendersid,String receiversId, String msgId, String body)
{   int rowseffected;
    try
     {    Establish();  
           String query=String.format("insert into allmsgs values(\"%s\",\"%s\",\"%s\",\"%s\",\"%d\");",generatemsgid(),sendersid,receiversId,body,0);
           setQuery(query);
           rowseffected=Update();
            if(rowseffected>0)
              System.out.println("Msg sent succesfully\n");
            else 
            System.out.println("Msg senting failed\n");
     }
     catch(Exception e)
     {   System.out.println("Unsuccesful Connection");
            System.out.println(e);
     }
} 

   public String generatemsgid()
    {  String msgid="";
    Random random = new Random();
    int x = random.nextInt(500); 
    String s=Integer.toString(x);
    msgid="MSG"+s;
    return msgid;
   }



    public String getreceiversid(String loginuserid,String profession)
    {   String id="";
         try
         {   
            Establish();
            String argu_query="select sanctuary from sancallemployees where empid=\""+loginuserid+"\"";
            setQuery(argu_query);
            ResultSet rs=Execute();
           
             rs.next();
            String query="select empid from sancallemployees where profession=\""+profession+"\" and sanctuary=\""+rs.getString(1)+"\"";
             setQuery(query);
             Establish();
             ResultSet rs2=Execute();
              rs2.next();
           id=rs2.getString(1);
         }
         catch(Exception e)
         {
             System.out.printf("Connection was Unsuccesful\n");
             System.out.println(e);
         }

             return id;
    }

    // public String generatemsgid()
    // {    String s="";
    //       return s;
    // }

    public void showInbox(String receiversId) 
    {
        System.out.println("The messages in the inbox are :");
        try
        {    Establish();  
              String query="select msgsent from allmsgs where receiversid=\""+receiversId+"\" and markasread=0";
              setQuery(query);
              ResultSet rs=Execute();
              while(rs.next())
              {
                  System.out.println(rs.getString(1));
              }
              String query2="update allmsgs set markasread=1 where receiversid=\""+receiversId+"\" and markasread=0";
              setQuery(query2);
               int numrowseffected=Update();
              // Close(getPst(), getCon());   
        }
        catch(Exception e)
        {   System.out.println("Unsuccesful Connection");
               System.out.println(e);
        }

    }

    public void seeallmsgs(String id)
    {    
       try
       {    Establish();  
            System.out.printf("All messages of %s are\n",id);
              String query1="select msgsent from allmsgs where receiversid=\""+id+"\"";
              setQuery(query1);
              ResultSet rs=Execute();
            while(rs.next())
           {
             System.out.println(rs.getString(1));
           }

           String query2="update allmsgs set markasread=1 where receiversid=\""+id+"\" and markasread=0";
           setQuery(query2);
            int numrowseffected=Update();
         // Close(getPst(), getCon()); 
      }

      catch(Exception ex)
     {   System.out.println("Unsuccesful Connection");
           System.out.println(ex);
     }

    }

    public void seeallmsgsbydirector(String id)
    {   System.out.printf("All messages of %s are\n",id);
    try
   {    Establish();  
       String query="select msgsent from allmsgs where receiversid=\""+id+"\"";
       setQuery(query);
       ResultSet rs=Execute();
       while(rs.next())
       {
           System.out.println(rs.getString(1));
       }
   }

   catch(Exception ex)
  {   System.out.println("Unsuccesful Connection");
        System.out.println(ex);
  }

    }

}


