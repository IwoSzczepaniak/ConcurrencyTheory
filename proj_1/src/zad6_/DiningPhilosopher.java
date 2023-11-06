package zad6_;

import zad5_.DiningRoom;

import java.util.concurrent.Semaphore;

public class DiningPhilosopher extends zad5_.DiningPhilosopher {

    public DiningPhilosopher(int id, Semaphore leftFork, Semaphore rightFork, DiningRoom diningRoom) {
        super(id, leftFork, rightFork, diningRoom);
    }

    public DiningPhilosopher(int id, Semaphore leftFork, Semaphore rightFork, DiningRoom diningRoom, boolean helpPrint) {
        super(id, leftFork, rightFork, diningRoom, helpPrint);
    }

    @Override
    public void run() {
        try {
            while (true) {
                think();
                long startTime = System.currentTimeMillis();
                if(diningRoom.tryEnter()) {
                    long endTime = System.currentTimeMillis();
                    totalWaitingTime += endTime - startTime;
                    pickUpForks();
                    eat();
                    putDownForks();
                    diningRoom.leave();
                }
                else {
                    pickUpForksOnCorridor();
                    eat();
                    putDownForks();
                }
            }
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }

    protected void pickUpForksOnCorridor() throws InterruptedException {
        long startTime, endTime;
        startTime = System.nanoTime();
        rightFork.acquire();
        if (this.helpPrint) System.out.println("Philosopher " + id + " picks up the right fork");
        leftFork.acquire();
        if (this.helpPrint) System.out.println("Philosopher " + id + " picks up the left fork");
        endTime = System.nanoTime();
        totalWaitingTime += endTime - startTime;
        numberOfWaitAttempts++;
    }
}


