package org.example;

import java.util.concurrent.TimeUnit;

public class Producer extends Thread {
    private Buffor buff;
    private int id;

    public Producer(Buffor buff_, int id_){
        this.buff = buff_;
        this.id = id_+1000;
    }

    public void run(){
        for(int i = 0; i < 100; i++){
            synchronized(buff) {
                buff.addToBuffer(UniqueID.nowyID());
                System.out.println(id + " wątek produkuje pizzę nr: " + i);
                buff.isNotUsed = false;
                buff.notifyAll();
            }

            try {
                TimeUnit.MICROSECONDS.sleep(200);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }

}
