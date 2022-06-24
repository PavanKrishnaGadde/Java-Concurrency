package concepts.semaphore;

import sun.awt.windows.ThemeReader;

import java.util.concurrent.Semaphore;

public class DBDriver {
    private Semaphore semaphore;

    public DBDriver() {
        semaphore = new Semaphore(6);
    }

    public void createConnection(String requestName) {
        try {
            semaphore.acquire();
            System.out.println("Creating a new connection for " + requestName);
            Thread.sleep(2000);
            System.out.println(requestName + "Connection closed");
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            semaphore.release();
        }
    }
}
