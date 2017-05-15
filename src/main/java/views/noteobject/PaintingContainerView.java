package views.noteobject;

import controllers.fxml.MainPageController;
import javafx.scene.canvas.Canvas;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import models.noteobject.PaintingContainer;
import utilities.*;

import java.io.Serializable;
import java.util.List;

/**
 * Created by jackflurry on 2017-04-26.
 */
public class PaintingContainerView extends AnchorPane implements Serializable, ObserverI<PaintingContainer> {

    private Canvas canvas;
    private Canvas borderCanvas;
    private boolean gotPaint;

    private final double TRIANGLE_QUANTIFIER_SMALL = 0.75;
    private final double TRIANGLE_QUANTIFIER_BIG = 1.25;
    private final int TRIANGLE_NUMBER_OF_CORNERS = 3;

    public PaintingContainerView(){
        gotPaint = false;
        canvas = new Canvas();
        borderCanvas = new Canvas();
        this.getChildren().add(borderCanvas);
        this.getChildren().add(canvas);
    }

    private void resizeWidthRight(double w){
        this.setWidth(w);
        canvas.setWidth(w);
        borderCanvas.setWidth(w);
        removeBorder();
        createBorder();
    }

    private void resizeHeightDown(double h){
        this.setHeight(h);
        canvas.setHeight(h);
        borderCanvas.setHeight(h);
        removeBorder();
        createBorder();
    }

    public void updateLayoutX(double x){
        this.setLayoutX(x);
        removeBorder();
        createBorder();
    }

    public void updateLayoutY(double y){
        this.setLayoutY(y);
        removeBorder();
        createBorder();
    }

    private void initCanvas(){
        canvas.setWidth(this.getWidth());
        canvas.setHeight(this.getHeight());
        borderCanvas.setWidth(this.getWidth());
        borderCanvas.setHeight(this.getHeight());
        createBorder();
    }

    public void createBorder(){
        borderCanvas.getGraphicsContext2D().strokeRect(canvas.getLayoutX(),canvas.getLayoutY(),canvas.getWidth(),canvas.getHeight());
}

    public void removeBorder(){
        borderCanvas.getGraphicsContext2D().clearRect(canvas.getLayoutX(),canvas.getLayoutY(),canvas.getWidth(),canvas.getHeight());
    }

    public boolean getPaintStatus(){
        return gotPaint;
    }

    public void createPaintStroke(double x, double y, PaintStrokeToData stroke){
        PaintingToData paintingToData = new PaintingToData(x,y,PaintingContainer.getPaintbrush(),Paintbrush.getSize(),Paintbrush.getColor());
        stroke.addPaintToStroke(paintingToData);
        paintPolygon(paintingToData);
    }


    public void fillCanvas(List<PaintStrokeToData> paintings) {
        gotPaint = true;
        canvas.getGraphicsContext2D().clearRect(canvas.getLayoutX(), canvas.getLayoutY(), canvas.getWidth(), canvas.getHeight());
        for (PaintStrokeToData stroke : paintings) {
            for (PaintingToData painting : stroke.getPaintStroke()) {
                paintPolygon(painting);
            }
        }
    }


    public void paintPolygon(PaintingToData paint){
        paint(paint.getPaintbrush(),paint.getColor(),paint.getX(),paint.getY(),paint.getSize());
    }

    public void paint(Paintbrush paintbrush, Color color, double x, double y, double size) {
        canvas.getGraphicsContext2D().setFill(color);
        switch (paintbrush) {
            case CIRCLE:
                canvas.getGraphicsContext2D().fillOval(x - (size / 2), y - (size / 2), size, size);
                break;
            case SQUARE:
                canvas.getGraphicsContext2D().fillRect(x - (size / 2), y - (size / 2), size, size);
                break;
            case TRIANGLE:
                double[] xPoints = {x - size * TRIANGLE_QUANTIFIER_BIG, x, x + size * TRIANGLE_QUANTIFIER_BIG};
                double[] yPoints = {y + size * TRIANGLE_QUANTIFIER_SMALL, y - size * TRIANGLE_QUANTIFIER_BIG, y + size * TRIANGLE_QUANTIFIER_SMALL};
                canvas.getGraphicsContext2D().fillPolygon(xPoints, yPoints, TRIANGLE_NUMBER_OF_CORNERS);
                break;
        }

    }

    @Override
    public void fireChange(PaintingContainer subject) {
        if(subject.getStatus()){
            updateLayoutX(subject.getLayoutX());
            updateLayoutY(subject.getLayoutY());
            resizeWidthRight(subject.getFitWidth());
            resizeHeightDown(subject.getFitHeight());
            if(subject.getIfNewPaint()) {
                fillCanvas(subject.getPaintings());
            }
            if(!MainPageController.getCurrentNodes().contains(this)){
                MainPageController.getCurrentNodes().add(this);
                initCanvas();
                this.requestFocus();
            }
        }else{
            MainPageController.getCurrentNodes().remove(this);
        }
    }
}
