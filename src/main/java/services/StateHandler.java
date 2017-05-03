package services;

import utilities.ObservableI;
import utilities.ObserverI;
import utilities.state.NoteStateI;
import utilities.state.WriteState;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by jackflurry on 2017-05-03.
 */
public class StateHandler implements ObservableI{

    private static StateHandler SINGLETON = new StateHandler();

    private List<ObserverI<StateHandler>> observers;

    private NoteStateI state;

    private StateHandler(){
        observers = new ArrayList<>();
        state = WriteState.getInstance();
    }

    public NoteStateI getState() {
        return state;
    }

    public static StateHandler getInstance() {
        return SINGLETON;
    }

    public void setState(NoteStateI state) {
        this.state = state;
        notifyListeners();
    }

    @Override
    public void addListener(ObserverI observer) {
        observers.add(observer);
    }

    @Override
    public void removeListener(ObserverI observer) {
        observers.remove(observer);
    }

    private void notifyListeners() {
        for (ObserverI<StateHandler> observer : observers)
            observer.fireChange(this);
    }
}
