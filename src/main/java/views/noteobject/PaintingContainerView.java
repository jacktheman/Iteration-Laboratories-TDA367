package views.noteobject;

import controllers.fxml.MainPageController;
import javafx.scene.canvas.Canvas;
import javafx.scene.layout.AnchorPane;
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


    public void fillCanvas(List<PaintStrokeToData> paintings){
        gotPaint = true;
        canvas.getGraphicsContext2D().clearRect(canvas.getLayoutX(),canvas.getLayoutY(),canvas.getWidth(),canvas.getHeight());
        for(PaintStrokeToData stroke : paintings){
            stroke.paintAll(canvas);
        }

    }

    public void paintPolygon(PaintingToData paint){
        paint.paint(canvas);
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
