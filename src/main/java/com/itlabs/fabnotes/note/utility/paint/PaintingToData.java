package com.itlabs.fabnotes.note.utility.paint;

import javafx.scene.paint.Color;

import java.io.Serializable;

/**
 * Created by jackflurry on 2017-05-11.
 */
public class PaintingToData implements Serializable {

    private final double x;
    private final double y;
    private final Paintbrush paintbrush;
    private final double size;
    private final ColorAdapter color;

    public PaintingToData(double x, double y, Paintbrush paintbrush, double size, Color color) {
        this.x = x;
        this.y = y;
        this.paintbrush = paintbrush;
        this.size = size;
        this.color = new ColorAdapter(color);
    }

    public Paintbrush getPaintbrush() {
        return paintbrush;
    }

    public Color getColor() {
        return new Color(color.getR(), color.getG(), color.getB(), color.getOpacity());
    }

    public double getSize() {
        return size;
    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }
}
