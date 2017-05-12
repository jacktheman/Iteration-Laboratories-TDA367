package models.noteobject;

import utilities.Paintbrush;

/**
 * Created by jackflurry on 2017-04-07.
 */
public class PaintingContainer implements NoteObjectI{

    private static Paintbrush paintbrush;

    private PaintingContainer(){}

    public static Paintbrush getPaintbrush() {
        return paintbrush;
    }

    public static void setPaintbrush(Paintbrush paintbrush) {
        PaintingContainer.paintbrush = paintbrush;
    }

    @Override
    public void setLayoutX(double d) {

    }

    @Override
    public double getLayoutX() {
        return 0;
    }

    @Override
    public void setLayoutY(double d) {

    }

    @Override
    public double getLayoutY() {
        return 0;
    }
}
