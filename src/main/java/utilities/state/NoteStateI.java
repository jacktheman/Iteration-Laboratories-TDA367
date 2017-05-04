package utilities.state;

import controllers.noteobject.NoteObjectControllerI;
import javafx.scene.input.MouseEvent;

/**
 * Created by jackflurry on 2017-04-27.
 */
public interface NoteStateI{

    NoteObjectControllerI getOnMousePressed(MouseEvent event);

    NoteObjectControllerI getOnMouseReleased(MouseEvent event);

}
