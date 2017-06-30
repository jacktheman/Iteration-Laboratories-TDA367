package com.itlabs.fabnotes.note.view;

import com.itlabs.fabnotes.note.model.TableContainer;
import com.itlabs.fabnotes.note.utility.observer.ObserverI;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.TilePane;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by alexandra on 2017-06-30.
 */
public class TableContainerView extends AnchorPane implements ObserverI<TableContainer> {

    private List<List<TextContainerView>> tableListView;
    private TilePane tilepane;

    public TableContainerView() {
        tableListView = new ArrayList<>();
        tilepane = new TilePane();

        this.getChildren().add(tilepane);

        tilepane.setPrefColumns(3);
    }


    public void addColumn (List<String> list) {
        List<TextContainerView> newColumn = new ArrayList<>();
        for (String string: list) {
            TextContainerView textContainerView = new TextContainerView(string, 0, 0);
            newColumn.add(textContainerView);
        }
        addToTilePane();
    }

    public void removeColumn () {
        List<TextContainerView> columns = tableListView.get(tableListView.size() - 1);
        for (TextContainerView textContainerView : columns) {
            tableListView.remove(textContainerView);
        }
        addToTilePane();
    }

    public void addRow (List<String> list) {
        for (int i = 0; i <list.size(); i++){
            TextContainerView view = new TextContainerView(list.get(i), 0, 0);
            tableListView.get(i).set(tableListView.get(0).size()-1, view);
        }
    }

    public void removeRow () {
        for (List<TextContainerView> list : tableListView){
            TextContainerView object = list.get(list.size() - 1);
            list.remove(object);
        }
        addToTilePane();
    }

    public void addToTilePane () {
        tilepane.getChildren().clear();
        for (int i = 0; i <tableListView.get(0).size(); i++){
            for (int j = 0; j < tableListView.size(); j++) {
                tilepane.getChildren().add(tableListView.get(j).get(i));
            }
        }
    }

    public void updateText(TableContainer tableContainer) {
        for (int i = 0; i < tableListView.size(); i++){
            for (int j = 0; j < tableListView.get(0).size(); j++){
                tableContainer.addText(i, j, tableListView.get(i).get(j).getText());
            }
        }
    }

    @Override
    public void fireChange(TableContainer subject) {
        this.setLayoutX(subject.getLayoutX());
        this.setLayoutY(subject.getLayoutY());
        List<List<String>> table = subject.getTable();
        if (table.size() > this.tableListView.size()){
            addColumn(table.get(table.size() - 1));
        } else if (table.size() < this.tableListView.size()){
            removeColumn();
        }
        if (table.get(0).size() > tableListView.get(0).size()){
            addRow(subject.getRow(table.get(0).size() - 1));
        } else if (table.get(0).size() < tableListView.get(0).size()) {
            removeRow();
        }
    }
}
