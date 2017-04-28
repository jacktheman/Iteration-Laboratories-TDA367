package utilities;

import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;

/**
 * Created by jackflurry on 2017-04-27.
 */
public interface NoteStateI {

    public void setOnMousePressed(MouseEvent event, AnchorPane notePane);

    public void setOnMouseReleased(MouseEvent event, AnchorPane notePane);

    public void setOnMouseMove(MouseEvent event, AnchorPane notePane);

}
