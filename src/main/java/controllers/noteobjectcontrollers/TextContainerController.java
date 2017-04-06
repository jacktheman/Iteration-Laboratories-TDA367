package controllers.noteobjectcontrollers;

import javafx.scene.Node;
import views.noteobjectviews.TextContainerView;

/**
 * Created by aron on 2017-04-06.
 */
public class TextContainerController {
    //private <T> model; Will be implemented later in development

    private Node view;

    public TextContainerController(String text, double layoutX, double layoutY) {
        view = new TextContainerView(text, layoutX, layoutY);
    }
}
