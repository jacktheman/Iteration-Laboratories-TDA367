package controllers.noteobject;


import controllers.fxml.MainPageController;
import javafx.scene.control.MenuItem;
import javafx.scene.image.Image;
import models.noteobject.ImageContainer;
import utilities.noteobjectbehaviors.DragDropBehavior;
import utilities.noteobjectbehaviors.DragDropResizeBehavior;
import utilities.noteobjectbehaviors.ResizeBehavior;
import views.noteobject.ImageContainerView;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;


/**
 * Created by jackflurry on 2017-04-07.
 */
public class ImageContainerController extends NoteObjectController<ImageContainerView> {

    private ImageContainer imageContainerModel;

    public ImageContainerController(Image image, double layoutX, double layoutY) {
        super(new ImageContainerView(image, layoutX, layoutY));
        this.imageContainerModel = new ImageContainer(image);
        super.setBehavior(new DragDropResizeBehavior(super.getNode()));
    }

    public ImageContainerController(URL url, double layoutX, double layoutY) {
        super(new ImageContainerView(url, layoutX, layoutY));
        this.imageContainerModel = new ImageContainer(new Image(url.toString()));
        super.setBehavior(new DragDropResizeBehavior(super.getNode()));
    }


    public Image returnModelImage() {
        return imageContainerModel.getImage();
    }


}

