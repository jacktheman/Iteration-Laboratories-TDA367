package utilities;

import controllers.noteobjectcontrollers.TextContainerController;
import javafx.scene.Node;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import models.notemodel.NoteModel;

/**
 * Created by jackflurry on 2017-04-27.
 */
public class WriteState extends NoteState{
    

    WriteState(AnchorPane notePane, NoteModel noteModel) {
        super(notePane,noteModel);
    }

    @Override
    public void setOnMousePressed(MouseEvent event) {
        if (super.getNoteModel() == null) {
            super.setNoteModel(new NoteModel()); 
        }

        Node currentFocus = super.getNotePane().getScene().getFocusOwner();
        super.getNotePane().requestFocus();
        super.getNoteModel().addNoteObjectController(new TextContainerController("", event.getX(), event.getY()));
        super.getNotePane().getChildren().clear();
        super.getNotePane().getChildren().addAll(super.getNoteModel().getNodes());
        super.getNoteModel().getNodes().get(super.getNoteModel().getNodes().size() - 1).requestFocus();


    }

    @Override
    public void setOnMouseReleased(MouseEvent event) {

    }

    @Override
    public void setOnMouseMove(MouseEvent event) {

    }
}
