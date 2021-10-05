package com.high.currency04.master;

public class SingleThreadCompute {
    public static Long handle(Long i) {
        try {
            Thread.sleep(5);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return i * i;
    }

    public static void main(String[] args) {
        Long start = System.currentTimeMillis();
        long tempResult;
        long result = 0;

        for (long i = 1; i < 1000001; i++) {
            System.out.println(i);
            tempResult = handle(i);
            result += tempResult;
        }
        System.out.println("single thread ,final result is  : " + result);
        Long end = System.currentTimeMillis();
        System.out.println("single thread ,process  time is : " + (end - start) +"  ms");
    }
}
