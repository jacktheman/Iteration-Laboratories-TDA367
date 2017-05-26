package com.itlabs.fabnotes.note.model;

/**
 * Created by svante on 2017-05-12.
 */
public interface NoteObjectResizeableI extends NoteObjectI {

    double getFitWidth();

    void setFitWidth(double d);

    double getFitHeight();

    void setFitHeight(double d);
}
