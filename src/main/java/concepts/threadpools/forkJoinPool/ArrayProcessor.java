package concepts.threadpools.forkJoinPool;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.ForkJoinTask;
import java.util.concurrent.RecursiveTask;

public class ArrayProcessor extends RecursiveTask<Integer> {

    private int[] inputArr;
    private static final int THRESHOLD = 5;

    public ArrayProcessor(int[] inputArr) {
        this.inputArr = inputArr;
    }

    @Override
    protected Integer compute() {
        if(inputArr.length>THRESHOLD) {
            return ForkJoinTask.invokeAll(createSubThreads()).stream()
                    .mapToInt(ForkJoinTask::join)
                    .sum();
        } else {
            try {
                return process();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        return null;
    }

    private List<ArrayProcessor> createSubThreads() {
        List<ArrayProcessor> list = new ArrayList<>();

        list.add(new ArrayProcessor(Arrays.copyOfRange(inputArr,0,inputArr.length/2)));
        list.add(new ArrayProcessor(Arrays.copyOfRange(inputArr,inputArr.length/2,inputArr.length)));

        return list;
    }

    private int process() throws InterruptedException {
        Thread.sleep(1000);
        int ans =  Arrays.stream(inputArr).filter(x -> x>0)
                .distinct()
                .map(x -> x*10)
                .sum();
        System.out.println(ans + " processed by: " + Thread.currentThread().getName());
        return ans;
    }
}
