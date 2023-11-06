package zad6_;

import zad5_.DiningRoom;

import java.io.FileWriter;
import java.io.IOException;
import java.util.concurrent.Semaphore;

import static java.lang.Thread.sleep;

public class Main {
    public static void main(String[] args) {
        int numPhilosophers = 50;
        DiningPhilosopher[] diningPhilosophers = new DiningPhilosopher[numPhilosophers];
        Semaphore[] forks = new Semaphore[numPhilosophers];
        DiningRoom diningRoom = new DiningRoom(numPhilosophers - 1);

        for (int i = 0; i < numPhilosophers; i++) {
            forks[i] = new Semaphore(1);
        }

        Thread[] threads = new Thread[numPhilosophers];
        for (int i = 0; i < numPhilosophers; i++) {
            diningPhilosophers[i] = new DiningPhilosopher(i, forks[i], forks[(i + 1) % numPhilosophers], diningRoom);
            threads[i] = new Thread(diningPhilosophers[i]);
            threads[i].start();
        }

        try {
            sleep(5000);
        } catch (Exception e) {
            System.out.println(e);
        }

        try (FileWriter fileWriter = new FileWriter("zad6.txt")) {
            for (int i = 0; i < numPhilosophers; i++) {
                threads[i].stop();
                String output = "Phisopher " + i + " " + diningPhilosophers[i].getAverageWaitingTime() + " ns";
                System.out.println(output);
                fileWriter.write(diningPhilosophers[i].getAverageWaitingTime() + System.lineSeparator());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

