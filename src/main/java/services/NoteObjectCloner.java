package services;

import controllers.fxml.MainPageController;
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


    private static Node cloneTableContainer(){
        if (node instanceof TableContainerView){
            TableContainerView tableContainerView = (TableContainerView) node;
            return null;
        }
        return null;
    }

    public static Node getCopiedObject(){
        if (MainPageController.getCurrentNote().getNodes().contains(node)) {
            Node temp;
            if((temp = cloneImageContainer()) != null){
            } else if ((temp = cloneTableContainer()) != null){
            }
            node = temp;
        }
        return node;
    }

    public static void setCopiedObject(Node node){
        NoteObjectCloner.node = node;
    }

}