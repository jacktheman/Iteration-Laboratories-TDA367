package com.itlabs.fabnotes.noteobject.behavior;

import javafx.scene.input.MouseEvent;

/**
 * Created by aron on 2017-05-23.
 */
abstract class NoteObjectBehavior implements NoteObjectBehaviorI {
    @Override
    public void onMousePressed(MouseEvent mouseEvent) {}

    @Override
    public void onMouseReleased(MouseEvent mouseEvent) {}

    @Override
    public void onMouseEntered(MouseEvent mouseEvent) {}

    @Override
    public void onMouseExited(MouseEvent mouseEvent) {}

    @Override
    public void onMouseMoved(MouseEvent mouseEvent) {}

    @Override
    public void onMouseDragged(MouseEvent mouseEvent) {}
}
