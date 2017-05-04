package views.noteobject;

import javafx.scene.canvas.Canvas;
import javafx.scene.layout.AnchorPane;
import utilities.Paintbrush;

/**
 * Created by jackflurry on 2017-04-26.
 */
public class PaintingContainerView extends AnchorPane {

    private Canvas canvas;
    private final double TRIANGLE_QUANTIFIER_SMALL = 0.75;
    private final double TRIANGLE_QUANTIFIER_BIG = 1.25;
    private final int TRIANGLE_UMBER_OF_CORNERS = 3;

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
                double [] xPoints = {x-size*TRIANGLE_QUANTIFIER_BIG,x,x+size*TRIANGLE_QUANTIFIER_BIG};
                double [] yPoints = {y+size*TRIANGLE_QUANTIFIER_SMALL,y-size*TRIANGLE_QUANTIFIER_BIG,y+size*TRIANGLE_QUANTIFIER_SMALL};
                canvas.getGraphicsContext2D().fillPolygon(xPoints,yPoints,TRIANGLE_UMBER_OF_CORNERS);
                break;
        }

    }

}
