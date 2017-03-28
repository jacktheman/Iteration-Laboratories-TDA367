import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * Created by aron on 2017-03-29.
 */
public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("MainPage.fxml"));

        primaryStage.setTitle("FabNotes");
        primaryStage.setScene(new Scene(root, 1233, 741));
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
