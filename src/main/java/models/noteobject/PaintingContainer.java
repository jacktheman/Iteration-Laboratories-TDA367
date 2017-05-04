package models.noteobject;

import utilities.Paintbrush;

/**
 * Created by jackflurry on 2017-04-07.
 */
public class PaintingContainer {

    private static Paintbrush paintbrush;

    private PaintingContainer(){}

    public static Paintbrush getPaintbrush() {
        return paintbrush;
    }

    public static void setPaintbrush(Paintbrush paintbrush) {
        PaintingContainer.paintbrush = paintbrush;
    }
}
