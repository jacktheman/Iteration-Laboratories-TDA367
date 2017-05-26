package com.itlabs.fabnotes.fxml.service.bridge;

import com.itlabs.fabnotes.note.controller.ImageContainerController;
import com.itlabs.fabnotes.note.controller.NoteObjectControllerI;
import com.itlabs.fabnotes.note.model.Note;
import com.itlabs.fabnotes.note.model.PaintingContainer;
import com.itlabs.fabnotes.note.model.TextContainer;
import com.itlabs.fabnotes.note.utility.paint.Paintbrush;
import javafx.scene.paint.Color;

import java.net.URL;

/**
 * Created by aron on 2017-05-23.
 */
public class NoteObjectBridge {
    public static void addImageToNote(URL url) {
        NoteObjectControllerI controller = new ImageContainerController(url, 0, 0);
        Note.getCurrentNote().addNoteObject(controller.getModel());
    }

    public static void setPaintbrush(String shape) {
        PaintingContainer.setPaintbrush(Paintbrush.parsePaintbrush(shape));
    }

    public static void setPaintbrushColor(Color color) {
        Paintbrush.setColor(color);
    }

    public static Color getPaintbrushColor() {
        return Paintbrush.getColor();
    }

    public static void setPaintbrushSize(double size) {
        Paintbrush.setSize(size);
    }

    public static double getPaintbrushSize() {
        return Paintbrush.getSize();
    }

    public static String getPaintbrush() {
        return PaintingContainer.getPaintbrush().toString();
    }

    public static void setIsItalic(boolean isItalic) {
        TextContainer.setIsItalic(isItalic);
    }

    public static void setIsBold(boolean isBold) {
        TextContainer.setIsBold(isBold);
    }

    public static void setFontFamilyName(String fontFamilyName) {
        TextContainer.setFontFamilyName(fontFamilyName);
    }

    public static void setFontSize(int fontSize) {
        TextContainer.setFontSize(fontSize);
    }

}
