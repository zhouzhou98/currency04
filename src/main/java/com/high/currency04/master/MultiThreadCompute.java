package com.high.currency04.master;

import java.util.Map;
import java.util.Set;

public class MultiThreadCompute {
    public static void main(String[] args) {
        Long start = System.currentTimeMillis();
        Integer nThreads = Runtime.getRuntime().availableProcessors();
        Master master = new Master(new ComputeWorker(), 100);
        for (long i = 1; i < 1000001; i++) {
            master.submit(i);
        }
        master.execute();
        Map<String, Object> resultMap = master.getResultMap();
        Long res = 0L;
        while (resultMap.size() > 0 || !master.isComplete()) {

            Set<String> keys = resultMap.keySet();
            String key = null;
            for (String k : keys) {
                key = k;
                break;
            }
            System.out.println(key);
            Long singleResult = null;
            if (key != null) {
                singleResult = (Long) resultMap.get(key);
            }

            if (singleResult != null) {
                res += singleResult;
            }

            if (key != null) {
                resultMap.remove(key);
            }
        }
        Long end = System.currentTimeMillis();

        System.out.println("multi thread ,testMasterWorker:" + res);
        System.out.println("multi thread ,process  time is : " + (end - start)
                + "  ms");
    }
}
