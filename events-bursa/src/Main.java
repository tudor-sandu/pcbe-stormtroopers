import java.util.ArrayList;
import java.util.Arrays;

public class Main {
    public static void main(String[] args) {
        Shareholder Darius = new Shareholder("Darius",200, 30, new ArrayList<>(Arrays.asList("intel-0")));
        Seller Intel = new Seller("intel-0", "Intel");
        Seller Amd = new Seller("amd-0", "AMD");

        //will buy
        Intel.generateStockToSell(1,5,60,65);

        //won't buy volume too big
        Intel.generateStockToSell(100,500,60,65);

        //won't buy because not a preferred seller
        Amd.generateStockToSell(20,30,100,105);
        Amd.generateStockToSell(100,120,100,105);

        Darius.printPortfolio();
        EventGenerator.getInstance().printEventStackTrace();

    }
}
