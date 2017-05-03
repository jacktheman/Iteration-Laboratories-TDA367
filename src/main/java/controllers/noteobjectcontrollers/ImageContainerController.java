package controllers.noteobjectcontrollers;


import javafx.scene.Cursor;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import models.noteobjectmodels.ImageContainerModel;
import utilities.noteobjectbehaviors.DragDropBehavior;
import utilities.noteobjectbehaviors.NoteObjectBehaviorI;
import utilities.noteobjectbehaviors.ResizeBehavior;
import views.noteobjectviews.ImageContainerView;

import java.net.URL;


/**
 * Created by jackflurry on 2017-04-07.
 */
public class ImageContainerController extends NoteObjectController<ImageContainerView> {


    private NoteObjectBehaviorI noteObjectBehavior;

    private ResizeBehavior resizeBehavior;

    private ImageContainerModel imageContainerModel;

    public ImageContainerController(Image image, double layoutX, double layoutY) {
        super(new ImageContainerView(image, layoutX, layoutY));
        this.imageContainerModel = new ImageContainerModel(image);
        noteObjectBehavior = new DragDropBehavior(super.getNode());
    }

    public ImageContainerController(URL url, double layoutX, double layoutY) {
        super(new ImageContainerView(url, layoutX, layoutY));
        this.imageContainerModel = new ImageContainerModel(new Image(url.toString()));
        noteObjectBehavior = new DragDropBehavior(super.getNode());
    }

    public Image returnModelImage() {
        return imageContainerModel.getImage();
    }


}

