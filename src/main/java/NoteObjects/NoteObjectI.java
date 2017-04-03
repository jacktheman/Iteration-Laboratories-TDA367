package NoteObjects;

import javafx.scene.layout.Pane;

/**
 * Created by aron on 2017-04-03.
 */
public interface NoteObjectI {
    /**
     * Get the pane.
     * @return the Pane
     */
    Pane getPane();

    /**
     * Set the layout x position.
     * @param x the x position
     */
    void setLayoutX(int x);

    /**
     * Set the layout y position.
     * @param y the y position
     */
    void setLayoutY(int y);

    /**
     * Get the layout x position.
     * @return the x position
     */
    int getLayoutX();

    /**
     * Get the layout y position.
     * @return the y position
     */
    int getLayoutY();

}
