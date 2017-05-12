package views.noteobject;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import models.noteobject.ImageContainer;
import utilities.ObserverI;

import java.io.Serializable;
import java.net.URL;

/**
 * Created by jackflurry on 2017-04-07.
 */
public class ImageContainerView extends ImageView implements Serializable, ObserverI<ImageContainer> {

    public ImageContainerView(Image image, double layoutX, double layoutY){
        super(image);
        this.setFitWidth(image.getWidth());
        this.setFitHeight(image.getHeight());
        this.setLayoutX(layoutX);
        this.setLayoutY(layoutY);
        this.setId("image-view");
    }

    public ImageContainerView(URL url, double layoutX, double layoutY){
        super(new Image(url.toString()));
        this.setFitWidth(this.getImage().getWidth());
        this.setFitHeight(this.getImage().getHeight());
        this.setLayoutX(layoutX);
        this.setLayoutY(layoutY);
        this.setId("image-view");
    }


    @Override
    public void fireChange(ImageContainer subject) {
        this.setFitWidth(subject.getFitWidth());
        this.setFitHeight(subject.getFitHeight());
        this.setLayoutX(subject.getLayoutX());
        this.setLayoutY(subject.getLayoutY());
    }
}
