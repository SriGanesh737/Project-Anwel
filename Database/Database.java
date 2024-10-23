package Database;
import java.sql.*;


public class Database{
    final static String Database_Name="aniwel";
    final static private String url="jdbc:mysql://localhost:3306/"+Database_Name;
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


    final public void Establish() throws Exception{

        //Class.forName("com.mysql.jdbc.Driver");
        setCon(DriverManager.getConnection(url,uname,pass));
        setSt(getCon().createStatement());
        return;
    }

    final public ResultSet Execute() throws SQLException {

        return getSt().executeQuery(getQuery());
    }
    final public int Update() throws SQLException {

        return getSt().executeUpdate(getQuery());
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





}

