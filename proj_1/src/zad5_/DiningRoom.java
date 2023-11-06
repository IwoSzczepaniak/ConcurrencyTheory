package zad5_;

import java.util.concurrent.Semaphore;

public class DiningRoom {
    protected final Semaphore availableSeats;

    public DiningRoom(int numSeats) {
        availableSeats = new Semaphore(numSeats);
    }

    public void enter() throws InterruptedException {
        availableSeats.acquire();
    }

    public boolean tryEnter(){
        return availableSeats.tryAcquire();
    }

    public void leave() {
        availableSeats.release();
    }

}
