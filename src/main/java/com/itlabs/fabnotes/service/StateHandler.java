package com.itlabs.fabnotes.service;

import com.itlabs.fabnotes.noteobject.controller.PaintingContainerController;
import com.itlabs.fabnotes.state.PaintState;
import com.itlabs.fabnotes.state.NoteStateI;
import com.itlabs.fabnotes.state.WriteState;

/**
 * Created by jackflurry on 2017-05-03.
 */
public class StateHandler {

    private static StateHandler SINGLETON = new StateHandler();

    private NoteStateI state;

    private StateHandler(){
        state = WriteState.getInstance();
    }

    public NoteStateI getState() {
        return state;
    }

    public static StateHandler getInstance() {
        return SINGLETON;
    }

    private void setState(NoteStateI state) {
        this.state = state;
        notifyPaintings();
    }

    public void changeToWriteState() {
        this.setState(WriteState.getInstance());
    }

    public void changeToPaintState() {
        this.setState(PaintState.getInstance());
    }

    public boolean isWriteState() {
        return this.state == WriteState.getInstance();
    }

    private void notifyPaintings() {
        if (state == PaintState.getInstance())
            PaintingContainerController.changeToPaintState();
        else
            PaintingContainerController.changeToWriteState();
    }

}
