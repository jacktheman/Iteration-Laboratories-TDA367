package models.noteobject;

import observers.ObservableI;
import observers.ObserverI;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by aron on 2017-04-03.
 */
public class TextContainer extends NoteObject implements ObservableI {

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

    private List<ObserverI<TextContainer>> listeners;

    public TextContainer(String text, double layoutX, double layoutY) {
        super();
        this.text = text;
        this.layoutX = layoutX;
        this.layoutY = layoutY;
        this.personalFontFamilyName = fontFamilyName;
        this.personalFontSize = fontSize;
        this.personalIsBold = isBold;
        this.personalIsItalic = isItalic;
        this.styles = DEFAULT_BACKGROUND + ";";
        this.isFocused = true;
        this.addStyle(VISABLE_BORDER);
        this.listeners = new ArrayList<>();
    }

    public TextContainer(TextContainer textContainer) {
        this(textContainer.getText(), textContainer.getLayoutX(), textContainer.getLayoutY());
        this.personalFontFamilyName = textContainer.getFontFamilyName();
        this.personalFontSize = textContainer.getFontSize();
        this.personalIsBold = textContainer.isBold();
        this.personalIsItalic = textContainer.isItalic();
    }

    @Override
    public double getLayoutX() {
        return this.layoutX;
    }

    @Override
    public void setLayoutX(double layoutX) {
        this.layoutX = layoutX;
    }

    @Override
    public double getLayoutY() {
        return this.layoutY;
    }

    @Override
    public void setLayoutY(double layoutY) {
        this.layoutY = layoutY;
    }

    @Override
    public void add() {
        for (ObserverI<TextContainer> listener : listeners)
            listener.fireChange(this);
    }

    @Override
    public void remove() {
        for (ObserverI<TextContainer> listener : listeners)
            listener.fireChange(this);
    }

    public String getFont() {
        String font = CSS_FONT;
        if (this.personalIsBold)
            font += "bold ";
        if (this.personalIsItalic)
            font += "italic ";
        font += this.personalFontSize + "px \"" + this.personalFontFamilyName + "\"";
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
        if (text.equals("") || !this.isFocused) {
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

    public String getFontFamilyName() {
        return this.personalFontFamilyName;
    }

    public int getFontSize() {
        return this.personalFontSize;
    }

    public boolean isBold() {
        return this.personalIsBold;
    }

    public boolean isItalic() {
        return this.personalIsItalic;
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

    @Override
    public int hashCode() {
        return super.hashCode()*2;
    }

    @Override
    public boolean equals(Object o) {
        if (o.hashCode() == this.hashCode()) {
            if (o instanceof TextContainer) {
                return true;
            }
        }
        return false;
    }

    @Override
    public void addListener(ObserverI observer) {
        if (!listeners.contains(observer))
            listeners.add(observer);
    }

    @Override
    public void removeListener(ObserverI observer) {
        listeners.remove(observer);
    }

    @Override
    public NoteObjectI duplicate() {
        return new TextContainer(this);
    }
}
