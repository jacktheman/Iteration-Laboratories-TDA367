package utilities;

import javafx.scene.canvas.Canvas;
import models.noteobject.PaintingContainer;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by jackflurry on 2017-05-11.
 */
public class PaintStrokeToData {

    private List<PaintingToData> paintStroke;

    public PaintStrokeToData(double x, double y){
        paintStroke = new ArrayList<>();
        createPaintStroke(x,y);
    }

    public void createPaintStroke(double x, double y){
        paintStroke.add(new PaintingToData(x,y,PaintingContainer.getPaintbrush(),Paintbrush.getSize(),Paintbrush.getColor()));
    }

    public void paintAll(Canvas canvas){
        for(PaintingToData painting : paintStroke){
            painting.paint(canvas);
        }
    }


}
