package concepts.threads.basic;

public class ThreadMethods {
    public static void main(String[] args) throws InterruptedException {
        Worker worker = new Worker();
        Thread thread = new Thread(worker);
        Thread workerThread = new WorkerThread();
        workerThread.setDaemon(true);
        thread.setPriority(Thread.MAX_PRIORITY);
        System.out.println("STATE: " + thread.getState());

        workerThread.start();
        thread.start();
        System.out.println("Threads Started");
        System.out.println("STATE: " + thread.getState());

        workerThread.setName("Worker");
        thread.setName("Major");

        System.out.println(workerThread.toString());
        System.out.println(thread.toString() + " id: " + thread.getId() + " Priority: " +  thread.getPriority());
        if(workerThread.isAlive()) {
            System.out.println("Worker thread alive....");
        }
        System.out.println("STATE: " + thread.getState());
        workerThread.interrupt();

        workerThread.join();
        thread.join();
        System.out.println("Threads Completed");
        if(workerThread.isDaemon()) {
            System.out.println("Deamon thread done....");
        }
        System.out.println("STATE: " + thread.getState());
    }

    public static class WorkerThread extends Thread {
        public void run() {
            System.out.println("WorkerThreading/....");
            if(Thread.currentThread().isDaemon()) {
                System.out.println("Deamon thread started....");
            }
            int i=0;
            while(!Thread.currentThread().isInterrupted()) {
                i++;
            }
            System.out.println("Interpput occured after: " + i);
        }
    }

    public static class Worker implements Runnable {
        @Override
        public void run() {
            System.out.println("Worker Running/....");
            for(int i=30;i<40;i++) {
                System.out.println(i);
            }
        }
    }
}
