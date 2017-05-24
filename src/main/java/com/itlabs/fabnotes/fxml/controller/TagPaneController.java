package com.itlabs.fabnotes.fxml.controller;

import com.itlabs.fabnotes.fxml.service.NoteBridge;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import java.net.URL;
import java.util.ResourceBundle;

/**
 * Created by alexandra on 2017-05-16.
 */
public class TagPaneController implements Initializable {

    @FXML
    private AnchorPane tagPane;

    @FXML
    private Label tagText;

    @FXML
    private Button removeTagButton;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }

    @FXML
    public void removeTag(ActionEvent event) {
        NoteBridge.removeNoteTag(tagText.getText());
        MainPageController.getInstance().loadNoteTagsInTagBar(NoteBridge.getNoteTags());
    }

}