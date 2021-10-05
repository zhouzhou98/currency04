package com.high.currency04.master;

import java.util.HashMap;
import java.util.Map;
import java.util.Queue;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentLinkedQueue;

public class Master {
    protected Queue<Object> workQueue = new ConcurrentLinkedQueue<>();
    protected Map<String, Object> resultMap = new ConcurrentHashMap<>();
    protected Map<String, Thread> workThreadMap = new HashMap<>();

    public Master(Worker worker,int countWorker){
        worker.setWorkQueue(workQueue);
        worker.setResultMap(resultMap);
        for(int i = 0; i < countWorker; i++) {
            workThreadMap.put(Integer.toString(i), new Thread(worker, Integer.toString(i)));
        }
    }

    public Map<String, Object> getResultMap() {
        return resultMap;
    }

    public boolean isComplete(){
        for(Map.Entry<String,Thread> entry: workThreadMap.entrySet()){
            if(entry.getValue().getState()!=Thread.State.TERMINATED){
                return false;
            }
        }
        return true;
    }
    //提交
    public void submit(Object job){
        workQueue.add(job);
    }
    //发起执行
    public void execute(){
        for(Map.Entry<String, Thread> entry : workThreadMap.entrySet()){
            entry.getValue().start();
        }
    }

}
