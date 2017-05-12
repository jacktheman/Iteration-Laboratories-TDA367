package models.noteobject;

import utilities.ObservableI;
import utilities.ObserverI;
import utilities.PaintStrokeToData;
import utilities.Paintbrush;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by jackflurry on 2017-04-07.
 */
public class PaintingContainer implements NoteObjectI, ObservableI{

    private static Paintbrush paintbrush;
    private List<PaintStrokeToData> paintings;

    public PaintingContainer(){
        paintings = new ArrayList<>();
    }

    public static Paintbrush getPaintbrush() {
        return paintbrush;
    }

    public static void setPaintbrush(Paintbrush paintbrush) {
        PaintingContainer.paintbrush = paintbrush;
    }


    public void removeLastPainting() {
        paintings.remove(paintings.size() - 1);
    }

    public void addPainting(PaintStrokeToData paintStroke){
        paintings.add(paintStroke);
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

    @Override
    public void addListener(ObserverI observer) {

    }

    @Override
    public void removeListener(ObserverI observer) {

    }
}
