package com.itlabs.fabnotes.note.model.noteobject.noteobject;

import com.itlabs.fabnotes.note.utility.observer.ObservableI;
import com.itlabs.fabnotes.note.utility.observer.ObserverI;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by aron on 2017-04-03.
 */
public class TextContainer extends NoteObject implements ObservableI {

    public static final int PADDING = 30;

    private static final String DEFAULT_FONT = "Calibri";
    private static final int DEFAULT_FONT_SIZE = 12;

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
        this.isFocused = true;
        this.listeners = new ArrayList<>();
    }

    public TextContainer(TextContainer textContainer) {
        this(textContainer.getText(), textContainer.getLayoutX(), textContainer.getLayoutY());
        this.personalFontFamilyName = textContainer.getFontFamilyName();
        this.personalFontSize = textContainer.getFontSize();
        this.personalIsBold = textContainer.isBold();
        this.personalIsItalic = textContainer.isItalic();
    }

    public void setIsFocused(boolean isFocused) {
        this.isFocused = isFocused;
    }

    public boolean isFocused() {
        return this.isFocused;
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
    public TextContainer duplicate() {
        return new TextContainer(this);
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
}
