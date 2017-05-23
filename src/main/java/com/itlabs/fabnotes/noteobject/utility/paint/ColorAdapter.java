package com.itlabs.fabnotes.noteobject.utility.paint;

import javafx.scene.paint.Color;
import java.io.Serializable;

/**
 * Created by alexandra on 2017-05-21.
 */

class ColorAdapter implements Serializable{

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
    
    double getR() {
        return r;
    }

    double getG() {
        return g;
    }

    double getB() {
        return b;
    }

    double getOpacity() {
        return opacity;
    }

}
