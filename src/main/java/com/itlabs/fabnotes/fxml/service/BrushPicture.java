package com.itlabs.fabnotes.fxml.service;

import com.itlabs.fabnotes.fxml.service.bridge.NoteObjectBridge;
import javafx.scene.canvas.Canvas;

/**
 * Created by jackflurry on 2017-05-26.
 */
public class BrushPicture {

    private static final double TRIANGLE_QUANTIFIER_SMALL = 0.75;
    private static final double TRIANGLE_QUANTIFIER_BIG = 1.25;
    private static final int TRIANGLE_NUMBER_OF_CORNERS = 3;

    private BrushPicture(){}

    public static void setBrushPicture(Canvas canvas){
        //clears picture before painting new
        canvas.getGraphicsContext2D().clearRect(canvas.getLayoutX(), canvas.getLayoutY(),
                canvas.getWidth(), canvas.getHeight());
        //paints new picture on Canvas
        switch (NoteObjectBridge.getPaintbrush()) {
            case "triangle":
                paintTriangle(canvas);
                break;
            case "square":
                paintSquare(canvas);
                break;
            case "circle":
                paintCircle(canvas);
                break;
        }

    }

    private static void paintCircle(Canvas canvas){
        canvas.getGraphicsContext2D().setFill(NoteObjectBridge.getPaintbrushColor());
        canvas.getGraphicsContext2D().fillOval(canvas.getWidth() / 2 - (NoteObjectBridge.getPaintbrushSize() / 2),
                canvas.getHeight() / 2 - (NoteObjectBridge.getPaintbrushSize() / 2),
                NoteObjectBridge.getPaintbrushSize(), NoteObjectBridge.getPaintbrushSize());
    }

    private static void paintSquare(Canvas canvas){
        canvas.getGraphicsContext2D().setFill(NoteObjectBridge.getPaintbrushColor());
        canvas.getGraphicsContext2D().fillRect(canvas.getWidth() / 2 - (NoteObjectBridge.getPaintbrushSize() / 2),
                canvas.getHeight() / 2 - (NoteObjectBridge.getPaintbrushSize() / 2),
                NoteObjectBridge.getPaintbrushSize(), NoteObjectBridge.getPaintbrushSize());
    }

    private static void paintTriangle(Canvas canvas){
        canvas.getGraphicsContext2D().setFill(NoteObjectBridge.getPaintbrushColor());
        double[] xPoints = {canvas.getWidth() / 2 - NoteObjectBridge.getPaintbrushSize()
                * TRIANGLE_QUANTIFIER_BIG, canvas.getWidth() / 2, canvas.getWidth() / 2
                + NoteObjectBridge.getPaintbrushSize() * TRIANGLE_QUANTIFIER_BIG};
        double[] yPoints = {canvas.getHeight() / 2 + NoteObjectBridge.getPaintbrushSize()
                * TRIANGLE_QUANTIFIER_SMALL, canvas.getHeight() / 2 - NoteObjectBridge.getPaintbrushSize()
                * TRIANGLE_QUANTIFIER_BIG, canvas.getHeight() / 2 + NoteObjectBridge.getPaintbrushSize()
                * TRIANGLE_QUANTIFIER_SMALL};
        canvas.getGraphicsContext2D().fillPolygon(xPoints, yPoints, TRIANGLE_NUMBER_OF_CORNERS);
    }



}
