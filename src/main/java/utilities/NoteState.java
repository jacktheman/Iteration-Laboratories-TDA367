package utilities;

import javafx.scene.layout.AnchorPane;
import models.notemodel.NoteModel;

/**
 * Created by jackflurry on 2017-04-27.
 */
abstract class NoteState implements NoteStateI {

    private AnchorPane notePane;
    private NoteModel noteModel;

    NoteState(AnchorPane notePane, NoteModel noteModel){
        this.notePane = notePane;
        this.noteModel = noteModel;
    }

    AnchorPane getNotePane(){
        return notePane;
    }

    NoteModel getNoteModel() {
        return noteModel;
    }

    void setNoteModel(NoteModel noteModel) {
        this.noteModel = noteModel;
    }
}
