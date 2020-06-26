package Controllers;

import DataProvider.Inventory;
import Models.InHouse;
import Models.Outsourced;
import Models.Part;
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
import java.text.NumberFormat;
import java.util.Locale;
import java.util.ResourceBundle;

public class ModifyPartController implements Initializable {

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
        if (inventory < min){
            new Alert(Alert.AlertType.ERROR, "Inventory level must be greater than Min").showAndWait();
            return;
        }
        if (inventory > max) {
            new Alert(Alert.AlertType.ERROR, "Inventory level must be less than Max").showAndWait();
            return;
        }

        if(btnRadioInHouse.isSelected()){
            InHouse part = new InHouse(Integer.valueOf(txtId.getText()),
                    txtName.getText(),
                    Double.valueOf(txtCost.getText()),
                    inventory,
                    min,
                    max,
                    Integer.valueOf(txtMachineId.getText()));
            int index = Inventory.getPartIndex(Integer.valueOf(txtId.getText()));
            Inventory.updatePart(index, part);
        }
        else {
            Outsourced part = new Outsourced(Integer.valueOf(txtId.getText()),
                    txtName.getText(),
                    Double.valueOf(txtCost.getText()),
                    inventory,
                    min,
                    min,
                    txtMachineId.getText());
            int index = Inventory.getPartIndex(Integer.valueOf(txtId.getText()));
            Inventory.updatePart(index, part);
        }
        stage = (Stage)((Button)event.getSource()).getScene().getWindow();
        root = FXMLLoader.load(getClass().getResource("/Views/Main.fxml"));
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    void onActionCancel(ActionEvent event) throws IOException {
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

    public void sendPart(Part part){
        txtId.setText(String.valueOf(part.getId()));
        txtCost.setText(String.valueOf(part.getPrice()));
        txtInventory.setText(String.valueOf(part.getStock()));
        txtMax.setText(String.valueOf(part.getMax()));
        txtMin.setText(String.valueOf(part.getMin()));
        txtName.setText(part.getName());
        if (part instanceof InHouse){
            txtMachineId.setText(String.valueOf(((InHouse) part).getMachineId()));
            btnRadioInHouse.setSelected(true);
        }
        else {
            lblMachineId.setText("Company Name");
            txtMachineId.setText(((Outsourced) part).getCompanyName());
            btnRadioOutsourced.setSelected(true);
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        btnRadioInHouse.selectedProperty().addListener(new ChangeListener<Boolean>() {
            @Override
            public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
                if (newValue) {
                    lblMachineId.setText("Machine Id");
                }

            }
        });

        btnRadioOutsourced.selectedProperty().addListener(new ChangeListener<Boolean>() {
            @Override
            public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
                if (newValue)
                    lblMachineId.setText("Company Name");
            }
        });
    }
}
