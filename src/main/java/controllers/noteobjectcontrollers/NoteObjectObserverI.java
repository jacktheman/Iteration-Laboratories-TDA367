package controllers.noteobjectcontrollers;

/**
 * Created by svante on 2017-04-07.
 */
public interface NoteObjectObserverI {

    public void fireChange(NoteObjectControllerI subject);
}
