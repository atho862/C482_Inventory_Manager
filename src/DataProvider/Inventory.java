package DataProvider;

import Models.Part;
import Models.Product;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class Inventory {
    private static ObservableList<Part> allParts = FXCollections.observableArrayList();
    private static ObservableList<Product> allProducts = FXCollections.observableArrayList();

    public static void addPart(Part newPart){
        allParts.add(newPart);
    }

    public static void addProduct(Product newProduct){
        allProducts.add(newProduct);
    }
    
    public static Part lookupPart(int id) {
        for (Part part : allParts
             ) {
            if (part.getId() == id){
                return part;
            }
            else{
                //Do Nothing
            }
        }
        return null;
    }

    private static Product lookupProduct(int productId) {
        for (Product product : allProducts
        ) {
            if (product.getId() == productId) {
                return product;
            } else {
                //Do nothing
            }
        }
        //If we get here, the product doesn't exist so we should return null
        return null;
    }

    public static Part lookupPart(String partName){
        for (Part part : allParts
             ) {
            if (part.getName() == partName){
                return part;
            }
            else {
                //Do nothing
            }
        }

        return null;
    }

    public static Product lookupProduct(String productName){
        for (Product product : allProducts
             ) {
            if (product.getName() == productName){
                return product;
            }
            else {
                //Do nothing
            }
        }

        return null;
    }

    public static void updatePart(int index, Part selectedPart){
        allParts.set(index, selectedPart);
    }

    public static void updateProduct(int index, Product selectedProduct){
        allProducts.set(index, selectedProduct);
    }

    public static boolean deletePart(Part selectedPart){
        for (Part part : allParts
             ) {
            if (part.getId() == selectedPart.getId()) {
                boolean isDeleted = allParts.remove(part);
                return isDeleted;
            }
        }

        return false;
    }

    public static boolean deleteProduct(Product selectedProduct){
        for (Product product : allProducts
             ) {
            if (product.getId() == selectedProduct.getId()) {
                boolean isDeleted = allProducts.remove(product);
                return isDeleted;
            }
        }

        return false;
    }

    public static ObservableList<Part> getAllParts(){
        return allParts;
    }

    public static ObservableList<Product> getAllProducts(){
        return allProducts;
    }

    private static Part getPartByIndex(int index){
        Part part = allParts.get(index);

        return part;
    }

    private static Product getProductByIndex(int index){
        Product product = allProducts.get(index);

        return product;
    }

    public static int getPartIndex(int partId){

        int index = -1;
        for (Part part : allParts) {
            index++;
            if (part.getId() == partId){
                return index;
            }
            else {
                //Continue looping
            }
        }
        //If we don't get an index when looping, return -1 to indicate the part doesn't exist
        return -1;
    }

    public static int getProductIndex(int productId){

        int index = -1;
        for (Product product : allProducts) {
            index++;
            if (product.getId() == productId){
                return index;
            }
            else {
                //Do nothing and continue looping
            }
        }
        //If we don't get an index when looping, return -1 to indicate the part doesn't exist
        return -1;
    }
}
