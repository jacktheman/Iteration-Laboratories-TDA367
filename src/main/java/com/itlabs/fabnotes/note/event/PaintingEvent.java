package com.itlabs.fabnotes.note.event;

import com.itlabs.fabnotes.note.model.PaintingContainer;

/**
 * Created by jackflurry on 2017-05-11.
 */
public class PaintingEvent extends Event<PaintingContainer> {

    public PaintingEvent(PaintingContainer model) {
        super(model);
    }

    @Override
    public void undo(){
        super.getModel().removeLastPainting();
        Event.getEvents().remove(this);
    }

}
