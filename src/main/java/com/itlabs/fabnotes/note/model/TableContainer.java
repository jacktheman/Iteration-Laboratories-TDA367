package com.itlabs.fabnotes.note.model;

import com.itlabs.fabnotes.note.utility.observer.ObservableI;
import com.itlabs.fabnotes.note.utility.observer.ObserverI;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by alexandra on 2017-06-30.
 */
public class TableContainer extends NoteObject implements ObservableI{

    private List <List<String>> tableList;
    private double layoutX;
    private double layoutY;

    private List<ObserverI<TableContainer>> listeners;

    public TableContainer() {
        tableList = new ArrayList<>();
        layoutX = 0;
        layoutY = 0;

        listeners = new ArrayList<>();

        for (int i = 0; i < 3; i++){
            List<String> column = new ArrayList<String>();
            tableList.add(column);
            column.add("");
            column.add("");
            column.add("");
        }
    }

    public TableContainer(double layoutX, double layoutY){
        this();
        this.layoutX = layoutX;
        this.layoutY = layoutY;
    }

    public void addColumn() {
        List<String> column = new ArrayList<String> ();
        for (int i = 0; i < getNumberOfRows(); i ++) {
            column.add("");
        }
        tableList.add(column);
    }

    public void addRow() {
        for (List<String> column: tableList){
            column.add("");
        }
    }

    public void deleteColumn(int i) {
        tableList.remove(i);
    }

    public void deleteRow(int i) {
        for (List<String> column : tableList) {
            column.remove(i);
        }
    }

    public int getNumberOfColumns() {
        return tableList.size();
    }

    public int getNumberOfRows() {
        return tableList.get(0).size();
    }

    public void addText(int x, int y, String str) {
        tableList.get(x).set(y, str);
    }

    @Override
    public void setLayoutX(double d) {
        this.layoutX = d;
    }

    @Override
    public double getLayoutX() {
        return layoutX;
    }

    @Override
    public void setLayoutY(double d) {
        this.layoutY = d;
    }

    @Override
    public double getLayoutY() {
        return layoutY;
    }

    @Override
    public void add() {
        for (ObserverI<TableContainer> observer : listeners){
            observer.fireChange(this);
        }
    }

    @Override
    public void remove() {
        for (ObserverI<TableContainer> observer : listeners){
            observer.fireChange(this);
        }
    }

    @Override
    public NoteObjectI duplicate() {
        return null;
    }

    @Override
    public void addListener(ObserverI observer) {
        listeners.add(observer);
    }

    @Override
    public void removeListener(ObserverI observer) {
        listeners.remove(observer);
    }
}
