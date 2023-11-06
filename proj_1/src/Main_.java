import java.io.FileWriter;
import java.io.IOException;
import java.util.concurrent.Semaphore;
import static java.lang.Thread.sleep;
import zad5_.DiningRoom;

// CHOOSE TASK BETWEEN 5 AND 6
import zad6_.DiningPhilosopher;

public class Main_ {

    static int sleepTime = 10000;

    static int numPhilosophers = 20;

    static String name = numPhilosophers + "_" + sleepTime/1000 + "_zad6.txt";
    static DiningPhilosopher[] diningPhilosophers = new DiningPhilosopher[numPhilosophers];
    static Semaphore[] forks = new Semaphore[numPhilosophers];
    static DiningRoom diningRoom = new DiningRoom(numPhilosophers - 1);
    static Thread[] threads = new Thread[numPhilosophers];

    public static void runTests(){
        for (int i = 0; i < numPhilosophers; i++) {
            forks[i] = new Semaphore(1);
        }

        for (int i = 0; i < numPhilosophers; i++) {
            diningPhilosophers[i] = new DiningPhilosopher(i, forks[i], forks[(i + 1) % numPhilosophers], diningRoom);
            threads[i] = new Thread(diningPhilosophers[i]);
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
                String output = "Phisopher " + i + " " + diningPhilosophers[i].getAverageWaitingTime() + " ns";
                System.out.println(output);
                fileWriter.write(diningPhilosophers[i].getAverageWaitingTime() + System.lineSeparator());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

        public static void main(String[] args) {
            runTests();
            saveAndPrintResults();
    }
}

