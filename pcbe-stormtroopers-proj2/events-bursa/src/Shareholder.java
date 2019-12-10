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
            if (ev.getStock().getShareHolderId()=="" &&ev.getStock().stockValue <= maxPrefferedStockValue 
                && ev.getStock().stockVolume <= maxPrefferedStockVolume && preferredSellers.contains
                (ev.getStock().getSellerId())) {
                // if(ev.getStock().inTranzaction==0){
                //     ev.getStock().inTranzaction=1;
                this.buyStock(ev.getStock());
                System.out.print("Buy: "+shareholderId+" cumpara ");
                ev.getStock().printStockInfo();
                // ev.setStockShareholder(shareholderId);
                // ev.getStock().inTranzaction=0;
                // }
            }
        } else if (ev.getEventTypeCode() == EventType.StockAcknowledge.getEventTypeCode()) {
            //if stock is his stock, add to portfolio
            int i=0;
            if (ev.getStock().getShareHolderId().equals(shareholderId)) {
                    for(Stock s:portfolio){
                        if(s.stockValue==ev.getStock().stockValue && s.stockName==ev.getStock().stockName)
                            {
                             s.stockVolume+=ev.getStock().stockVolume; 
                             i=1;
                            }
                        }
                if(i==0)
                portfolio.add(ev.getStock());
            }
        }
    }

    public void printPortfolio() {
        System.out.println("\nPortofoliu "+shareholderId+":");
        for (Stock st : portfolio) {
            System.out.print("-->");
            st.printStockInfo();
        }
    }
}
