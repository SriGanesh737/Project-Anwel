package BirdLifeSanc;

import java.sql.*;
import java.time.LocalDate;
import Database.*;


public class birdvisitbyseason extends Database
{   private String season;

    public String getSeason() 
    {
        return season;
    }
    
    public void setSeason(String season) 
    {
        this.season = season;
    }
    
    public birdvisitbyseason()
    { 
    
    }
    
    public String toString()
    {   return super.toString()+"Season at which they occur here:"+getSeason()+"\n";
       
    }
    
    public void getseasonbydate(int month)
    {    int k=month/4;
         String s=""; 
         switch(k)
         {   case 0 : s="Autumn";
                         break;
             case 1 : s="Summer";
                        break;
             case 2 : s="Monsoon";
                         break;
             case 3 : s="Winter";
                        break;
         }
    
         setSeason(s);  
    }
    
    public void getbirdbyseason()
    {   
        LocalDate today = LocalDate.now();
        int month = today.getMonthValue();
         getseasonbydate(month);
          try
          {
           Establish();
           String query="select birdbreed from birdseason where season=\""+getSeason()+"\"";
           setQuery(query);
            ResultSet rs= Execute();
            while(rs.next())
                  {   System.out.println(rs.getString(1));
                  } 
                  Close(getSt(),getCon());
         }
           catch(Exception e)
           {
            System.out.println("Unsuccesful Connection");
            System.out.println(e);
           }        
    }

}


