package org.example;

import java.lang.reflect.Array;
import java.util.LinkedList;
import java.util.Queue;

public class Buffor {
    private Queue<Integer> buf = new LinkedList<>();
    boolean isNotUsed = true;

    public void addToBuffer(Integer newInteger) {
        buf.add(newInteger);
    }

    public Integer getFromBuffer(){
        return buf.poll();
    }

    public boolean isNotUsed(){
        return isNotUsed;
    }



}




