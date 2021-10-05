package com.high.currency04.cluster;
import java.util.Map;
import java.util.Set;
import org.junit.Test;

/**
 * @author huanglaoxie(微信:yfct-8888)
 * @className MultiThreadCompute
 * @description：
 * @date 2017/11/26 11:58
 */
public class MultiThreadCompute {

	@Test
	public void testCompute() {
		Long start = System.currentTimeMillis();
		Master master = new Master(new ComputeWorker(), 100);
		for (long i = 1; i < 1000001; i++) {
			master.submit(i);
		}
		master.execute();

		long re = 0;
		Map<String, Object> resultMap = master.getResultMap();
		
		while (resultMap.size() > 0 || !master.isComplete()) {
			Set<String> keys = resultMap.keySet();
			String key = null;
			for (String k : keys) {
				key = k;
				break;
			}
			Long singleResult = null;
			if (key != null)
				singleResult = (Long) resultMap.get(key);
			if (singleResult != null)
				re += singleResult;
			if (key != null)
				resultMap.remove(key);
		}
		
		
		Long end = System.currentTimeMillis();

		System.out.println("multi thread ,testMasterWorker:" + re);
		System.out.println("multi thread ,process  time is : " + (end - start)
				+ "  ms");
	}



}
