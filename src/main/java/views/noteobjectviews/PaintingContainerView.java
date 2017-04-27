package views.noteobjectviews;

import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.layout.AnchorPane;
import javafx.scene.shape.Polygon;
import utilities.Paintbrush;

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

    public void paint(Paintbrush paintbrush, double x, double y){
        double size = Paintbrush.getSize();
        switch (paintbrush){
            case CIRCLE:
                canvas.getGraphicsContext2D().fillOval(x,y,size,size);
            case SQUARE:
                canvas.getGraphicsContext2D().fillRect(x,y,size,size);
            case TRIANGLE:
                double [] xpoints = {x-size*10,x,x+size*10};
                double [] ypoints = {y-size*5,y+size*10,y-size*5};
                canvas.getGraphicsContext2D().fillPolygon(xpoints,ypoints,3);
        }

    }

}
