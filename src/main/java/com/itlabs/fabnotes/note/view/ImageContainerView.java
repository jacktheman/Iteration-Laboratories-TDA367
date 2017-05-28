package com.itlabs.fabnotes.note.view;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import com.itlabs.fabnotes.note.model.Note;
import com.itlabs.fabnotes.note.model.ImageContainer;
import com.itlabs.fabnotes.note.model.NoteObjectI;
import com.itlabs.fabnotes.note.utility.observer.ObserverI;

import java.io.Serializable;
import java.net.URL;

/**
 * Created by jackflurry on 2017-04-07.
 */
public class ImageContainerView extends ImageView implements Serializable, ObserverI<ImageContainer> {

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
            if (!Note.getCurrentNodes().contains(this))
                Note.getCurrentNodes().add(this);
        } else {
            Note.getCurrentNodes().remove(this);
        }
    }
}
