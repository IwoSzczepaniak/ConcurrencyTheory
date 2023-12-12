package org.example;

public class Main {
    public static void main(String[] args) {
        Buffor buffer = new Buffor();

        Producer producer;
        Consumer consumer;

        for(int i = 0; i<10; i++) {
            producer = new Producer(buffer, i);
            producer.start();
        }
        for(int i = 0; i<10; i++) {
            consumer = new Consumer(buffer, i);
            consumer.start();
        }

    }
}

