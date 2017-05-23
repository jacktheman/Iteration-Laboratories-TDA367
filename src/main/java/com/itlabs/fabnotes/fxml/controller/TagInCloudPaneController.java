package com.itlabs.fabnotes.fxml.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import com.itlabs.fabnotes.note.model.Note;
import org.xml.sax.SAXException;
import com.itlabs.fabnotes.save.NoteSave;
import com.itlabs.fabnotes.fxml.service.FileHandler;

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
            FileHandler.removeTagFromTagList(tagToRemove);
            TagPageController.getInstance().getTagFlowPane().getChildren().clear();
            TagPageController.loadTagFlowPane();
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            List<File> filesList = FileHandler.tagList(tagToRemove);
            File[] filesArray = new File[filesList.size()];
            filesList.toArray(filesArray);
            for (int i = 0; i < filesArray.length; i++) {
                NoteSave note = FileHandler.loadNote(filesArray[i]);
                note.getTags().remove(tagToRemove);
                FileHandler.saveNote(note);
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
        if (Note.getCurrentNote().getTags().contains(tagToRemove.toLowerCase())) {
            Note.getCurrentNote().removeTag(tagTextInCloud.getText().toLowerCase());
            MainPageController.getInstance().loadNoteTagsInTagBar(Note.getCurrentNote().getTags());
        }
    }

}