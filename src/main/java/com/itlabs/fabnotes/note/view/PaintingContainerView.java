package com.itlabs.fabnotes.note.view;

import com.itlabs.fabnotes.note.utility.paint.PaintStrokeToData;
import com.itlabs.fabnotes.note.utility.paint.Paintbrush;
import com.itlabs.fabnotes.note.utility.paint.PaintingToData;
import javafx.scene.canvas.Canvas;
import javafx.scene.layout.AnchorPane;
import com.itlabs.fabnotes.note.model.Note;
import com.itlabs.fabnotes.note.model.PaintingContainer;
import com.itlabs.fabnotes.note.utility.observer.ObserverI;

import java.io.Serializable;
import java.util.List;

/**
 * Created by jackflurry on 2017-04-26.
 */
public class PaintingContainerView extends AnchorPane implements Serializable, ObserverI<PaintingContainer> {

    private Canvas canvas;
    private Canvas borderCanvas;
    private boolean gotPaint;

    private static final double TRIANGLE_QUANTIFIER_SMALL = 0.75;
    private static final double TRIANGLE_QUANTIFIER_BIG = 1.25;
    private static final int TRIANGLE_NUMBER_OF_CORNERS = 3;

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

    private void updateLayoutX(double x){
        this.setLayoutX(x);
        removeBorder();
        createBorder();
    }

    private void updateLayoutY(double y){
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
        borderCanvas.getGraphicsContext2D().strokeRect(canvas.getLayoutX(),canvas.getLayoutY(),
                canvas.getWidth(),canvas.getHeight());
}

    public void removeBorder(){
        borderCanvas.getGraphicsContext2D().clearRect(canvas.getLayoutX(),canvas.getLayoutY(),
                canvas.getWidth(),canvas.getHeight());
    }

    public boolean getPaintStatus(){
        return gotPaint;
    }

    public void createPaintStroke(double x, double y, PaintStrokeToData stroke){
        PaintingToData paintingToData = new PaintingToData(x,y,PaintingContainer.getPaintbrush(),
                Paintbrush.getSize(),Paintbrush.getColor());
        stroke.addPaintToStroke(paintingToData);
        paint(paintingToData);
    }


    private void fillCanvas(List<PaintStrokeToData> paintings) {
        gotPaint = true;
        canvas.getGraphicsContext2D().clearRect(canvas.getLayoutX(), canvas.getLayoutY(),
                canvas.getWidth(), canvas.getHeight());
        for (PaintStrokeToData stroke : paintings) {
            for (PaintingToData painting : stroke.getPaintStroke()) {
                paint(painting);
            }
        }
    }

    private void paint(PaintingToData paint) {
        canvas.getGraphicsContext2D().setFill(paint.getColor());
        switch (paint.getPaintbrush()) {
            case CIRCLE:
                paintCircle(paint);
                break;
            case SQUARE:
                paintSquare(paint);
                break;
            case TRIANGLE:
                paintTriangle(paint);
                break;
        }

    }

    private void paintCircle(PaintingToData paint){
        canvas.getGraphicsContext2D().fillOval(paint.getX() - (paint.getSize() / 2),
                paint.getY() - (paint.getSize() / 2), paint.getSize(), paint.getSize());
    }

    private void paintSquare(PaintingToData paint){
        canvas.getGraphicsContext2D().fillRect(paint.getX() - (paint.getSize() / 2),
                paint.getY() - (paint.getSize() / 2), paint.getSize(), paint.getSize());
    }

    private void paintTriangle(PaintingToData paint){
        double[] xPoints = {paint.getX() - paint.getSize() * TRIANGLE_QUANTIFIER_BIG, paint.getX(),
                paint.getX() + paint.getSize() * TRIANGLE_QUANTIFIER_BIG};
        double[] yPoints = {paint.getY() + paint.getSize() * TRIANGLE_QUANTIFIER_SMALL,
                paint.getY() - paint.getSize() * TRIANGLE_QUANTIFIER_BIG,
                paint.getY() + paint.getSize() * TRIANGLE_QUANTIFIER_SMALL};
        canvas.getGraphicsContext2D().fillPolygon(xPoints, yPoints, TRIANGLE_NUMBER_OF_CORNERS);
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
            if(!Note.getCurrentNodes().contains(this)){
                Note.getCurrentNodes().add(this);
                initCanvas();
                this.requestFocus();
            }
        }else{
            Note.getCurrentNodes().remove(this);
        }
    }
}
