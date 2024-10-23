package BirdLifeSanc;

import java.sql.*;
import Database.*;

public class BirdStatistics extends Database
{   private int count=0;
  
    public int getCount() 
    {
        return count;
    }
    
    public void setCount(int count) 
    {
        this.count = count;
    }
    
    public int checkcount(String breed)
    {     
       try
       {
            Establish();
            String query="select sum(count) from birddata where birdbreed=\""+breed+"\"";
              setQuery(query);
             ResultSet rs= Execute();
              rs.next();
             setCount(rs.getInt(1));
              Close(getSt(),getCon());
             }
        catch(Exception e)
        {
         System.out.println("Unsuccesful Connection");
         System.out.println(e);
        }           
        return getCount();
    }      
}
