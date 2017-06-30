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

    public TableContainerController(double layoutX, double layoutY) {
        super(new TableContainerView(), new TableContainer(layoutX, layoutY));
        super.getModel().addListener(super.getNode());
        super.setBehavior(new DragDropBehavior(super.getModel(), super.getNode()));
        initFocusPropertyListener();
        initTextPropertyListener();
    }


    private void initTextPropertyListener() {
        for (List<TextContainerView> textContainerViewsList : super.getNode().getTableListView()) {
            for (TextContainerView textContainerView : textContainerViewsList) {
                textContainerView.textProperty().addListener((value, oldValue, newValue) -> {
                    super.getNode().updateText(super.getModel());
                    textContainerView.changeBorder();
                    textContainerView.updateTextContainerSize();
                });
            }
        }
    }

    private void initFocusPropertyListener() {
        for (List<TextContainerView> textContainerViewsList : super.getNode().getTableListView()) {
            for (TextContainerView textContainerView : textContainerViewsList) {
                super.getNode().focusedProperty().addListener(e -> {
                    textContainerView.changeBorder();
                });
            }
        }
    }
}
