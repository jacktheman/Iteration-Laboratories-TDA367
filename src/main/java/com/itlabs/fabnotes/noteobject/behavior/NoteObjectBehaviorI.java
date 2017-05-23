package com.itlabs.fabnotes.noteobject.behavior;

import javafx.scene.input.MouseEvent;

/**
 * Created by aron on 2017-05-03.
 */
public interface NoteObjectBehaviorI {
    void onMousePressed(MouseEvent mouseEvent);
    void onMouseReleased(MouseEvent mouseEvent);
    void onMouseEntered(MouseEvent mouseEvent);
    void onMouseExited(MouseEvent mouseEvent);
    void onMouseMoved(MouseEvent mouseEvent);
    void onMouseDragged(MouseEvent mouseEvent);
}
