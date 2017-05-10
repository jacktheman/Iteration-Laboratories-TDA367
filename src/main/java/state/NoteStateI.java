package state;

import controllers.noteobject.NoteObjectControllerI;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;

import java.net.MalformedURLException;

/**
 * Created by jackflurry on 2017-04-27.
 */
public interface NoteStateI{

    NoteObjectControllerI getOnMousePressed(AnchorPane notePane, MouseEvent event) throws MalformedURLException;

    NoteObjectControllerI getOnMouseReleased(AnchorPane notePane, MouseEvent event) throws MalformedURLException;

}
