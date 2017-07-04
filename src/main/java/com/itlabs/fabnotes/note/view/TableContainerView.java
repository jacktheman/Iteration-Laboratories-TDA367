package com.itlabs.fabnotes.note.view;

import com.itlabs.fabnotes.note.model.Note;
import com.itlabs.fabnotes.note.model.TableContainer;
import com.itlabs.fabnotes.note.utility.ArrayMatrix;
import com.itlabs.fabnotes.note.utility.observer.ObserverI;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.TilePane;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by alexandra on 2017-06-30.
 */
public class TableContainerView extends AnchorPane implements ObserverI<TableContainer> {

    private ArrayMatrix<TextContainerView> tableListView;
    private TilePane tilepane;

    public TableContainerView() {
        tableListView = new ArrayMatrix<>();
        tilepane = new TilePane(5, 5);

        this.getChildren().add(tilepane);

        tilepane.setPrefColumns(3);

        for (int i = 0; i < 3; i++) {
            tableListView.set(i, 0, createTableCell(""));
            tableListView.set(i, 1, createTableCell(""));
            tableListView.set(i, 2, createTableCell(""));
        }

        addToTilePane();
        
        this.setStyle("-fx-background-color: RED");
    }

    public TableContainerView(TableContainer tableContainer) {
        tableListView = new ArrayMatrix<>();
        tilepane = new TilePane(5, 5);

        this.getChildren().add(tilepane);
        tilepane.setPrefColumns(tableContainer.getWidth());
        setText(tableContainer.getWidth(), tableContainer.getHeight(), tableContainer.getTable());
        addToTilePane();
        this.setStyle("-fx-background-color: RED");
    }
    
    private TextContainerView createTableCell (String str) {
        TextContainerView textContainerView = new TextContainerView(str, 0, 0);
        textContainerView.switchToVisibleBorder();
        return textContainerView;
    }


    public void addColumn (List<String> list) {
        int x = tableListView.sizeWidth();
        for (int i = 0; i < list.size(); i++) {
            tableListView.set(x, i, createTableCell(list.get(i)));
        }
        tilepane.setPrefColumns(tilepane.getPrefColumns() + 1);
        addToTilePane();
    }

    public void removeColumn () {
        tableListView.removeColumn(tableListView.sizeWidth()-1);
        tilepane.setPrefColumns(tilepane.getPrefColumns() - 1);
        addToTilePane();
    }

    public void addRow (List<String> list) {
        int y = tableListView.sizeHeight();
        for (int i = 0; i <list.size(); i++){
            TextContainerView view = createTableCell(list.get(i));
            tableListView.set(i, y, view);
        }
    }

    public void removeRow () {
        tableListView.removeRow(tableListView.sizeHeight());
        addToTilePane();
    }

    public void addToTilePane () {
        tilepane.getChildren().clear();
        for (int i = 0; i <tableListView.sizeHeight(); i++){
            for (int j = 0; j < tableListView.sizeWidth(); j++) {
                tilepane.getChildren().add(tableListView.get(j, i));
            }
        }
    }

    public void setText (int width, int height, ArrayMatrix<String> arrayMatrix) {
        for (int i = 0; i < width; i++) {
            for (int j = 0; j < height; j++) {
                String str = arrayMatrix.get(i, j);
                if (str == null) {
                    str = "";
                }
                tableListView.set(i, j, createTableCell(str));
            }
        }
    }

    public void updateText(TableContainer tableContainer) {
        for (int i = 0; i < tableListView.sizeWidth(); i++){
            for (int j = 0; j < tableListView.sizeHeight(); j++){
                tableContainer.addText(i, j, tableListView.get(i, j).getText());
            }
        }
    }

    public ArrayMatrix<TextContainerView> getTableListView() {
        return tableListView;
    }

    @Override
    public void fireChange(TableContainer subject) {
        this.setLayoutX(subject.getLayoutX());
        this.setLayoutY(subject.getLayoutY());
        ArrayMatrix<String> table = subject.getTable();
        if (subject.getWidth() > this.tableListView.sizeWidth()){
            addColumn(subject.getColumn(subject.getWidth() - 1));
        } else if (subject.getWidth() < this.tableListView.sizeWidth()){
            removeColumn();
        }
        if (subject.getHeight() > tableListView.sizeHeight()){
            addRow(subject.getRow(subject.getHeight() - 1));
        } else if (subject.getHeight() < tableListView.sizeHeight()) {
            removeRow();
        }

        if (!Note.getCurrentNodes().contains(this)) {
            if (subject.isAlive()) {
                Note.getCurrentNodes().add(this);
                this.requestFocus();
            }
        } else {
            if (!subject.isAlive()) {
                Note.getCurrentNodes().remove(this);
            }
        }
    }
}
