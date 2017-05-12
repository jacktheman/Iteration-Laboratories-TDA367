package models.noteobject;

import java.io.Serializable;

/**
 * Created by svante on 2017-05-12.
 */
public interface NoteObjectI extends Serializable{

    void setLayoutX(double d);

    double getLayoutX();

    void setLayoutY(double d);

    double getLayoutY();

    void add();

    void remove();

}
