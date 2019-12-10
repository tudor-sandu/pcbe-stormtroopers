public class Stock {
    private String sellerId;
    private volatile String shareHolderId="";
    public String stockName;
    public int stockVolume;
    public int stockValue;
    // public volatile int inTranzaction=0;
    Stock(String stockName, int stockVolume, int stockValue) {
        this.stockName = stockName;
        this.stockValue = stockValue;
        this.stockVolume = stockVolume;
    }

    public void printStockInfo() {
        System.out.print(stockVolume +" stocuri ");
        System.out.print(stockName);
        System.out.print(" cu valoarea "+stockValue);
        System.out.print(" si id-ul "+sellerId+".\n");
 
    }

    public String getSellerId() {
        return sellerId;
    }

    public String getShareHolderId() {
        return shareHolderId;
    }

    public void setSellerId(String sellerId) {
        this.sellerId = sellerId;
    }

    public void setShareHolderId(String shareHolderId) {
        this.shareHolderId = shareHolderId;
    }
}
