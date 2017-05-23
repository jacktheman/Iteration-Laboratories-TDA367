package services;

import controllers.noteobject.ImageContainerController;
import controllers.noteobject.NoteObjectControllerI;
import models.note.Note;
import models.noteobject.PaintingContainer;
import models.noteobject.TextContainer;
import utilities.Paintbrush;

import java.net.URL;

/**
 * Created by aron on 2017-05-23.
 */
public class NoteObjectConfigHelper {
    public static void addImageToNote(URL url) {
        NoteObjectControllerI controller = new ImageContainerController(url, 0, 0);
        Note.getCurrentNote().addNoteObject(controller.getModel());
    }

    public static void setPaintbrush(String shape) {
        PaintingContainer.setPaintbrush(Paintbrush.parsePaintbrush(shape));
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
