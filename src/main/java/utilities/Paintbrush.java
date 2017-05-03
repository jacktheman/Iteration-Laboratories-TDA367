package utilities;

import javafx.scene.paint.Color;

/**
 * Created by jackflurry on 2017-04-26.
 */
public enum Paintbrush {

    CIRCLE, TRIANGLE, SQUARE;

    private static int brushSize = 10;
    private static Color brushColor;

    public static void setSize(int size) {
        brushSize = size;
    }

    public static void setColor(Color color) {
        brushColor = color;
    }

    public static int getSize() {
        return brushSize;
    }

    public static Color getColor() {
        return brushColor;
    }

}