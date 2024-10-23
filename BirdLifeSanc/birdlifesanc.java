package BirdLifeSanc;

import java.util.*;
import java.util.Date;

class bird 
{   
    private String birdbreed;
    private String birdgender;
    private String birdid;
    private Date birddob;
    private  String typeof;
    private String birdloc;

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

    public bird(String breed,String g,String id,Date dob,String typof,String loc)
    {   
        setBirdbreed(breed);
        setBirddob(dob);
        setBirdgender(g);
        setBirdid(id);
        setBirdloc(loc);
        setTypeof(typof);
    }

    public bird()
    {

    }

    public String toString()
    {   return String.format("Breed: %s\nBird Id: %s\nGender: %s\nSpecies Type: %s\n Date of Birth: %s\n Bird Location: %s\n",getBirdbreed(),getBirdid(),getBirdgender(),getTypeof(),getBirddob(),getBirdloc());

    }
    
}

class sancaempcomparatorbasedonagedesc implements Comparator<allbirdsancemployees>
{   public int compare(allbirdsancemployees a1,allbirdsancemployees a2)
    {   return a2.getAge()-a1.getAge();
    }

}

class sancaempcomparatorbasedonageasc implements Comparator<allbirdsancemployees>
{   public int compare(allbirdsancemployees a1,allbirdsancemployees a2)
    {    return a1.getAge()-a2.getAge();
    }
}

class sancaempcomparatorbasedonsaldesc implements Comparator<allbirdsancemployees>
{   public int compare(allbirdsancemployees a1,allbirdsancemployees a2)
    {    return a2.getSalary()-a1.getSalary();
    }

}

class sancaempcomparatorbasedonsalasc implements Comparator<allbirdsancemployees>
{   public int compare(allbirdsancemployees a1,allbirdsancemployees a2)
    {     return a1.getSalary()-a2.getSalary();
    }
}

class sancaempcomparatorbasedonnamedesc implements Comparator<allbirdsancemployees>
{   public int compare(allbirdsancemployees a1,allbirdsancemployees a2)
    {      return a2.getName().compareTo(a1.getName());
    }
}

class sancaempcomparatorbasedonnameasc implements Comparator<allbirdsancemployees>
{    public int compare(allbirdsancemployees a1,allbirdsancemployees a2)
    {    return a1.getName().compareTo(a2.getName());
    }
}

public class birdlifesanc
{     
}
