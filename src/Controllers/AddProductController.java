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
import java.io.IOException;
import java.net.URL;
import java.text.NumberFormat;
import java.util.ResourceBundle;

public class AddProductController implements Initializable {

    private ObservableList<Part> associatedParts;
    private ObservableList<Part> filteredParts;
    private Stage stage;
    private Parent root;

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
        Product product = new Product(Integer.valueOf(txtId.getText()),
                txtName.getText(),
                Double.valueOf(txtPrice.getText()),
                Integer.valueOf(txtInventory.getText()),
                Integer.valueOf(txtMin.getText()),
                Integer.valueOf(txtMax.getText()),
                associatedParts);
        Inventory.addProduct(product);

        stage = (Stage)((Button)event.getSource()).getScene().getWindow();
        root = FXMLLoader.load(getClass().getResource("/Views/Main.fxml"));
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    void onActionCancelBtn(ActionEvent event) throws IOException {
        stage = (Stage)((Button)event.getSource()).getScene().getWindow();
        root = FXMLLoader.load(getClass().getResource("/Views/Main.fxml"));
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    void onActionSearchBtn(ActionEvent event) {
        String searchString = txtSearch.getText();
        if (SearchUtility.isStringNumeric(searchString)) {
            Part part = Inventory.lookupPart(Integer.parseInt(searchString));
            if (part == null) {
                new Alert(Alert.AlertType.INFORMATION, String.format("Could not locate part with Id %s", searchString)).showAndWait();
                return;
            }
            filteredParts = FXCollections.observableArrayList();
            filteredParts.add(part);
            tblViewAddAssociatedPart.setItems(filteredParts);
        }
        else {
            Part part = Inventory.lookupPart(searchString);
            if (part == null) {
                new Alert(Alert.AlertType.INFORMATION, String.format("Could not locate part with Name %s", searchString)).showAndWait();
                return;
            }
            filteredParts = FXCollections.observableArrayList();
            filteredParts.add(part);
            tblViewAddAssociatedPart.setItems(filteredParts);
        }
    }

    @FXML
    void onActionAddBtn(ActionEvent event) {
        Part part = tblViewAddAssociatedPart.getSelectionModel().getSelectedItem();
        if (associatedParts.contains(part)) {
            new Alert(Alert.AlertType.ERROR, "This part is already associated with the current product.").showAndWait();
            return;
        }

        associatedParts.add(part);
    }

    @FXML
    void onActionDeleteBtn(ActionEvent event) {
        Part part = tblViewAssociatedParts.getSelectionModel().getSelectedItem();
        associatedParts.remove(part);
    }

    public void sendProductId(int id){
        txtId.setText(String.valueOf(id));
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
                if (empty) {
                    setText(null);
                }
                else {
                    setText(currencyFormat.format(price));
                }
            }
        });
    }

    private void initializeAssociatedPartsTable(){
        associatedParts = FXCollections.observableArrayList();
        tblViewAssociatedParts.setItems(associatedParts);
        tblColumnPartId.setCellValueFactory(new PropertyValueFactory<>("id"));
        tblColumnPartName.setCellValueFactory(new PropertyValueFactory<>("name"));
        tblColumnInventory.setCellValueFactory(new PropertyValueFactory<>("stock"));
        tblColumnPrice.setCellValueFactory(new PropertyValueFactory<>("price"));
        NumberFormat currencyFormat = NumberFormat.getCurrencyInstance();
        tblColumnPrice.setCellFactory(tc -> new TableCell<Part, Double>(){
            @Override
            protected void updateItem(Double price, boolean empty) {
                super.updateItem(price, empty);
                if (empty) {
                    setText(null);
                }
                else {
                    setText(currencyFormat.format(price));
                }
            }
        });
    }
}
