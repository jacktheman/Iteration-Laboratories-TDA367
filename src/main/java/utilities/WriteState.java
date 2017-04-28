package utilities;

import controllers.noteobjectcontrollers.TextContainerController;
import javafx.scene.Node;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import models.notemodel.NoteModel;

/**
 * Created by jackflurry on 2017-04-27.
 */
public class WriteState implements NoteStateI {

    private static WriteState SINGLETON = new WriteState();

    private WriteState() {
    }

    public void setOnMousePressed(MouseEvent event, AnchorPane notePane) {

    }

    public void setOnMouseReleased(MouseEvent event, AnchorPane notePane) {
        notePane.getChildren().add((new TextContainerController("", event.getX(), event.getY())).getNode());
    }

    public void setOnMouseMove(MouseEvent event, AnchorPane notePane) {

    }

    public static WriteState getInstance() {
        return SINGLETON;
    }
}
