package com.itlabs.fabnotes.note.utility;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by alexandra on 2017-07-04.
 */
public class ArrayMatrix<T> {

    private ArrayList<ArrayList<T>> arrayMatrix;

    public ArrayMatrix() {
        arrayMatrix = new ArrayList<>();
    }

    public T get(int x, int y){
        if (x <= arrayMatrix.size() - 1 && y <= arrayMatrix.get(0).size() - 1 ) {
            return arrayMatrix.get(x).get(y);
        }
        return null;
    }

    public void set (int x, int y, T value) {
        if (x > arrayMatrix.size() - 1){
            arrayMatrix.add(x, new ArrayList<>());
        }
        arrayMatrix.get(x).add(y, value);
    }

    public void remove (T value) {
        for (int i = 0; i < arrayMatrix.size(); i++) {
            for (int j = 0; j < arrayMatrix.get(i).size(); j++) {
                if (arrayMatrix.get(i).get(j) == value) {
                    arrayMatrix.get(i).remove(i);
                    return;
                }
            }
        }
    }

    public void removeColumn (int x) {
        arrayMatrix.remove(x);
    }

    public void removeRow (int y) {
        for (List<T> value : arrayMatrix) {
            value.remove(y);
        }
    }

    public int sizeWidth () {
        return arrayMatrix.size();
    }

    public int sizeHeight() {
        int height = 0;
        for (List<T> value: arrayMatrix){
            if (value.size() > height) {
                height = value.size();
            }
        }
     return height;
    }

    public static <T> void transferTable (ArrayMatrix<T> from, ArrayMatrix<T> to) {
        for (int i = 0; i < from.sizeWidth(); i++) {
            for (int j= 0; j < from.sizeHeight(); j++) {
                T t = from.get(i, j);
                if (t != null) {
                    to.set(i, j, t);
                }
            }
        }
    }

    public void transferTo (ArrayMatrix<T> to) {
        transferTable(this, to);
    }


}
