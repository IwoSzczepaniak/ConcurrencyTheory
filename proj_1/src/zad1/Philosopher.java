package zad1;

import java.util.concurrent.Semaphore;

public class Philosopher implements Runnable {
    protected int id;

    protected Semaphore leftFork;
    protected Semaphore rightFork;

    protected long totalWaitingTime = 0;
    protected int numberOfWaitAttempts = 0;

    protected boolean helpPrint = false;

    protected boolean isRunning = true;

    public Philosopher(int id, Semaphore leftFork, Semaphore rightFork) {
        this.id = id;
        this.leftFork = leftFork;
        this.rightFork = rightFork;
    }

    public Philosopher(int id, Semaphore leftFork, Semaphore rightFork, boolean helpPrint) {
        this.id = id;
        this.leftFork = leftFork;
        this.rightFork = rightFork;
        this.helpPrint = helpPrint;
    }

    public void run() {
        try {
            while (isRunning) {
                think();
                pickUpForks();
                eat();
                putDownForks();
            }
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }

    protected void think() throws InterruptedException {
        if (this.helpPrint) System.out.println("Philosopher " + id + " is thinking");
    }

    protected void pickUpForks() throws InterruptedException {
        long startTime, endTime, startTime2, endTime2;

        startTime = System.nanoTime();
        leftFork.acquire();
        endTime = System.nanoTime();
        if (this.helpPrint) System.out.println("Philosopher " + id + " picks up the left fork");
        startTime2 = System.nanoTime();
        rightFork.acquire();
        endTime2 = System.nanoTime();
        if (this.helpPrint) System.out.println("Philosopher " + id + " picks up the right fork");
        totalWaitingTime += (endTime - startTime) + (endTime2 - startTime2);
        numberOfWaitAttempts++;
    }

    protected void eat() throws InterruptedException {
        if (this.helpPrint) System.out.println("Philosopher " + id + " is eating");
    }

    protected void putDownForks() {
        leftFork.release();
        rightFork.release();
        if (this.helpPrint) System.out.println("Philosopher " + id + " puts down both forks");
    }

    public synchronized double getAverageWaitingTime() {
        if (numberOfWaitAttempts == 0) {
            return 0;
        }
        return (double) totalWaitingTime / numberOfWaitAttempts;
    }

    public void stopThread(){
        isRunning = false;
    }
}