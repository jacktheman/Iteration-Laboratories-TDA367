package com.itlabs.fabnotes.noteobject.utility;


import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by jackflurry on 2017-05-11.
 */
public class PaintStrokeToData implements Serializable {

    private List<PaintingToData> paintStroke;


    public PaintStrokeToData(){
        paintStroke = new ArrayList<>();
    }

    public void addPaintToStroke(PaintingToData paintingToData){
        paintStroke.add(paintingToData);
    }

    public List<PaintingToData> getPaintStroke() {
        return paintStroke;
    }
}
