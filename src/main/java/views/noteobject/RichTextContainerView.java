package views.noteobject;

import org.fxmisc.richtext.InlineCssTextArea;

import java.io.Serializable;

/**
 * Created by aron on 2017-05-09.
 */
public class RichTextContainerView extends InlineCssTextArea implements Serializable {

    private final static String DEFAULT_BACKGROUNF = "-fx-background-color: transparent";
    private final static String VISABLE_BORDER = "-fx-border-color: lightskyblue";
    private final static String INVISABLE_BORDER = "-fx-border-color: transparent";

    private String styles;

    public RichTextContainerView(String text, double layoutX, double layoutY) {
        super(text);
        this.setLayoutX(layoutX);
        this.setLayoutY(layoutY);
        this.setPrefWidth(15); //+15 just random small number
        this.setPrefHeight(15);
        this.setWrapText(true);
        this.styles = DEFAULT_BACKGROUNF + ";" + VISABLE_BORDER + ";";
    }

    public RichTextContainerView(double layoutX, double layoutY) {
        this("", layoutX, layoutY);
    }

    public void changeFocus() {
        if (this.isFocused()) {
            if (!this.getText().equals("")) {
                switchToVisibleBorder();
            }
        } else {
            switchToInvisibleBorder();
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
            this.switchToInvisibleBorder();
        else
            this.switchToVisibleBorder();
    }

    private void addStyle(String style) {
        if (!this.styles.contains(style)) {
            style = style.replace(";", "");
            this.styles += style + ";";
        }
    }

    private void removeStyle(String style) {
        style = style.replace(";", "");
        this.styles = this.styles.replace(style + ";", "");
    }

    private void switchToVisibleBorder() {
        this.removeStyle(INVISABLE_BORDER);
        this.addStyle(VISABLE_BORDER);
        this.setStyle(this.styles);
    }

    private void switchToInvisibleBorder() {
        this.removeStyle(VISABLE_BORDER);
        this.addStyle(INVISABLE_BORDER);
        this.setStyle(this.styles);
    }
}
