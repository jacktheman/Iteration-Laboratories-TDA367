package com.itlabs.fabnotes.note.save;

import com.itlabs.fabnotes.note.controller.*;
import com.itlabs.fabnotes.note.model.*;

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
    private List<TableContainer> tableContainers;

    public NoteSave(String name, List<String> tags, List<NoteObjectI> models) {
        this.name = name;
        this.tags = tags;
        textContainers = new ArrayList<>();
        imageContainers = new ArrayList<>();
        paintingContainers = new ArrayList<>();
        tableContainers = new ArrayList<>();
        for (NoteObjectI model : models) {
            if (model instanceof TextContainer)
                textContainers.add(new TextContainer((TextContainer) model));
            else if (model instanceof ImageContainer)
                imageContainers.add(new ImageContainer((ImageContainer) model));
            else if (model instanceof PaintingContainer)
                paintingContainers.add(new PaintingContainer((PaintingContainer) model));
            else if (model instanceof  TableContainer)
                tableContainers.add(new TableContainer((TableContainer) model));
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
        models.addAll(tableContainers);
        return models;
    }

    public boolean loadControllers(){
        List<NoteObjectControllerI> controllers = new ArrayList<>();
        for (TextContainer textContainer : textContainers)
            controllers.add(new TextContainerController(textContainer));
        for (ImageContainer imageContainer : imageContainers)
            controllers.add(new ImageContainerController(imageContainer));
        for (PaintingContainer paintingContainer : paintingContainers)
            controllers.add(new PaintingContainerController(paintingContainer));
        for (TableContainer tableContainer : tableContainers)
            controllers.add(new TableContainerController(tableContainer));


        if (controllers.isEmpty())
            return false;

        setCurrentNote(controllers);
        setCurrentNodes(controllers);
        return true;
    }

    private void setCurrentNote(List<NoteObjectControllerI> controllers) {
        Note note = new Note(name);
        note.setTags(tags);
        for (NoteObjectControllerI controller : controllers)
            note.getModels().add(controller.getModel());
        Note.setCurrentNote(note);
    }

    private void setCurrentNodes(List<NoteObjectControllerI> controllers) {
        Note.getCurrentNodes().clear();
        for (NoteObjectControllerI controller : controllers)
            Note.getCurrentNodes().add(controller.getNode());
    }
}
