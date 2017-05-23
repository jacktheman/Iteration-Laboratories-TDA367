package com.itlabs.fabnotes.fxml.controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.FlowPane;
import org.xml.sax.SAXException;
import com.itlabs.fabnotes.service.filemanagment.FileHandler;
import java.io.IOException;

import com.itlabs.fabnotes.service.NoteSave;

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

    private static TagPageController SINGLETON;

    private List<String> tagsList;
    private String[] tagsArray;
    private File[] notes;

    public void initialize(URL url, ResourceBundle resourceBundle) {
        SINGLETON = this;
        loadTagFlowPane();
        updateNoteList();
        addListenerToSearchField();
    }

    void listNotes(File[] notes) {
        if (notes.length > 0)
            noteListView.getItems().clear();
        for (File note : notes) {
            String listItem = note.getName().replace(FileHandler.FILE_TYPE, " ");
            listItem += "  [" + (new Date(note.lastModified())).toString() + "]";
            noteListView.getItems().add(listItem);
        }
    }

    public static void updateNoteList(){
        SINGLETON.listNotes(FileHandler.listNotes());
    }

    private void addListenerToSearchField(){
       searchField.textProperty().addListener((observableValue, oldValue, newValue) -> {
           try {
                List<File> searchList =  FileHandler.searchList(newValue);
                File[] searchArray = new File[searchList.size()];
                for (int i = 0; i < searchList.size(); i++)
                    searchArray[i] = searchList.get(i);
                listNotes(searchArray);
           } catch (IOException e) {
               e.printStackTrace();
           } catch (SAXException e) {
               e.printStackTrace();
           } catch (ParserConfigurationException e) {
               e.printStackTrace();
           }
       });
    }

    public static void loadTagFlowPane() {
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
                FXMLLoader loadTag = new FXMLLoader(TagPageController.class.getResource("/com/itlabs/fabnotes/TagInCloudPane.fxml"));
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

    @FXML
    private void onMousePressedMenuItem(MouseEvent mouseEvent) {
        try {
            String fileName = noteListView.getSelectionModel().getSelectedItem();
            NoteSave noteSave = FileHandler.loadNote(new File(FileHandler.FILE_PATH +
                    fileName.substring(0, fileName.indexOf("[") - 3) + FileHandler.FILE_TYPE));
            MainPageController.getInstance().loadNoteSave(noteSave);
        } catch (SAXException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

}