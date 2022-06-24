package concepts.threadpools.forkJoinPool;

import java.util.concurrent.ForkJoinPool;

public class ForkJoinPoolMain {

    public static void main(String[] args) throws InterruptedException {
        ForkJoinPool forkJoinPool = new ForkJoinPool(8);

        CapitalizeAction capitalizeAction = new CapitalizeAction("Its is raining continuously today. Its been so many since it rained like this.");

        forkJoinPool.execute(capitalizeAction);

        Thread.sleep(1000);
        System.out.println(forkJoinPool.getActiveThreadCount());
        System.out.println(forkJoinPool.getPoolSize());
        System.out.println(forkJoinPool.getAsyncMode());
        System.out.println(forkJoinPool.hasQueuedSubmissions());
        System.out.println(forkJoinPool.getQueuedSubmissionCount());
        System.out.println(forkJoinPool.getQueuedTaskCount());

        System.out.println(forkJoinPool.getParallelism());

        int[] inputs = {200,3,-300,22,43,-1000,98,0,87,32,444,53,-29877,90,87,3,4,2,44,21,554,32,43,567,32,9,0,0,0,0,23,4,21,87,43,32,9,65,32,65,23,44};

        ArrayProcessor arrayProcessor = new ArrayProcessor(inputs);

        Integer result = forkJoinPool.invoke(arrayProcessor);
        System.out.println(forkJoinPool.getStealCount());
        forkJoinPool.shutdown();
        System.out.println("Result: "  + result);
        capitalizeAction.join();
    }
}
