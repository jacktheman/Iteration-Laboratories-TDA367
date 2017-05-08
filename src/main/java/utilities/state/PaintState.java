package utilities.state;

import controllers.noteobject.NoteObjectControllerI;
import controllers.noteobject.PaintingContainerController;

import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import java.net.MalformedURLException;

/**
 * Created by jackflurry on 2017-04-27.
 */
public class PaintState extends NoteState {

    private static PaintState SINGLETON = new PaintState();

    private PaintState(){}


    public static PaintState getInstance(){
        return SINGLETON;
    }

    @Override
    public NoteObjectControllerI getOnMousePressed(AnchorPane notePane, MouseEvent event) throws MalformedURLException {
        if (!super.pressedFocusOwner(notePane, event)) {
            if (event.getButton().equals(MouseButton.PRIMARY)) {
                return new PaintingContainerController(event);
            }
        }
        return null;
    }

    @Override
    public NoteObjectControllerI getOnMouseReleased(AnchorPane notePane, MouseEvent event) {
        return null;
    }

}
