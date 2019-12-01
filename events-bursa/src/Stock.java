public class Stock {
    private String sellerId;
    private String shareHolderId;
    public String stockName;
    public int stockVolume;
    public int stockValue;

    Stock(String stockName, int stockVolume, int stockValue) {
        this.stockName = stockName;
        this.stockValue = stockValue;
        this.stockVolume = stockVolume;
    }

    public void printStockInfo() {
        System.out.println(stockName);
        System.out.println(stockValue);
        System.out.println(stockVolume);
        System.out.println(sellerId);
        System.out.println(shareHolderId);

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
