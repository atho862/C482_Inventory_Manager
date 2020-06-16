package Controllers;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;

public class MainController {

    @FXML
    private TableView<?> tblProducts;

    @FXML
    private Button btnProductsModify;

    @FXML
    private Label lblProducts;

    @FXML
    private Button btnProductsAdd;

    @FXML
    private Label lblParts;

    @FXML
    private TableView<?> tblParts;

    @FXML
    private Button btnPartsAdd;

    @FXML
    private Button btnProductsSearch;

    @FXML
    private Button btnPartsDelete;

    @FXML
    private Button btnProductsDelete;

    @FXML
    private Button btnSearchParts;

    @FXML
    private TextField txtProductsSearch;

    @FXML
    private TextField txtSearchParts;

    @FXML
    private Button btnPartsModify;

    @FXML
    private Button btnExit;
}
