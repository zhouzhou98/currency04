package com.high.currency04.master;

public class ComputeWorker extends Worker{


    @Override
    public Object handle(Object input) {
        try {
            Thread.sleep(5);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        Long i = (Long) input;
        return  i * i;
    }
}
