package com.high.currency04.cluster;
import java.util.HashMap;
import java.util.Map;
import java.util.Queue;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentLinkedQueue;

/**
 * @author huanglaoxie(微信:yfct-8888)
 * @className Master
 * @description：
 * @date 2017/11/26 11:58
 */

public class Master {

	protected Queue<Object> workQueue = new ConcurrentLinkedQueue<Object>();
	//装载Worker线程
	protected Map<String,Thread> workerThreadMap=new HashMap<String,Thread>();
    //结果集
	protected Map<String,Object> resultMap = new ConcurrentHashMap<String,Object>();
	

	public boolean isComplete(){
		for(Map.Entry<String,Thread> entry:workerThreadMap.entrySet()){
			if(entry.getValue().getState()!=Thread.State.TERMINATED){
				return false;
			}
		}
		return true;
	}
	

	public Master(Worker worker,int countWorker){
		worker.setWorkQueue(workQueue);
		worker.setResultMap(resultMap);
		for(int i=0;i<countWorker ;i++)
			workerThreadMap.put(Integer.toString(i), new Thread(worker,Integer.toString(i)));
	}
	
     //提交
	public void submit(Object job){
		workQueue.add(job);
	}
	

	public Map<String,Object>  getResultMap(){
		return resultMap;
	}
	
    //发起执行
	public void execute(){
		for(Map.Entry<String, Thread> entry:workerThreadMap.entrySet()){
			entry.getValue().start();
		}
	}
}
