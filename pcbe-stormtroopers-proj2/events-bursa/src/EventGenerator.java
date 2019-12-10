import java.util.ArrayList;
import java.util.Map;


public class EventGenerator {
    ArrayList<Map.Entry<Listener, EventType>> listenerList = new ArrayList<>();
    ArrayList<EventType> eventStackTrace = new ArrayList<>();
    private static EventGenerator single_instance = null;


    private EventGenerator() {
    }

    // static method to create instance of Singleton class
    public static EventGenerator getInstance() {
        if (single_instance == null)
            single_instance = new EventGenerator();

        return single_instance;
    }

    public void registerListener(Listener l, EventType eventType) {
        Map.Entry<Listener, EventType> pair = new java.util.AbstractMap.SimpleEntry<>(l, eventType);
        listenerList.add(pair);
    }

    public void sendEvent(Event ev) {
        eventStackTrace.add(ev.getEventType());
        for (Map.Entry<Listener, EventType> l : listenerList) {
            if (ev.getEventTypeCode() == l.getValue().getEventTypeCode()) {
                l.getKey().consumeEvent(ev);
            }
        }
    }

    public void printEventStackTrace() {
        for (EventType evt : eventStackTrace) {
            System.out.println(evt.getEventTypeCode());
        }
    }
}
