package controllers.noteobject;

import javafx.scene.image.Image;
import models.noteobject.ImageContainer;
import models.noteobject.NoteObjectI;
import services.ObserverBus;
import events.AddNoteEvent;
import events.Event;
import controllers.noteobjectbehaviors.DragDropResizeBehavior;
import views.noteobject.ImageContainerView;

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
            super(new ImageContainerView(new Image(imageContainer.getURL()), imageContainer.getLayoutX(), imageContainer.getLayoutY()),
                    new ImageContainer(imageContainer.getURL()));
            Event.addEvent(new AddNoteEvent(super.getModel()));
            super.setBehavior(new DragDropResizeBehavior(super.getModel(), super.getNode()));
            ObserverBus.addListener(super.getModel(), super.getNode());

    }




    public Image returnModelImage() {
        return super.getModel().getImage();
    }


}

