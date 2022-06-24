package concepts.interrupts;

public class IOProcessor implements Runnable {

    @Override
    public void run() {
        System.out.println("Processing input...");
        //making IO call
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            System.out.println("Received an interruption..stopping thread");
        }

        System.out.println("Processing complete..");

    }
}
