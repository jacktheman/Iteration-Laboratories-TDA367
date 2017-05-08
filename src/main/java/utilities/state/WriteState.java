package utilities.state;

import controllers.noteobject.ImageContainerController;
import controllers.noteobject.NoteObjectControllerI;
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
public class WriteState extends NoteState {

    private static WriteState SINGLETON = new WriteState();

    private WriteState() {
    }

    public static WriteState getInstance() {
        return SINGLETON;
    }

    @Override
    public NoteObjectControllerI getOnMousePressed(AnchorPane notePane, MouseEvent event) {
        return null;
    }

    @Override
    public NoteObjectControllerI getOnMouseReleased(AnchorPane notePane, MouseEvent event) throws MalformedURLException {
        if (!super.pressedFocusOwner(notePane, event)) {
            if (event.getButton().equals(MouseButton.PRIMARY)) {
                return new TextContainerController("",event.getX(),event.getY());
            } else if (event.getButton().equals(MouseButton.SECONDARY)) {
                FileChooser fileChooser = FileChooserFactory.getImageChooser();
                File file = fileChooser.showOpenDialog(notePane.getScene().getWindow());
                return new ImageContainerController(file.toURI().toURL(), event.getX(), event.getY());
            }
        }
        return null;
    }
}
