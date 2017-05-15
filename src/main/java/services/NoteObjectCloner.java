package services;

import controllers.noteobject.ImageContainerController;
import controllers.noteobject.NoteObjectControllerI;
import javafx.scene.Node;
import models.note.Note;
import models.noteobject.ImageContainer;
import models.noteobject.NoteObjectI;
import models.noteobject.Table;
import views.noteobject.ImageContainerView;
import views.noteobject.TableContainerView;

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
