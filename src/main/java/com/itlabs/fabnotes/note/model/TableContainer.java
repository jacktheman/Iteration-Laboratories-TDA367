package com.itlabs.fabnotes.note.model;

import com.itlabs.fabnotes.note.utility.ArrayMatrix;
import com.itlabs.fabnotes.note.utility.observer.ObservableI;
import com.itlabs.fabnotes.note.utility.observer.ObserverI;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by alexandra on 2017-06-30.
 */
public class TableContainer extends NoteObject implements ObservableI{

    private ArrayMatrix<String> tableList;
    private double layoutX;
    private double layoutY;
    private boolean alive;
    private int width;
    private int height;

    private List<ObserverI<TableContainer>> listeners;

    private static final int DEFAULT_WIDTH = 3;
    private static final int DEFAULT_HEIGHT = 3;

    public TableContainer() {
        tableList = new ArrayMatrix<>();
        layoutX = 0;
        layoutY = 0;
        alive = false;
        width = DEFAULT_WIDTH;
        height = DEFAULT_HEIGHT;

        listeners = new ArrayList<>();
    }

    public TableContainer(TableContainer tableContainer) {
        this.layoutX = tableContainer.getLayoutX();
        this.layoutY = tableContainer.getLayoutY();
        this.width = tableContainer.getWidth();
        this.height = tableContainer.getHeight();
        listeners = new ArrayList<>();
        alive = false;
        tableList = new ArrayMatrix<>();
        tableContainer.getTable().transferTo(tableList);
    }

    public TableContainer(double layoutX, double layoutY){
        this();
        this.layoutX = layoutX;
        this.layoutY = layoutY;
    }

    public void addColumn() {
        width++;
        fireChange();
    }

    public void addRow() {
        height++;
        fireChange();
    }

    public void deleteColumn(int i) {
        if (width > 1 ) {
            tableList.removeColumn(i);
            width--;
            fireChange();
        }
    }

    public void deleteRow(int i) {
        if (height > 1) {
            tableList.removeRow(i);
            height--;
            fireChange();
        }
    }

    public void addText(int x, int y, String str) {
        tableList.set(x, y, str);
    }

    private void fireChange () {
        for (ObserverI<TableContainer> observer : listeners){
            observer.fireChange(this);
        }
    }

    public List<String> getAllContents () {
        ArrayList<String> strings = new ArrayList<>();
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                String temp = tableList.get(j, i);
                if (temp == null) {
                    temp = "";
                }
                strings.add(temp);
            }
        }
        return strings;
    }

    public ArrayMatrix<String> getTable () {
        return tableList;
    }

    public List<String> getRow (int i) {
        List<String> row = new ArrayList<>();
        for (int x = 0; x < width; x++) {
            String str = tableList.get(x, i);
            if (str == null) {
                str = "";
            }
            row.add(str);
        }
        return row;
    }

    public List<String> getColumn (int i) {
        List<String> column = new ArrayList<>();
        for (int y = 0; y < height; y++) {
            String str = tableList.get(i, y);
            if (str == null) {
                str = "";
            }
            column.add(str);
        }
        return column;
    }

    public boolean isAlive (){
        return alive;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public void setWidth(int i) {
        width = i;
    }

    public void setHeight(int i){
        height = i;
    }

    @Override
    public void setLayoutX(double d) {
        this.layoutX = d;
        fireChange();
    }

    @Override
    public double getLayoutX() {
        return layoutX;
    }

    @Override
    public void setLayoutY(double d) {
        this.layoutY = d;
        fireChange();
    }

    @Override
    public double getLayoutY() {
        return layoutY;
    }

    @Override
    public void add() {
        alive = true;
        fireChange();
    }

    @Override
    public void remove() {
        alive = false;
        fireChange();
    }

    @Override
    public NoteObjectI duplicate() {
        TableContainer tableContainer = new TableContainer();
        tableContainer.setWidth(getWidth());
        tableContainer.setHeight(getHeight());
        tableList.transferTo(tableContainer.getTable());
        return tableContainer;
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
