package com.itlabs.fabnotes.noteobject.controller;

import com.itlabs.fabnotes.noteobject.model.ImageContainer;
import com.itlabs.fabnotes.noteobject.service.ObserverBus;
import com.itlabs.fabnotes.note.event.AddNoteEvent;
import com.itlabs.fabnotes.note.event.Event;
import com.itlabs.fabnotes.noteobject.behavior.DragDropResizeBehavior;
import com.itlabs.fabnotes.noteobject.view.ImageContainerView;

import java.net.URL;

/**
 * Created by jackflurry on 2017-04-07.
 */
public class ImageContainerController extends NoteObjectController<ImageContainerView, ImageContainer> {

    public ImageContainerController(URL url, double layoutX, double layoutY) {
        super(new ImageContainerView(url, layoutX, layoutY), new ImageContainer(url.toString()));
        Event.addEvent(new AddNoteEvent(super.getModel()));
        super.setBehavior(new DragDropResizeBehavior(super.getModel(), super.getNode()));
        ObserverBus.addListener(super.getModel(), super.getNode());
    }

    public ImageContainerController(ImageContainer imageContainer){
            super(new ImageContainerView(imageContainer), new ImageContainer(imageContainer));
            super.getModel().setFitWidth(imageContainer.getFitWidth());
            super.getModel().setFitHeight(imageContainer.getFitHeight());
            Event.addEvent(new AddNoteEvent(super.getModel()));
            super.setBehavior(new DragDropResizeBehavior(super.getModel(), super.getNode()));
            ObserverBus.addListener(super.getModel(), super.getNode());

    }

    @Override
    public ImageContainerController clone() throws CloneNotSupportedException {
        return new ImageContainerController(super.getModel());
    }
}

