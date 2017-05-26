package com.itlabs.fabnotes.note.model;

import java.io.Serializable;

/**
 * Created by svante on 2017-05-12.
 */
public interface NoteObjectI extends Serializable {

    void setLayoutX(double d);

    double getLayoutX();

    void setLayoutY(double d);

    double getLayoutY();

    void add();

    void remove();

    NoteObjectI duplicate();
}
