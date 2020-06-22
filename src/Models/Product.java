package Models;

import javafx.collections.ObservableList;

public class Product {
    private int id;
    private String name;
    private double price;
    private int stock;
    private int min;
    private int max;
    private ObservableList<Part> associatedParts;

    public Product(int productId, String productName, double productPrice, int productStock,int productMin, int productMax, ObservableList<Part> productAssociatedParts){
        this.id = productId;
        this.name = productName;
        this.price = productPrice;
        this.stock = productStock;
        this.min = productMin;
        this.max = productMax;
        this.associatedParts = productAssociatedParts;
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

    public void setStock(int stock){ this.stock = stock; }

    public void setMin(int newMin){
        this.min = newMin;
    }

    public void setMax(int newMax){
        this.max = newMax;
    }

    public void addAssociatedPart(Part part){
        this.associatedParts.add(part);
    }

    public void deleteAssociatedPart(Part part){
        this.associatedParts.remove(part);
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

    public int getStock() { return this.stock; }

    public int getMin(){
        return this.min;
    }

    public int getMax(){
        return this.max;
    }

    public ObservableList<Part> getAssociatedParts(){
        return this.associatedParts;
    }
}
