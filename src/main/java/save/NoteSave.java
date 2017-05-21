package save;

import controllers.noteobject.ImageContainerController;
import controllers.noteobject.NoteObjectControllerI;
import controllers.noteobject.PaintingContainerController;
import controllers.noteobject.TextContainerController;
import models.note.Note;
import models.noteobject.ImageContainer;
import models.noteobject.NoteObjectI;
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
    private List<String> tags;

    private List<TextContainer> textContainers;
    private List<ImageContainer> imageContainers;
    private List<PaintingContainer> paintingContainers;

    public NoteSave(String name, List<String> tags, List<NoteObjectI> models) {
        this.name = name;
        this.tags = tags;
        textContainers = new ArrayList<>();
        imageContainers = new ArrayList<>();
        paintingContainers = new ArrayList<>();
        for (NoteObjectI model : models) {
            if (model instanceof TextContainer)
                textContainers.add(new TextContainer((TextContainer) model));
            else if (model instanceof ImageContainer)
                imageContainers.add(new ImageContainer((ImageContainer) model));
            else if (model instanceof PaintingContainer)
                paintingContainers.add(new PaintingContainer((PaintingContainer) model));
        }
    }

    public NoteSave(Note note) {
        this(note.getName(), note.getTags(), note.getModels());
    }

    public String getName() {
        return this.name;
    }

    public List<String> getTags() {
        return this.tags;
    }

    public List<NoteObjectI> getModels() {
        List<NoteObjectI> models = new ArrayList<>();
        models.addAll(textContainers);
        models.addAll(imageContainers);
        models.addAll(paintingContainers);
        return models;
    }

    public List<NoteObjectControllerI> loadControllers(){
        List<NoteObjectControllerI> controllers = new ArrayList<>();
        for (TextContainer textContainer : textContainers)
            controllers.add(new TextContainerController(textContainer));
        for (ImageContainer imageContainer : imageContainers)
            controllers.add(new ImageContainerController(imageContainer));
        for (PaintingContainer paintingContainer : paintingContainers)
            controllers.add(new PaintingContainerController(paintingContainer));
        return controllers;
    }
}
