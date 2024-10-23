package BirdLifeSanc;

import java.util.*;
public class check 
{  Random random = new Random();   
    
    public int x = random.nextInt(100); 
    public int y = random.nextInt(100); 
   
    public String puritycheck()
    {   if(x>65)
          return String.format("Water has good Purity percentage of %d\n",x);

         else
         return String.format("Water is bad, should be checked\n");

    }

    public String Qualitycheck()
    {   if(y>75)
        return String.format("Water has good Quality percentage of %d\n",y);
         
         else
         return String.format("Water is bad, should be checked\n");

    }

    public void checkavail()
    {   
        
    }
    
}
