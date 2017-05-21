package controllers.fxml;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import models.note.Note;
import services.FileHandler;

import java.io.IOException;

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
        if (Note.getCurrentNote().getTags().contains(tagToRemove.toLowerCase())){
            Note.getCurrentNote().removeTagFromNote(tagTextInCloud.getText().toLowerCase());
            MainPageController.getInstance().loadNoteTagsInTagBar(Note.getCurrentNote().getTags());
        }
        try {
            FileHandler.removeTagFromTagList(tagToRemove);
            TagPageController.getInstance().getTagFlowPane().getChildren().clear();
            TagPageController.loadTagFlowPane();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}