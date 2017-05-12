package views.noteobject;

import javafx.scene.canvas.Canvas;
import javafx.scene.layout.AnchorPane;
import utilities.*;

import java.io.Serializable;

/**
 * Created by jackflurry on 2017-04-26.
 */
public class PaintingContainerView extends AnchorPane implements Serializable, ObserverI {

    private Canvas canvas;
    private Canvas borderCanvas;
    private final int DEFAULT_CANVAS_SIZE = 50;
    private final int PAINTING_AREA_RESIZING_CONSTANT = 10;
    private boolean gotPaint;

    public PaintingContainerView(double x, double y){
        this.setLayoutX(x- DEFAULT_CANVAS_SIZE/2);
        this.setLayoutY(y- DEFAULT_CANVAS_SIZE/2);
        this.setWidth(DEFAULT_CANVAS_SIZE);
        this.setHeight(DEFAULT_CANVAS_SIZE);
        canvas = new Canvas(this.getWidth(),this.getHeight());
        borderCanvas = new Canvas(this.getWidth(),this.getHeight());
        this.getChildren().add(borderCanvas);
        this.getChildren().add(canvas);
        createBorder();
        gotPaint = false;
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
        if(layoutX > this.getWidth() - (PAINTING_AREA_RESIZING_CONSTANT+Paintbrush.getSize())) {
            resizeWidthRight();
            removeBorder();
            createBorder();
        }
        if(layoutY > this.getHeight() - (PAINTING_AREA_RESIZING_CONSTANT+Paintbrush.getSize())) {
            resizeHeightDown();
            removeBorder();
            createBorder();
        }
    }

    public boolean getPaintStatus(){
        return gotPaint;
    }


    public void fillCanvas(){
        canvas.getGraphicsContext2D().clearRect(canvas.getLayoutX(),canvas.getLayoutY(),canvas.getWidth(),canvas.getHeight());
        //TODO
    }

    @Override
    public void fireChange(Object subject) {

    }
}
