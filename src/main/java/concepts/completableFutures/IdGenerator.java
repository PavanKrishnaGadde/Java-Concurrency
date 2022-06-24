package concepts.completableFutures;

import java.util.Random;

public class IdGenerator {

    public int getNewId() {
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return new Random().nextInt(10000);
    };
}
