package utilities.state;

import controllers.noteobject.NoteObjectControllerI;
import controllers.noteobject.TextContainerController;
import javafx.scene.input.MouseEvent;

/**
 * Created by jackflurry on 2017-04-27.
 */
public class WriteState implements NoteStateI {

    private static WriteState SINGLETON = new WriteState();

    private String fontFamilyName;

    private WriteState() {
    }

    public static WriteState getInstance() {
        return SINGLETON;
    }

    public void setFont (String fontFamilyName) {
        this.fontFamilyName = fontFamilyName;
        System.out.println(fontFamilyName);
    }

    @Override
    public NoteObjectControllerI getOnMousePressed(MouseEvent event) {
        return null;
    }

    @Override
    public NoteObjectControllerI getOnMouseReleased(MouseEvent event) {
        return new TextContainerController("",event.getX(),event.getY());
    }


}
