package com.itlabs.fabnotes.noteobject.controller;

import javafx.scene.control.MenuItem;
import com.itlabs.fabnotes.noteobject.model.NoteObjectI;
import com.itlabs.fabnotes.noteobject.view.TableContainerView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by jackflurry on 2017-04-07.
 */
public class TableController extends NoteObjectController<TableContainerView, NoteObjectI> {

    public TableController(double x, double y, double rows, double columns) {
        super(new TableContainerView(rows, columns), null);
        super.getNode().setLayoutX(x);
        super.getNode().setLayoutY(y);
    }


    @Override
    List<MenuItem> initContextMenuItems() {
        List<MenuItem> menuItemList = new ArrayList<>();
        return menuItemList;
    }

}
