package Message;
import java.sql.SQLException;
import java.sql.*;
import com.mysql.cj.protocol.Resultset;

import Database.*;
import zoopack.*;

public class message extends Database {

    // added an attribute senders and recievers id's and its getters and setters
    private int sendersId;
    private int receiversId;
    private String messageid;
    private int markAsRead = 0;
    private String body;

    //
    public message() {

    }

    public message(String messageid, int sendersId, String body) {
        this.setSendersId(sendersId);
        this.setMessageid(messageid);
        this.setBody(body);
    }

    public int getReceiversId() {
        return receiversId;
    }

    public void setReceiversId(int receiversId) {
        this.receiversId = receiversId;
    }

    public int getSendersId() {
        return sendersId;
    }

    public void setSendersId(int sendersId) {
        this.sendersId = sendersId;
    }

    public String getMessageid() {
        return messageid;
    }

    public void setMessageid(String messageid) {
        this.messageid = messageid;
    }

    public int getMarkAsRead() {
        return markAsRead;
    }

    public void setMarkAsRead(int markAsRead) {
        this.markAsRead = markAsRead;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    // gives message details using messageId.
    public static void getMessageDetails(String messageId) {
        for (message mes : ZooManagement.ml) {
            if (mes.getMessageid().compareTo(mes.getMessageid()) == 0) {
                System.out.println("The details of the message are:\n" + mes);
            }
        }
    }

    // Used for storing messages into database call when ever adding a message into
    // the arraylist
    public void storeMessage() {

        Database db = new Database();

        try {
            db.Establish();
        } catch (Exception e1) {
            // TODO Auto-generated catch block
            e1.printStackTrace();
        }

        db.setQuery(String.format("insert into message values(%d,%d,\"%s\",%d,\"%s\")", this.getSendersId(),
                this.getReceiversId(), this.getMessageid(), this.getMarkAsRead(), this.getBody()));

        try {
            db.Update();
        } catch (SQLException e) {
            System.out.println("here mam while adding message to database error occurred!!+_+ O_O");
            e.printStackTrace();
        }

        try {
            db.Close(db.getSt(), db.getCon());
        } catch (SQLException e) {
            System.out.println("Error in closing ¬_¬");
            e.printStackTrace();
        }

    }

    public static void markRead(String msgId) {
        Database db = new Database();

        try {
            db.Establish();
        } catch (Exception e1) {
            e1.printStackTrace();
        }

        db.setQuery(String.format("update message set markAsRead= 1 where messageId=\"%s\"", msgId));

        try {
            db.Update();
        } catch (SQLException e) {
            System.out.println("here bro while adding message to database error occurred!!+_+ O_O");
            e.printStackTrace();
        }

        try {
            db.Close(db.getSt(), db.getCon());
        } catch (SQLException e) {
            System.out.println("Error in closing ¬_¬");
            e.printStackTrace();
        }
        //System.out.println("Message read succesfully!!!");
    }

    public static void showUnreadMessages(int id)
    {
          Database db = new Database();

        try {
            db.Establish();
        } catch (Exception e1) {
            // TODO Auto-generated catch block
            e1.printStackTrace();
        }

        db.setQuery(String.format("select * from message where receiversId=%d and markAsRead=0",id));

        try {
            ResultSet rs = db.Execute();
           while(rs.next())
           {
               System.out.println("Senders Id: " + rs.getInt("sendersId") + "\n Body: " + rs.getString("body"));
               markRead(rs.getString("messageId"));
           }
        } catch (SQLException e) {
            System.out.println("here bro while adding message to database error occurred!!+_+ O_O");
            e.printStackTrace();
        }

        try {
            db.Close(db.getSt(), db.getCon());
        } catch (SQLException e) {
            System.out.println("Error in closing ¬_¬");
            e.printStackTrace();
        }
    }

    public static void showInbox(int id)
    {

        Database db = new Database();

        try {
            db.Establish();
        } catch (Exception e1) {
            // TODO Auto-generated catch block
            e1.printStackTrace();
        }

        db.setQuery(String.format("select * from message where receiversId=%d ",id));

        try {
            ResultSet rs = db.Execute();
           while(rs.next())
           {
               System.out.println("Senders Id: " + rs.getInt("sendersId") + "\n Body: " + rs.getString("body"));
           }
        } catch (SQLException e) {
            System.out.println("here bro while adding message to database error occurred!!+_+ O_O");
            e.printStackTrace();
        }

        try {
            db.Close(db.getSt(), db.getCon());
        } catch (SQLException e) {
            System.out.println("Error in closing ¬_¬");
            e.printStackTrace();
        }
    }

    @Override
    public String toString() {
        return String.format("Message id: %s\n SendersId: %d\n ReceiversId: %d\n MessageBody:%s\n\n", getMessageid(),
                getSendersId(), getReceiversId(), getBody());
    }

}
