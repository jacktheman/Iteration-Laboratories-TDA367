package controllers.fxml;

import controllers.noteobject.NoteObjectControllerI;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.FlowPane;
import services.FileHandler;
import java.io.IOException;
import models.note.Note;
import services.FileHandler;
import utilities.NoteSave;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.List;
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
    private List<String> tagsList;
    private String[] tagsArray;
    private File[] notes;
    private static TagPageController SINGLETON;

    public void initialize(URL url, ResourceBundle resourceBundle) {

        SINGLETON = this;
        loadTagFlowPane();
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

    static void loadTagFlowPane () {
        try {
            SINGLETON.setTagsList(FileHandler.loadTags());
        } catch (IOException e) {
            e.printStackTrace();
        }
        String [] str = new String[SINGLETON.getTagsList().size()];
        SINGLETON.setTagsArray(str);
        SINGLETON.getTagsList().toArray(SINGLETON.getTagsArray());

        for (int i = 0; i < SINGLETON.getTagsArray().length; i++) {
            String tagText = SINGLETON.getTagsArray()[i];
            try {
                AnchorPane tag;
                FXMLLoader loadTag = new FXMLLoader(TagPageController.class.getResource("/TagPane.fxml"));
                tag = loadTag.load();
                ((Label) tag.getChildren().get(0)).setText(tagText);
                SINGLETON.getTagFlowPane().getChildren().add(tag);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    void setTagsList(List<String> list) {
        tagsList = list;
    }

    void setTagsArray (String [] str) {
        tagsArray = str;
    }

    public List<String> getTagsList () {
        return tagsList;
    }

    public static TagPageController getInstance() {
        return SINGLETON;
    }

    public FlowPane getTagFlowPane() {
        return tagFlowPane;
    }

    public String [] getTagsArray() {
        return tagsArray;
    }



}
