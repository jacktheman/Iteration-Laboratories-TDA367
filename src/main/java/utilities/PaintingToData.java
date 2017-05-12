package utilities;

import javafx.scene.canvas.Canvas;
import javafx.scene.paint.Color;

/**
 * Created by jackflurry on 2017-05-11.
 */
public class PaintingToData {

    private final double TRIANGLE_QUANTIFIER_SMALL = 0.75;
    private final double TRIANGLE_QUANTIFIER_BIG = 1.25;
    private final int TRIANGLE_NUMBER_OF_CORNERS = 3;

    private final double x;
    private final double y;
    private final Paintbrush paintbrush;
    private final double size;
    private final Color color;

    public PaintingToData(double x, double y, Paintbrush paintbrush, double size, Color color) {
        this.x = x;
        this.y = y;
        this.paintbrush = paintbrush;
        this.size = size;
        this.color = color;
    }

    public void paint(Canvas canvas) {
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
}
