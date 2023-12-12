package org.example;

import java.util.concurrent.TimeUnit;

public class Consumer extends Thread{
    private Buffor buff;
    private int id;

    public Consumer(Buffor buff_, int id_){
        this.buff = buff_;
        this.id = id_;
    }

    public void run(){
        for(int i = 0; i < 100; i++){
            synchronized (buff) {
                while(buff.isNotUsed()) {
                    try {
                        buff.wait();
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                }
                System.out.println(id + " wątek zjada pizzę nr:" + buff.getFromBuffer());
                buff.isNotUsed = true;
            }
            try {
                TimeUnit.MICROSECONDS.sleep(200);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }


            buff.isNotUsed = false;
        }
    }
}
