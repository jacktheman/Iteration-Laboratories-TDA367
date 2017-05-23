package com.itlabs.fabnotes.noteobject.utility.paint;

import javafx.scene.paint.Color;

/**
 * Created by jackflurry on 2017-04-26.
 */
public enum Paintbrush {

    CIRCLE, TRIANGLE, SQUARE;

    private static final int DEFAULT_BRUSH_SIZE = 10;

    private static double brushSize = DEFAULT_BRUSH_SIZE;
    private static Color brushColor;

    public static void setSize(double size) {
        brushSize = size;
    }

    public static void setColor(Color color) {
        brushColor = color;
    }

    public static double getSize() {
        return brushSize;
    }

    public static Color getColor() {
        return brushColor;
    }

    public static Paintbrush parsePaintbrush(String string) {
        switch (string.toLowerCase()) {
            case "circle":
                return CIRCLE;
            case "triangle":
                return TRIANGLE;
            case "square":
                return SQUARE;
        }
        return null;
    }

    @Override
    public String toString() {
        switch (this) {
            case CIRCLE:
                return "circle";
            case TRIANGLE:
                return "triangle";
            case SQUARE:
                return "square";
        }
        return null;
    }

}