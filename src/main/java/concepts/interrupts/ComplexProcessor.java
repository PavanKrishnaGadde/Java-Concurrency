package concepts.interrupts;

public class ComplexProcessor implements Runnable {

    @Override
    public void run() {
        for(int i=0;i<1000;i++) {
            System.out.println(i + " :processing...");
            int t=2000;
            while(t-->0) {
                //some process
            };
            if(Thread.currentThread().isInterrupted()) {
                System.out.println("Interrupt occurred stopping the process");
                break;
            }
        }
        System.out.println("Processing done...");
    }
}

