package models.noteobject;

import observers.ObservableI;
import observers.ObserverI;
import utilities.PaintStrokeToData;
import utilities.Paintbrush;

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

    private final int DEFAULT_CANVAS_SIZE = 100;
    private final int PAINTING_AREA_RESIZING_CONSTANT = 10;

    private double x;
    private double y;
    private double w;
    private double h;

    public PaintingContainer(double x, double y){
        super();
        paintings = new ArrayList<>();
        listeners = new ArrayList<>();
        this.x = x - DEFAULT_CANVAS_SIZE/2;
        this.y = y - DEFAULT_CANVAS_SIZE/2;
        this.w = DEFAULT_CANVAS_SIZE;
        setFitHeight(DEFAULT_CANVAS_SIZE);
        isAlive = true;
        newPaint = false;
    }

    public PaintingContainer(PaintingContainer model){
        super();
        paintings = new ArrayList<>(model.getPaintings());
        listeners = new ArrayList<>();
        this.x = model.getLayoutX();
        this.y = model.getLayoutY();
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

    public List<PaintStrokeToData> getPaintings() {
        return paintings;
    }

    @Override
    public void setLayoutX(double x) {
        this.x = x;
        notifyListeners();
    }

    @Override
    public double getLayoutX() {
        return x;
    }

    @Override
    public void setLayoutY(double y) {
        this.y = y;
        notifyListeners();
    }

    @Override
    public double getLayoutY() {
        return y;
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
