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
public class ImageContainer implements ObservableI {

    private Image image;
    private File file;

    private List<ObserverI<ImageContainer>> observerIList;
    private double fitWidth;
    private double fitHeight;
    private double layoutX;
    private double layoutY;
    private double quota;


    public ImageContainer(Image image){
        this.image = image;
        this.quota = Math.min(fitHeight / fitWidth, fitWidth / fitHeight);
        this.observerIList = new ArrayList<>();

    }
    public double getFitWidth() {
        return fitWidth;
    }

    public void setFitWidth(double fitWidth) {
        this.fitWidth = fitWidth;
        this.fireChange();
    }

    public double getFitHeight() {
        return fitHeight;
    }

    public void setFitHeight(double fitHeight) {
        this.fitHeight = fitHeight;
        this.fireChange();
    }

    public double getLayoutX() {
        return layoutX;
    }

    public void setLayoutX(double layoutX) {
        this.layoutX = layoutX;
        this.fireChange();
    }

    public double getLayoutY() {
        return layoutY;
    }

    public void setLayoutY(double layoutY) {
        this.layoutY = layoutY;
        this.fireChange();
    }

    public void setQuota(double quota) {
        this.quota = quota;
        this.fireChange();
    }

    public ImageContainer(File file){
        this.file = file;
    }

    public Image getImage(){
        return this.image;
    }

    public File getFile(){
        return this.file;
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
}
