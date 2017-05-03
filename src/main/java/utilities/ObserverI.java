package utilities;

/**
 * Created by jackflurry on 2017-05-03.
 */
public interface ObserverI<T extends ObservableI> {

    void fireChange(T subject);

}
