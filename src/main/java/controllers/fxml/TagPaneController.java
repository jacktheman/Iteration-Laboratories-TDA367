package controllers.fxml;


import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import models.note.Note;
import org.reactfx.collection.LiveArrayList;
import sun.security.tools.keytool.Main;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

/**
 * Created by alexandra on 2017-05-16.
 */
public class TagPaneController implements Initializable {

    private static ObservableList<String> tags = new LiveArrayList<>();

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
        Note.getCurrentNote().removeTagFromNote((tagText.getText()));
        MainPageController.getInstance().loadNoteTagsInTagBar(Note.getCurrentNote().getTags());
    }

    public static ObservableList<String> getTags() {
        return tags;
    }
}
