package controllers.noteobjectcontrollers;

import javafx.scene.Node;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import models.noteobjectmodels.PaintingContainerModel;
import utilities.Paintbrush;
import views.noteobjectviews.PaintingContainerView;

/**
 * Created by jackflurry on 2017-04-07.
 */
public class PaintingContainerController extends NoteObjectController<PaintingContainerView> {

    private boolean isPainting;

    public PaintingContainerController(){
        super(new PaintingContainerView());
    }

    public void changePaintbrush(){

    }



    public void setOnMouseMove(MouseEvent event) {
        if(isPainting){
            Node focus = super.getNode().getScene().getFocusOwner();
            if( focus instanceof PaintingContainerView){
                if(event.getButton().equals(MouseButton.PRIMARY)){
                    ((PaintingContainerView) focus).paint(PaintingContainerModel.getPaintbrush(),event.getX(),event.getY());
                }
            }
        }
        (super.getNode()).paint(PaintingContainerModel.getPaintbrush(),event.getX(),event.getY());
    }
}
