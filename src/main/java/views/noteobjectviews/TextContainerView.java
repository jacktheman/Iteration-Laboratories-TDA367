package views.noteobjectviews;

import javafx.scene.control.TextArea;

/**
 * Created by aron on 2017-04-06.
 */
public class TextContainerView extends TextArea {
    public TextContainerView(String text, double layoutX, double layoutY) {
        super(text);
        this.setLayoutX(layoutX);
        this.setLayoutY(layoutY);
        this.setPrefWidth(0);
        this.setPrefHeight(0);
        this.setWrapText(true);
    }

    public TextContainerView(double layoutX, double layoutY) {
        this("", layoutX, layoutY);
    }

    public void changeFocus() {
        if (this.isFocused()) {
            if (!this.getText().equals("")) {
                this.setStyle("-fx-border-color: lightskyblue");
            }
        } else {
            this.setStyle("-fx-border-color: transparent");
        }
    }
    
    public void changeTextContainerSize(double newWidth, double newHeight){
        if (newWidth != this.getPrefWidth())
            this.setPrefWidth(newWidth);
        if (newHeight != this.getPrefHeight())
            this.setPrefHeight(newHeight);
    }

    public void changeBorderColour(){
        if (this.getText().equals(""))
            this.setStyle("-fx-border-color: transparent");
        else
            this.setStyle("-fx-border-color: lightskyblue");
    }

}


