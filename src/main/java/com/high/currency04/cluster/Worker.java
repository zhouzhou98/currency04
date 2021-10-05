package com.high.currency04.cluster;
import java.util.Map;
import java.util.Queue;

/**
 * @author huanglaoxie(微信:yfct-8888)
 * @className Worker
 * @description：
 * @date 2017/11/26 11:58
 */
public class Worker implements Runnable{

	protected Queue<Object> workQueue;

	protected Map<String,Object> resultMap;
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
			 if (input == null) break;
			 Object result=handle(input);
			 resultMap.put(Integer.toString(input.hashCode()), result);
		}
	}
}
