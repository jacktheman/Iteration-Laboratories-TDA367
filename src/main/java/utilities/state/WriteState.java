package utilities.state;

import controllers.noteobject.ImageContainerController;
import controllers.noteobject.NoteObjectControllerI;
import controllers.noteobject.TableController;
import controllers.noteobject.TextContainerController;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.MenuItem;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;

import javafx.scene.layout.AnchorPane;
import javafx.stage.FileChooser;
import services.FileChooserFactory;

import java.io.File;
import java.net.MalformedURLException;

import utilities.ObservableI;
import utilities.ObserverI;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by jackflurry on 2017-04-27.
 */


public class WriteState extends NoteState implements ObservableI {


    private static WriteState SINGLETON = new WriteState();

    private String fontFamilyName;

    private int textSize;

    private List<ObserverI<WriteState>> listeners;

    private WriteState() {
        listeners = new ArrayList<>();
        fontFamilyName = "Calibri";
        textSize = 12;
    }

    public static WriteState getInstance() {
        return SINGLETON;
    }

    public void setFont (String fontFamilyName) {
        this.fontFamilyName = fontFamilyName;
        notifyListeners();
    }

    public void setSize (int textSize) {
        this.textSize = textSize;
        notifyListeners();
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

    @Override
    public void addListener(ObserverI observer) {
        listeners.add(observer);
    }

    @Override
    public void removeListener(ObserverI observer) {
        listeners.remove(observer);
    }

    private void notifyListeners () {
        for (int i = 0; i < listeners.size(); i++){
            listeners.get(i).fireChange(this);
        }
    }

    public String getFontFamilyName () {
        return fontFamilyName;
    }

    public int getTextSize() {
        return  textSize;
    }

}
