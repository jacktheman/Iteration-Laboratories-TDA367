package controllers.noteobject;

import javafx.scene.image.Image;
import models.noteobject.ImageContainer;
import utilities.events.AddNoteEvent;
import utilities.events.Event;
import utilities.noteobjectbehaviors.DragDropResizeBehavior;
import views.noteobject.ImageContainerView;

import java.net.URL;

/**
 * Created by jackflurry on 2017-04-07.
 */
public class ImageContainerController extends NoteObjectController<ImageContainerView, ImageContainer> {

    public ImageContainerController(Image image, double layoutX, double layoutY) {
        super(new ImageContainerView(image, layoutX, layoutY), new ImageContainer(image));
        Event.addEvent(new AddNoteEvent(super.getNode()));
        super.setBehavior(new DragDropResizeBehavior(super.getNode()));
    }

    public ImageContainerController(URL url, double layoutX, double layoutY) {
        super(new ImageContainerView(url, layoutX, layoutY), new ImageContainer(new Image(url.toString())));
        Event.addEvent(new AddNoteEvent(super.getNode()));
        super.setBehavior(new DragDropResizeBehavior(super.getNode()));
    }

    public Image returnModelImage() {
        return super.getModel().getImage();
    }


}

