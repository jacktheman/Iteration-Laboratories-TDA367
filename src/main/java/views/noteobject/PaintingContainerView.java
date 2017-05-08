package views.noteobject;

import javafx.scene.canvas.Canvas;
import javafx.scene.layout.AnchorPane;
import models.noteobject.PaintingContainer;
import utilities.Paintbrush;

import java.io.Serializable;

/**
 * Created by jackflurry on 2017-04-26.
 */
public class PaintingContainerView extends AnchorPane implements Serializable {

    private Canvas canvas;
    private Canvas borderCanvas;
    private final double TRIANGLE_QUANTIFIER_SMALL = 0.75;
    private final double TRIANGLE_QUANTIFIER_BIG = 1.25;
    private final int TRIANGLE_UMBER_OF_CORNERS = 3;
    private final int DEFAULT_CANVAS_SIZE = 50;
    private final int DEFAULT_CANVAS_CROP = 25;
    private final int PAINTING_AREA_RESIZING_CONSTANT = 10;

    private boolean gotPaint = false;

    public PaintingContainerView(double x, double y){
        this.setLayoutX(x-DEFAULT_CANVAS_CROP);
        this.setLayoutY(y- DEFAULT_CANVAS_CROP);
        this.setWidth(DEFAULT_CANVAS_SIZE);
        this.setHeight(DEFAULT_CANVAS_SIZE);
        canvas = new Canvas(this.getWidth(),this.getHeight());
        borderCanvas = new Canvas(this.getWidth(),this.getHeight());
        this.getChildren().add(borderCanvas);
        this.getChildren().add(canvas);
        createBorder();
    }

    private void resizeWidthRight(){
        this.setWidth(getWidth() + PAINTING_AREA_RESIZING_CONSTANT);
        canvas.setWidth(this.getWidth());
        borderCanvas.setWidth(this.getWidth());
    }

    private void resizeHeightDown(){
        this.setHeight(getHeight() + PAINTING_AREA_RESIZING_CONSTANT);
        canvas.setHeight(this.getHeight());
        borderCanvas.setHeight(this.getHeight());
    }

    public void createBorder(){
        borderCanvas.getGraphicsContext2D().strokeRect(canvas.getLayoutX(),canvas.getLayoutY(),canvas.getWidth(),canvas.getHeight());
    }

    public void removeBorder(){
        borderCanvas.getGraphicsContext2D().clearRect(canvas.getLayoutX(),canvas.getLayoutY(),canvas.getWidth(),canvas.getHeight());
    }

    private void paintingSizeCounter(double layoutX, double layoutY){
        if(layoutX > this.getWidth() - PAINTING_AREA_RESIZING_CONSTANT) {
            resizeWidthRight();
            removeBorder();
            createBorder();
        }
        if(layoutY > this.getHeight() - PAINTING_AREA_RESIZING_CONSTANT) {
            resizeHeightDown();
            removeBorder();
            createBorder();
        }
    }

    public boolean getPaintStatus(){
        return gotPaint;
    }

    public void paint(double x, double y){
        gotPaint = true;
        double size = Paintbrush.getSize();
        paintingSizeCounter(x,y);
        canvas.getGraphicsContext2D().setFill(Paintbrush.getColor());
        switch (PaintingContainer.getPaintbrush()){
            case CIRCLE:
                canvas.getGraphicsContext2D().fillOval(x,y,size,size);
                break;
            case SQUARE:
                canvas.getGraphicsContext2D().fillRect(x,y,size,size);
                break;
            case TRIANGLE:
                double [] xPoints = {x-size*TRIANGLE_QUANTIFIER_BIG,x,x+size*TRIANGLE_QUANTIFIER_BIG};
                double [] yPoints = {y+size*TRIANGLE_QUANTIFIER_SMALL,y-size*TRIANGLE_QUANTIFIER_BIG,y+size*TRIANGLE_QUANTIFIER_SMALL};
                canvas.getGraphicsContext2D().fillPolygon(xPoints,yPoints,TRIANGLE_UMBER_OF_CORNERS);
                break;
        }

    }

}
