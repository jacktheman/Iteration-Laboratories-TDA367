package utilities.noteobjectbehaviors;

import javafx.scene.Cursor;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;

/**
 * Created by svante on 2017-05-03.
 */
public class ResizeBehavior implements NoteObjectBehaviorI {
    
    private ImageView imageView;

    private enum ResizablePositions {
        LEFT_UPPER_CORNER, LEFT_LOWER_CORNER, RIGHT_UPPER_CORNER,
        RIGHT_LOWER_CORNER, UPPER_AREA, LEFT_AREA, BOTTOM_AREA, RIGHT_AREA
    }

    @Override
    public void onMousePressed(MouseEvent mouseEvent) {
    }

    @Override
    public void onMouseReleased(MouseEvent mouseEvent) {
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
        ResizablePositions resizablePositions = returnCursorLocation(mouseEvent);
        System.out.println("DragDetected");
        dragResize(resizablePositions, mouseEvent);
    }

    public ResizeBehavior(ImageView view){
        this.imageView = view;
    }

    private ResizablePositions returnCursorLocation(MouseEvent mouseEvent) {
        ResizablePositions pos = null;

        if (mouseEvent.getX() >= 0 && mouseEvent.getX() <= 5 && mouseEvent.getY() >= 0 && mouseEvent.getY() <= 5) {
            pos = ResizablePositions.LEFT_UPPER_CORNER;
        } else if (mouseEvent.getX() >= 0 && mouseEvent.getX() <= 5 && mouseEvent.getY() >= imageView.getFitHeight() - 5 && mouseEvent.getY() <= imageView.getFitHeight()) {
            pos = ResizablePositions.LEFT_LOWER_CORNER;
        } else if (mouseEvent.getX() >= imageView.getFitWidth() - 5 && mouseEvent.getX() <= imageView.getFitWidth() && mouseEvent.getY() >= 0 && mouseEvent.getY() <= 5) {
            pos = ResizablePositions.RIGHT_UPPER_CORNER;
        } else if (mouseEvent.getX() >= imageView.getFitWidth() - 5 && mouseEvent.getX() <= imageView.getFitWidth() && mouseEvent.getY() >= imageView.getFitHeight() - 5 && mouseEvent.getY() <= imageView.getFitHeight()) {
            pos = ResizablePositions.RIGHT_LOWER_CORNER;
        } else if (mouseEvent.getY() >= 0 && mouseEvent.getY() <= 5) {
            pos = ResizablePositions.UPPER_AREA;
        } else if (mouseEvent.getX() >= 0 && mouseEvent.getX() <= 5) {
            pos = ResizablePositions.LEFT_AREA;
        } else if (mouseEvent.getY() >= imageView.getFitHeight() - 5 && mouseEvent.getY() <= imageView.getFitHeight()) {
            pos = ResizablePositions.BOTTOM_AREA;
        } else if (mouseEvent.getX() >= imageView.getFitWidth() - 5 && mouseEvent.getX() <= imageView.getFitWidth()) {
            pos = ResizablePositions.RIGHT_AREA;
        }

        return pos;

    }

    private void dragResize(ResizablePositions position, MouseEvent event){

        double nodeX, nodeY, mouseX, mouseY;
        mouseX = event.getX();
        mouseY = event.getY();
        nodeX = imageView.getFitWidth();
        nodeY = imageView.getFitHeight();

        if(position == ResizablePositions.LEFT_UPPER_CORNER){

        } else if (position == ResizablePositions.LEFT_LOWER_CORNER){

        } else if (position == ResizablePositions.RIGHT_UPPER_CORNER){

        } else if (position == ResizablePositions.RIGHT_LOWER_CORNER){

        } else if (position == ResizablePositions.UPPER_AREA){

        } else if (position == ResizablePositions.LEFT_AREA){

        } else if (position == ResizablePositions.BOTTOM_AREA){

        } else if (position == ResizablePositions.RIGHT_AREA){
            imageView.setFitWidth(nodeX + mouseX - nodeX);
        }
    }

    private void changeCursorBasedOnPosition(ResizablePositions pos) {

        if (pos != null) {
            //System.out.println(pos.toString());
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
