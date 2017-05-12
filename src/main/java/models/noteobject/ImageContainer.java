package models.noteobject;

import javafx.scene.image.Image;
import utilities.ObservableI;
import utilities.ObserverI;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by svante on 2017-04-26.
 */
public class ImageContainer extends NoteObjectResizeable implements ObservableI {

    private Image image;

    public String getURL() {
        return URL;
    }

    public void setURL(String URL) {
        this.URL = URL;
    }

    private String URL;

    private List<ObserverI<ImageContainer>> observerIList;
    private double fitWidth;
    private double fitHeight;
    private double layoutX;
    private double layoutY;
    private double quota;


    public ImageContainer(Image image){
        super();
        this.image = image;
        this.fitWidth = image.getWidth();
        this.fitHeight = image.getHeight();
        this.quota = Math.min(fitHeight / fitWidth, fitWidth / fitHeight);
        this.observerIList = new ArrayList<>();

    }

    public ImageContainer(String URL){
        this(new Image(URL));
        this.URL = URL;
    }

    @Override
    public double getFitWidth() {
        return fitWidth;
    }

    @Override
    public void setFitWidth(double fitWidth) {
        this.fitWidth = fitWidth;
        this.fireChange();
    }

    @Override
    public double getFitHeight() {
        return fitHeight;
    }

    @Override
    public void setFitHeight(double fitHeight) {
        this.fitHeight = fitHeight;
        this.fireChange();
    }

    @Override
    public double getLayoutX() {
        return layoutX;
    }

    @Override
    public void setLayoutX(double layoutX) {
        this.layoutX = layoutX;
        this.fireChange();
    }

    @Override
    public double getLayoutY() {
        return layoutY;
    }

    @Override
    public void add() {

    }

    @Override
    public void remove() {

    }

    @Override
    public void setLayoutY(double layoutY) {
        this.layoutY = layoutY;
        this.fireChange();
    }

    public void setQuota(double quota) {
        this.quota = quota;
        this.fireChange();
    }

    public Image getImage(){
        return this.image;
    }

    public double getQuota() { return this.quota; }

    @Override
    public void addListener(ObserverI observer) {
        if(!observerIList.contains(observer)) {
            observerIList.add(observer);
        }
    }

    @Override
    public void removeListener(ObserverI observer) {
        observerIList.remove(observer);
    }

    private void fireChange(){
        for (ObserverI observer : observerIList) {
            observer.fireChange(this);
        }
    }

    @Override
    public int hashCode() {
        return (super.hashCode() + super.getModelNumber() + 2*super.hashCode()*super.getModelNumber())*3;
    }

    @Override
    public boolean equals(Object o) {
        if (o.hashCode() == this.hashCode()) {
            if (o instanceof ImageContainer) {
                return true;
            }
        }
        return false;
    }
}
