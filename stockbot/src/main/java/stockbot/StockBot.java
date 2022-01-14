package stockbot;

import java.time.Duration;
import java.time.Instant;
public class StockBot {
    //public StockCheck stockCheck;
    //private static TeleBotImplementation teleBot = new TeleBotImplementation();

    public static void main(String[] args ){
        TeleBotImplementation.registerBot();
        String url1 = "https://www.microcenter.com/product/609038/raspberry-pi-4-model-b-4gb-ddr4";
        MicroCenter rp4 = new MicroCenter(url1, "head > script:nth-child(68)", "");
        while (true){
            rp4.updateStock();
            if(rp4.inStock){
                TeleBotImplementation.sendMessage("Raspberry Pi 4 is Available. View at: https://www.microcenter.com/product/609038/raspberry-pi-4-model-b-4gb-ddr4");
                break;
            } 
            else{
                try {
                    Thread.sleep(5000);
                } catch (InterruptedException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }
        }

    }
}