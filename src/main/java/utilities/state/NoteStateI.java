package utilities.state;

import controllers.noteobjectcontrollers.NoteObjectControllerI;
import javafx.scene.input.MouseEvent;
import utilities.ObservableI;

/**
 * Created by jackflurry on 2017-04-27.
 */
public interface NoteStateI{

    NoteObjectControllerI setOnMousePressed(MouseEvent event);

    NoteObjectControllerI setOnMouseReleased(MouseEvent event);

}
