package BirdLifeSanc;

import java.util.*;
import java.sql.*;
import java.io.*;
import Database.*;
public class mainfunctions2
{
    public void callbirdstatistics()
    {    BirdStatistics bs=new BirdStatistics();
        ResultSet rs=null;
        String breed="";
        try
        {    String query="select distinct birdbreed from birddata";
            Database db=new Database();
            db.Establish();
            db.setQuery(query);
            rs=db.Execute();

            while(rs.next())
            {   breed=rs.getString("birdbreed");
                System.out.println(breed+"--------"+bs.checkcount(breed));
            }

            db.Close(db.getSt(), db.getCon());
        }
        catch(Exception e)
        {
            System.out.printf("Could not execute this operation....(Connection fault)\n");
            System.out.println(e);
        }
    }

    public void callgetbirdbyseason() throws ClassNotFoundException, SQLException
    {     birdvisitbyseason b=new birdvisitbyseason();
        b.getbirdbyseason();
    }

    public String getprofession(String loginid)
    {  ResultSet rs=null;
        String profession="";
        try
        {    String query="select profession from sancallemployees where empid=\""+loginid+"\"";
            Database db=new Database();
            db.Establish();
            db.setQuery(query);
            rs=db.Execute();
            rs.next();
            profession=rs.getString(1);
        }
        catch(Exception e)
        {
            System.out.printf("Could not execute this operation....(Connection fault)\n");
            System.out.println(e);
        }
        return profession;

    }

    public String getname(String loginid)
    {  ResultSet rs=null;
        String name="";
        try
        {    String query="select name from sancallemployees where empid=\""+loginid+"\"";
            Database db=new Database();
            db.Establish();
            db.setQuery(query);
            rs=db.Execute();
            rs.next();
            name=rs.getString("name");
        }
        catch(Exception e)
        {
            System.out.printf("Could not execute this operation....(Connection fault)\n");
            System.out.println(e);
        }
        return name;
    }

    public void sendmsgtoaperson(allbirdsancemployees e,String loginid,String profession)
    {    Scanner sc=new Scanner(System.in);
        System.out.printf("Type the message you want to send:");
        String msg=sc.nextLine();
        e.sendMessage(loginid,e.getreceiversid(loginid,profession),e.generatemsgid(),msg);

    }

    public void populateTables()
    {
        FileReader fr1;
        FileReader fr2;
        FileReader fr3;
        FileReader fr4;
        Database db =new Database();

        Scanner sin1=null;
        Scanner sin2=null;
        Scanner sin3=null;
        Scanner sin4=null;

        try
        {
            fr1 = new FileReader("src/birdlifesanccsvfiles/sancallemployees.csv");
            sin1 = new Scanner(fr1);
        }

        catch (FileNotFoundException e)
        {
            System.out.println("File not Linked .....1");
            e.printStackTrace();
        }

        sin1.nextLine();
        String row1,row2,row3,row4;

        while (sin1.hasNext())
        {
            row1 = sin1.nextLine();
            String[] tokens = row1.split(",");

            if(checkpresent("sancallemployees",tokens[0],"empid",db)==false)
            {  try
            {
                db.Establish();
                String query="insert into sancallemployees values("+tokens[0]+","+tokens[1]+","+tokens[2]+","+Float.parseFloat(tokens[3])+","+Float.parseFloat(tokens[4])+","+tokens[5]+","+tokens[6]+");";

                db.setQuery(query);
                int numofrowseffected=db.Update();


            }

            catch(Exception e)
            {     e.printStackTrace();
                System.out.println("Unsuccesful Population of tables");
            }
            }

        }


        try
        {
            fr2=new FileReader("src/birdlifesanccsvfiles/birddata.csv");
            sin2 = new Scanner(fr2);
        }
        catch (FileNotFoundException e)
        {
            System.out.println("File not Linked .....2");
            e.printStackTrace();
        }

        sin2.nextLine();

        while (sin2.hasNext())
        {
            row2 = sin2.nextLine();
            String[] tokens = row2.split(",");
            if(checkpresent("birddata",tokens[0],"birdId",db)==false)
            {   try
            {
                db.Establish();
                String query="insert into birddata values("+tokens[0]+","+tokens[1]+","+tokens[2]+","+tokens[3]+","+tokens[4]+","+tokens[5]+","+Float.parseFloat(tokens[6])+");";
                db.setQuery(query);
                int numofrowseffected=db.Update();

            }
            catch(Exception e)
            {     e.printStackTrace();
                System.out.println("Unsuccesful Population of tables");
            }
            }

        }



        try
        {

            fr3=new FileReader("src/birdlifesanccsvfiles/sancturies.csv");
            sin3 = new Scanner(fr3);
        }

        catch (FileNotFoundException e)
        {
            System.out.println("File not Linked .....3");
            e.printStackTrace();
        }

        sin3.nextLine();

        while (sin3.hasNext())
        {
            row3 = sin3.nextLine();
            String[] tokens = row3.split(",");
            if(checkpresent("sancturies",tokens[0],"sid",db)==false)
            {   try
            {
                db.Establish();
                String query="insert into sancturies values("+tokens[0]+","+tokens[1]+","+Float.parseFloat(tokens[2])+","+Float.parseFloat(tokens[3])+","+Float.parseFloat(tokens[3])+");";
                db.setQuery(query);
                int numofrowseffected=db.Update();

            }
            catch(Exception e)
            {    e.printStackTrace();
                System.out.println("Unsuccesful Population of tables");
            }

            }
        }


        try
        {
            fr4=new FileReader("src/birdlifesanccsvfiles/birdseason.csv");
            sin4 = new Scanner(fr4);
        }

        catch (FileNotFoundException e)
        {
            System.out.println("File not Linked .....4");
            e.printStackTrace();
        }

        sin4.nextLine();

        while (sin4.hasNext())
        {
            row4 = sin4.nextLine();
            String[] tokens = row4.split(",");
            try
            {
                db.Establish();
                String query="insert into birdseason values("+tokens[0]+","+tokens[1]+");";
                db.setQuery(query);
                int numofrowseffected=db.Update();

            }
            catch(Exception e)
            {   e.printStackTrace();
                System.out.println("Unsuccesful Population of tables");
            }
        }

    }



    public  boolean checkpresent(String tablename,String id,String colname,Database db)
    {
        int k=1000;
        try
        {
            db.Establish();
            db.setQuery("select count(*) from "+tablename+" where "+colname+"="+id);
            ResultSet rs=db.Execute();
            rs.next();
            k=rs.getInt(1);

        }

        catch(Exception e)
        {
            e.printStackTrace();
            System.out.println("Unsuccessful connection....11");
        }

        if(k==0)
            return false;
        else
            return true;
    }



    public void bulkinsertionupdation()
    {
        FileReader fr1;
        FileReader fr2;
        FileReader fr3;

        Database db =new Database();

        Scanner sin1=null;
        Scanner sin2=null;
        Scanner sin3=null;

        try
        {
            fr1 = new FileReader("src/birdlifesanccsvfiles/sancallemployees.csv");
            sin1 = new Scanner(fr1);
        }

        catch (FileNotFoundException e)
        {
            System.out.println("File not Linked .....");
            e.printStackTrace();
        }

        sin1.nextLine();
        String row1,row2,row3,row4;

        while (sin1.hasNext())
        {
            row1 = sin1.nextLine();
            String[] tokens = row1.split(",");

            if(checkpresent("sancallemployees",tokens[0],"empid",db)==false)
            {  try
            {
                db.Establish();
                String query="insert into sancallemployees values("+tokens[0]+","+tokens[1]+","+tokens[2]+","+Float.parseFloat(tokens[3])+","+Float.parseFloat(tokens[4])+","+tokens[5]+","+tokens[6]+");";

                db.setQuery(query);
                int numofrowseffected=db.Update();

            }

            catch(Exception e)
            {     e.printStackTrace();
                System.out.println("Unsuccesful Population of tables");
            }
            }

            else if(checkpresent("sancallemployees",tokens[0],"empid",db)==true)
            {  try
            {
                db.Establish();
                String query="update sancallemployees set empid="+tokens[0]+",name="+tokens[1]+",gender="+tokens[2]+",age="+tokens[3]+",salary="+tokens[4]+",profession="+tokens[5]+",sanctuary="+tokens[6]+"where empid="+tokens[0]+";";

                db.setQuery(query);
                int numofrowseffected=db.Update();

            }

            catch(Exception e)
            {
                System.out.println("Unsuccesful Updation of tables....");
            }

            }

        }


        try
        {
            fr2=new FileReader("src/birdlifesanccsvfiles/birddata.csv");
            sin2 = new Scanner(fr2);
        }
        catch (FileNotFoundException e)
        {
            System.out.println("File not Linked .....");
            e.printStackTrace();
        }

        sin2.nextLine();

        while (sin2.hasNext())
        {
            row2 = sin2.nextLine();
            String[] tokens = row2.split(",");
            if(checkpresent("birddata",tokens[0],"birdId",db)==false)
            {  try
            {
                db.Establish();
                String query="insert into birddata values("+tokens[0]+","+tokens[1]+","+tokens[2]+","+tokens[3]+","+tokens[4]+","+tokens[5]+","+Float.parseFloat(tokens[6])+");";
                db.setQuery(query);
                int numofrowseffected=db.Update();

            }
            catch(Exception e)
            {     e.printStackTrace();
                System.out.println("Unsuccesful Population of tables");
            }
            }

            else if(checkpresent("birddata",tokens[0],"birdId",db)==true)
            {  try
            {
                db.Establish();
                String query="update birddata set birdId="+tokens[0]+",birdbreed="+tokens[1]+",birdgender="+tokens[2]+",birddob="+tokens[3]+",typeof="+tokens[4]+",birdloc="+tokens[5]+",count="+tokens[6]+"where birdId="+tokens[0]+";";

                db.setQuery(query);
                int numofrowseffected=db.Update();

            }

            catch(Exception e)
            {
                System.out.println("Unsuccesful Updatation of tables....");
            }
            }

        }



        try
        {
            fr3=new FileReader("src/birdlifesanccsvfiles/sancturies.csv");
            sin3 = new Scanner(fr3);
        }

        catch (FileNotFoundException e)
        {
            System.out.println("File not Linked .....");
            e.printStackTrace();
        }

        sin3.nextLine();

        while (sin3.hasNext())
        {
            row3 = sin3.nextLine();
            String[] tokens = row3.split(",");
            if(checkpresent("sancturies",tokens[0],"sid",db)==false)
            {  try
            {
                db.Establish();
                String query="insert into sancturies values("+tokens[0]+","+tokens[1]+","+Float.parseFloat(tokens[2])+","+Float.parseFloat(tokens[3])+","+Float.parseFloat(tokens[3])+");";
                db.setQuery(query);
                int numofrowseffected=db.Update();

            }
            catch(Exception e)
            {    e.printStackTrace();
                System.out.println("Unsuccesful Population of tables");
            }
            }

            else if(checkpresent("sancturies",tokens[0],"sid",db)==true)
            {
                try
                {
                    db.Establish();
                    String query="update sancturies set sid="+tokens[0]+",sname="+tokens[1]+",area="+tokens[2]+",establishyr="+tokens[3]+",meanppt="+tokens[4]+"where sid="+tokens[0]+";";

                    db.setQuery(query);
                    int numofrowseffected=db.Update();

                }

                catch(Exception e)
                {
                    System.out.println("Unsuccesful Updatation of tables....");
                }
            }

        }

    }


    public void truncatetables()
    {   String query1="truncate table sancallemployees";
        String query2="truncate table birddata";
        String query3="truncate table sancturies";
        String query4="truncate table birdseason";
        Database db=new Database();
        try
        {
            db.Establish();
            db.setQuery(query1);
            int numrowseffected1=db.Update();

            db.setQuery(query2);
            int numrowseffected2=db.Update();

            db.setQuery(query3);
            int numrowseffected3=db.Update();

            db.setQuery(query4);
            int numrowseffected4=db.Update();
        }
        catch(Exception e)
        {
            System.out.println("Unsuccesful truncation....");
        }

    }


    public void operations(int choice,allbirdsancemployees e,String loginid) throws Exception
    {

        switch(choice)
        {   case 1 : {   // send msg to doctor
            sendmsgtoaperson(e,loginid,"Doctor");
            break;
        }

            case 2 :  {  //send msg to director
                sendmsgtoaperson(e,loginid,"Director");
                break;
            }

            case 3 :   {  //send msg to manager
                sendmsgtoaperson(e, loginid,"Manager");
                break;
            }

            case 4 :   {  //send msg to caretaker
                sendmsgtoaperson(e, loginid,"Care Taker");
                break;
            }

            case 5 :  {  // show inbox's by using empid
                e.showInbox(loginid);
                break;
            }

            case 6 : {   // See all msg's (mark as read=0/1) of their own account
                Database db=new Database();
                db.Establish();
                String query="select count(msgsent) from allmsgs where receiversid=\""+loginid+"\"";
                db.setQuery(query);
                ResultSet rs1=db.Execute();
                rs1.next();
                int countmsg=rs1.getInt(1);
                if(countmsg==0)
                    System.out.println("No Messages Present Here...");
                else
                    e.seeallmsgs(loginid);
                break;
            }


            case 7 :    {   // get bird statistics  i.e. get count of all bird breeds present in sanc
                callbirdstatistics();
                break;
            }
            case 8 :  {   //  Current bird in sanctuaries based on todays date
                callgetbirdbyseason();
                break;
            }

            case 9 :  {   // Getting data of birds based on season entered by User
                mainfunctions mf=new mainfunctions();
                mf.getdatabyseason();
                break;
            }


            case 10: {   // check water purity and quality and send message
                mainfunctions mf=new mainfunctions();
                mf.checkpurityquality(loginid, e);
                break;
            }




            case 11 :  {        // Searching using partial strings
                Scanner sc=new Scanner(System.in);
                System.out.printf("Enter based on which you want to search:");
                mainfunctions mf=new mainfunctions();
                String searchbasedon="";
                int p=0;
                while(p==0)
                {    searchbasedon=sc.nextLine();
                    if(searchbasedon.equals("name") || searchbasedon.equals("empid") || searchbasedon.equals("gender") || searchbasedon.equals("profession") || searchbasedon.equals("sanctuary"))
                    {   mf.searchbasedon(searchbasedon);
                        p=1;
                    }
                    else
                        System.out.printf("Enter the correct feild:");
                }
                break;
            }

            case 12 :{    // searching using comparisions... maximum age and salary comparisions
                mainfunctions mf=new mainfunctions();
                System.out.println("Enter which data you want to compare with:");
                System.out.println("1.) Employee data\n2.)Bird Statistics");
                System.out.println("Enter any of above options :");
                Scanner sc=new Scanner(System.in);
                int tablechoice=0;
                int p=0;
                while(p==0)
                {   try
                {  tablechoice=sc.nextInt();
                    p=1;
                }
                catch(Exception ex)
                {   System.out.println("Enter a valid Integer:");
                }
                    sc.nextLine();
                }
                switch(tablechoice)
                {  case 1 : {  System.out.println("Enter the colomn name you want to compare:");
                    String basedon=sc.nextLine();
                    System.out.println("Enter the comparision value(e.g. year>2001):");
                    int k;
                    k=sc.nextInt();
                    System.out.println("Enter the operator to perform comparision(e.g. >/</=):");
                    sc.nextLine();
                    String operator=sc.nextLine();

                    mf.searchemployeecomparisions("sancallemployees",basedon,k,operator);
                    break;
                }

                    case 2 : {         System.out.println("Enter the comparision value(birds count value):");
                        int k;
                        k=sc.nextInt();
                        System.out.println("Enter the operator to perform comparision:");
                        sc.nextLine();
                        String operator=sc.nextLine();
                        mf.searchemployeecomparisions("birddata","count",k,operator);
                        break;
                    }
                }
                break;
            }

            case 13 : {   // sorting based on salary /age for employees
                ArrayList<allbirdsancemployees>al=new ArrayList<>();
                Database db=new Database();
                db.Establish();
                String query="select empid,name,age,salary,sanctuary from sancallemployees";
                db.setQuery(query);
                ResultSet rs=db.Execute();
                while(rs.next())
                {  al.add(new allbirdsancemployees(rs.getString(2),rs.getString(1),rs.getInt(4),rs.getString(5),rs.getInt(3)));

                }
                String ch="";
                Scanner sc=new Scanner(System.in);
                System.out.println("Sort in asc/desc:");
                int l=0;
                while(l==0)
                {     ch=sc.nextLine();
                    if(ch.equals("asc")==false && ch.equals("desc")==false)
                        System.out.println("Enter a valid String");
                    else
                        l=1;
                }
                l=0;
                System.out.println("Sort, based on name/age/salary");
                String option="";
                while(l==0)
                {      option =sc.nextLine();
                    if(option.equals("name")==false && option.equals("age")==false && option.equals("salary")==false)
                        System.out.println("Enter a valid String:");
                    else
                        l=1;
                }

                if(ch.equals("asc"))
                {
                    switch(option)
                    {   case "name" :{ Collections.sort(al,new sancaempcomparatorbasedonnameasc());
                        break;
                    }
                        case "age":{  Collections.sort(al,new sancaempcomparatorbasedonageasc());
                            break;
                        }

                        case "salary":{   Collections.sort(al,new sancaempcomparatorbasedonsalasc());
                            break;
                        }
                    }

                }

                else
                {   switch(option)
                {   case "name" :{    Collections.sort(al,new sancaempcomparatorbasedonnamedesc());
                    break;
                }
                    case "age":{  Collections.sort(al,new sancaempcomparatorbasedonagedesc());
                        break;
                    }

                    case "salary":{   Collections.sort(al,new sancaempcomparatorbasedonsaldesc());
                        break;
                    }
                }

                }
                System.out.println("Employee Details are:");
                for(int i=0;i<al.size();i++)
                {
                    System.out.printf("Name : %s\n",al.get(i).getName());
                    System.out.printf("Employee Id : %s\n",al.get(i).getId());
                    System.out.printf("Age : %d\n",al.get(i).getAge());
                    System.out.printf("Salary : %d\n",al.get(i).getSalary());
                    System.out.printf("Sanctuary : %s\n",al.get(i).getsanc());
                    System.out.println("\n");
                }
                break;
            }

            case 14: {   // updation or insertion bulk
                bulkinsertionupdation();
                System.out.println("Tables have been Updated Successfully...\n");
                break;
            }
        }
    }

    public void maininsidecode(String loginid)
    {
        //  After login of particular user using his id
        //String loginid="DOC143";
        int k=1;   // Enter choice =-1 to terminate              // flag=0 for other than user and flag=1 for user

        System.out.println("Choose Any one of Below Options...!!\n1.)Send Message to Doctor\n2.)Send Message to Director of Sanctuary\n3.)Send Message to Manager");
        System.out.println("4.)Send Message to Care Taker\n5.)Open Your Inbox...!\n6.)Open all messages..!");
        System.out.println("7.)Bird Statistics...\n8.)Types of birds based on Current Season\n9.)Types of Birds Based on Season");
        System.out.println("10.)Check Purity and Quality of Water in the Sanctuary");
        System.out.println("11.)Search Employee Data(Based on Partial Strings)\n12.)Searching Using Comparisions\n13.)Sorting of Employee data");
        System.out.println("14.)Updation/Insertion in bulk...");
        System.out.println("If you want to Exit Enter -1.....!!");

        String profession=getprofession(loginid);
        switch(profession)
        {  case "Care Taker" : {
            sanccaretaker ct=new sanccaretaker(getname(loginid),loginid);
            Scanner sc=new Scanner(System.in);

            while(k==1)
            {  try
            {  System.out.println("Enter your choice...");
                int choice=sc.nextInt();
                if(choice==-1)
                    k=0;
                else
                {
                    if(choice!=11 &&choice!=12 &&choice!=13 && choice!=14)
                        operations(choice,ct,loginid);
                    else
                        System.out.printf("The Employee with Id %s cannot perform this operation...\n",loginid);

                }


            }
            catch(Exception e)
            {
                System.out.println(e);
            }
            }
            sc.close();
            break;
        }

            case "Director" :  {   sancdirector dt=new sancdirector();
                Scanner sc=new Scanner(System.in);
                int choice;
                while(k==1)
                {
                    try
                    {   System.out.println("Enter your choice...");
                        choice=sc.nextInt();
                        if(choice==-1)
                            k=0;
                        else
                        {
                            operations(choice,dt,loginid);
                        }

                    }
                    catch(Exception e)
                    {
                        System.out.println(e);
                    }
                }
                sc.close();
                break;
            }

            case "Doctor" :  {       sancdoctor doc=new sancdoctor();
                Scanner sc=new Scanner(System.in);

                while(k==1)
                {
                    try
                    {    System.out.println("Enter your choice...");
                        int choice=sc.nextInt();
                        if(choice==-1)
                            k=0;
                        else
                        {
                            if( choice!=10 &&choice!=11 && choice!=12 && choice!=13 &&choice!=14 )
                                operations(choice,doc,loginid);
                            else
                                System.out.printf("The Employee with Id %s cannot perform this operation\n",loginid);
                        }

                    }
                    catch(Exception e)
                    {
                        System.out.println(e);
                    }
                }

                sc.close();
                break;
            }

            case "Manager"  : {     sancmanager mg=new sancmanager();
                Scanner sc=new Scanner(System.in);

                while(k==1)
                {
                    try
                    {      System.out.println("Enter your choice...");
                        int choice=sc.nextInt();
                        if(choice==-1)
                            k=0;
                        else
                        {
                            if( choice!=14 )
                                operations(choice,mg,loginid);
                            else
                                System.out.printf("The Employee with Id %s cannot perform this operation\n",loginid);


                        }

                    }
                    catch(Exception e)
                    {
                        System.out.println(e);
                    }

                }
                sc.close();
                break;
            }
        }


    }
}
