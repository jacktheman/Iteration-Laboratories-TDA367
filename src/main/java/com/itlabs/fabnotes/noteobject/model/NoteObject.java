package com.itlabs.fabnotes.noteobject.model;

/**
 * Created by aron on 2017-05-12.
 */
abstract class NoteObject implements NoteObjectI {
    private static int modelAmount = 0;

    private int modelNumber;

    NoteObject() {
        modelNumber = modelAmount++;
    }

    private int getModelNumber() {
        return modelNumber;
    }

    @Override
    public int hashCode() {
        return (super.hashCode() + getModelNumber() + 2*super.hashCode()*getModelNumber());
    }

}
