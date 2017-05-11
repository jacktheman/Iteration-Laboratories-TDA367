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

    private double nodeX;
    private double nodeY;
    private double oldX;
    private double oldY;
    private double quota;

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
        dragResize(mouseEvent);
    }

    public ResizeBehavior(ImageView view){
        this.imageView = view;
        this.nodeX = imageView.getFitWidth();
        this.nodeY = imageView.getFitHeight();
        this.oldX = imageView.getLayoutX();
        this.oldY = imageView.getLayoutY();
        this.quota = Math.min(nodeY / nodeX, nodeX / nodeY);
    }

    private void updateVariables(){
        this.nodeX = imageView.getFitWidth();
        this.nodeY = imageView.getFitHeight();
        this.oldX = imageView.getLayoutX();
        this.oldY = imageView.getLayoutY();
        this.quota = Math.min(nodeY / nodeX, nodeX / nodeY);
    }

    private ResizablePositions returnCursorLocation(MouseEvent mouseEvent) {
        ResizablePositions pos = null;

        if (cursorIsInUpperLeftCorner(mouseEvent)) {
            pos = ResizablePositions.LEFT_UPPER_CORNER;
        } else if (cursorIsInLowerLeftCorner(mouseEvent)) {
            pos = ResizablePositions.LEFT_LOWER_CORNER;
        } else if (cursorIsInUpperRightCorner(mouseEvent)) {
            pos = ResizablePositions.RIGHT_UPPER_CORNER;
        } else if (cursorIsInLowerRightCorner(mouseEvent)) {
            pos = ResizablePositions.RIGHT_LOWER_CORNER;
        } else if (cursorIsInUpperArea(mouseEvent)) {
            pos = ResizablePositions.UPPER_AREA;
        } else if (cursorIsInLeftArea(mouseEvent)) {
            pos = ResizablePositions.LEFT_AREA;
        } else if (cursorIsInBottomArea(mouseEvent)) {
            pos = ResizablePositions.BOTTOM_AREA;
        } else if (cursorIsInRightArea(mouseEvent)) {
            pos = ResizablePositions.RIGHT_AREA;
        }

        return pos;

    }

    private void dragResize(MouseEvent event){
        double mouseX, mouseY;
        mouseX = event.getX();
        mouseY = event.getY();
        updateVariables();

        if(pos == ResizablePositions.LEFT_UPPER_CORNER){
            leftUpperCornerResize(mouseY);
        } else if (pos == ResizablePositions.LEFT_LOWER_CORNER){
            leftLowerCornerResize(mouseY);
        } else if (pos == ResizablePositions.RIGHT_UPPER_CORNER){
            rightUpperCornerResize(mouseY);
        } else if (pos == ResizablePositions.RIGHT_LOWER_CORNER){
            rightLowerCornerResize(mouseY);
        } else if (pos == ResizablePositions.UPPER_AREA){
            upperAreaResize(mouseY);
        } else if (pos == ResizablePositions.LEFT_AREA){
            leftAreaResize(mouseX);
        } else if (pos == ResizablePositions.BOTTOM_AREA){
            bottomAreaResize(mouseY);
        } else if (pos == ResizablePositions.RIGHT_AREA){
            rightAreaResize(mouseX);
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

    private boolean cursorIsInUpperLeftCorner(MouseEvent event){
        return (event.getX() >= 0 && event.getX() <= BORDER_WIDTH && event.getY() >= 0 && event.getY() <= BORDER_WIDTH);
    }

    private boolean cursorIsInLowerLeftCorner(MouseEvent event){
        return (event.getX() >= 0 && event.getX() <= BORDER_WIDTH && event.getY() >= imageView.getFitHeight() - BORDER_WIDTH && event.getY() <= imageView.getFitHeight());
    }

    private boolean cursorIsInUpperRightCorner(MouseEvent event){
        return (event.getX() >= imageView.getFitWidth() - BORDER_WIDTH && event.getX() <= imageView.getFitWidth() && event.getY() >= 0 && event.getY() <= BORDER_WIDTH);
    }

    private boolean cursorIsInLowerRightCorner(MouseEvent event){
        return (event.getX() >= imageView.getFitWidth() - BORDER_WIDTH && event.getX() <= imageView.getFitWidth() && event.getY() >= imageView.getFitHeight() - BORDER_WIDTH && event.getY() <= imageView.getFitHeight());
    }

    private boolean cursorIsInUpperArea(MouseEvent event){
        return (event.getY() >= 0 && event.getY() <= BORDER_WIDTH);
    }

    private boolean cursorIsInLeftArea(MouseEvent event){
        return (event.getX() >= 0 && event.getX() <= BORDER_WIDTH);
    }

    private boolean cursorIsInBottomArea(MouseEvent event){
        return (event.getY() >= imageView.getFitHeight() - BORDER_WIDTH);
    }

    private boolean cursorIsInRightArea(MouseEvent event){
        return (event.getX() >= imageView.getFitWidth() - BORDER_WIDTH);
    }

    private void leftUpperCornerResize(double mouseY){

        if(mouseY <= oldY || mouseY >= oldY) {
            imageView.setLayoutY(oldY + mouseY);
            imageView.setFitHeight(nodeY - mouseY);
            imageView.setLayoutX(oldX + mouseY / quota);
            imageView.setFitWidth(imageView.getFitHeight() / quota);
        }

    }

    private void leftLowerCornerResize(double mouseY){
        if(mouseY <= nodeY || mouseY >= nodeY) {
            imageView.setLayoutX(oldX + nodeX - mouseY / quota);
            imageView.setFitWidth(mouseY / quota);
            imageView.setFitHeight(mouseY);
        }
    }

    private void rightUpperCornerResize(double mouseY){
        if(mouseY <= oldY || mouseY >= oldY) {
            imageView.setLayoutY(oldY + mouseY);
            imageView.setFitHeight(nodeY - mouseY);
            imageView.setFitWidth(imageView.getFitHeight() / quota);
        }
    }

    private void rightLowerCornerResize(double mouseY){
        if(mouseY <= nodeY || mouseY >= nodeY){
            imageView.setFitHeight(mouseY);
            imageView.setFitWidth(mouseY / quota);
        }
    }

    private void upperAreaResize(double mouseY){
        imageView.setLayoutY(oldY + mouseY);
        imageView.setFitHeight(nodeY - mouseY);
    }

    private void leftAreaResize(double mouseX){
        imageView.setLayoutX(oldX + mouseX);
        imageView.setFitWidth(nodeX - mouseX);
    }

    private void bottomAreaResize(double mouseY){
        imageView.setFitHeight(mouseY);
    }

    private void rightAreaResize(double mouseX){
        imageView.setFitWidth(mouseX);
    }



}
