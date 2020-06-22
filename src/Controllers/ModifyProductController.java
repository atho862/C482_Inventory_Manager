package Controllers;

import Models.Part;
import Models.Product;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class ModifyProductController implements Initializable {
    Stage stage;
    Parent root;

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
    void onActionSaveBtn(ActionEvent event) {

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

    }

    @FXML
    void onActionAddBtn(ActionEvent event) {

    }

    @FXML
    void onActionDeleteBtn(ActionEvent event) {

    }

    public void sendProduct(Product product){
        txtId.setText(String.valueOf(product.getId()));
        txtName.setText(product.getName());
        txtInventory.setText(String.valueOf(product.getStock()));
        txtPrice.setText(String.valueOf(product.getPrice()));
        txtMin.setText(String.valueOf(product.getMin()));
        txtMax.setText(String.valueOf(product.getMax()));
        tblViewAssociatedParts.setItems(product.getAssociatedParts());
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }
}
