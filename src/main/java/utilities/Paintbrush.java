package utilities;

import javafx.scene.paint.Color;

/**
 * Created by jackflurry on 2017-04-26.
 */
public enum Paintbrush {

    CIRCLE, RECTANGLE, SQUARE;

    private static int brushSize;
    private static Color brushColor;

    public static void setSize(int size) {
        brushSize = size;
    }

    public static void setColor(Color color) {
        brushColor = color;
    }

    public int getSize() {
        return brushSize;
    }

    public Color getColor() {
        return brushColor;
    }

}