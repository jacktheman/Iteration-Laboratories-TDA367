package state;

import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import models.noteobject.NoteObjectI;

import java.net.MalformedURLException;

/**
 * Created by jackflurry on 2017-04-27.
 */
public interface NoteStateI{

    NoteObjectI getOnMousePressed(AnchorPane notePane, MouseEvent event) throws MalformedURLException;

    NoteObjectI getOnMouseReleased(AnchorPane notePane, MouseEvent event) throws MalformedURLException;

}
