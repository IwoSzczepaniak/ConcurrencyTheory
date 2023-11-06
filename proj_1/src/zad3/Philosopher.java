package zad3;

import java.util.concurrent.Semaphore;

public class Philosopher extends zad1.Philosopher{


    public Philosopher(int id, Semaphore leftFork, Semaphore rightFork) {
        super(id, leftFork, rightFork);
    }

    @Override
    protected void pickUpForks() throws InterruptedException {
        long startTime, endTime, startTime2, endTime2;

        if (id % 2 != 0) {
            super.pickUpForks();
        }
        else{
            startTime2 = System.nanoTime();
            rightFork.acquire();
            endTime2 = System.nanoTime();
            if (this.helpPrint) System.out.println("Philosopher " + id + " picks up the right fork");
            startTime = System.nanoTime();
            leftFork.acquire();
            endTime = System.nanoTime();
            if (this.helpPrint) System.out.println("Philosopher " + id + " picks up the left fork");
            totalWaitingTime += (endTime - startTime) + (endTime2 - startTime2);
            numberOfWaitAttempts++;
        }

    }

}