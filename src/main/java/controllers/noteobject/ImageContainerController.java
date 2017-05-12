package controllers.noteobject;

import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
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

    @Override
    void onMouseDragged(MouseEvent event){
        super.getModel().setFitHeight(super.getNode().getFitHeight());
        super.getModel().setFitWidth(super.getNode().getFitWidth());
        super.getModel().setLayoutX(event.getSceneX());
        super.getModel().setLayoutY(event.getSceneY()    );
        System.out.println(super.getModel().getLayoutX());
    }


    public Image returnModelImage() {
        return super.getModel().getImage();
    }


}

