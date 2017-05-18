package observers;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by aron on 2017-05-01.
 */
public class ObserverBus implements ObserverI {

    private static List<ObserverBus> buses = new ArrayList<>();

    private ObserverI observer;

    private ObserverBus(ObserverI observer) {
        this.observer = observer;
    }

    public static void addListener(ObservableI subject, ObserverI observer) {
        ObserverBus bus = new ObserverBus(observer);
        buses.add(bus);
        subject.addListener(bus);
    }

    public static void removeListener(ObservableI subject, ObserverI observer) {
        for (ObserverBus bus : buses) {
            if (bus.observer == observer) {
                subject.removeListener(bus);
                buses.remove(bus);
                break;
            }
        }
    }

    @Override
    public void fireChange(Object subject) {
        System.out.println("Subject: " + subject.toString());
        System.out.println("Observer: " + observer.toString());
        System.out.println();
        observer.fireChange(subject);
    }
}
