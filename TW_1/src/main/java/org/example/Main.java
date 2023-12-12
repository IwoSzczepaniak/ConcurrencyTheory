package org.example;

import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;


public class Main {
    public static void firstA(int n){
        Rewolwerowiec rewolwerowiec = new Rewolwerowiec();
        ExecutorService exec = Executors.newCachedThreadPool();
        for(int i = 0; i<n; i++){
            ThreadRewolwerowiec wtk = new ThreadRewolwerowiec(rewolwerowiec);
            exec.execute(wtk);
        }
        exec.shutdown();
    }

    public static void firstBC(int n){
        Rewolwerowiec rewolwerowiec = new Rewolwerowiec();
        ExecutorService exec = Executors.newCachedThreadPool();
        for(int i = 0; i<n; i++){
            ThreadRewolwerowiec wtk = new ThreadRewolwerowiecOd(rewolwerowiec, 4);
            exec.execute(wtk);
        }
        exec.shutdown();
    }

    public static void second(int n){

    }



    public static void main(String[] args) {
//        firstA(10);
        firstBC(10);
    }
}