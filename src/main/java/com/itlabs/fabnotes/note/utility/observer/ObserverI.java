package com.itlabs.fabnotes.note.utility.observer;

/**
 * Created by jackflurry on 2017-05-03.
 */
public interface ObserverI<T extends Object> {

    void fireChange(T subject);

}
