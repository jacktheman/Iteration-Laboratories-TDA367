package controllers.state;

import controllers.noteobject.NoteObjectControllerI;
import controllers.noteobject.TableController;
import controllers.noteobject.TextContainerController;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;

import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;

import java.net.MalformedURLException;

import utilities.ObservableI;
import utilities.ObserverI;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by jackflurry on 2017-04-27.
 */


public class WriteState extends NoteState {


    private static WriteState SINGLETON = new WriteState();

    private WriteState() {}

    public static WriteState getInstance() {
        return SINGLETON;
    }

    @Override
    public NoteObjectControllerI getOnMouseReleased(AnchorPane notePane, MouseEvent event) throws MalformedURLException {
        if (!super.pressedFocusOwner(notePane, event)) {
            if (event.getButton().equals(MouseButton.PRIMARY)) {
                return new TextContainerController("", event.getX(), event.getY());
            } else if (event.getButton().equals(MouseButton.MIDDLE)) {
                return new TableController(event.getX(),event.getY(), 3, 3);
            }
        }
        return null;
    }

}
