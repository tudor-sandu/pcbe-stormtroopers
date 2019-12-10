public enum EventType {
    NewStock(1),
    StockBought(2),
    StockAcknowledge(3),
    ; // semicolon needed when fields / methods follow

    private final int eventTypeCode;

    EventType(int eventTypeCode) {
        this.eventTypeCode = eventTypeCode;
    }

    public int getEventTypeCode() {
        return this.eventTypeCode;
    }
}