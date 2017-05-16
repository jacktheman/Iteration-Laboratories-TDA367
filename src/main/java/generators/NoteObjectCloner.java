package generators;

import controllers.noteobject.ImageContainerController;
import controllers.noteobject.NoteObjectControllerI;
import controllers.noteobject.PaintingContainerController;
import javafx.scene.Node;
import models.noteobject.ImageContainer;
import models.noteobject.NoteObjectI;
import models.noteobject.PaintingContainer;
import models.noteobject.Table;

/**
 * Created by svante on 2017-05-09.
 */
public class NoteObjectCloner {

    private static NoteObjectI model;

    private NoteObjectCloner(){}

    private static NoteObjectControllerI cloneImageContainer(){
        if (model instanceof ImageContainer){
            return new ImageContainerController((ImageContainer)NoteObjectCloner.model);
        }
        return null;
    }

    private static NoteObjectControllerI clonePaintingContainer(){
        if (model instanceof PaintingContainer) {
            return new PaintingContainerController((PaintingContainer) model);
        }
        return null;
    }

    private static Node cloneTableContainer(){
        if (model instanceof Table){
            return null;
        }
        return null;
    }

    public static NoteObjectI getCopiedObject(){
            NoteObjectControllerI temp;
            if((temp = cloneImageContainer()) != null){
                return temp.getModel();
            } else if((temp = clonePaintingContainer()) != null) {
                return temp.getModel();
            }
        return null;
    }

    public static void setCopiedObject(NoteObjectI model){
        NoteObjectCloner.model = model;
    }

    public static NoteObjectI getModel(){
        return model;
    }

}
