package models.noteobjectmodels;

import utilities.Paintbrush;

/**
 * Created by jackflurry on 2017-04-07.
 */
public class PaintingContainerModel {

    private static Paintbrush paintbrush;

    PaintingContainerModel(Paintbrush paintbrush){
        this.paintbrush = paintbrush;
    }

    public static Paintbrush getPaintbrush() {
        return paintbrush;
    }

    public void setPaintbrush(Paintbrush paintbrush) {
        this.paintbrush = paintbrush;
    }
}
