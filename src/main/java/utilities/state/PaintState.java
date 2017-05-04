package utilities.state;

import controllers.noteobject.ImageContainerController;
import controllers.noteobject.NoteObjectControllerI;
import controllers.noteobject.PaintingContainerController;
import controllers.noteobject.TextContainerController;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.FileChooser;
import services.FileChooserFactory;

import java.io.File;
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
                return new PaintingContainerController(event.getX(), event.getY());
            }
        }
        return null;
    }

    @Override
    public NoteObjectControllerI getOnMouseReleased(AnchorPane notePane, MouseEvent event) {
        return null;
    }

}
