package com.itlabs.fabnotes.noteobject.observers;

/**
 * Created by jackflurry on 2017-05-03.
 */
public interface ObservableI<T extends Object>  {

    void addListener(ObserverI<T> observer);

    void removeListener(ObserverI<T> observer);

}
