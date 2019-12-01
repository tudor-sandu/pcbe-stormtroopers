import java.util.ArrayList;
import java.util.Random;

public class Seller implements Listener {
    public String sellerId;
    public String sellerName;


    Seller(String sellerId, String sellerName) {
        EventGenerator.getInstance().registerListener(this, EventType.StockBought);
        this.sellerId = sellerId;
        this.sellerName = sellerName;
    }

    public void generateStockToSell(int minStockVolume, int maxStockVolume, int minStockPrice, int maxStockPrice) {
        Random r = new Random();
        int stockVolume = r.nextInt((maxStockVolume - minStockVolume) + 1) + minStockVolume;
        int stockPrice = r.nextInt((maxStockPrice - minStockPrice) + 1) + minStockPrice;
        Stock randomStock = new Stock(sellerName, stockVolume, stockPrice);
        randomStock.setSellerId(sellerId);
        EventGenerator.getInstance().sendEvent(new Event(EventType.NewStock, randomStock));
    }

    private void sendStockSoldAck(Stock stock) {
        EventGenerator.getInstance().sendEvent(new Event(EventType.StockAcknowledge, stock));
    }

    @Override
    public void consumeEvent(Event ev) {
        if (ev.getEventTypeCode() == EventType.StockBought.getEventTypeCode() && ev.getStock().getSellerId().equals(sellerId)) {
            sendStockSoldAck(ev.getStock());
        }
    }
}
