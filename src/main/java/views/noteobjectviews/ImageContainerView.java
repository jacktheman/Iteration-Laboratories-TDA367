package views.noteobjectviews;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.net.URL;

/**
 * Created by jackflurry on 2017-04-07.
 */
public class ImageContainerView extends ImageView {

    public ImageContainerView(Image image, double layoutX, double layoutY){
        super(image);
        this.setLayoutX(layoutX);
        this.setLayoutY(layoutY);
        this.setId("image-view");
    }

    public ImageContainerView(URL url, double layoutX, double layoutY){
        super(new Image(url.toString()));
        this.setLayoutX(layoutX);
        this.setLayoutY(layoutY);
        this.setId("image-view");
    }


}
