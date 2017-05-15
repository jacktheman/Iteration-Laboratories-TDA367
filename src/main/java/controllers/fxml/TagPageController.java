package controllers.fxml;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.FlowPane;
import services.FileHandler;
import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

/**
 * Created by aron on 2017-03-31.
 */
public class TagPageController implements Initializable {

    @FXML
    private TextField searchField;
    @FXML //TODO fix so that: ListView<T>
    private ListView noteListView;
    @FXML
    private FlowPane tagFlowPane;
    private List<String> tagsList;
    private String[] tagsArray;

    public static TagPageController getInstance() {
        return SINGLETON;
    }

    private static TagPageController SINGLETON;

    public void initialize(URL url, ResourceBundle resourceBundle) {
        SINGLETON = this;
        loadTagFlowPane();
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

    List<String> getTagsList () {
        return tagsList;
    }

    public FlowPane getTagFlowPane() {
        return tagFlowPane;
    }

    String [] getTagsArray() {
        return tagsArray;
    }



}
