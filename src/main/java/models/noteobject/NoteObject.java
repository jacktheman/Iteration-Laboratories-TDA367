package models.noteobject;

/**
 * Created by aron on 2017-05-12.
 */
abstract class NoteObject implements NoteObjectI {
    private static int modelAmount = 0;

    private int modelNumber;

    NoteObject() {
        modelNumber = modelAmount++;
    }

    int getModelNumber() {
        return modelNumber;
    }
}
