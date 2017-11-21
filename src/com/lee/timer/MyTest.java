package com.lee.timer;

public class MyTest {
	public static void main(String[] args) {
//		TimerUtils.runAll(MyTest.class, new Object[]{1,"b"});
//		TimerUtils.batchRunAll(MyTest.class, 1, new Object[][]{{1, "a"},{2, "b"},{3, "c"}});
		
		
	}
	
	@RunTimer
	public void sum(int i){
		long sum = 0;
		for (int j = 0; j < i; j++) {
			sum+=j;
		}
		System.out.println(sum);
	}
	
//	@RunTimer
	public void test(int i, String str) {
		System.out.println("i: "+ i + " str:"+ str);
		try {
			Thread.sleep(2);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
//	@RunTimer
	public void test02(int i, String str) {
//		System.out.println("test running: "+str);
		try {
			Thread.sleep(5);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}
