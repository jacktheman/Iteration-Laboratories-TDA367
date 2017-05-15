package utilities;

import javafx.scene.canvas.Canvas;
import models.noteobject.PaintingContainer;
import views.noteobject.PaintingContainerView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by jackflurry on 2017-05-11.
 */
public class PaintStrokeToData {

    private List<PaintingToData> paintStroke;
    private PaintingContainerView view;

    public PaintStrokeToData(double x, double y, PaintingContainerView view){
        paintStroke = new ArrayList<>();
        this.view = view;
        createPaintStroke(x,y);

    }

    public void createPaintStroke(double x, double y){
        PaintingToData paintingToData = new PaintingToData(x,y,PaintingContainer.getPaintbrush(),Paintbrush.getSize(),Paintbrush.getColor());
        paintStroke.add(paintingToData);
        view.paintPolygon(paintingToData);

    }

    public void paintAll(Canvas canvas){
        for(PaintingToData painting : paintStroke){
            painting.paint(canvas);
        }
    }


}
