package com.itlabs.fabnotes.note.controller;

import com.itlabs.fabnotes.note.behavior.DragDropBehavior;
import com.itlabs.fabnotes.note.model.Note;
import com.itlabs.fabnotes.note.model.TableContainer;
import com.itlabs.fabnotes.note.view.TableContainerView;
import com.itlabs.fabnotes.note.view.TextContainerView;
import javafx.scene.control.MenuItem;

import java.util.List;

/**
 * Created by alexandra on 2017-06-30.
 */
public class TableContainerController extends NoteObjectController<TableContainerView, TableContainer> {

    private static final String ADD_COLUMN = "Lägg till kolumn";
    private static final String REMOVE_COLUMN = "Ta bort kolumn";
    private static final String ADD_ROW = "Lägg till rad";
    private static final String REMOVE_ROW = "Ta bort rad";

    public TableContainerController(TableContainer tableContainer) {
        super(new TableContainerView(tableContainer), tableContainer);
        super.getModel().addListener(super.getNode());
        super.setBehavior(new DragDropBehavior(super.getModel(), super.getNode()));
        initTextPropertyListener();
        initContextMenu();
    }

    public TableContainerController(double layoutX, double layoutY) {
        this(new TableContainer(layoutX, layoutY));
    }

    private void initContextMenu () {
        super.getContextMenu().getItems().addAll(createColumn(), removeColumn(), createRow(), removeRow());
    }

    private MenuItem createColumn () {
        MenuItem addColumn = new MenuItem(ADD_COLUMN);
        addColumn.setOnAction(e -> {
            super.getModel().addColumn();
        });
        return addColumn;
    }

    private MenuItem removeColumn () {
        MenuItem removeColumn = new MenuItem(REMOVE_COLUMN);
        removeColumn.setOnAction(e -> {
            super.getModel().deleteColumn(super.getModel().getWidth()-1);
        });
        return removeColumn;
    }

    private MenuItem createRow () {
        MenuItem addRow = new MenuItem(ADD_ROW);
        addRow.setOnAction(e -> {
            super.getModel().addRow();
        });
        return addRow;
    }

    private MenuItem removeRow () {
        MenuItem removeRow = new MenuItem(REMOVE_ROW);
        removeRow.setOnAction(e -> {
            super.getModel().deleteRow(super.getModel().getHeight()-1);
        });
        return removeRow;
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
