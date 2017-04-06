package controllers.noteobjectcontrollers;

import javafx.beans.InvalidationListener;
import javafx.beans.Observable;
import javafx.scene.Node;
import javafx.scene.control.*;
import models.noteobjectmodels.TextContainerModel;
import views.noteobjectviews.TextContainerView;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by aron on 2017-04-06.
 */
public class TextContainerController extends NoteObjectController implements Observable {

    private TextArea textArea;
    private TextContainerModel textContainerModel;
    private List<InvalidationListener> listeners;

    public TextContainerController(String text, double layoutX, double layoutY) {
        super(new TextContainerView(text, layoutX, layoutY));
        this.textArea = (TextArea) super.getNode();
        this.textContainerModel = new TextContainerModel(text);
        this.textContainerModel.getTextHolder().textProperty().bind(textArea.textProperty());
        this.listeners = new ArrayList<>();
    }

    @Override
    public Node getNode() {
        return textArea;
    }

    @Override
    void onFocus() {

    }

    private void listener() {
        textArea.textProperty().addListener(e -> {
            int newWidth = (int) textContainerModel.getTextHolder().getLayoutBounds().getWidth() + 30; // + 30 because padding
            int newHeight = (int) textContainerModel.getTextHolder().getLayoutBounds().getHeight() + 30;
            if (newWidth != textArea.getPrefWidth())
                textArea.setPrefWidth(newWidth);
            if (newHeight != textArea.getPrefHeight())
                textArea.setPrefHeight(newHeight);

            if (textArea.getText().equals("")) {
                textArea.setStyle("-fx-border-color: transparent");
            } else {
                textArea.setStyle("-fx-border-color: lightskyblue");
            }
        });


        //Lambdauttryck: En listener som gör något när något händer i textArea
    }

    private void listener2() {
        textArea.focusedProperty().addListener(e -> {
            if (!textArea.isFocused()) {
                if (textArea.getText().equals("")) {
                    notifyListeners();
                } else {
                    textArea.setStyle("-fx-border-color: transparent");
                }
            } else {
                if (!textArea.getText().equals("")) {
                    textArea.setStyle("-fx-border-color: lightskyblue");
                }
            }


        });
    }

    @Override
    public void addListener(InvalidationListener invalidationListener) {
        listeners.add(invalidationListener);
    }

    @Override
    public void removeListener(InvalidationListener invalidationListener) {
        listeners.remove(invalidationListener);
    }

    private void notifyListeners(){
        for(InvalidationListener listener: listeners){
            listener.invalidated(this);
        }
    }
}
