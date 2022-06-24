package concepts.threads.runtime;

import java.io.IOException;

public class RunTimeTest {
    public void finalize() {
        System.out.println("Object cleaned up");
    }
    public static void main(String[] args) throws IOException {
        System.out.println(Runtime.getRuntime().availableProcessors());
        for(int i=0;i<1000;i++) {
            new RunTimeTest();
        }
        System.out.println(Runtime.getRuntime().maxMemory());
        System.out.println(Runtime.getRuntime().freeMemory());
        System.out.println(Runtime.getRuntime().totalMemory());
        Runtime.getRuntime().gc();
        System.out.println("After Garbage collection");
        System.out.println(Runtime.getRuntime().freeMemory());

        Runtime.getRuntime().exec("notepad");
//        Runtime.getRuntime().exec("shutdown -r -t 0");




    }
}
