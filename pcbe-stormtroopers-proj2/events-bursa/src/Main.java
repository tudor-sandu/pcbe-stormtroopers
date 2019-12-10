import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.concurrent.TimeUnit;
public class Main {
    public static boolean isBetween(int x, int lower, int upper) {
        return lower <= x && x <= upper;
      }
    public static void main(String[] args) {
        Shareholder Alex = new Shareholder("Alex",200, 30, new ArrayList<>(Arrays.asList("amd-0","samsung-0")));
        Shareholder Darius = new Shareholder("Darius",300, 40, new ArrayList<>(Arrays.asList("intel-0","samsung-0")));
        Seller Intel = new Seller("intel-0", "Intel");
        Seller Amd = new Seller("amd-0", "AMD");
        Seller Movidius = new Seller("intel-0", "Movidius");
        Seller Samsung = new Seller("samsung-0", "SAMSUNG");
        Seller Continental = new Seller("conti-0", "Continental");
        List<Seller> sellers = new ArrayList<>();
        sellers.addAll(Arrays.asList(Intel,Amd,Movidius,Samsung,Continental));
        Random random = new Random();
        

        new Thread(new Runnable(){
            @Override
            public void run(){
                int randint;
                int i = 0;
                while(true){
                    try{
            randint= random.nextInt(16);
            if(isBetween(randint, 0, 5))
                Continental.generateStockToSell(1, 100, 10, 100);
            else if(isBetween(randint, 6, 8))
                Amd.generateStockToSell(5,100,100,105);
            else if(isBetween(randint, 9, 11))
                Movidius.generateStockToSell(11,20,30,50);
            else if(isBetween(randint, 12, 14))
                Intel.generateStockToSell(10,50,60,65);
            else if(isBetween(randint, 14, 16))
                Samsung.generateStockToSell(15,75,80,90);
            i++;
            if(i==200){
            System.out.println("\n\nLicitatie incheiata. Situatie finala:");
            Darius.printPortfolio();
            Alex.printPortfolio();
                break;
            }
            Thread.sleep(15);
            
                }                    
                catch(InterruptedException ie ){
                    System.out.println("InterruptedException caught");
                }
            }
        }
        }).start();

        // //will buy
        // Intel.generateStockToSell(1,5,60,65);

        // //won't buy volume too big
        // Intel.generateStockToSell(100,500,60,65);

        // //won't buy because not a preferred seller
        // Amd.generateStockToSell(20,30,100,105);
        // Amd.generateStockToSell(100,120,100,105);



        // EventGenerator.getInstance().printEventStackTrace();

    }
}
