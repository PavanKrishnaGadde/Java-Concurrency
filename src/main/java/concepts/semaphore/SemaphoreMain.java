package concepts.semaphore;

import java.util.stream.IntStream;

public class SemaphoreMain {

    public static void main(String[] args) {

        SimpleDBDriver simpleDBDriver = new SimpleDBDriver();
        IntStream.rangeClosed(0,10).forEach((i)-> new Thread(()-> simpleDBDriver.createConnection("simpleRequest" + i)).start());

        DBDriver dbDriver = new DBDriver();
        IntStream.range(0,100).forEach((i) -> new Thread(()->dbDriver.createConnection("request" + i)).start());

        ComplexDriver complexDriver = new ComplexDriver();
        IntStream.rangeClosed(0,10).forEach((i)-> new Thread(()-> complexDriver.createConnection("complexRequest" + i)).start());

    }
}
