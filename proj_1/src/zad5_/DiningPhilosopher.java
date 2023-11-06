package zad5_;

import java.util.concurrent.Semaphore;

public class DiningPhilosopher extends zad1.Philosopher {

    protected DiningRoom diningRoom;

    public DiningPhilosopher(int id, Semaphore leftFork, Semaphore rightFork, DiningRoom diningRoom) {
        super(id, leftFork, rightFork);
        this.diningRoom = diningRoom;
    }

    public DiningPhilosopher(int id, Semaphore leftFork, Semaphore rightFork, DiningRoom diningRoom, boolean helpPrint) {
        super(id, leftFork, rightFork, helpPrint);
        this.diningRoom = diningRoom;
    }
    @Override
    public void run() {
        try {
            while (true) {
                think();
                long startTime = System.currentTimeMillis();
                if(diningRoom.tryEnter()) {
                    long endTime = System.currentTimeMillis();
                    totalWaitingTime += endTime - startTime;
                    pickUpForks();
                    eat();
                    putDownForks();
                    diningRoom.leave();
                }
                else {
                    putDownForks();
                }

//                think();
//                long startTime = System.currentTimeMillis();
//                diningRoom.enter();
//                long endTime = System.currentTimeMillis();
//                totalWaitingTime += endTime - startTime;
//                pickUpForks();
//                eat();
//                putDownForks();
//                diningRoom.leave();
            }
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }

}

