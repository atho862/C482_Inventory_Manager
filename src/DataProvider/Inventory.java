package DataProvider;

import Models.Part;
import Models.Product;
import javafx.collections.ObservableList;

public class Inventory {
    private ObservableList<Part> allParts;
    private ObservableList<Product> allProducts;

    private void addPart(Part newPart){
        allParts.add(newPart);

    }

    private void addProduct(Product newProduct){
        allProducts.add(newProduct);
    }
    
    private Part lookupPart(int id) {
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

    private Product lookupProduct(int productId) {
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
}
