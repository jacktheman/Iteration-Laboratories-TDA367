package models.noteobject;

import javafx.beans.value.ObservableValue;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;

/**
 * Created by aron on 2017-04-03.
 */
public class TextContainer {

    public static final int PADDING = 30;

    public static final String DEFAULT_FONT = "Calibri";
    public static final int DEFAULT_FONT_SIZE = 12;
    public static final String CSS_FONT = "-fx-font: ";

    private final static String DEFAULT_BACKGROUND = "-fx-background-color: transparent";
    private final static String VISABLE_BORDER = "-fx-border-color: lightskyblue";
    private final static String INVISABLE_BORDER = "-fx-border-color: transparent";

    private static String fontFamilyName = DEFAULT_FONT;
    private static int fontSize = DEFAULT_FONT_SIZE;
    private static boolean isBold = false;
    private static boolean isItalic = false;

    private String text;

    private String personalFontFamilyName;
    private int personalFontSize;
    private boolean personalIsBold;
    private boolean personalIsItalic;

    private double layoutX;
    private double layoutY;

    private String styles;

    private boolean isFocused;

    private Text textHolder;

    public TextContainer(String text, double layoutX, double layoutY) {
        this.text = text;
        this.layoutX = layoutX;
        this.layoutY = layoutY;
        this.textHolder = new Text(text);
        this.personalFontFamilyName = fontFamilyName;
        this.personalFontSize = fontSize;
        this.personalIsBold = isBold;
        this.personalIsItalic = isItalic;
        this.styles = DEFAULT_BACKGROUND + ";";
        this.isFocused = true;
        this.setFont();
        this.addStyle(VISABLE_BORDER);
    }

    public void bindTextProperties(ObservableValue<? extends String> textProperty) {
        textHolder.textProperty().bind(textProperty);
    }

    public double getLayoutX() {
        return this.layoutX;
    }

    public void setLayoutX(double layoutX) {
        this.layoutX = layoutX;
    }

    public double getLayoutY() {
        return this.layoutY;
    }

    public void setLayoutY(double layoutY) {
        this.layoutY = layoutY;
    }

    public double getWidth() {
        return textHolder.getLayoutBounds().getWidth();
    }

    public double getHeight() {
        return textHolder.getLayoutBounds().getHeight();
    }

    private void setFont() {
        Font font;
        if (personalIsBold && personalIsItalic)
            font = Font.font(this.personalFontFamilyName, FontWeight.BOLD, FontPosture.ITALIC, this.personalFontSize);
        else if (personalIsBold)
            font = Font.font(this.personalFontFamilyName, FontWeight.BOLD, this.personalFontSize);
        else if (personalIsItalic)
            font = Font.font(this.personalFontFamilyName, FontPosture.ITALIC, this.personalFontSize);
        else
            font = Font.font(this.personalFontFamilyName, this.personalFontSize);
        this.textHolder.setFont(font);
    }

    public String getFont() {
        String font = CSS_FONT;
        if (isBold)
            font += "bold ";
        if (isItalic)
            font += "italic ";
        font += this.personalFontSize + "px \"" + fontFamilyName + "\"";
        return font;
    }

    public void addStyle(String style) {
        style = style.replace(";", "");
        if (!this.styles.contains(style)) {
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
    }

    private void switchToInvisibleBorder() {
        this.removeStyle(VISABLE_BORDER);
        this.addStyle(INVISABLE_BORDER);
    }

    public void changeBorder() {
        if (this.textHolder.getText().length() == 0 || !this.isFocused) {
            switchToInvisibleBorder();
        } else {
            switchToVisibleBorder();
        }
    }

    public String getStyles() {
        return this.styles;
    }

    public void setIsFocused(boolean isFocused) {
        this.isFocused = isFocused;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getText() {
        return this.text;
    }

    public int getFontSize() {
        return this.personalFontSize;
    }

    public static void setFontFamilyName(String fontFamilyName) {
        TextContainer.fontFamilyName = fontFamilyName;
    }

    public static void setFontSize(int fontSize) {
        TextContainer.fontSize = fontSize;
    }

    public static void setIsBold(boolean isBold) {
        TextContainer.isBold = isBold;
    }

    public static void setIsItalic(boolean isItalic) {
        TextContainer.isItalic = isItalic;
    }
}
