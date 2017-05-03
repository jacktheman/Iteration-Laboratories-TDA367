package services;

import controllers.noteobjectcontrollers.NoteObjectControllerI;
import controllers.noteobjectcontrollers.NoteObjectObserverI;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by aron on 2017-05-01.
 */
public class ObserverBus implements NoteObjectObserverI {

    private static List<ObserverBus> buses = new ArrayList<>();

    private NoteObjectObserverI observer;

    private ObserverBus(NoteObjectObserverI observer) {
        this.observer = observer;
    }

    public static void addListener(NoteObjectControllerI subject, NoteObjectObserverI observer) {
        ObserverBus bus = new ObserverBus(observer);
        buses.add(bus);
        subject.addListener(bus);
    }

    public static void removeListener(NoteObjectControllerI subject, NoteObjectObserverI observer) {
        for (ObserverBus bus : buses) {
            if (bus.observer == observer) {
                subject.removeListener(bus);
                buses.remove(bus);
                break;
            }
        }
    }

    @Override
    public void fireChange(NoteObjectControllerI subject) {
        System.out.println("Subject: " + subject.toString());
        System.out.println("Observer: " + observer.toString());
        System.out.println();
        observer.fireChange(subject);
    }
}
