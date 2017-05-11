package utilities;

import controllers.noteobject.NoteObjectControllerI;
import controllers.noteobject.TextContainerController;
import models.noteobject.ImageContainer;
import models.noteobject.PaintingContainer;
import models.noteobject.TextContainer;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by aron on 2017-05-11.
 */
public class NoteSave implements Serializable {
    private String name;
    private String tags;

    private List<TextContainer> textContainers;
    private List<ImageContainer> imageContainers;
    private List<PaintingContainer> paintingContainers;

    public NoteSave(String name, String tags, List<Object> models) {
        this.name = name;
        this.tags = tags;
        textContainers = new ArrayList<>();
        imageContainers = new ArrayList<>();
        paintingContainers = new ArrayList<>();
        for (Object objetc : models) {
            if (objetc instanceof TextContainer)
                textContainers.add((TextContainer) objetc);
            else if (objetc instanceof ImageContainer)
                imageContainers.add((ImageContainer) objetc);
            else if (objetc instanceof PaintingContainer)
                paintingContainers.add((PaintingContainer) objetc);
        }
    }

    public String getName() {
        return this.name;
    }

    public String getTags() {
        return this.tags;
    }

    public List<Object> getModels() {
        List<Object> models = new ArrayList<>();
        models.addAll(textContainers);
        models.addAll(imageContainers);
        models.addAll(paintingContainers);
        return models;
    }

    public List<NoteObjectControllerI> loadControllers(){
        List<NoteObjectControllerI> controllers = new ArrayList<>();
        controllers.addAll(loadTextContainers());
        //controllers.addAll(loadImageContainers());
        //controllers.addAll(loadPaintingtContainers());
        return controllers;
    }

    private List<NoteObjectControllerI> loadTextContainers() {
        List<NoteObjectControllerI> controllers = new ArrayList<>();
        for (TextContainer textContainer : textContainers)
            controllers.add(new TextContainerController(textContainer));
        return controllers;
    }

    private List<NoteObjectControllerI> loadImageContainers() {
        return null;
    }

    private List<NoteObjectControllerI> loadPaintingtContainers() {
        return null;
    }
}
