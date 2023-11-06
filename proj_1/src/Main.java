import java.io.FileWriter;
import java.io.IOException;
import java.util.concurrent.Semaphore;
import static java.lang.Thread.sleep;

// CHOOSE TASK BETWEEN 1 AND 4
import zad4.Philosopher;


public class Main {
    static int sleepTime = 10000;
    static int numPhilosophers = 20;
    static String name = numPhilosophers + "_" + sleepTime/1000 + "_zad4.txt";


    static Philosopher[] philosophers = new Philosopher[numPhilosophers];
    static Semaphore[] forks = new Semaphore[numPhilosophers];
    static Thread[] threads = new Thread[numPhilosophers];


    public static void runTests(){

        for (int i = 0; i < numPhilosophers; i++) {
            forks[i] = new Semaphore(1);
        }

        for (int i = 0; i < numPhilosophers; i++) {
            philosophers[i] = new Philosopher(i, forks[i], forks[(i + 1) % numPhilosophers]);
            threads[i] = new Thread(philosophers[i]);
            threads[i].start();
        }

        try {
            sleep(sleepTime);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void saveAndPrintResults(){
        try (FileWriter fileWriter = new FileWriter(name)) {
            for (int i = 0; i < numPhilosophers; i++) {
                threads[i].stop();
//                philosophers[i].stopThread();
                String output = "Phisopher " + i + " " + philosophers[i].getAverageWaitingTime() + " ns";
                System.out.println(output);
                fileWriter.write(philosophers[i].getAverageWaitingTime() + System.lineSeparator());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println(name);
    }

    public static void main(String[] args) {
        runTests();
        saveAndPrintResults();
    }
}




