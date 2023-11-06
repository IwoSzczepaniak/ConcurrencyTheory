package zad2;

import java.util.concurrent.Semaphore;

public class Philosopher extends zad1.Philosopher {

    public Philosopher(int id, Semaphore leftFork, Semaphore rightFork) {
        super(id, leftFork, rightFork);
    }

    @Override
    protected void pickUpForks(){
        long startTime, endTime;
        while (true) {
            startTime = System.nanoTime();
            if (leftFork.tryAcquire() && rightFork.tryAcquire()) {
                endTime = System.nanoTime();
                if (this.helpPrint) System.out.println("Philosopher " + id + " picks up both forks");
                totalWaitingTime += endTime - startTime;
                numberOfWaitAttempts++;
            } else {
                if (this.helpPrint) System.out.println("Philosopher " + id + " couldn't pick up both forks, releasing and retrying.");
            }
            leftFork.release();
            rightFork.release();

        }
    }
}