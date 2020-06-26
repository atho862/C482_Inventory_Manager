package Controllers;

import DataProvider.Inventory;
import Models.Part;
import Models.Product;
import Utilities.SearchUtility;
import javafx.collections.FXCollections;
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

import javax.swing.text.NumberFormatter;
import java.io.IOException;
import java.net.URL;
import java.text.NumberFormat;
import java.util.Locale;
import java.util.ResourceBundle;

public class ModifyProductController implements Initializable {
    Stage stage;
    Parent root;
    ObservableList<Part> associatedParts;
    ObservableList<Part> filteredPartList;

    @FXML
    private TextField txtMin;

    @FXML
    private TableColumn<Part, Double> tblColumnAddPrice;

    @FXML
    private TextField txtName;

    @FXML
    private TableColumn<Part, String> tblColumnAddPartName;

    @FXML
    private Label lblId;

    @FXML
    private Label lblName;

    @FXML
    private Button btnDelete;

    @FXML
    private TableView<Part> tblViewAssociatedParts;

    @FXML
    private TextField txtMax;

    @FXML
    private TableColumn<Part, Double> tblColumnPrice;

    @FXML
    private Label lblMin;

    @FXML
    private TableColumn<Part, Integer> tblColumnPartId;

    @FXML
    private TableColumn<Part, Integer> tblColumnInventory;

    @FXML
    private Button btnSearch;

    @FXML
    private TableColumn<Part, Integer> tblColumnAddInventory;

    @FXML
    private TableColumn<Part, Integer> tblColumnAddPartId;

    @FXML
    private Label lblMax;

    @FXML
    private TableView<Part> tblViewAddAssociatedPart;

    @FXML
    private TextField txtInventory;

    @FXML
    private TextField txtId;

    @FXML
    private Button btnCancel;

    @FXML
    private Button btnSave;

    @FXML
    private Button btnAdd;

    @FXML
    private TableColumn<Part, String> tblColumnPartName;

    @FXML
    private TextField txtSearch;

    @FXML
    private Label lblInventory;

    @FXML
    private Label lblPrice;

    @FXML
    private TextField txtPrice;

    @FXML
    void onActionSaveBtn(ActionEvent event) throws IOException {
        int inventory = Integer.valueOf(txtInventory.getText());
        int min = Integer.valueOf(txtMin.getText());
        int max = Integer.valueOf(txtMax.getText());

        if (inventory < min) {
            new Alert(Alert.AlertType.ERROR, "Inventory level must be greater than Min").showAndWait();
            return;
        }
        if (inventory > max) {
            new Alert(Alert.AlertType.ERROR, "Inventory level must be less than Max").showAndWait();
            return;
        }
        if (associatedParts.size() == 0) {
            new Alert(Alert.AlertType.ERROR, "A product must have at least one associated part").showAndWait();
            return;
        }

        Product product = new Product(Integer.valueOf(txtId.getText()),
                txtName.getText(),
                Double.valueOf(txtPrice.getText()),
                inventory,
                min,
                max,
                associatedParts);
        int index = Inventory.getProductIndex(product.getId());
        Inventory.updateProduct(index, product);

        stage = (Stage)((Button)event.getSource()).getScene().getWindow();
        root = FXMLLoader.load(getClass().getResource("/Views/Main.fxml"));
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    void onActionCancelBtn(ActionEvent event) throws IOException {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Are you sure you want to leave this page? Any unsaved changes will be lost!", ButtonType.YES, ButtonType.NO);
        alert.showAndWait();

        if (alert.getResult() == ButtonType.YES) {
            stage = (Stage)((Button)event.getSource()).getScene().getWindow();
            root = FXMLLoader.load(getClass().getResource("/Views/Main.fxml"));
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        }
        else {
            return;
        }
    }

    @FXML
    void onActionSearchBtn(ActionEvent event) {
        String searchString = txtSearch.getText();
        if (SearchUtility.isStringNumeric(searchString)) {
            Part part = Inventory.lookupPart(Integer.parseInt(searchString));
            if (part == null) {
                new Alert(Alert.AlertType.INFORMATION, String.format("Could not find part with Id %s.", searchString)).showAndWait();
                return;
            }
            filteredPartList = FXCollections.observableArrayList();
            filteredPartList.add(part);
            tblViewAddAssociatedPart.setItems(filteredPartList);
        }
        else {
            Part part = Inventory.lookupPart(searchString);
            if (part == null) {
                new Alert(Alert.AlertType.INFORMATION, String.format("Could not find part with name %s.", searchString)).showAndWait();
                return;
            }
            filteredPartList = FXCollections.observableArrayList();
            filteredPartList.add(part);
            tblViewAddAssociatedPart.setItems(filteredPartList);
        }
    }

    @FXML
    void onActionAddBtn(ActionEvent event) {
        Part partToAdd = tblViewAddAssociatedPart.getSelectionModel().getSelectedItem();
        if (associatedParts.contains(partToAdd)){
            new Alert(Alert.AlertType.ERROR, "This part is already associated with the current product").showAndWait();
            return;
        }
        associatedParts.add(partToAdd);
        tblViewAssociatedParts.setItems(associatedParts);
    }

    @FXML
    void onActionDeleteBtn(ActionEvent event) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Are you sure you want to remove this assoicated part?", ButtonType.YES, ButtonType.NO);
        alert.showAndWait();

        if (alert.getResult() == ButtonType.YES) {
            Part partToDelete = tblViewAssociatedParts.getSelectionModel().getSelectedItem();
            associatedParts.remove(partToDelete);
        }
        else {
            return;
        }
    }

    public void sendProduct(Product product){

        txtId.setText(String.valueOf(product.getId()));
        txtName.setText(product.getName());
        txtInventory.setText(String.valueOf(product.getStock()));
        txtPrice.setText(String.valueOf(product.getPrice()));
        txtMin.setText(String.valueOf(product.getMin()));
        txtMax.setText(String.valueOf(product.getMax()));
        associatedParts = FXCollections.observableArrayList();
        for (Part part : product.getAssociatedParts()
             ) {
            associatedParts.add(part);
        }
        tblViewAssociatedParts.setItems(associatedParts);
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        initializeAddAssociatedPartsTable();
        initializeAssociatedPartsTable();
    }

    private void initializeAddAssociatedPartsTable(){
        tblViewAddAssociatedPart.setItems(Inventory.getAllParts());
        tblColumnAddPartId.setCellValueFactory(new PropertyValueFactory<>("id"));
        tblColumnAddPartName.setCellValueFactory(new PropertyValueFactory<>("name"));
        tblColumnAddInventory.setCellValueFactory(new PropertyValueFactory<>("stock"));
        tblColumnAddPrice.setCellValueFactory(new PropertyValueFactory<>("price"));
        NumberFormat currencyFormat = NumberFormat.getCurrencyInstance();
        tblColumnAddPrice.setCellFactory(tc -> new TableCell<Part, Double>(){
            @Override
            protected void updateItem(Double price, boolean empty) {
                super.updateItem(price, empty);
                if (empty){
                    setText(null);
                }
                else {
                    setText(currencyFormat.format(price));
                }
            }
        });
    }

    private void initializeAssociatedPartsTable(){
        associatedParts = tblViewAssociatedParts.getItems();
        tblColumnPartId.setCellValueFactory(new PropertyValueFactory<>("id"));
        tblColumnPartName.setCellValueFactory(new PropertyValueFactory<>("name"));
        tblColumnInventory.setCellValueFactory(new PropertyValueFactory<>("stock"));
        tblColumnPrice.setCellValueFactory(new PropertyValueFactory<>("price"));

        NumberFormat currencyFormat = NumberFormat.getCurrencyInstance();
        tblColumnPrice.setCellFactory(tc -> new TableCell<Part, Double>(){
            @Override
            protected void updateItem(Double price, boolean empty) {
                super.updateItem(price, empty);
                if (empty){
                    setText(null);
                }
                else {
                    setText(currencyFormat.format(price));
                }
            }
        });
    }
}
