package utilities;

import controllers.noteobjectcontrollers.PaintingContainerController;
import javafx.scene.Node;
import javafx.scene.canvas.Canvas;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import models.notemodel.NoteModel;
import models.noteobjectmodels.PaintingContainerModel;
import views.noteobjectviews.PaintingContainerView;


/**
 * Created by jackflurry on 2017-04-27.
 */
public class PaintState extends NoteState{

    private boolean isPainting;

    PaintState(AnchorPane notePane, NoteModel noteModel) {
        super(notePane,noteModel);
    }

    @Override
    public void setOnMousePressed(MouseEvent event) {
        if(event.getButton().equals(MouseButton.PRIMARY))
            isPainting = true;

    }

    @Override
    public void setOnMouseReleased(MouseEvent event) {
        if(event.getButton().equals(MouseButton.PRIMARY))
            isPainting = false;
    }

    @Override
    public void setOnMouseMove(MouseEvent event) {
        if(isPainting){
            Node focus = super.getNotePane().getScene().getFocusOwner();
            if( focus instanceof PaintingContainerView){
                if(event.getButton().equals(MouseButton.PRIMARY)){
                    ((PaintingContainerView) focus).paint(PaintingContainerModel.getPaintbrush(),event.getX(),event.getY());
                }
            }
        }
        PaintingContainerController paintingContainerController = new PaintingContainerController();
        paintingContainerController.setOnMouseMove(event);
    }
}
