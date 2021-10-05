package com.high.currency04.master;

import java.util.Locale;
import java.util.Map;
import java.util.Queue;

public class Worker implements Runnable{
    protected Queue<Object> workQueue;
    protected Map<String, Object> resultMap;

    public void setWorkQueue(Queue<Object> workQueue) {
        this.workQueue = workQueue;
    }
    public void setResultMap(Map<String, Object> resultMap) {
        this.resultMap = resultMap;
    }

    public Object handle(Object input){
        return input;
    }

    @Override
    public void run() {
        while (true) {
            Object input = workQueue.poll();
            if(input == null) {
                break;
            }
            Object result = handle(input);
            resultMap.put(Integer.toString(input.hashCode()), result);
        }
    }

    public static void main(String[] args) {

        String s = "皱皱ISIS";
        String s1 = s.toLowerCase(Locale.ROOT);
        System.out.println(s1);

    }
}
