package concepts.interrupts;

public class ResumingProcessor implements Runnable {

    @Override
    public void run() {
        int interruptCount=0;
        for(int i=0;i<10000;i++) {
            System.out.println(i + " :processing...");
            int t=2000000000;
            while(t-->0) {
                //some process
            };
            if(Thread.currentThread().isInterrupted()) {
                if(interruptCount<2) {
                    System.out.println("Interrupt occurred by ignoring it for now: " + interruptCount);
                    //reset the interrupt
                    Thread.currentThread().interrupted();
                } else {
                    System.out.println("Interrupt occurred stopping the process");
                    break;
                }
                interruptCount++;

            }
        }
        System.out.println("Processing done...");
    }
}

