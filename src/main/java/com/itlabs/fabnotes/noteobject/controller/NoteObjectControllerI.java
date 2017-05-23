package com.itlabs.fabnotes.noteobject.controller;

import javafx.scene.Node;
import com.itlabs.fabnotes.noteobject.model.NoteObjectI;

/**
 * Created by svante on 2017-04-06.
 */
public interface NoteObjectControllerI {
    Node getNode();
    NoteObjectI getModel();
}
