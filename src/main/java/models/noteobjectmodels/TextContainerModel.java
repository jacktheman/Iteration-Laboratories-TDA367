package models.noteobjectmodels;

import javafx.scene.text.Text;

/**
 * Created by aron on 2017-04-03.
 */
public class TextContainerModel {


    /**
     * A Text object for resizing the TextArea.
     *
     * Purely for logic. The TextArea contains a ScrollPane,
     * which makes it so that we can't resize it dynamically
     * without extra information about the contents size on screen.
     */
    private Text textHolder;

    public TextContainerModel(String text) {
        textHolder = new Text(text);
    }

    public Text getTextHolder(){
        return textHolder;
    }
}
