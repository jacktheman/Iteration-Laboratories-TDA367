package controllers.fxml;

import controllers.noteobject.NoteObjectControllerI;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.FlowPane;
import models.note.Note;
import services.FileHandler;
import utilities.NoteSave;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

/**
 * Created by aron on 2017-03-31.
 */
public class TagPageController implements Initializable {

    @FXML
    private TextField searchField;
    @FXML
    private ListView<String> noteListView;
    @FXML
    private FlowPane tagFlowPane;

    private String [] tags;

    private File[] notes;

    public void initialize(URL url, ResourceBundle resourceBundle) {
        tags = Note.getCurrentNote().getTags().split(".");
        FXMLLoader loadTags = new FXMLLoader(getClass().getResource("/TagPane.fxml"));

        listNotes();
    }

    private void listNotes() {
        notes = FileHandler.listNotes();
        for (File note : notes) {
            String listItem = note.getName().replace(FileHandler.FILE_TYPE, " ");
            listItem += "  [" + (new Date(note.lastModified())).toString() + "]";
            noteListView.getItems().add(listItem);
        }
    }

    @FXML
    private void onMousePressedMenuItem(MouseEvent mouseEvent) {
        try {
            String fileName = noteListView.getSelectionModel().getSelectedItem();
            NoteSave noteSave = FileHandler.loadNote(new File(FileHandler.FILE_PATH +
                    fileName.substring(0, fileName.indexOf("[") - 3) + FileHandler.FILE_TYPE));
            MainPageController.loadNoteSave(noteSave);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

    }
}