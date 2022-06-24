package concepts.threads.basic;

public class WorkerThread implements Runnable{
    private String message;
    public WorkerThread(String message) {
        this.message = message;
    }

    public void run() {
        System.out.println(Thread.currentThread().getName() + " " + Thread.currentThread().getId() + " started");
        try {
            processMessage();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(Thread.currentThread().getName() + " " + Thread.currentThread().getId() + " ended");
    }

    private void processMessage() throws InterruptedException {
        Thread.sleep(1000);
        System.out.println("Message is: " + message);
    }
}
