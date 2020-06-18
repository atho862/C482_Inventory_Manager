package Controllers;

import DataProvider.Inventory;
import Models.Part;
import Models.Product;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import java.net.URL;
import java.util.ResourceBundle;

public class MainController implements Initializable {

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
    void onActionAddProduct(ActionEvent event) {

    }

    @FXML
    void onActionModifyProduct(ActionEvent event) {

    }

    @FXML
    void onActionDeleteProduct(ActionEvent event) {

    }

    @FXML
    void onActionPartsSearch(ActionEvent event) {

    }

    @FXML
    void onActionAddPart(ActionEvent event) {

    }

    @FXML
    void onActionModifyPart(ActionEvent event) {

    }

    @FXML
    void onActionDeletePart(ActionEvent event) {

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
}
