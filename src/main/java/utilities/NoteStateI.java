package utilities;

import controllers.noteobjectcontrollers.NoteObjectControllerI;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;

/**
 * Created by jackflurry on 2017-04-27.
 */
public interface NoteStateI {

    public NoteObjectControllerI setOnMousePressed(MouseEvent event);

    public NoteObjectControllerI setOnMouseReleased(MouseEvent event);

}
