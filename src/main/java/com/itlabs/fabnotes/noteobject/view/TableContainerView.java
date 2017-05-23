package com.itlabs.fabnotes.noteobject.view;

import com.sun.javafx.geom.BaseBounds;
import com.sun.javafx.geom.transform.BaseTransform;
import com.sun.javafx.jmx.MXNodeAlgorithm;
import com.sun.javafx.jmx.MXNodeAlgorithmContext;
import com.sun.javafx.sg.prism.NGNode;
import javafx.scene.Node;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

import java.io.Serializable;

/**
 * Created by jackflurry on 2017-04-07.
 */
public class TableContainerView extends TableView implements Serializable {


    public TableContainerView(double rows, double columns) {
        for (int i = 1; i <= columns; i++) {
            TableColumn tableColumn = new TableColumn(Integer.toString(i));
            this.getColumns().add(tableColumn);

        }
    }

}
