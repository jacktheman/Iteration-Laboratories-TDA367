package com.itlabs.fabnotes.note.model;

import com.itlabs.fabnotes.note.utility.observer.ObservableI;
import com.itlabs.fabnotes.note.utility.observer.ObserverI;
import com.itlabs.fabnotes.note.utility.paint.PaintStrokeToData;
import com.itlabs.fabnotes.note.utility.paint.Paintbrush;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by jackflurry on 2017-04-07.
 */
public class PaintingContainer extends NoteObjectResizeable implements ObservableI{

    private static Paintbrush paintbrush;
    private List<PaintStrokeToData> paintings;
    private List<ObserverI<PaintingContainer>> listeners;
    private boolean isAlive;
    private boolean newPaint;

    private static final int DEFAULT_CANVAS_SIZE = 100;
    private static final int PAINTING_AREA_RESIZING_CONSTANT = 10;

    private double layoutX;
    private double layoutY;
    private double w;
    private double h;

    public PaintingContainer(double x, double y, boolean newPaint) {
        super();
        paintings = new ArrayList<>();
        listeners = new ArrayList<>();
        this.layoutX = x;
        this.layoutY = y;
        this.w = DEFAULT_CANVAS_SIZE;
        setFitHeight(DEFAULT_CANVAS_SIZE);
        isAlive = true;
        this.newPaint = newPaint;
    }

    public PaintingContainer(double middleX, double middleY){
        super();
        paintings = new ArrayList<>();
        listeners = new ArrayList<>();
        this.layoutX = middleX - DEFAULT_CANVAS_SIZE/2;
        this.layoutY = middleY - DEFAULT_CANVAS_SIZE/2;
        this.w = DEFAULT_CANVAS_SIZE;
        setFitHeight(DEFAULT_CANVAS_SIZE);
        isAlive = true;
        newPaint = false;
    }

    public PaintingContainer(PaintingContainer model){
        super();
        paintings = new ArrayList<>(model.getPaintings());
        listeners = new ArrayList<>();
        this.layoutX = model.getLayoutX();
        this.layoutY = model.getLayoutY();
        this.w = model.getFitWidth();
        setFitHeight(model.getFitHeight());
        isAlive = true;
        newPaint = model.getIfNewPaint();
    }

    public static Paintbrush getPaintbrush() {
        return paintbrush;
    }

    public static void setPaintbrush(Paintbrush paintbrush) {
        PaintingContainer.paintbrush = paintbrush;
    }

    public void removeLastPainting() {
        paintings.remove(paintings.size() - 1);
        notifyListeners();
        newPaint = true;
    }

    public void addPainting(PaintStrokeToData paintStroke){
        paintings.add(paintStroke);
        newPaint = true;
        notifyListeners();
    }

    public void paintingSizeCounter(double layoutX, double layoutY){
        newPaint = false;
        if(layoutX > w - (PAINTING_AREA_RESIZING_CONSTANT)) {
            setFitWidth(layoutX + PAINTING_AREA_RESIZING_CONSTANT + Paintbrush.getSize());
        }
        if(layoutY > h - (PAINTING_AREA_RESIZING_CONSTANT)) {
            setFitHeight(layoutY + PAINTING_AREA_RESIZING_CONSTANT + Paintbrush.getSize());
        }
    }

    public void setPaintings(List<PaintStrokeToData> paintings) {
        this.paintings = paintings;
    }

    public List<PaintStrokeToData> getPaintings() {
        return paintings;
    }

    @Override
    public void setLayoutX(double x) {
        this.layoutX = x;
        notifyListeners();
    }

    @Override
    public double getLayoutX() {
        return layoutX;
    }

    @Override
    public void setLayoutY(double y) {
        this.layoutY = y;
        notifyListeners();
    }

    @Override
    public double getLayoutY() {
        return layoutY;
    }

    @Override
    public double getFitWidth() {
        return w;
    }

    @Override
    public void setFitWidth(double w) {
        this.w = w;
        notifyListeners();
    }

    @Override
    public double getFitHeight() {
        return h;
    }

    @Override
    public void setFitHeight(double h) {
        this.h = h;
        notifyListeners();
    }

    public boolean getStatus(){
        return isAlive;
    }

    public boolean getIfNewPaint(){
        return newPaint;
    }

    private void notifyListeners(){
        for (int i = 0; i < listeners.size(); i++)
            listeners.get(i).fireChange(this);
    }

    @Override
    public void add() {
        isAlive = true;
        newPaint = true;
        notifyListeners();
    }

    @Override
    public void remove() {
        isAlive = false;
        notifyListeners();
    }

    @Override
    public void addListener(ObserverI observer) {
        if(!listeners.contains(observer)){
            listeners.add(observer);
            observer.fireChange(this    );
        }
    }

    @Override
    public void removeListener(ObserverI observer) {
        listeners.remove(observer);
    }

    public List<ObserverI<PaintingContainer>> getListeners() {
        return listeners;
    }

    @Override
    public int hashCode() {
        return super.hashCode()*5;
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

    @Override
    public NoteObjectResizeableI duplicate() {
        return new PaintingContainer(this);
    }

}
