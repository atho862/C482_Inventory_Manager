package Models;

public abstract class Part {
    private int id;
    private String name;
    private double price;
    private int stock;
    private int min;
    private int max;

    public Part(int partId, String partName, double partPrice, int partStock, int partMin, int partMax){
        this.id = partId;
        this.name = partName;
        this.price = partPrice;
        this.stock = partStock;
        this.min = partMin;
        this.max = partMax;
    }

    public void setId(int newId){
        this.id = newId;
    }

    public void setName(String newName){
        this.name = newName;
    }

    public void setPrice(double newPrice){
        this.price = newPrice;
    }

    public void setStock(int newStock){
        this.stock = newStock;
    }

    public void setMin(int newMin){
        this.min = newMin;
    }

    public void setMax(int newMax){
        this.max = newMax;
    }

    public int getId(){
        return this.id;
    }

    public String getName(){
        return this.name;
    }

    public double getPrice(){
        return this.price;
    }

    public int getStock(){
        return this.stock;
    }

    public int getMin(){
        return this.min;
    }

    public int getMax(){
        return this.max;
    }

}
