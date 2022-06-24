package concepts.semaphore;

import java.util.concurrent.Semaphore;

public class SimpleDBDriver {
    private Semaphore semaphore;

    public SimpleDBDriver() {
        semaphore = new Semaphore(3);
    }

    public void createConnection(String requestName) {
        semaphore.acquireUninterruptibly();
        System.out.println("Creating a new connection for " + requestName);
        System.out.println(requestName + "Connection closed");
        semaphore.release();
    }
}

