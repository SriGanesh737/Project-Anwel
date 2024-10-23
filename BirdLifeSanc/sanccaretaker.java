package BirdLifeSanc;

import java.util.*;

// import javax.sound.midi.Soundbank;
import java.sql.*;
// import java.sql.Connection;
// import java.sql.PreparedStatement;
// import java.sql.ResultSet;
// import java.sql.Statement;
//import java.time.LocalDate;
//import java.time.Month;
 //import java.util.*;
//import java.util.Date;

public class sanccaretaker extends allbirdsancemployees
{   
    public sanccaretaker(String name, String id) 
     {
        super.setName(name);
        super.setId(id);
     }

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
      int x = random.nextInt(300); 
      String s=Integer.toString(x);
      msgid="MSG"+s;
      return msgid;
    }
    
   
}
