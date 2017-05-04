package utilities.state;

import controllers.noteobject.NoteObjectControllerI;
import controllers.noteobject.PaintingContainerController;
import javafx.scene.input.MouseEvent;

/**
 * Created by jackflurry on 2017-04-27.
 */
public class PaintState implements NoteStateI {

    private static PaintState SINGLETON = new PaintState();

    private PaintState(){}


    public static PaintState getInstance(){
        return SINGLETON;
    }

    @Override
    public NoteObjectControllerI getOnMousePressed(MouseEvent event) {
        return null;
    }

    @Override
    public NoteObjectControllerI getOnMouseReleased(MouseEvent event) {
        return new PaintingContainerController(event.getX(),event.getY());
    }

}
