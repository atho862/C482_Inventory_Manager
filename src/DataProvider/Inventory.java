package DataProvider;

import Models.Part;
import Models.Product;
import javafx.collections.ObservableList;

public class Inventory {
    private static ObservableList<Part> allParts;
    private static ObservableList<Product> allProducts;

    public static void addPart(Part newPart){
        allParts.add(newPart);
    }

    public static void addProduct(Product newProduct){
        allProducts.add(newProduct);
    }
    
    public static Part lookupPart(int id) {
        for (Part part : allParts
             ) {
            if (part.id == id){
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
            if (product.id == productId) {
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
            if (part.name == partName){
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
            if (product.name == productName){
                return product;
            }
            else {
                //Do nothing
            }
        }

        return null;
    }

    public static void updatePart(int index, Part selectedPart){
        Part partToUpdate = getPartByIndex(index);
        partToUpdate = selectedPart;
    }

    public static void updateProduct(int index, Product selectedProduct){
        Product productToUpdate = getProductByIndex(index);
        productToUpdate = selectedProduct;
    }

    public static boolean deletePart(Part selectedPart){
        for (Part part : allParts
             ) {
            if (part.id == selectedPart.id) {
                boolean isDeleted = allParts.remove(part);
                return isDeleted;
            }
        }

        return false;
    }

    public static boolean deleteProduct(Product selectedProduct){
        for (Product product : allProducts
             ) {
            if (product.id == selectedProduct.id) {
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
}
