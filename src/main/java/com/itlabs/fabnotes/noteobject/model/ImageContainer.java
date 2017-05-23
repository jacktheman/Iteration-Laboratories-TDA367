package com.itlabs.fabnotes.noteobject.model;

import javafx.scene.image.Image;
import com.itlabs.fabnotes.noteobject.utility.observer.ObservableI;
import com.itlabs.fabnotes.noteobject.utility.observer.ObserverI;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by svante on 2017-04-26.
 */
public class ImageContainer extends NoteObjectResizeable implements ObservableI {


    private boolean remove;


    private String URL;

    private List<ObserverI<ImageContainer>> observerIList;
    private double fitWidth;
    private double fitHeight;
    private double layoutX;
    private double layoutY;
    private double quota;



    ImageContainer(String URL, double width, double height){ //Testing constructor to avoid FX issues
        super();
        this.URL = URL;
        this.fitWidth = width;
        this.fitHeight = height;
        this.quota = Math.min(fitHeight / fitWidth, fitWidth/fitHeight);
        this.observerIList = new ArrayList<>();

    }


    public ImageContainer(String URL){
        super();
        this.URL = URL;
        Image image = new Image(URL);
        this.fitWidth = image.getWidth();
        this.fitHeight = image.getHeight();
        this.quota = Math.min(fitHeight / fitWidth, fitWidth / fitHeight);
        this.observerIList = new ArrayList<>();
    }

    public ImageContainer(ImageContainer imageContainer) {
        super();
        this.URL = imageContainer.getURL();
        this.layoutX = imageContainer.getLayoutX();
        this.layoutY = imageContainer.getLayoutY();
        this.fitWidth = imageContainer.getFitWidth();
        this.fitHeight = imageContainer.getFitHeight();
        this.quota = imageContainer.getQuota();
        this.observerIList = new ArrayList<>();
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
    public String getURL() {
        return URL;
    }

    public void setURL(String URL) {
        this.URL = URL;
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
        remove = false;
        fireChange();
    }

    @Override
    public void remove() {
        remove = true;
        fireChange();
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

    public double getQuota() { return this.quota; }

    @Override
    public void addListener(ObserverI observer) {
        if(!observerIList.contains(observer)) {
            observerIList.add(observer);
            observer.fireChange(this);
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
        return super.hashCode()*3;
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

    @Override
    public ImageContainer duplicate() {
        return new ImageContainer(this);
    }

    public boolean getRemove(){
        return remove;
    }
}
