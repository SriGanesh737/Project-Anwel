package BirdLifeSanc;

import java.util.*;
// import javax.sound.midi.Soundbank;
import java.sql.*;


public class sancdoctor extends allbirdsancemployees
{ 
   
    public sancdoctor() 
    {  

    }

    public sancdoctor(String name, String id, int salary) 
    {
      super(name,id,salary,"",0);
       
    }
    
    public void sendMessage(String sendersid,String receiversId, String msgId, String body) 
    {
        pushmsgintodb(sendersid,receiversId, msgId, body); 
    }

    public String generatemsgid()
    {  String msgid="";
      Random random = new Random();
      int x = random.nextInt(200); 
      String s=Integer.toString(x);
      msgid="MSG"+s;
      return msgid;
    }

    public void pushmsgintodb(String sendersid,String receiversId, String msgId, String body)
    {   int rowseffected;
        
        try
         {     Establish();  
               String query=String.format("insert into allmsgs values(\"%s\",\"%s\",\"%s\",\"%s\",\"%d\");",generatemsgid(),sendersid,receiversId,body,0);
               setQuery(query);
               rowseffected=Update();
                if(rowseffected>0)
                  System.out.println("Msg sent succesfully\n");
                else 
                System.out.println("Msg senting failed\n");
         }
         catch(Exception e)
         {   System.out.println("Unsuccesful Connection....pro here");
                System.out.println(e);
         }
   } 
    
}