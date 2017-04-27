package utilities;

import javafx.scene.input.MouseEvent;

/**
 * Created by jackflurry on 2017-04-27.
 */
public interface NoteStateI {

    public void setOnMousePressed(MouseEvent event);

    public void setOnMouseReleased(MouseEvent event);

    public void setOnMouseMove(MouseEvent event);

}
