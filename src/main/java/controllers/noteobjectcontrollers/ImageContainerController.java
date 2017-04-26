package controllers.noteobjectcontrollers;

import javafx.scene.Node;
import javafx.scene.image.Image;
import models.noteobjectmodels.ImageContainerModel;
import views.noteobjectviews.ImageContainerView;

import java.net.URL;

/**
 * Created by jackflurry on 2017-04-07.
 */
public class ImageContainerController extends NoteObjectController<ImageContainerView> {

    private ImageContainerModel imageContainerModel;

    public ImageContainerController(Image image, double layoutX, double layoutY){
        super(new ImageContainerView(image, layoutX, layoutY));
        this.imageContainerModel = new ImageContainerModel(image);
    }

    public ImageContainerController(URL url, double layoutX, double layoutY){
        super(new ImageContainerView(url,layoutX, layoutY));
        this.imageContainerModel = new ImageContainerModel(new Image(url.toString()));
    }

    public Image returnModelImage(){
        return imageContainerModel.getImage();
    }

}
