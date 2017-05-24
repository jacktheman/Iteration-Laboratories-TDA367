package com.itlabs.fabnotes.fxml.controller;

import com.itlabs.fabnotes.fxml.service.NoteBridge;
import com.itlabs.fabnotes.fxml.service.SavedNoteBridge;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;
import java.io.File;
import java.io.IOException;
import java.util.List;

/**
 * Created by alexandra on 2017-05-17.
 */
public class TagInCloudPaneController {


    @FXML
    private Label tagTextInCloud;

    @FXML
    private Button removeTagInCloudButton;

    @FXML
    void removeTagInCloud(ActionEvent event) {
        String tagToRemove = tagTextInCloud.getText();
        try {
            SavedNoteBridge.removeTagsFromTagList(tagToRemove);
            TagPageController.getInstance().getTagFlowPane().getChildren().clear();
            TagPageController.loadTagFlowPane();
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            List<File> filesList = SavedNoteBridge.getNotesWithTag(tagToRemove);
            File[] filesArray = new File[filesList.size()];
            filesList.toArray(filesArray);
            for (int i = 0; i < filesArray.length; i++) {
                SavedNoteBridge savedNoteBridge = SavedNoteBridge.loadSavedNote(filesArray[i]);
                savedNoteBridge.getTags().remove(tagToRemove);
                savedNoteBridge.save();
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        } catch (SAXException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (TransformerException e) {
            e.printStackTrace();
        }
        if (NoteBridge.getNoteTags().contains(tagToRemove.toLowerCase())) {
            NoteBridge.removeNoteTag(tagTextInCloud.getText().toLowerCase());
            MainPageController.getInstance().loadNoteTagsInTagBar(NoteBridge.getNoteTags());
        }
    }

}