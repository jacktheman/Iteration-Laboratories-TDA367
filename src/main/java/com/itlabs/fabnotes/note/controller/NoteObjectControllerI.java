package com.itlabs.fabnotes.note.controller;

import javafx.scene.Node;
import com.itlabs.fabnotes.note.model.noteobject.noteobject.NoteObjectI;

/**
 * Created by svante on 2017-04-06.
 */
public interface NoteObjectControllerI {
    Node getNode();
    NoteObjectI getModel();
}
