import java.util.Objects;

public abstract class Item implements Comparable<Item>{
    private double price;
    private int invQuantity;
    private int soldQuantity;
    private int currentCartQuan;

    public Item(double price, int invQuantity){
        this.price = price;
        this.invQuantity = invQuantity;
        this.soldQuantity = 0;
        this.currentCartQuan = 0;
    }

    //Returns the total revenue (price * amount) if at least 'amount' items are in stock
    //Return 0 otherwise (i.e., no sale is completed)
    public double sellUnits(int amount){
        if( amount > 0 && invQuantity >= amount){
            invQuantity -= amount;
            soldQuantity += amount;
        }
        return 0;
    }

    @Override
    public boolean equals(Object o) {

        if (o == null || getClass() != o.getClass()) return false;

        if(this instanceof Vehicle && o instanceof Vehicle){
            return ((Vehicle) this).getMake().equals(((Vehicle) o).getMake()) &&
                    ((Vehicle) this).getModel().equals(((Vehicle) o).getModel());
        }

        if(this instanceof Tire && o instanceof Tire ){
            return ((Tire) this).wheelDiameter == ((Tire)o).wheelDiameter &&
                    ((Tire) this).sectionWidth == ((Tire) o).sectionWidth;
        }
        return false;
    }

    @Override
    public int hashCode() {
        if(this instanceof Vehicle){
            return Objects.hash(((Vehicle) this).getMake(), ((Vehicle) this).getModel());
        }

        if(this instanceof Tire){
            return Objects.hash(((Tire) this).wheelDiameter,((Tire) this).sectionWidth);
        }
        return 0;
    }

    @Override
    public int compareTo(Item o) {
        int result = o.getSoldQuantity() - this.getSoldQuantity(); // descending
        if (result != 0) return result;
        return this.toString().compareTo(o.toString()); // break ties
    }

    public double getPrice(){return price;}
    public int getInvQuantity(){return invQuantity;}
    public int getSoldQuantity(){return soldQuantity;}

    public void setInvQuantity(int invQuantity) {
        this.invQuantity = invQuantity;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public void setSoldQuantity(int soldQuantity) {
        this.soldQuantity = soldQuantity;
    }


    public int getCurrentCartQuan() {
        return currentCartQuan;
    }

    public void setCurrentCartQuan(int currentCartQuan) {
        this.currentCartQuan = currentCartQuan;
    }
}
