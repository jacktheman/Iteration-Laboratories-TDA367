package utilities.noteobjectbehaviors;

import javafx.scene.Cursor;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;

/**
 * Created by svante on 2017-0BORDER_WIDTH-03.
 */
public class ResizeBehavior implements NoteObjectBehaviorI {

    public static final double BORDER_WIDTH = 10;
    
    private ImageView imageView;

    private static ResizablePositions pos;

    static ResizablePositions getPos(){
        return pos;
    }

    @Override
    public void onMousePressed(MouseEvent mouseEvent) {
        imageView.requestFocus();
        imageView.toFront();
        pos = returnCursorLocation(mouseEvent);
    }

    @Override
    public void onMouseReleased(MouseEvent mouseEvent) {
        pos = null;
    }

    @Override
    public void onMouseEntered(MouseEvent mouseEvent) {
    }

    @Override
    public void onMouseExited(MouseEvent mouseEvent) {
    }

    @Override
    public void onMouseMoved(MouseEvent mouseEvent) {
        ResizablePositions position = returnCursorLocation(mouseEvent);
        changeCursorBasedOnPosition(position);
    }

    @Override
    public void onMouseDragged(MouseEvent mouseEvent) {
        dragResize(pos, mouseEvent);
    }

    public ResizeBehavior(ImageView view){
        this.imageView = view;
    }

    private ResizablePositions returnCursorLocation(MouseEvent mouseEvent) {
        ResizablePositions pos = null;

        if (mouseEvent.getX() >= 0 && mouseEvent.getX() <= BORDER_WIDTH && mouseEvent.getY() >= 0 && mouseEvent.getY() <= BORDER_WIDTH) {
            pos = ResizablePositions.LEFT_UPPER_CORNER;
        } else if (mouseEvent.getX() >= 0 && mouseEvent.getX() <= BORDER_WIDTH && mouseEvent.getY() >= imageView.getFitHeight() - BORDER_WIDTH && mouseEvent.getY() <= imageView.getFitHeight()) {
            pos = ResizablePositions.LEFT_LOWER_CORNER;
        } else if (mouseEvent.getX() >= imageView.getFitWidth() - BORDER_WIDTH && mouseEvent.getX() <= imageView.getFitWidth() && mouseEvent.getY() >= 0 && mouseEvent.getY() <= BORDER_WIDTH) {
            pos = ResizablePositions.RIGHT_UPPER_CORNER;
        } else if (mouseEvent.getX() >= imageView.getFitWidth() - BORDER_WIDTH && mouseEvent.getX() <= imageView.getFitWidth() && mouseEvent.getY() >= imageView.getFitHeight() - BORDER_WIDTH && mouseEvent.getY() <= imageView.getFitHeight()) {
            pos = ResizablePositions.RIGHT_LOWER_CORNER;
        } else if (mouseEvent.getY() >= 0 && mouseEvent.getY() <= BORDER_WIDTH) {
            pos = ResizablePositions.UPPER_AREA;
        } else if (mouseEvent.getX() >= 0 && mouseEvent.getX() <= BORDER_WIDTH) {
            pos = ResizablePositions.LEFT_AREA;
        } else if (mouseEvent.getY() >= imageView.getFitHeight() - BORDER_WIDTH /*&& mouseEvent.getY() <= imageView.getFitHeight()*/) {
            pos = ResizablePositions.BOTTOM_AREA;
        } else if (mouseEvent.getX() >= imageView.getFitWidth() - BORDER_WIDTH) {
            pos = ResizablePositions.RIGHT_AREA;
        }

        return pos;

    }

    private void dragResize(ResizablePositions position, MouseEvent event){

        double nodeX, nodeY, mouseX, mouseY, oldX, oldY, quota;
        mouseX = event.getX();
        mouseY = event.getY();
        nodeX = imageView.getFitWidth();
        nodeY = imageView.getFitHeight();
        oldX = imageView.getLayoutX();
        oldY = imageView.getLayoutY();
        quota = imageView.getFitHeight() / imageView.getFitWidth();

        if(position == ResizablePositions.LEFT_UPPER_CORNER){

        } else if (position == ResizablePositions.LEFT_LOWER_CORNER){

        } else if (position == ResizablePositions.RIGHT_UPPER_CORNER){
            /*if(mouseX <= oldX || mouseX >= oldX) {
                imageView.setFitWidth(mouseX);
                imageView.setLayoutY(oldY + mouseY);
                imageView.setFitHeight(mouseX * quota);
            }
            if (mouseY <= oldY || mouseY >= oldY){
                imageView.setLayoutY(oldY + mouseY);
                imageView.setFitHeight(oldY + mouseY);
                imageView.setFitWidth(mouseY / quota);
            } */
        } else if (position == ResizablePositions.RIGHT_LOWER_CORNER){
            if(mouseX <= oldX || mouseX >= oldX){
                imageView.setFitWidth(mouseX);
                imageView.setFitHeight(mouseX * quota);
            }
            if(mouseY <= oldY || mouseY >= oldY){
                imageView.setFitHeight(mouseY);
                imageView.setFitWidth(mouseY / quota);
            }
        } else if (position == ResizablePositions.UPPER_AREA){
            imageView.setLayoutY(oldY + mouseY);
            imageView.setFitHeight(nodeY - mouseY);
        } else if (position == ResizablePositions.LEFT_AREA){
            imageView.setLayoutX(oldX + mouseX);
            imageView.setFitWidth(nodeX - mouseX);
        } else if (position == ResizablePositions.BOTTOM_AREA){
            imageView.setFitHeight(mouseY);
        } else if (position == ResizablePositions.RIGHT_AREA){
            imageView.setFitWidth(mouseX);
        }
    }

    private void changeCursorBasedOnPosition(ResizablePositions pos) {

        if (pos != null) {
            switch (pos) {
                case RIGHT_UPPER_CORNER:
                    imageView.setCursor(Cursor.NE_RESIZE);
                    break;
                case RIGHT_LOWER_CORNER:
                    imageView.setCursor(Cursor.SE_RESIZE);
                    break;
                case LEFT_UPPER_CORNER:
                    imageView.setCursor(Cursor.NW_RESIZE);
                    break;
                case LEFT_LOWER_CORNER:
                    imageView.setCursor(Cursor.SW_RESIZE);
                    break;
                case BOTTOM_AREA:
                    imageView.setCursor(Cursor.S_RESIZE);
                    break;
                case UPPER_AREA:
                    imageView.setCursor(Cursor.N_RESIZE);
                    break;
                case RIGHT_AREA:
                    imageView.setCursor(Cursor.E_RESIZE);
                    break;
                case LEFT_AREA:
                    imageView.setCursor(Cursor.W_RESIZE);
                    break;
            }
        } else {
            imageView.setCursor(Cursor.HAND);
        }
    }


}
