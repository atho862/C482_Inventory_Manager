package Controllers;

import DataProvider.Inventory;
import Models.InHouse;
import Models.Outsourced;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
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

public class AddPartController implements Initializable {

    Stage stage;
    Parent root;

    @FXML
    private RadioButton btnRadioOutsourced;

    @FXML
    private TextField txtMin;

    @FXML
    private TextField txtName;

    @FXML
    private TextField txtCost;

    @FXML
    private ToggleGroup btnRadioPartType;

    @FXML
    private Label lblMax;

    @FXML
    private TextField txtMachineId;

    @FXML
    private TextField txtInventory;

    @FXML
    private Label lblId;

    @FXML
    private TextField txtId;

    @FXML
    private Label lblMachineId;

    @FXML
    private Button btnCancel;

    @FXML
    private Label lblName;

    @FXML
    private Button btnSave;

    @FXML
    private TextField txtMax;

    @FXML
    private RadioButton btnRadioInHouse;

    @FXML
    private Label lblInventory;

    @FXML
    private Label lblPrice;

    @FXML
    private Label lblMin;

    @FXML
    void onActionSave(ActionEvent event) throws IOException {
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
        if (btnRadioInHouse.isSelected()){
            InHouse part = new InHouse(Integer.valueOf(txtId.getText()),
                    txtName.getText(),
                    Double.valueOf(txtCost.getText()),
                    inventory,
                    min,
                    max,
                    Integer.valueOf(txtMachineId.getText()));
            Inventory.addPart(part);
        }
        else {
            Outsourced part = new Outsourced(Integer.valueOf(txtId.getText()),
                    txtName.getText(),
                    Double.valueOf(txtCost.getText()),
                    inventory,
                    min,
                    max,
                    txtMachineId.getText());
            Inventory.addPart(part);
        }

        stage = (Stage)((Button)event.getSource()).getScene().getWindow();
        root = FXMLLoader.load(getClass().getResource("/Views/Main.fxml"));
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    void onActionCancel(ActionEvent event) throws IOException {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Are you sure you want to leave this page? Any unsaved data will be lost!", ButtonType.YES, ButtonType.NO);
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

    public void sendPartId(int id){
        txtId.setText(String.valueOf(id));
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        btnRadioInHouse.selectedProperty().addListener(new ChangeListener<Boolean>() {
            @Override
            public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
                if (newValue) {
                    lblMachineId.setText("Machine Id");
                    txtMachineId.setPromptText("Machine Id");
                }

            }
        });

        btnRadioOutsourced.selectedProperty().addListener(new ChangeListener<Boolean>() {
            @Override
            public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
                if (newValue)
                lblMachineId.setText("Company Name");
                txtMachineId.setPromptText("Company Name");
            }
        });
    }
}
