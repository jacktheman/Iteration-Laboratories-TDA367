package controllers.noteobjectbehaviors;

import javafx.scene.Cursor;
import javafx.scene.Node;
import javafx.scene.input.MouseEvent;
import models.noteobject.NoteObjectResizeableI;

/**
 * Created by svante on 2017-0BORDER_WIDTH-03.
 */
public class ResizeBehavior implements NoteObjectBehaviorI {

    public static final double BORDER_WIDTH = 10;

    private final NoteObjectResizeableI imageContainer;
    private final Node node;

    private double nodeX;
    private double nodeY;
    private double oldX;
    private double oldY;
    private double quota;

    private boolean minimumSizeReached;

    private static ResizablePositions pos;

    static ResizablePositions getPos() {
        return pos;
    }

    @Override
    public void onMousePressed(MouseEvent mouseEvent) {
        node.requestFocus();
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

    public ResizeBehavior(NoteObjectResizeableI model, Node view) {
        this.imageContainer = model;
        this.node = view;
        updateVariables();
    }

    private void updateVariables() {
        this.nodeX = imageContainer.getFitWidth();
        this.nodeY = imageContainer.getFitHeight();
        this.oldX = imageContainer.getLayoutX();
        this.oldY = imageContainer.getLayoutY();
        this.quota = fixQuota(nodeY / nodeX, nodeX / nodeY);
    }

    private void confirmVariables() {
        imageContainer.setFitWidth(this.nodeX);
        imageContainer.setFitHeight(this.nodeY);
        imageContainer.setLayoutX(this.oldX);
        imageContainer.setLayoutY(this.oldY);
    }

    private double fixQuota(double v1, double v2) {
        if (this.nodeX > this.nodeY)
            return Math.min(v1, v2);
        return Math.max(v1, v2);
    }

    private void checkImageSize() {
        this.minimumSizeReached = (this.nodeX <= 40)
                || (this.nodeY <= 40);
        if (minimumSizeReached) {
            if (this.nodeX < this.nodeY) {
                this.nodeX = 40;
                this.nodeY = this.nodeX / quota;
            } else {
                this.nodeY = 40;
                this.nodeX = this.nodeY / quota;
            }
        }
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

    private void dragResize(MouseEvent event) {
        double mouseX, mouseY;
        mouseX = event.getX();
        mouseY = event.getY();

        updateVariables();

        if (pos == ResizablePositions.LEFT_UPPER_CORNER) {
            leftUpperCornerResize(mouseY);
        } else if (pos == ResizablePositions.LEFT_LOWER_CORNER) {
            leftLowerCornerResize(mouseY);
        } else if (pos == ResizablePositions.RIGHT_UPPER_CORNER) {
            rightUpperCornerResize(mouseY);
        } else if (pos == ResizablePositions.RIGHT_LOWER_CORNER) {
            rightLowerCornerResize(mouseY);
        } else if (pos == ResizablePositions.UPPER_AREA) {
            upperAreaResize(mouseY);
        } else if (pos == ResizablePositions.LEFT_AREA) {
            leftAreaResize(mouseX);
        } else if (pos == ResizablePositions.BOTTOM_AREA) {
            bottomAreaResize(mouseY);
        } else if (pos == ResizablePositions.RIGHT_AREA) {
            rightAreaResize(mouseX);
        }

        this.quota = fixQuota(nodeY / nodeX, nodeX / nodeY);

        checkImageSize();

        confirmVariables();
    }

    private void changeCursorBasedOnPosition(ResizablePositions pos) {

        if (pos != null) {
            switch (pos) {
                case RIGHT_UPPER_CORNER:
                    node.setCursor(Cursor.NE_RESIZE);
                    break;
                case RIGHT_LOWER_CORNER:
                    node.setCursor(Cursor.SE_RESIZE);
                    break;
                case LEFT_UPPER_CORNER:
                    node.setCursor(Cursor.NW_RESIZE);
                    break;
                case LEFT_LOWER_CORNER:
                    node.setCursor(Cursor.SW_RESIZE);
                    break;
                case BOTTOM_AREA:
                    node.setCursor(Cursor.S_RESIZE);
                    break;
                case UPPER_AREA:
                    node.setCursor(Cursor.N_RESIZE);
                    break;
                case RIGHT_AREA:
                    node.setCursor(Cursor.E_RESIZE);
                    break;
                case LEFT_AREA:
                    node.setCursor(Cursor.W_RESIZE);
                    break;
            }
        } else {
            node.setCursor(Cursor.HAND);
        }
    }

    private boolean cursorIsInUpperLeftCorner(MouseEvent event) {
        return (event.getX() >= 0 && event.getX() <= BORDER_WIDTH && event.getY() >= 0 && event.getY() <= BORDER_WIDTH);
    }

    private boolean cursorIsInLowerLeftCorner(MouseEvent event) {
        return (event.getX() >= 0 && event.getX() <= BORDER_WIDTH && event.getY() >= imageContainer.getFitHeight() - BORDER_WIDTH && event.getY() <= imageContainer.getFitHeight());
    }

    private boolean cursorIsInUpperRightCorner(MouseEvent event) {
        return (event.getX() >= imageContainer.getFitWidth() - BORDER_WIDTH && event.getX() <= imageContainer.getFitWidth() && event.getY() >= 0 && event.getY() <= BORDER_WIDTH);
    }

    private boolean cursorIsInLowerRightCorner(MouseEvent event) {
        return (event.getX() >= imageContainer.getFitWidth() - BORDER_WIDTH && event.getX() <= imageContainer.getFitWidth() && event.getY() >= imageContainer.getFitHeight() - BORDER_WIDTH && event.getY() <= imageContainer.getFitHeight());
    }

    private boolean cursorIsInUpperArea(MouseEvent event) {
        return (event.getY() >= 0 && event.getY() <= BORDER_WIDTH);
    }

    private boolean cursorIsInLeftArea(MouseEvent event) {
        return (event.getX() >= 0 && event.getX() <= BORDER_WIDTH);
    }

    private boolean cursorIsInBottomArea(MouseEvent event) {
        return (event.getY() >= imageContainer.getFitHeight() - BORDER_WIDTH);
    }

    private boolean cursorIsInRightArea(MouseEvent event) {
        return (event.getX() >= imageContainer.getFitWidth() - BORDER_WIDTH);
    }

    private void leftUpperCornerResize(double mouseY) {
        if (!minimumSizeReached || mouseY < 0) {
            oldY = oldY + mouseY;
            nodeY = nodeY - mouseY;
            oldX = oldX + mouseY / quota;
            nodeX = nodeY / quota;
        }
    }

    private void leftLowerCornerResize(double mouseY) {
        if (!minimumSizeReached || mouseY > nodeY) {
            this.oldX = this.oldX + this.nodeX - mouseY / this.quota;
            this.nodeX = mouseY / this.quota;
            this.nodeY = mouseY;
        }
    }

    private void rightUpperCornerResize(double mouseY) {
        if (!minimumSizeReached || mouseY < 0) {
            this.oldY = this.oldY + mouseY;
            this.nodeY = this.nodeY - mouseY;
            this.nodeX = this.nodeY / this.quota;
        }
    }

    private void rightLowerCornerResize(double mouseY) {
        if (!minimumSizeReached || mouseY > nodeY) {
            this.nodeY = mouseY;
            this.nodeX = mouseY / this.quota;
        }
    }

    private void upperAreaResize(double mouseY) {
        this.oldY = this.oldY + mouseY;
        this.nodeY = this.nodeY - mouseY;
    }

    private void leftAreaResize(double mouseX) {
        this.oldX = this.oldX + mouseX;
        this.nodeX = this.nodeX - mouseX;
    }

    private void bottomAreaResize(double mouseY) {
        this.nodeY = mouseY;
    }

    private void rightAreaResize(double mouseX) {
        this.nodeX = mouseX;
    }


}
