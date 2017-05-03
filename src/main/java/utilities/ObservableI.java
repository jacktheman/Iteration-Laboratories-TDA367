package utilities;

/**
 * Created by jackflurry on 2017-05-03.
 */
public interface ObservableI {

    void addListener(ObserverI observer);

    void removeListener(ObserverI observer);

}
