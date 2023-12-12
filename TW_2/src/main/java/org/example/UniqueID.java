package org.example;

public class UniqueID {
    private static int numerId = 0;
    synchronized public static int nowyID () {return numerId ++; }
}