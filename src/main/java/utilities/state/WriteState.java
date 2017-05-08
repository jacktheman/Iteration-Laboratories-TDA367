package utilities.state;

import controllers.noteobject.NoteObjectControllerI;
import controllers.noteobject.TextContainerController;
import javafx.scene.input.MouseEvent;
import utilities.ObservableI;
import utilities.ObserverI;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by jackflurry on 2017-04-27.
 */
public class WriteState implements NoteStateI, ObservableI {

    private static WriteState SINGLETON = new WriteState();

    private String fontFamilyName;

    private List<ObserverI<WriteState>> listeners;

    private WriteState() {
        listeners = new ArrayList<>();
    }

    public static WriteState getInstance() {
        return SINGLETON;
    }

    public void setFont (String fontFamilyName) {
        this.fontFamilyName = fontFamilyName;
    }

    @Override
    public NoteObjectControllerI getOnMousePressed(MouseEvent event) {
        return null;
    }

    @Override
    public NoteObjectControllerI getOnMouseReleased(MouseEvent event) {
        TextContainerController controller = new TextContainerController("",event.getX(),event.getY());
        this.addListener(controller);
        return controller;
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
}
