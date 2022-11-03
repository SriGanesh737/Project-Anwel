import java.util.Iterator;

public class Food {
    private String type;
    private int quantity;

    public Food() {
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public void consumed_food(int taken, String type) {
        Iterator<Food> iter = ZooManagement.fl.iterator();
        while (iter.hasNext()) {
            Food fd = iter.next();
            // food quantity must not be neagative
            if (fd.getType().compareTo(type) == 0) {
                fd.setQuantity(fd.getQuantity() - taken);
            }

        }

    }

    public void add_food(int given, String type) {
        Iterator<Food> iter = ZooManagement.fl.iterator();
        while (iter.hasNext()) {
            Food fd = iter.next();
            if (fd.getType().compareTo(type) == 0) {
                fd.setQuantity(fd.getQuantity() + given);
            }

        }

    }

    public boolean availability(String type) 
    {
        Iterator<Food> iter = ZooManagement.fl.iterator();
        while (iter.hasNext()) 
         {
            Food fd = iter.next();
            if (fd.getType().compareTo(type) == 0) 
            {
                if (fd.getQuantity() > 10)
                    return true;
            }

        }
        return false;
    }

}
