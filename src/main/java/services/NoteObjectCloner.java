package services;

import controllers.noteobject.ImageContainerController;
import controllers.noteobject.PaintingContainerController;
import controllers.noteobject.TableController;
import javafx.scene.Node;
import models.noteobject.ImageContainer;
import views.noteobject.ImageContainerView;
import views.noteobject.PaintingContainerView;
import views.noteobject.TableContainerView;

/**
 * Created by svante on 2017-05-09.
 */
public class NoteObjectCloner {

    private static Node node;

    private NoteObjectCloner(){}

    private static Node cloneImageContainer(){
        if (node instanceof ImageContainerView){
            ImageContainerView imageContainerView = (ImageContainerView) node;
            return new ImageContainerController(imageContainerView.getImage(),
                    imageContainerView.getLayoutX(), imageContainerView.getLayoutY()).getNode();
        }
        return null;
    }

    private static Node clonePaintingContainer(){
        if (node instanceof PaintingContainerView){
            PaintingContainerView paintingContainerView = (PaintingContainerView)node;
            return new PaintingContainerController(paintingContainerView).getNode();
        }
        return null;
    }

    private static Node cloneTableContainer(){
        if (node instanceof TableContainerView){
            TableContainerView tableContainerView = (TableContainerView) node;
            return null;
        }
        return null;
    }

    private static Node getCopiedObject(){
        return node;
    }

    private static void setCopiedObject(Node node){
        NoteObjectCloner.node = node;
    }

}
