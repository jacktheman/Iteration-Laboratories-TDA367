package views.noteobjectviews;

import javafx.scene.canvas.Canvas;
import javafx.scene.layout.AnchorPane;
import utilities.Paintbrush;

/**
 * Created by jackflurry on 2017-04-26.
 */
public class PaintingContainerView extends AnchorPane {

    private Canvas canvas;

    public PaintingContainerView(double x, double y){
        this.setLayoutX(x);
        this.setLayoutY(y);
        this.setPrefSize(500,500);
        canvas = new Canvas(500,500);
        this.getChildren().add(canvas);
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
        canvas.getGraphicsContext2D().setFill(Paintbrush.getColor());
        switch (paintbrush){
            case CIRCLE:
                canvas.getGraphicsContext2D().fillOval(x,y,size,size);
                break;
            case SQUARE:
                canvas.getGraphicsContext2D().fillRect(x,y,size,size);
                break;
            case TRIANGLE:
                double [] xPoints = {x-size*1.5,x,x+size*1.5};
                double [] yPoints = {y+size*0.75,y-size*1.5,y+size*0.75};
                canvas.getGraphicsContext2D().fillPolygon(xPoints,yPoints,3);
                break;
        }

    }

}
