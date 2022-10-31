public class message {

    //added an attribute senders and recievers id's and its getters and setters
    private int sendersId;
    private int receiversId;
    private String messageid;
    private int markAsRead=0;
    private String body;

    public message()
    {

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
    public static void getMessageDetails(String messageId)
    {
          for(message mes: ZooManagement.ml)
          {
             if(mes.getMessageid().compareTo(mes.getMessageid())==0)
             {
                System.out.println("The details of the message are:\n"+mes);
             }
          }
    }

    @Override
    public String toString()
    {
        return String.format("Message id: %s\n SendersId: %d\n ReceiversId: %d\n MessageBody:%s\n\n", getMessageid(),
                getSendersId(), getReceiversId(), getBody());
    }

}
