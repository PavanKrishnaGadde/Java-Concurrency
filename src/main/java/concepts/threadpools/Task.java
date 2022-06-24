package concepts.threadpools;

public class Task implements Runnable{
    private String taskName;

    public Task(String taskName) {
        this.taskName = taskName;
    }
    @Override
    public void run() {
        System.out.println("Task Started..");
        for(int i=0;i<20;i++)
            System.out.println(Thread.currentThread().getName() + " " + taskName +  " message " +  i);
        System.out.println("Task Ended..");
    }
}
