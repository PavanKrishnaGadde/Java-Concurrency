package concepts.semaphore;

import java.util.concurrent.Semaphore;

public class ComplexDriver {
    private Semaphore semaphore;

    public ComplexDriver() {
        semaphore = new Semaphore(6);
    }

    public void createConnection(String requestName) {
        try {
            semaphore.acquire(3);
            System.out.println("Creating a new connection for " + requestName);
            Thread.sleep(2000);
            System.out.println(requestName + "Connection closed");
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            semaphore.release(3);
        }
    }
}

