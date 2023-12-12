package org.example;

public class ThreadRewolwerowiecOd extends ThreadRewolwerowiec{

    int startOd;

    ThreadRewolwerowiecOd(Rewolwerowiec x, int n) {
        super(x);
        startOd = n;
    }

    @Override
    public void run() {
        for(int i = startOd; i<rewolwerowiec.odliczanie.length; i++){
            System.out.println(rewolwerowiec.odliczanie[i]);
        }
        rewolwerowiec.koniecOdliczania();
    }
}
