import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.File;

/**
 * Created by aron on 2017-03-29.
 */
public class Main extends Application {



    @Override
    public void start(Stage primaryStage) throws Exception {
        String path = System.getProperty("user.home");
        path += File.separator + ".fabNotes";
        File customDir = new File(path);
        if (!customDir.exists()) {
            if (!customDir.mkdirs()) {
                System.err.println(customDir + " didn't get created");
            }
        }



        Parent root = FXMLLoader.load(getClass().getResource("MainPage.fxml"));

        primaryStage.setTitle("FabNotes");
        primaryStage.setScene(new Scene(root, 1233, 741));
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
