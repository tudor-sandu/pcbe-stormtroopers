public class Event {
    private EventType eventType;
    private Stock stock;

    Event(EventType eventType, Stock stock) {
        this.eventType = eventType;
        this.stock = stock;
    }

    public int getEventTypeCode() {
        return eventType.getEventTypeCode();
    }

    public EventType getEventType() {
        return eventType;
    }

    public Stock getStock() {
        return stock;
    }
}
