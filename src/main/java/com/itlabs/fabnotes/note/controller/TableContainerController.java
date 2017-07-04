package com.itlabs.fabnotes.note.controller;

import com.itlabs.fabnotes.note.behavior.DragDropBehavior;
import com.itlabs.fabnotes.note.model.Note;
import com.itlabs.fabnotes.note.model.TableContainer;
import com.itlabs.fabnotes.note.view.TableContainerView;
import com.itlabs.fabnotes.note.view.TextContainerView;

import java.util.List;

/**
 * Created by alexandra on 2017-06-30.
 */
public class TableContainerController extends NoteObjectController<TableContainerView, TableContainer> {

    public TableContainerController(TableContainer tableContainer) {
        super(new TableContainerView(tableContainer), tableContainer);
        super.getModel().addListener(super.getNode());
        super.setBehavior(new DragDropBehavior(super.getModel(), super.getNode()));
        initTextPropertyListener();
    }

    public TableContainerController(double layoutX, double layoutY) {
        this(new TableContainer(layoutX, layoutY));
    }


    private void initTextPropertyListener() {
        for (int i = 0; i < super.getModel().getWidth(); i++) {
            for (int j =0; j < super.getModel().getHeight(); j++) {
                TextContainerView textContainerView = super.getNode().getTableListView().get(i, j);
                if (textContainerView != null) {
                    textContainerView.textProperty().addListener((value, oldValue, newValue) -> {
                        super.getNode().updateText(super.getModel());
                        textContainerView.updateTextContainerSize();
                    });
                }
            }
        }
    }


}
