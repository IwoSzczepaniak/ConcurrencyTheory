package old_ideas.zad5;

import java.util.concurrent.Semaphore;

public class Philosopher implements Runnable {
    private int id;

    private int wait_time = 0;
    private Semaphore leftFork;
    private Semaphore rightFork;

    private Referee referee;

    public Philosopher(int id, Semaphore leftFork, Semaphore rightFork, Referee arbiter) {
        this.id = id;
        this.leftFork = leftFork;
        this.rightFork = rightFork;
        this.referee = arbiter;
    }

    public void run() {
        try {
            while (true) {
                think();
                pickUpForks();
                eat();
                putDownForks();
            }
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }

    private void think() throws InterruptedException {
        System.out.println("zad1.Philosopher " + id + " is thinking");
        Thread.sleep((long) (Math.random() * wait_time));
    }

    private void pickUpForks() throws InterruptedException {
        if(referee.canSbSeat()) {
            referee.seatInTable();
            leftFork.acquire();
            System.out.println("zad1.Philosopher " + id + " picks up the left fork");
            rightFork.acquire();
            System.out.println("zad1.Philosopher " + id + " picks up the right fork");
        }
    }

    private void eat() throws InterruptedException {
        System.out.println("zad1.Philosopher " + id + " is eating");
        Thread.sleep((long) (Math.random() * wait_time));
    }

    private void putDownForks() {
        leftFork.release();
        rightFork.release();
        referee.standOutOfTable();
        System.out.println("zad1.Philosopher " + id + " puts down both forks");
    }
}