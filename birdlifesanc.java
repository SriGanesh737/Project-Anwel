import java.util.*;
import java.time.LocalDate;
import java.time.Month;

class Date
{   private String day;
    private String month;
    private String year;
    public String getDay() 
    {
        return day;
    }
    public void setDay(String day) 
    {
        this.day = day;
    }
    public String getMonth() 
    {
        return month;
    }
    public void setMonth(String month) 
    {
        this.month = month;
    }
    public String getYear() 
    {
        return year;
    }
    public void setYear(String year) 
    {
        this.year = year;
    }
    public Date(String day, String month, String year) 
    {
        this.day = day;
        this.month = month;
        this.year = year;
    }

    public String toString() 
    {
       return String.format("%s-%s-%s",getDay(),getMonth(),getYear());
    }

    

    
    

}
class bird
{   private String birdname;
    private String birdbreed;
    private String birdgender;
    private String birdid;
    private Date birddob;
    private  String typeof;
    private String birdloc;

    public String getBirdname() 
    {
        return birdname;
    }
    public void setBirdname(String birdname) 
    {
        this.birdname = birdname;
    }
    public String getBirdbreed() 
    {
        return birdbreed;
    }
    public void setBirdbreed(String birdbreed) 
    {
        this.birdbreed = birdbreed;
    }
    public String getBirdgender() 
    {
        return birdgender;
    }
    public void setBirdgender(String birdgender) 
    {
        this.birdgender = birdgender;
    }
    public String getBirdid() 
    {
        return birdid;
    }
    public void setBirdid(String birdid) 
    {
        this.birdid = birdid;
    }
    public Date getBirddob() 
    {
        return birddob;
    }
    public void setBirddob(Date birddob) 
    {
        this.birddob = birddob;
    }
    public String getTypeof() 
    {
        return typeof;
    }
    public void setTypeof(String typeof) 
    {
        this.typeof = typeof;
    }
    public String getBirdloc() 
    {
        return birdloc;
    }
    public void setBirdloc(String birdloc) 
    {
        this.birdloc = birdloc;
    }

    public bird(String bname,String breed,String g,String id,Date dob,String typof,String loc)
    {   
        setBirdbreed(breed);
        setBirddob(dob);
        setBirdgender(g);
        setBirdid(id);
        setBirdloc(loc);
        setTypeof(typof);
        setBirdname(bname);
    }

    public bird()
    {

    }

    public String toString()
    {   return String.format("BirdName: %s\nBreed: %s\nBird Id: %s\nGender: %s\nSpecies Type: %s\n Date of Birth: %s\n Bird Location: %s\n",getBirdname(),getBirdbreed(),getBirdid(),getBirdgender(),getTypeof(),getBirddob(),getBirdloc());

    }
    
}

class birdvisitbyseason extends bird
{   private String season;

public String getSeason() 
{
    return season;
}

public void setSeason(String season) 
{
    this.season = season;
}

public birdvisitbyseason(String bname,String breed,String g,String id,Date dob,String typof,String loc,String season)
{   super(bname,breed,g,id,dob,typof,loc);
    setSeason(season);
}

public birdvisitbyseason()
{

}

public String toString()
{   return super.toString()+"Season at which they occur here:"+getSeason()+"\n";
   
}

}



class BirdStatistics
{   private int count=0;
  
public int getCount() 
{
    return count;
}

public void setCount(int count) 
{
    this.count = count;
}

public int checkcount(ArrayList<bird> b,String breed)
{   for(bird x:b)
    {  if(x.getBirdbreed()==breed)
          count+=1; 
    }

    return getCount();
}

public String toString()
{   return super.toString()+"Breed Count:"+getCount();

}



}

class check
{   Random random = new Random();   
    
    public int x = random.nextInt(100); 
    public int y = random.nextInt(100); 
   
    public void puritycheck()
    {   if(x>65)
         System.out.printf("Water has good Purity percentage of %d\n",x);

         else
         System.out.printf("Water is bad, should be checked\n");

    }

    public void Qualitycheck()
    {   if(y>75)
          System.out.printf("Water has good Quality percentage of %d\n",y);

         else
          System.out.printf("Water is bad, should be checked\n");

    }

    public void checkavail()
    {   
        
        

    }

}

public class birdlifesanc
{    public static void main(String[] args)
    {   ArrayList<bird> bl=new ArrayList<>();
        ArrayList<Date> dl=new ArrayList<>();
          dl.add(new Date("10","08","2018"));
          dl.add(new Date("12","01","2019"));
          dl.add(new Date("28","09","2022"));
          dl.add(new Date("30","12","2017"));

        bl.add(new birdvisitbyseason("Haswi","Pigeon","M","PG098",dl.get(0),"Feral","Block-A,17","Spring"));
        bl.add(new birdvisitbyseason("Swetch","Pigeon","F","PG009",dl.get(1),"Band-tailed","Block-B,12","Autumn"));
        bl.add(new birdvisitbyseason("snooper","Parrot","M","PT010",dl.get(2),"cockatoos","Block-C,27","Winter"));
        bl.add(new birdvisitbyseason("Jimy","Eagle","F","EG121",dl.get(3),"Harpy","Block-E,25","Summer"));

        BirdStatistics b=new BirdStatistics();
         System.out.println(b.checkcount(bl,"Punju"));
        for(bird x :bl)
         {
             System.out.println(x);
         }

        check c=new check();
        c.Qualitycheck(); 
        c.puritycheck();
    
    }

}



