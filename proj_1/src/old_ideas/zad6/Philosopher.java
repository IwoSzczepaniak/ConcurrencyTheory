package old_ideas.zad6;

import java.util.concurrent.Semaphore;

public class Philosopher implements Runnable {
    private int id;

    private int wait_time = 0;
    private Semaphore leftFork;
    private Semaphore rightFork;



    private DiningRoom dr;

    public Philosopher(int id, Semaphore leftFork, Semaphore rightFork, DiningRoom dr) {
        this.id = id;
        this.leftFork = leftFork;
        this.rightFork = rightFork;
        this.dr = dr;
    }

    public void run() {
        try {
            while (true) {
                think();
                if(dr.canSbSeat()){
                    pickUpForks();
                    eat(false);
                    putDownForks();
                }
                else{
                    pickUpForksOnCorridor();
                    eat(true);
                    putDownForksOnCorridor();
                }
            }
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }

    private void think() throws InterruptedException {
        System.out.println("old_ideas.zad6.Philosopher " + id + " is thinking");
        Thread.sleep((long) (Math.random() * wait_time));
    }

    private void pickUpForks() throws InterruptedException {
        dr.seatInTable();
        leftFork.acquire();
        System.out.println("old_ideas.zad6.Philosopher " + id + " picks up the left fork");
        rightFork.acquire();
        System.out.println("old_ideas.zad6.Philosopher " + id + " picks up the right fork");
    }

    private void pickUpForksOnCorridor() throws InterruptedException {
        rightFork.acquire();
        System.out.println("old_ideas.zad6.Philosopher " + id + " picks up the right fork on corridor");
        leftFork.acquire();
        System.out.println("old_ideas.zad6.Philosopher " + id + " picks up the left fork on corridor");
    }


    private void eat(boolean corridor) throws InterruptedException {
        if(corridor)System.out.println("old_ideas.zad6.Philosopher " + id + " is eating on corridor");
        else System.out.println("old_ideas.zad6.Philosopher " + id + " is eating");
        Thread.sleep((long) (Math.random() * wait_time));
    }

    private void putDownForks() {
        dr.standOutOfTable();
        leftFork.release();
        rightFork.release();
        System.out.println("old_ideas.zad6.Philosopher " + id + " puts down both forks");
    }

    private void putDownForksOnCorridor() {
//        rightFork.release();
        leftFork.release();
        rightFork.release();


        System.out.println("old_ideas.zad6.Philosopher " + id + " puts down both forks");
    }
}