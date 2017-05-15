package views.noteobject;

import controllers.fxml.MainPageController;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import models.noteobject.ImageContainer;
import models.noteobject.NoteObjectI;
import utilities.ObserverI;

import java.io.Serializable;
import java.net.URL;

/**
 * Created by jackflurry on 2017-04-07.
 */
public class ImageContainerView extends ImageView implements Serializable, ObserverI<ImageContainer> {

    public ImageContainerView(Image image, double layoutX, double layoutY) {
        super(image);
        this.setFitWidth(image.getWidth());
        this.setFitHeight(image.getHeight());
        this.setLayoutX(layoutX);
        this.setLayoutY(layoutY);
        this.setId("image-view");
    }


    public ImageContainerView(URL url, double layoutX, double layoutY) {
        super(new Image(url.toString()));
        this.setFitWidth(this.getImage().getWidth());
        this.setFitHeight(this.getImage().getHeight());
        this.setLayoutX(layoutX);
        this.setLayoutY(layoutY);
        this.setId("image-view");
    }

    public ImageContainerView(NoteObjectI model){
        super(new Image(((ImageContainer)model).getURL()));
        this.setFitWidth(this.getImage().getWidth());
        this.setFitHeight(this.getImage().getHeight());
        this.setLayoutX(model.getLayoutX());
        this.setLayoutY(model.getLayoutY());
        this.setId("image-view");
    }


    @Override
    public void fireChange(ImageContainer subject) {
        if (!subject.getRemove()) {
            this.setFitWidth(subject.getFitWidth());
            this.setFitHeight(subject.getFitHeight());
            this.setLayoutX(subject.getLayoutX());
            this.setLayoutY(subject.getLayoutY());
            if (!MainPageController.getCurrentNodes().contains(this))
                MainPageController.getCurrentNodes().add(this);
        } else {
            MainPageController.getCurrentNodes().remove(this);
        }
    }
}
