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
public class PaintingContainer extends NoteObject implements ObservableI{

    private static Paintbrush paintbrush;
    private List<PaintStrokeToData> paintings;

    public PaintingContainer(){
        super();
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
    public void add() {

    }

    @Override
    public void remove() {

    }

    @Override
    public void addListener(ObserverI observer) {

    }

    @Override
    public void removeListener(ObserverI observer) {

    }

    @Override
    public int hashCode() {
        return (super.hashCode() + super.getModelNumber() + 2*super.hashCode()*super.getModelNumber())*5;
    }

    @Override
    public boolean equals(Object o) {
        if (o.hashCode() == this.hashCode()) {
            if (o instanceof PaintingContainer) {
                return true;
            }
        }
        return false;
    }
}
