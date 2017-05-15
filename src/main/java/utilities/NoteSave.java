package utilities;

import controllers.noteobject.ImageContainerController;
import controllers.noteobject.NoteObjectControllerI;
import controllers.noteobject.TextContainerController;
import models.note.Note;
import models.noteobject.ImageContainer;
import models.noteobject.NoteObjectI;
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
    //private List<PaintingContainer> paintingContainers;

    public NoteSave(String name, String tags, List<NoteObjectI> models) {
        this.name = name;
        this.tags = tags;
        textContainers = new ArrayList<>();
        imageContainers = new ArrayList<>();
        //paintingContainers = new ArrayList<>();
        for (NoteObjectI model : models) {
            if (model instanceof TextContainer)
                textContainers.add(new TextContainer((TextContainer) model));
            else if (model instanceof ImageContainer)
                imageContainers.add(new ImageContainer(((ImageContainer) model).getURL()));
            //else if (model instanceof PaintingContainer)
                //paintingContainers.add(new PaintingContainer());
        }
    }

    public NoteSave(Note note) {
        this(note.getName(), note.getTags(), note.getModels());
    }

    public String getName() {
        return this.name;
    }

    public String getTags() {
        return this.tags;
    }

    public List<NoteObjectI> getModels() {
        List<NoteObjectI> models = new ArrayList<>();
        models.addAll(textContainers);
        models.addAll(imageContainers);
        //models.addAll(paintingContainers);
        return models;
    }

    public List<NoteObjectControllerI> loadControllers(){
        List<NoteObjectControllerI> controllers = new ArrayList<>();
        controllers.addAll(loadTextContainers());
        controllers.addAll(loadImageContainers());
        //controllers.addAll(loadPaintingContainers());
        return controllers;
    }

    private List<NoteObjectControllerI> loadTextContainers() {
        List<NoteObjectControllerI> controllers = new ArrayList<>();
        for (TextContainer textContainer : textContainers)
            controllers.add(new TextContainerController(textContainer));
        return controllers;
    }

    private List<NoteObjectControllerI> loadImageContainers() {
        List<NoteObjectControllerI> controllers = new ArrayList<>();
        for (ImageContainer imageContainer : imageContainers)
            controllers.add(new ImageContainerController(imageContainer));
        return controllers;
    }

    private List<NoteObjectControllerI> loadPaintingContainers() {
        return null;
    }
}
