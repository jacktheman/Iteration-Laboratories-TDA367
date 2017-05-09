package utilities.state;

import controllers.fxml.MainPageController;
import controllers.noteobject.NoteObjectControllerI;
import controllers.noteobject.TextContainerController;
import javafx.scene.Node;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.MenuItem;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;

import java.net.MalformedURLException;

/**
 * Created by aron on 2017-05-04.
 */
abstract class NoteState implements NoteStateI {

    private ContextMenu contextMenu;

    boolean pressedFocusOwner(AnchorPane notePane, MouseEvent event) {
        Node focusOwner = notePane.getScene().getFocusOwner();
        if (notePane.getChildren().contains(focusOwner)) {
            double x1 = focusOwner.getLayoutX();
            double x2 = focusOwner.getLayoutX() + focusOwner.getBoundsInLocal().getWidth();
            double y1 = focusOwner.getLayoutY();
            double y2 = focusOwner.getLayoutY() + focusOwner.getBoundsInLocal().getHeight();

            if ((event.getX() >= x1 && event.getX() <= x2 && event.getY() >= y1 && event.getY() <= y2)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public NoteObjectControllerI getOnMousePressed(AnchorPane notePane, MouseEvent event) throws MalformedURLException {
        MenuItem paste = new MenuItem("Klistra in");
        MenuItem remove = new MenuItem("Ta bort");
        paste.setOnAction(actionEvent -> {
            //pasteToNote();
        });
        if(!pressedFocusOwner(notePane, event)) {
            if (event.getSource().equals(notePane) && event.getButton().equals(MouseButton.SECONDARY)) {
                contextMenu = new ContextMenu(paste, remove);
                contextMenu.show(notePane, event.getScreenX(), event.getScreenY());
            }
        }
        return null;
    }

    @Override
    public NoteObjectControllerI getOnMouseReleased(AnchorPane notePane, MouseEvent event) throws MalformedURLException{

        return null;
    }

    public ContextMenu getContextMenu(){
        return this.contextMenu;
    }




}
