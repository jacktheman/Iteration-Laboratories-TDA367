package controllers.noteobjectcontrollers;


import javafx.scene.Cursor;
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

    private enum ResizablePositions {
        LEFT_UPPER_CORNER, LEFT_LOWER_CORNER, RIGHT_UPPER_CORNER, RIGHT_LOWER_CORNER, UPPER_AREA, LEFT_AREA, BOTTOM_AREA, RIGHT_AREA
    }

    private NoteObjectBehaviorI noteObjectBehavior;

    private ImageContainerModel imageContainerModel;

    public ImageContainerController(Image image, double layoutX, double layoutY) {
        super(new ImageContainerView(image, layoutX, layoutY));
        this.imageContainerModel = new ImageContainerModel(image);
        noteObjectBehavior = new DragDropBehavior(super.getNode());
        setOnMouseMoved();
    }

    public ImageContainerController(URL url, double layoutX, double layoutY) {
        super(new ImageContainerView(url, layoutX, layoutY));
        this.imageContainerModel = new ImageContainerModel(new Image(url.toString()));
        noteObjectBehavior = new DragDropBehavior(super.getNode());
        setOnMouseMoved();
    }

    public Image returnModelImage() {
        return imageContainerModel.getImage();
    }

    private void setOnMouseMoved() {
        super.getNode().setOnMouseMoved(mouseEvent -> {
            ResizablePositions position = returnCursorLocation(mouseEvent);
            changeCursorBasedOnPosition(position);
        });
    }

    private ResizablePositions returnCursorLocation(MouseEvent mouseEvent) {
        ResizablePositions pos = null;

        if (mouseEvent.getX() >= 0 && mouseEvent.getX() <= 5 && mouseEvent.getY() >= 0 && mouseEvent.getY() <= 5) {
            pos = ResizablePositions.LEFT_UPPER_CORNER;
        } else if (mouseEvent.getX() >= 0 && mouseEvent.getX() <= 5 && mouseEvent.getY() >= super.getNode().getFitHeight() - 5 && mouseEvent.getY() <= super.getNode().getFitHeight()) {
            pos = ResizablePositions.LEFT_LOWER_CORNER;
        } else if (mouseEvent.getX() >= super.getNode().getFitWidth() - 5 && mouseEvent.getX() <= super.getNode().getFitWidth() && mouseEvent.getY() >= 0 && mouseEvent.getY() <= 5) {
            pos = ResizablePositions.RIGHT_UPPER_CORNER;
        } else if (mouseEvent.getX() >= super.getNode().getFitWidth() - 5 && mouseEvent.getX() <= super.getNode().getFitWidth() && mouseEvent.getY() >= super.getNode().getFitHeight() - 5 && mouseEvent.getY() <= super.getNode().getFitHeight()) {
            pos = ResizablePositions.RIGHT_LOWER_CORNER;
        } else if (mouseEvent.getY() >= 0 && mouseEvent.getY() <= 5) {
            pos = ResizablePositions.UPPER_AREA;
        } else if (mouseEvent.getX() >= 0 && mouseEvent.getX() <= 5) {
            pos = ResizablePositions.LEFT_AREA;
        } else if (mouseEvent.getY() >= super.getNode().getFitHeight() - 5 && mouseEvent.getY() <= super.getNode().getFitHeight()) {
            pos = ResizablePositions.BOTTOM_AREA;
        } else if (mouseEvent.getX() >= super.getNode().getFitWidth() - 5 && mouseEvent.getX() <= super.getNode().getFitWidth()) {
            pos = ResizablePositions.RIGHT_AREA;
        }

        return pos;

    }

    private void changeCursorBasedOnPosition(ResizablePositions pos) {

        if (pos != null) {
            System.out.println(pos.toString());
            switch (pos) {
                case RIGHT_UPPER_CORNER:
                    super.getNode().setCursor(Cursor.NE_RESIZE);
                    break;
                case RIGHT_LOWER_CORNER:
                    super.getNode().setCursor(Cursor.SE_RESIZE);
                    break;
                case LEFT_UPPER_CORNER:
                    super.getNode().setCursor(Cursor.NW_RESIZE);
                    break;
                case LEFT_LOWER_CORNER:
                    super.getNode().setCursor(Cursor.SW_RESIZE);
                    break;
                case BOTTOM_AREA:
                    super.getNode().setCursor(Cursor.S_RESIZE);
                    break;
                case UPPER_AREA:
                    super.getNode().setCursor(Cursor.N_RESIZE);
                    break;
                case RIGHT_AREA:
                    super.getNode().setCursor(Cursor.E_RESIZE);
                    break;
                case LEFT_AREA:
                    super.getNode().setCursor(Cursor.W_RESIZE);
                    break;
            }
        } else {
            super.getNode().setCursor(Cursor.HAND);
        }
    }

}

