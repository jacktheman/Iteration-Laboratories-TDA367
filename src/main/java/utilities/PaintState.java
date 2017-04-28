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
public class PaintState implements NoteStateI{

    private boolean isPainting;

    private static PaintState SINGLETON = new PaintState();

    private PaintState(){}

    @Override
    public void setOnMousePressed(MouseEvent event, AnchorPane notePane) {
        if(event.getButton().equals(MouseButton.PRIMARY))
            isPainting = true;

    }

    @Override
    public void setOnMouseReleased(MouseEvent event, AnchorPane notePane) {
        if(event.getButton().equals(MouseButton.PRIMARY))
            isPainting = false;
    }

    @Override
    public void setOnMouseMove(MouseEvent event, AnchorPane notePane) {
        if(isPainting){
            Node focus = notePane.getScene().getFocusOwner();
            if( focus instanceof PaintingContainerView){
                if(event.getButton().equals(MouseButton.PRIMARY)){
                    ((PaintingContainerView) focus).paint(PaintingContainerModel.getPaintbrush(),event.getX(),event.getY());
                }
            }
        }
        PaintingContainerController paintingContainerController = new PaintingContainerController();
        paintingContainerController.setOnMouseMove(event);
    }

    public static PaintState getInstance(){
        return SINGLETON;
    }
}
