package NoteObjects.Objects;

import NoteObjects.NoteObjectI;
import javafx.scene.Node;
import javafx.scene.layout.Pane;

/**
 * Created by aron on 2017-04-03.
 */
abstract class NoteObject implements NoteObjectI {

    private Pane pane = new Pane();

    void addToPane(Node... nodes) {
        pane.getChildren().addAll(nodes);
    }

    void removeFromPane(Node... nodes) {
        pane.getChildren().removeAll(nodes);
    }

    public Pane getPane() {
        return pane;
    }

    public void setLayoutX(int x) {
        pane.setLayoutX(x);
    }

    public void setLayoutY(int y) {
        pane.setLayoutY(y);
    }

    public int getLayoutX() {
        return (int) pane.getLayoutX();
    }

    public int getLayoutY() {
        return (int) pane.getLayoutY();
    }

    abstract void initPane();
}
