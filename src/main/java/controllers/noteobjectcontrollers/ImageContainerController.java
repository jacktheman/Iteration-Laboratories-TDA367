package controllers.noteobjectcontrollers;


import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import models.noteobjectmodels.ImageContainerModel;
import utilities.noteobjectbehaviors.DragDropBehavior;
import utilities.noteobjectbehaviors.NoteObjectBehaviorI;
import views.noteobjectviews.ImageContainerView;

import java.net.URL;

/**
 * Created by jackflurry on 2017-04-07.
 */
public class ImageContainerController extends NoteObjectController<ImageContainerView> {

    private enum ResizablePositions{
        LEFT_UPPER_CORNER, LEFT_LOWER_CORNER, RIGHT_UPPER_CORNER, RIGHT_LOWER_CORNER, UPPER_AREA, LEFT_AREA, BOTTOM_AREA, RIGHT_AREA
    }

    private NoteObjectBehaviorI noteObjectBehavior;

    private ImageContainerModel imageContainerModel;

    public ImageContainerController(Image image, double layoutX, double layoutY){
        super(new ImageContainerView(image, layoutX, layoutY));
        this.imageContainerModel = new ImageContainerModel(image);
        noteObjectBehavior = new DragDropBehavior(super.getNode());
    }

    public ImageContainerController(URL url, double layoutX, double layoutY){
        super(new ImageContainerView(url,layoutX, layoutY));
        this.imageContainerModel = new ImageContainerModel(new Image(url.toString()));
        noteObjectBehavior = new DragDropBehavior(super.getNode());
    }

    public Image returnModelImage(){
        return imageContainerModel.getImage();
    }

    private ResizablePositions isInUpperLeft(MouseEvent mouseEvent){
        if(mouseEvent.getX() >= 0 && mouseEvent.getX() <= 5 && mouseEvent.getY() >= 0 && mouseEvent.getY() <= 5)
        {
            return ResizablePositions.LEFT_UPPER_CORNER;
        } else {
            return null;
        }
    }

    private ResizablePositions isInLowerLeft(MouseEvent mouseEvent){
        if(mouseEvent.getX() >= 0 && mouseEvent.getX() <= 5 && mouseEvent.getY() >= mouseEvent.getSceneY() - 5 && mouseEvent.getY() <= mouseEvent.getSceneY())
        {
            return ResizablePositions.LEFT_LOWER_CORNER;
        } else {
            return null;
        }
    }

    private ResizablePositions isInUpperRight(MouseEvent mouseEvent){
        if(mouseEvent.getX() >= mouseEvent.getSceneX() - 5 && mouseEvent.getX() <= mouseEvent.getSceneX() && mouseEvent.getY() >= 0 && mouseEvent.getY() <= 5)
        {
            return ResizablePositions.RIGHT_UPPER_CORNER;
        } else {
            return null;
        }
    }

    private ResizablePositions isInLowerRight(MouseEvent mouseEvent){
        if(mouseEvent.getX() >= mouseEvent.getSceneX() - 5 && mouseEvent.getX() <= mouseEvent.getSceneX() && mouseEvent.getY() >= mouseEvent.getSceneY() - 5 && mouseEvent.getY() <= mouseEvent.getSceneY())
        {
            return ResizablePositions.RIGHT_LOWER_CORNER;
        } else {
            return null;
        }
    }

    private ResizablePositions isInUpperArea(MouseEvent mouseEvent){
        if(mouseEvent.getX() > 5 && mouseEvent.getX() < mouseEvent.getSceneX() -5 && mouseEvent.getY() >= 0 && mouseEvent.getY() <= 5)
        {
            return ResizablePositions.UPPER_AREA;
        } else {
            return null;
        }
    }

    private ResizablePositions isInLeftArea(MouseEvent mouseEvent){
        if(mouseEvent.getX() >= 0 && mouseEvent.getX() <= 5 && mouseEvent.getY() > 5 && mouseEvent.getY() <mouseEvent.getSceneY() - 5)
        {
            return ResizablePositions.LEFT_AREA;
        } else {
            return null;
        }
    }

    private ResizablePositions isInBottomArea(MouseEvent mouseEvent){
        if(mouseEvent.getX() > 5 && mouseEvent.getX() < mouseEvent.getSceneX() - 5 && mouseEvent.getY() >= mouseEvent.getSceneY() -5 && mouseEvent.getY() <= mouseEvent.getSceneY() )
        {
            return ResizablePositions.BOTTOM_AREA;
        } else {
            return null;
        }
    }

    private ResizablePositions isInRightArea(MouseEvent mouseEvent){
        if(mouseEvent.getX() >= mouseEvent.getSceneX() -5 && mouseEvent.getX() <= mouseEvent.getSceneX() && mouseEvent.getY() > 5 && mouseEvent.getY() <mouseEvent.getSceneY() - 5)
        {
            return ResizablePositions.RIGHT_AREA;
        } else {
            return null;
        }
    }




    private void currentResizablePosition(ResizablePositions pos){

        switch(pos){
            case RIGHT_UPPER_CORNER:
            case RIGHT_LOWER_CORNER:
            case LEFT_UPPER_CORNER:
            case LEFT_LOWER_CORNER:
            case BOTTOM_AREA:
            case UPPER_AREA:
            case RIGHT_AREA:
            case LEFT_AREA:
        }
    }

}

