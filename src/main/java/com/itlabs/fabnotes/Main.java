package com.itlabs.fabnotes;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import com.itlabs.fabnotes.service.filemanagment.FileHandler;

import java.io.File;

/**
 * Created by aron on 2017-03-29.
 */
public class Main extends Application {



    @Override
    public void start(Stage primaryStage) throws Exception {
        File customDir = new File(FileHandler.FILE_DIR);
        if (!customDir.exists()) {
            if (!customDir.mkdirs()) {
                System.err.println(customDir + " didn't get created");
            }
        }
        File tagList = new File(FileHandler.TAG_LIST);
        if (!tagList.exists()) {
            if (!tagList.createNewFile()) {
                System.err.println(tagList + " didn't get created");
            }
        }



        Parent root = FXMLLoader.load(getClass().getResource("MainPage.fxml"));

        primaryStage.setTitle("FabNotes");
        //primaryStage.getIcons().add(new Image(getClass().getResource("Fab32x32.png").toString()));
        primaryStage.setScene(new Scene(root, 1233, 741));
        primaryStage.show();

    }

    public static void main(String[] args) {
        launch(args);
    }
}
