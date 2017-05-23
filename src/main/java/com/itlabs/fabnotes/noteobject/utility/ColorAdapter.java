package com.itlabs.fabnotes.noteobject.utility;

import javafx.scene.paint.Color;
import java.io.Serializable;

/**
 * Created by alexandra on 2017-05-21.
 */

public class ColorAdapter implements Serializable{

    private double r;
    private double g;
    private double b;
    private double opacity;


    ColorAdapter(Color color) {
        this.r = color.getRed();
        this.g = color.getGreen();
        this.b = color.getBlue();
        this.opacity = color.getOpacity();
    }
    
    public double getR() {
        return r;
    }

    public double getG() {
        return g;
    }

    public double getB() {
        return b;
    }

    public double getOpacity() {
        return opacity;
    }

}
