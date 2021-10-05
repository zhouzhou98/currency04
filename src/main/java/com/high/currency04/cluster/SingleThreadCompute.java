package com.high.currency04.cluster;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * @author huanglaoxie(微信:yfct-8888)
 * @className SingleThreadCompute
 * @description：
 * @date 2017/11/26 11:58
 */
public class SingleThreadCompute {

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}



	@Test
	public void test() {
		Long start = System.currentTimeMillis();
		long tempResult = 0;
		long result = 0;

		for (long i = 1; i < 1000001; i++) {
			// for(int i=1;i<6;i++){
			tempResult = handle(i);
			result += tempResult;
		}
		System.out.println("single thread ,final result is  : " + result);
		Long end = System.currentTimeMillis();
		System.out.println("single thread ,process  time is : " + (end - start)+"  ms");
	}

	public static long handle(long i) {
		try {
			//模拟执行校验逻辑
			Thread.sleep(5);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		return i*i;
	}
}
