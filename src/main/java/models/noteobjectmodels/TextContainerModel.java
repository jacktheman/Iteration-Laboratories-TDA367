package models.noteobjectmodels;

import javafx.beans.value.ObservableValue;
import javafx.scene.text.Text;
import utilities.ObservableI;
import utilities.ObserverI;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by aron on 2017-04-03.
 */
public class TextContainerModel implements ObservableI {

    /**
     * A Text object for resizing the TextArea.
     *
     * Purely for logic. The TextArea contains a ScrollPane,
     * which makes it so that we can't resize it dynamically
     * without extra information about the contents size on screen.
     */
    private Text textHolder;

    private List<ObserverI> listeners;

    public TextContainerModel(String text) {
        textHolder = new Text(text);
        listeners = new ArrayList<>();
    }

    public void bindTextProperties(ObservableValue<? extends String> textProperty) {
        textHolder.textProperty().bind(textProperty);
    }

    public double getWidth() {
        return textHolder.getLayoutBounds().getWidth();
    }

    public double getHeight() {
        return textHolder.getLayoutBounds().getHeight();
    }

    public void notifyListeners() {
        for (int i = 0; i < listeners.size(); i++) {
            listeners.get(i).fireChange(this);
        }
    }

    @Override
    public void addListener(ObserverI observer) {
        listeners.add(observer);
    }

    @Override
    public void removeListener(ObserverI observer) {
        listeners.remove(observer);
    }
}
