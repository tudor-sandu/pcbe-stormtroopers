import java.util.ArrayList;

public class Shareholder implements Listener {

    private ArrayList<Stock> portfolio = new ArrayList<>();
    private String shareholderId;
    private int maxPrefferedStockValue;
    private int maxPrefferedStockVolume;
    private ArrayList<String> preferredSellers;

    Shareholder(String shareholderId, int maxStockValue, int maxStockVolume, ArrayList<String> preferredSellers) {
        EventGenerator.getInstance().registerListener(this, EventType.NewStock);
        EventGenerator.getInstance().registerListener(this, EventType.StockAcknowledge);
        this.shareholderId = shareholderId;
        this.maxPrefferedStockValue = maxStockValue;
        this.maxPrefferedStockVolume = maxStockVolume;
        this.preferredSellers = preferredSellers;
    }

    public void buyStock(Stock stock) {
        //send intention to buy stock, not actually bought since we don't get an acknowledge, 2 shareholders may try to buy same stock at once
        stock.setShareHolderId(shareholderId);
        EventGenerator.getInstance().sendEvent(new Event(EventType.StockBought, stock));
    }

    @Override
    public void consumeEvent(Event ev) {
        if (ev.getEventTypeCode() == EventType.NewStock.getEventTypeCode()) {
            //decide if shareholder wants to buy stock, filters can be implemented here
            if (ev.getStock().stockValue <= maxPrefferedStockValue && ev.getStock().stockVolume <= maxPrefferedStockVolume && preferredSellers.contains(ev.getStock().getSellerId())) {
                this.buyStock(ev.getStock());
            }
        } else if (ev.getEventTypeCode() == EventType.StockAcknowledge.getEventTypeCode()) {
            //if stock is his stock, add to portfolio
            if (ev.getStock().getShareHolderId().equals(shareholderId)) {
                portfolio.add(ev.getStock());
            }
        }
    }

    public void printPortfolio() {
        for (Stock st : portfolio) {
            st.printStockInfo();
        }
    }
}
