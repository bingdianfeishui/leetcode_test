package com.lee.timer;

import java.util.Arrays;

public class BatchRunResult {
	private String methodName;
	private int times;
	private long	nanoTime;
	private Object[] batchResults;
	public BatchRunResult() { }

	public String getMethodName() {
		return methodName;
	}
	public void setMethodName(String methodName) {
		this.methodName = methodName;
	}
	
	public int getTimes() {
		return times;
	}

	public void setTimes(int times) {
		this.times = times;
	}

	public long getNanoTime() {
		return nanoTime;
	}
	public void setNanoTime(long nanoTime) {
		this.nanoTime = nanoTime;
	}
	public Object[] getBatchResults() {
		return batchResults;
	}
	public void setBatchResults(Object[] batchResults) {
		this.batchResults = batchResults;
	}
	public double getMilliSeconds(){
		return nanoTime / 1000000.0;
	}
	
	@Override
	public String toString() {
		
		StringBuilder sb = new StringBuilder();
		
		if(batchResults!=null && batchResults.length > 0){
			if(batchResults[0] instanceof Object[]){
				for(Object obj: batchResults){
					sb.append(Arrays.toString((Object[])obj) + "\t");
				}
			} else if(batchResults[0] instanceof byte[]){
				for(Object obj: batchResults){
					sb.append(Arrays.toString((byte[])obj) + "\t");
				}
			}else if(batchResults[0] instanceof short[]){
				for(Object obj: batchResults){
					sb.append(Arrays.toString((short[])obj) + "\t");
				}
			}else if(batchResults[0] instanceof int[]){
				for(Object obj: batchResults){
					sb.append(Arrays.toString((int[])obj) + "\t");
				}
			}else if(batchResults[0] instanceof long[]){
				for(Object obj: batchResults){
					sb.append(Arrays.toString((long[])obj) + "\t");
				}
			}else if(batchResults[0] instanceof float[]){
				for(Object obj: batchResults){
					sb.append(Arrays.toString((float[])obj) + "\t");
				}
			}else if(batchResults[0] instanceof double[]){
				for(Object obj: batchResults){
					sb.append(Arrays.toString((double[])obj) + "\t");
				}
			}else if(batchResults[0] instanceof char[]){
				for(Object obj: batchResults){
					sb.append(Arrays.toString((char[])obj) + "\t");
				}
			}else if(batchResults[0] instanceof boolean[]){
				for(Object obj: batchResults){
					sb.append(Arrays.toString((boolean[])obj) + "\t");
				}
			}
			else{
				sb.append(Arrays.toString(batchResults) );
			}
		}
		
		return methodName +"\t" + String.format(" %.2f ms", getMilliSeconds()) +"\t "+ sb.toString() ;
	}
	
	
}
