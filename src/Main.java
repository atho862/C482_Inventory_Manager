import DataProvider.Inventory;
import Models.InHouse;
import Models.Part;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {

@Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("/Views/Main.fxml"));
        Scene scene = new Scene(root);
        primaryStage.setTitle("Inventory Manager");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {

        InHouse partOne = new InHouse(1, "Test Part", 50.00, 5, 1, 5, 1);
        InHouse partTwo = new InHouse(2, "Test Part 1", 75.00, 10, 1, 10, 2);
        Inventory.addPart(partOne);
        Inventory.addPart(partTwo);

        launch(args);
    }
}
