package org.example;

class ThreadNNRK extends Thread {
    Foo nnrk;

    ThreadNNRK(Foo x) {
        this.nnrk = x;
    }

    public void run() {

        for (int i = 0; i < 5; i++) {
            System.out.println(nnrk.val);
            nnrk.val = nnrk.fooFunc();
        }


    }
}
