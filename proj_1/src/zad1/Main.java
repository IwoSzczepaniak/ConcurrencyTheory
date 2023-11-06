package zad1;

import java.util.concurrent.Semaphore;

import static java.lang.Thread.sleep;


public class Main {
    public static void main(String[] args) {
        int numPhilosophers = 20;
        Philosopher[] philosophers = new Philosopher[numPhilosophers];
        Semaphore[] forks = new Semaphore[numPhilosophers];

        for (int i = 0; i < numPhilosophers; i++) {
            forks[i] = new Semaphore(1);
        }

        for (int i = 0; i < numPhilosophers; i++) {
            philosophers[i] = new Philosopher(i, forks[i], forks[(i + 1) % numPhilosophers]);
            Thread t = new Thread(philosophers[i]);
            t.start();
        }


    }
}




