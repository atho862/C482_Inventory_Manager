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
        if (btnRadioInHouse.isSelected()){
            InHouse part = new InHouse(Integer.valueOf(txtId.getText()),
                    txtName.getText(),
                    Double.valueOf(txtCost.getText()),
                    Integer.valueOf(txtInventory.getText()),
                    Integer.valueOf(txtMin.getText()),
                    Integer.valueOf(txtMax.getText()),
                    Integer.valueOf(txtMachineId.getText()));
            Inventory.addPart(part);
        }
        else {
            Outsourced part = new Outsourced(Integer.valueOf(txtId.getText()),
                    txtName.getText(),
                    Double.valueOf(txtCost.getText()),
                    Integer.valueOf(txtId.getText()),
                    Integer.valueOf(txtMin.getText()),
                    Integer.valueOf(txtMin.getText()),
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
        stage = (Stage)((Button)event.getSource()).getScene().getWindow();
        root = FXMLLoader.load(getClass().getResource("/Views/Main.fxml"));
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
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
