package org.example;

public class ThreadRewolwerowiec extends Thread {
    Rewolwerowiec rewolwerowiec;
    ThreadRewolwerowiec(Rewolwerowiec x) {
        this.rewolwerowiec = x;
    }

    public void run(){
        for(String el:rewolwerowiec.odliczanie){
            System.out.println(el);
        }
        String[] end = {};
        rewolwerowiec.setOdliczanie(end);
    }

}
