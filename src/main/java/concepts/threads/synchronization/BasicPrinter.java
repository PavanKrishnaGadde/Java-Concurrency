package concepts.threads.synchronization;

public class BasicPrinter {
    private int number;
    private final int LIMIT;
    public BasicPrinter(int limit) {
        number = 0;
        LIMIT = limit;
    }

    public void printOddNumbers() throws InterruptedException {
        while(number<LIMIT) {
            if (number%2!=0)
                System.out.println("ODD: " + number);
            number++;
        }
    }

    public void printEvenNumbers() throws InterruptedException {
        while(number<LIMIT) {
            if(number%2==0)
                System.out.println("EVEN: " + number);
            number++;
        }
    }

}
