package views.noteobjectviews;

import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.layout.AnchorPane;

/**
 * Created by jackflurry on 2017-04-26.
 */
public class PaintingContainerView extends AnchorPane {

    private Canvas canvas;

    public PaintingContainerView(){
        this.setPrefSize(50,50);
        canvas = new Canvas(50,50);
    }

    public void resizeWidth(double x){
        this.setPrefWidth(this.getLayoutX() + getPrefWidth() + x);
        canvas.setWidth(this.getWidth());
    }

    public void resizeHeight(double y){
        this.setPrefHeight(this.getLayoutY() + getPrefHeight() + y);
        canvas.setHeight(this.getHeight());
    }

    public GraphicsContext getGraphicsContext(){
        return canvas.getGraphicsContext2D();
    }
}
