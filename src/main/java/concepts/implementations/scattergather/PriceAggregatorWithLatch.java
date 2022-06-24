package concepts.implementations.scattergather;

import java.util.Set;
import java.util.concurrent.CountDownLatch;

public class PriceAggregatorWithLatch implements Runnable {

    private Set<Double> prices;
    private String url;
    private CountDownLatch countDownLatch;

    public PriceAggregatorWithLatch(Set<Double> prices, String url, CountDownLatch countDownLatch) {
        this.prices = prices;
        this.url = url;
        this.countDownLatch = countDownLatch;
    }


    private double getPriceFromWebsite(String webSiteName) throws InterruptedException {
        switch (webSiteName) {
            case "amazon" : {
                Thread.sleep(500);
                return 100;
            }
            case "flipkart": {
                Thread.sleep(5000);
                return 250.88;
            }
            case "ebay": {
                Thread.sleep(200);
                return 160;
            }
            case "walmart": {
                Thread.sleep(1000);
                return 220.34;
            }
            case "instamart": {
                Thread.sleep(4000);
                return 184.90;
            }
            default: return 0.00;
        }
    }

    @Override
    public void run() {
        try {
            prices.add(getPriceFromWebsite(url));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        countDownLatch.countDown();
    }
}
