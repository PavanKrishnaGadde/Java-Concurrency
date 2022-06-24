package concepts.threads.locks.readWriteLocks;

import java.util.Date;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class ReadWriteLocksMain {
    public static void main(String[] args) throws ExecutionException, InterruptedException {

        SeatBookingService service = new SeatBookingService(20);

        ExecutorService readerService = Executors.newFixedThreadPool(8);
        ExecutorService writerService = Executors.newFixedThreadPool(8);

        for(int i=0;i<20;i++) {
            int x=i;
            writerService.submit(() -> service.bookSeat(x));
        }

        for(int i=0;i<20;i++) {
            readerService.submit(() -> service.getUnbookedSeats());
        }


        readerService.shutdown();
        writerService.shutdown();
    }
}
