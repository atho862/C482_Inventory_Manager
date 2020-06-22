package Controllers;

import DataProvider.Inventory;
import Models.Part;
import Models.Product;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class MainController implements Initializable {

    Stage stage;
    Parent root;

    @FXML
    private TableColumn<Part, Integer> tblColumnPartId;

    @FXML
    private TableView<Product> tblProducts;

    @FXML
    private Button btnProductsModify;

    @FXML
    private TableColumn<Part, Integer> tblColumnPartInventory;

    @FXML
    private Label lblProducts;

    @FXML
    private TableColumn<Product, Integer> tblColumnProductInventory;

    @FXML
    private Button btnProductsAdd;

    @FXML
    private Label lblParts;

    @FXML
    private TableView<Part> tblParts;

    @FXML
    private Button btnPartsAdd;

    @FXML
    private Button btnProductsSearch;

    @FXML
    private Button btnPartsDelete;

    @FXML
    private TableColumn<Part, Double> tblColumnPartPrice;

    @FXML
    private Button btnProductsDelete;

    @FXML
    private Button btnSearchParts;

    @FXML
    private TableColumn<Part, String> tblColumnPartName;

    @FXML
    private TextField txtProductsSearch;

    @FXML
    private TextField txtSearchParts;

    @FXML
    private Button btnPartsModify;

    @FXML
    private Button btnExit;

    @FXML
    private TableColumn<Product, Integer> tblColumnProductId;

    @FXML
    private TableColumn<Product, String> tblColumnProductName;

    @FXML
    private TableColumn<Product, Double> tblColumnProductPrice;

    @FXML
    void onActionSearchProduct(ActionEvent event) {

    }

    @FXML
    void onActionAddProduct(ActionEvent event) throws IOException {

        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/Views/AddProduct.fxml"));
        loader.load();

        AddProductController controller = loader.getController();
        controller.sendProductId(getNextProductId());

        stage = (Stage)((Button)event.getSource()).getScene().getWindow();
        Parent scene = loader.getRoot();
        stage.setScene(new Scene(scene));
        stage.show();
    }

    @FXML
    void onActionModifyProduct(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/Views/ModifyProduct.fxml"));
        loader.load();

        ModifyProductController controller = loader.getController();
        controller.sendProduct(tblProducts.getSelectionModel().getSelectedItem());

        stage = (Stage)((Button)event.getSource()).getScene().getWindow();
        root = loader.getRoot();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    void onActionDeleteProduct(ActionEvent event) {
        Product productToDelete = tblProducts.getSelectionModel().getSelectedItem();
        boolean isDeleted = Inventory.deleteProduct(productToDelete);
        if (isDeleted){
            tblProducts.setItems(Inventory.getAllProducts());
        }
    }

    @FXML
    void onActionPartsSearch(ActionEvent event) {

    }

    @FXML
    void onActionAddPart(ActionEvent event) throws IOException {

        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/Views/AddPart.fxml"));
        loader.load();

        AddPartController controller = loader.getController();
        controller.sendPartId(getNextPartId());

        stage = (Stage)((Button)event.getSource()).getScene().getWindow();
        Parent scene = loader.getRoot();
        stage.setScene(new Scene(scene));
        stage.show();
    }

    @FXML
    void onActionModifyPart(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/Views/ModifyPart.fxml"));
        loader.load();

        ModifyPartController controller = loader.getController();
        controller.sendPart(tblParts.getSelectionModel().getSelectedItem());

        stage = (Stage)((Button)event.getSource()).getScene().getWindow();
        root = loader.getRoot();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    void onActionDeletePart(ActionEvent event) {
        Part partToDelete = tblParts.getSelectionModel().getSelectedItem();
        boolean isDeleted = Inventory.deletePart(partToDelete);
        if (isDeleted) {
            tblParts.setItems(Inventory.getAllParts());
        }
    }

    @FXML
    void onActionExit(ActionEvent event){
        System.exit(0);
    }


@Override
    public void initialize(URL url, ResourceBundle rb){

    initializePartsTable();
    initializeProductsTable();

    }

    private void initializePartsTable(){
        tblParts.setItems(Inventory.getAllParts());
        tblColumnPartId.setCellValueFactory(new PropertyValueFactory<>("id"));
        tblColumnPartName.setCellValueFactory(new PropertyValueFactory<>("name"));
        tblColumnPartInventory.setCellValueFactory(new PropertyValueFactory<>("stock"));
        tblColumnPartPrice.setCellValueFactory(new PropertyValueFactory<>("price"));
    }

    private void initializeProductsTable(){
        tblProducts.setItems(Inventory.getAllProducts());
        tblColumnProductId.setCellValueFactory(new PropertyValueFactory<>("id"));
        tblColumnProductName.setCellValueFactory(new PropertyValueFactory<>("name"));
        tblColumnProductInventory.setCellValueFactory(new PropertyValueFactory<>("stock"));
        tblColumnProductPrice.setCellValueFactory(new PropertyValueFactory<>("price"));
    }

    private int getNextPartId(){
        int nextId = 0;
        ObservableList<Part> parts = Inventory.getAllParts();
        for (Part part : Inventory.getAllParts()
             ) {
                nextId = part.getId();
        }
        nextId++;
        return nextId;
    }

    private int getNextProductId(){
        int nextId = 0;
        ObservableList<Product> products = Inventory.getAllProducts();
        for (Product product : products
             ) {
            nextId  = product.getId();
        }
        nextId++;
        return nextId;
    }
}
