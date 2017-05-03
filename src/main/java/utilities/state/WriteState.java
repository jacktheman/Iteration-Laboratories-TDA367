package utilities.state;

import controllers.noteobjectcontrollers.NoteObjectControllerI;
import controllers.noteobjectcontrollers.TextContainerController;
import javafx.scene.input.MouseEvent;

/**
 * Created by jackflurry on 2017-04-27.
 */
public class WriteState implements NoteStateI {

    private static WriteState SINGLETON = new WriteState();

    private WriteState() {
    }

    public static WriteState getInstance() {
        return SINGLETON;
    }

    @Override
    public NoteObjectControllerI setOnMousePressed(MouseEvent event) {
        return null;
    }

    @Override
    public NoteObjectControllerI setOnMouseReleased(MouseEvent event) {
        return new TextContainerController("",event.getX(),event.getY());
    }
}
