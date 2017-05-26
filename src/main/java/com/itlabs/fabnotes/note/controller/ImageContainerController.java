package com.itlabs.fabnotes.note.controller;

import com.itlabs.fabnotes.note.model.ImageContainer;
import com.itlabs.fabnotes.note.behavior.DragDropResizeBehavior;
import com.itlabs.fabnotes.note.view.ImageContainerView;

import java.net.URL;

/**
 * Created by jackflurry on 2017-04-07.
 */
public class ImageContainerController extends NoteObjectController<ImageContainerView, ImageContainer> {

    public ImageContainerController(URL url, double layoutX, double layoutY) {
        super(new ImageContainerView(url, layoutX, layoutY), new ImageContainer(url.toString()));
        super.setBehavior(new DragDropResizeBehavior(super.getModel(), super.getNode()));
        super.getModel().addListener(super.getNode());
    }

    public ImageContainerController(ImageContainer imageContainer){
            super(new ImageContainerView(imageContainer), new ImageContainer(imageContainer));
            super.getModel().setFitWidth(imageContainer.getFitWidth());
            super.getModel().setFitHeight(imageContainer.getFitHeight());
            super.setBehavior(new DragDropResizeBehavior(super.getModel(), super.getNode()));
            super.getModel().addListener(super.getNode());
    }

    @Override
    public ImageContainerController clone() throws CloneNotSupportedException {
        return new ImageContainerController(super.getModel());
    }
}

