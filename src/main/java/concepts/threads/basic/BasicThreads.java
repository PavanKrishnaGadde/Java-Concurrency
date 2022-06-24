package concepts.threads.basic;

public class BasicThreads {
    public static void main(String[] args) {
        Thread thread = new Thread(new Worker());
        Thread workerThread = new WorkerThread();
        workerThread.start();
        thread.start();
    }

    public static class WorkerThread extends Thread {
        public void run() {
            System.out.println("Worker Threading/....");
            for(int i=0;i<100;i++) {
                System.out.println(i);
            }
        }
    }

    public static class Worker implements Runnable {
        @Override
        public void run() {
            System.out.println("Worker Running in Thread/....");
            for(int i=300;i<400;i++) {
                System.out.println(i);
            }
        }
    }
}
