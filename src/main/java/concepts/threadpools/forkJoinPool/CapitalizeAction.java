package concepts.threadpools.forkJoinPool;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.concurrent.ForkJoinTask;
import java.util.concurrent.RecursiveAction;

public class CapitalizeAction extends RecursiveAction {
    private String input = "";
    private static final int THRESHOLD = 5;

    public CapitalizeAction(String input) {
        this.input=input;
    }

    @Override
    protected void compute() {

        if(input.length()>THRESHOLD) {
//            ForkJoinTask.invokeAll(createSubThreads());
            List<CapitalizeAction> list = createSubThreads();
            list.get(0).fork();
            list.get(1).fork();
        } else {
            try {
                process();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

    }

    private List<CapitalizeAction> createSubThreads() {
        List<CapitalizeAction> list = new ArrayList<>();

        list.add(new CapitalizeAction(input.substring(0,input.length()/2)));
        list.add(new CapitalizeAction(input.substring(input.length()/2)));

        return list;
    }

    private void process() throws InterruptedException {
        String out = input.toUpperCase(Locale.ROOT);
        Thread.sleep(1000);
        System.out.println(out + " processed by: " + Thread.currentThread().getName());
    }
}
