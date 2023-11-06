package old_ideas.zad5;

import java.util.concurrent.Semaphore;


public class Main {
    public static void main(String[] args) {
        int numPhilosophers = 2;
        Philosopher[] philosophers = new Philosopher[numPhilosophers];
        Semaphore[] forks = new Semaphore[numPhilosophers];
        Referee arbiter = new Referee(numPhilosophers-1);

        for (int i = 0; i < numPhilosophers; i++) {
            forks[i] = new Semaphore(1);
        }

        for (int i = 0; i < numPhilosophers; i++) {
            philosophers[i] = new Philosopher(i, forks[i], forks[(i + 1) % numPhilosophers], arbiter);
            Thread t = new Thread(philosophers[i]);
            t.start();
        }
    }
}




