package zad3;

import zad2.Philosopher;

import java.io.FileWriter;
import java.io.IOException;
import java.util.concurrent.Semaphore;

import static java.lang.Thread.sleep;


public class Main {
    public static void main(String[] args) {
        int numPhilosophers = 50;
        zad2.Philosopher[] philosophers = new zad2.Philosopher[numPhilosophers];
        Semaphore[] forks = new Semaphore[numPhilosophers];

        for (int i = 0; i < numPhilosophers; i++) {
            forks[i] = new Semaphore(1);
        }

        Thread[] threads = new Thread[numPhilosophers];
        for (int i = 0; i < numPhilosophers; i++) {
            philosophers[i] = new Philosopher(i, forks[i], forks[(i + 1) % numPhilosophers]);
            threads[i] = new Thread(philosophers[i]);
            threads[i].start();
        }

        try {
            sleep(5000);
        } catch (Exception e) {
            System.out.println(e);
        }

        try (FileWriter fileWriter = new FileWriter("zad3.txt")) {
            for (int i = 0; i < numPhilosophers; i++) {
                threads[i].stop();
                String output = "Phisopher " + i + " " + philosophers[i].getAverageWaitingTime() + " ns";
                System.out.println(output);
                fileWriter.write(philosophers[i].getAverageWaitingTime() + System.lineSeparator());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}



