package Controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import java.net.URL;
import java.util.ResourceBundle;

public class AddPartController implements Initializable {

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
    void onActionSave(ActionEvent event) {

    }

    @FXML
    void onActionCancel(ActionEvent event) {

    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }
}
