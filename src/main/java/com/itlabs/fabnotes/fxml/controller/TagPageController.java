package com.itlabs.fabnotes.fxml.controller;

import com.itlabs.fabnotes.fxml.service.FileHandler;
import com.itlabs.fabnotes.fxml.service.bridge.FileHandlerBridge;
import com.itlabs.fabnotes.fxml.service.bridge.SavedNoteBridge;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.FlowPane;
import org.xml.sax.SAXException;
import java.io.IOException;

import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.net.URL;
import java.util.List;
import java.sql.Date;
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

    private static final String TAG_IN_CLOUD_PANE = "/com/itlabs/fabnotes/TagInCloudPane.fxml";
    private static final String COULD_NOT_FIND_NOTE_ERROR_MESSAGE = "Could not find note";

    private static TagPageController SINGLETON;

    private List<String> tagsList;
    private String[] tagsArray;

    public void initialize(URL url, ResourceBundle resourceBundle) {
        SINGLETON = this;
        loadTagFlowPane();
        updateNoteList();
        addListenerToSearchField();
    }

    private void listNotes(List<File> notes) {
        if (notes.size() > 0)
            noteListView.getItems().clear();
        for (File note : notes) {
            String listItem = note.getName().replace(FileHandler.FILE_TYPE, " ");
            listItem += "  [" + (new Date(note.lastModified())).toString() + "]";
            noteListView.getItems().add(listItem);
        }
    }

    static void updateNoteList(){
        SINGLETON.listNotes(FileHandler.listNotes());
    }

    private void addListenerToSearchField(){
       searchField.textProperty().addListener((observableValue, oldValue, newValue) -> {
           try {
               List<File> searchList = FileHandler.searchList(newValue);
               listNotes(searchList);
           } catch (IOException e) {
               e.printStackTrace();
           } catch (SAXException e) {
               e.printStackTrace();
           } catch (ParserConfigurationException e) {
               e.printStackTrace();
           } catch (ClassNotFoundException e) {
               e.printStackTrace();
           }
       });
    }

    static void loadTagFlowPane() {
        try {
            SINGLETON.setTagsList(FileHandler.loadTags());
        } catch (IOException e) {
            e.printStackTrace();
        }
        String [] str = new String[SINGLETON.getTagsList().size()];
        SINGLETON.setTagsArray(str);
        SINGLETON.getTagsList().toArray(SINGLETON.getTagsArray());
        SINGLETON.getTagFlowPane().getChildren().clear();
        for (int i = 0; i < SINGLETON.getTagsArray().length; i++) {
            String tagText = SINGLETON.getTagsArray()[i];
            try {
                AnchorPane tag;
                FXMLLoader loadTag = new FXMLLoader(TagPageController.class.getResource(TAG_IN_CLOUD_PANE));
                tag = loadTag.load();
                ((Label) tag.getChildren().get(0)).setText(tagText);
                SINGLETON.getTagFlowPane().getChildren().add(tag);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private void setTagsList(List<String> list) {
        tagsList = list;
    }

    private void setTagsArray (String [] str) {
        tagsArray = str;
    }

    private List<String> getTagsList () {
        return tagsList;
    }

    public static TagPageController getInstance() {
        return SINGLETON;
    }

    FlowPane getTagFlowPane() {
        return tagFlowPane;
    }

    private String [] getTagsArray() {
        return tagsArray;
    }

    @FXML
    private void onMousePressedMenuItem(MouseEvent mouseEvent) {
        try {
            String fileName = noteListView.getSelectionModel().getSelectedItem();
            MainPageController.getInstance().loadNoteSave(SavedNoteBridge.loadSavedNote(new File(FileHandler.FILE_PATH +
                    fileName.substring(0, fileName.indexOf("[") - 3) + FileHandler.FILE_TYPE)));
        } catch (SAXException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (NullPointerException e) {
            System.out.println(COULD_NOT_FIND_NOTE_ERROR_MESSAGE);
        }
    }

    @FXML
    private void onKeyPressed (KeyEvent event) {
            try {
                if (event.getCode().equals(KeyCode.ENTER)) {
                    keyPressedLoad();
                } else if (event.getCode() == KeyCode.DELETE || event.getCode() == KeyCode.BACK_SPACE){
                    keyPressedDelete();
                }
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            } catch (ParserConfigurationException e) {
                e.printStackTrace();
            } catch (SAXException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            } catch (NullPointerException e) {
                System.out.println(COULD_NOT_FIND_NOTE_ERROR_MESSAGE);
            }

    }

    private void keyPressedLoad() throws ClassNotFoundException, ParserConfigurationException, SAXException, IOException {
        String fileName = noteListView.getSelectionModel().getSelectedItem();
        int fileIndex = noteListView.getSelectionModel().getSelectedIndex();
        noteListView.getFocusModel().focus(fileIndex);
        MainPageController.getInstance().loadNoteSave(SavedNoteBridge.loadSavedNote(new File(FileHandler.FILE_PATH
                + fileName.substring(0, fileName.indexOf("[") -3) + FileHandler.FILE_TYPE)));
    }

    private void keyPressedDelete() {
        String fileName = noteListView.getSelectionModel().getSelectedItem();
        FileHandler.deleteFile(new File(FileHandler.FILE_PATH
                + fileName.substring(0, fileName.indexOf("[") -3) + FileHandler.FILE_TYPE));
        updateNoteList();
    }


}
