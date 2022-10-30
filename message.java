public class message {
    private String messageid;
    private int markAsRead=0;
    private String body;
     //
    public message()
    {

    }

    public message(String messageid, String body) {
        this.messageid = messageid;
        this.body = body;
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


}
