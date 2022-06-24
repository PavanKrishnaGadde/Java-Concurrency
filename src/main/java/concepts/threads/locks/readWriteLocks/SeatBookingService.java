package concepts.threads.locks.readWriteLocks;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class SeatBookingService {
    private boolean[] isBooked;

    private ReentrantReadWriteLock readWriteLock;
    private ReentrantReadWriteLock.ReadLock readLock;
    private ReentrantReadWriteLock.WriteLock writeLock;


    public SeatBookingService(int seatsCount) {
        isBooked = new boolean[seatsCount];
        readWriteLock = new ReentrantReadWriteLock();
        readLock = readWriteLock.readLock();
        writeLock = readWriteLock.writeLock();
    }

    public List<Integer> getUnbookedSeats() {
        readLock.lock();
        List<Integer> seatNums = new ArrayList<>();
        try {
            List<Integer> list = new ArrayList<>();
            Thread.sleep(3000);
            for(int i=0;i<isBooked.length;i++) {
                if(!isBooked[i]) seatNums.add(i);
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            readLock.unlock();
        }
        System.out.println("Empty Seats: " + seatNums + " at " + new Date() + " by " + Thread.currentThread().getName());
        return seatNums;
    }

    public boolean bookSeat(int seatId) {
        writeLock.lock();
        boolean isSuccess = false;
        try {
            Thread.sleep(3000);
            if(seatId >=0 && seatId < isBooked.length && !isBooked[seatId]) {
                isBooked[seatId] = true;
                isSuccess = true;
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            writeLock.unlock();
        }
        System.out.println("Seat " + seatId + " booking request at " + new Date() + " " + isSuccess + " by " + Thread.currentThread().getName());
        return isSuccess;
    }
}
