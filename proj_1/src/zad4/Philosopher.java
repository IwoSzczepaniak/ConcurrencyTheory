package zad4;

import java.util.Random;
import java.util.concurrent.Semaphore;

public class Philosopher extends zad1.Philosopher {


    public Philosopher(int id, Semaphore leftFork, Semaphore rightFork) {
        super(id, leftFork, rightFork);
    }

    @Override
    protected void pickUpForks() throws InterruptedException {
        Random rand = new Random();
        boolean val = rand.nextBoolean();

        if (val) {
            super.pickUpForks();
        }
        else{
            long startTime, endTime, startTime2, endTime2;
            startTime2 = System.nanoTime();
            rightFork.acquire();
            endTime2 = System.nanoTime();
            if (this.helpPrint) System.out.println("Philosopher " + id + " picks up the right fork" + " random val is: " + val);
            startTime = System.nanoTime();
            leftFork.acquire();
            endTime = System.nanoTime();
            if (this.helpPrint) System.out.println("Philosopher " + id + " picks up the left fork"+ " random val is: " + val);
            totalWaitingTime += (endTime - startTime) + (endTime2 - startTime2);
            numberOfWaitAttempts++;
        }

    }

}