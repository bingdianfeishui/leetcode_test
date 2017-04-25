package com.lee.timer;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

public class TimerUtils {
	/**
	 * ����һ�������ִ��clazz�������д���@{@link RunTimer}ע��ķ������ɶ��ִ�У�����ʱ ע�⣺
	 * Ҫִ�д˷�����Ҫ�����д���@RunTimerע��ķ�����������ͬ���β���ʽ������ȫ��Ϊ�ղ�
	 * 
	 * @param clazz
	 *            ��������Ŀ����
	 * @param times
	 *            �ظ�ִ�еĴ���
	 * @param args
	 *            ���������
	 */
	public static void runAll(Class<?> clazz, int times, Object... args) {
		try {
			Method[] methods = clazz.getDeclaredMethods();
			for (Method method : methods) {
				if(method.getDeclaredAnnotation(RunTimer.class) != null)
					invoke(clazz, method, times, args);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * @see #runAll(Class, int, Object...) ���� times=1
	 * @param clazz
	 *            ��������Ŀ����
	 * @param args
	 *            �������
	 */
	public static void runAll(Class<?> clazz, Object... args) {
		runAll(clazz, 1, args);
	}

	/**
	 * @see #runAll(Class, int, Object...) ����argsΪ�����飬��Ҫ�����д���ע��ķ������ǿղ�
	 * @param clazz
	 *            ��������Ŀ����
	 * @param times
	 *            �ظ�ִ�д���
	 */
	public static void runAll(Class<?> clazz, int times) {
		runAll(clazz, times, new Object[] {});
	}

	/**
	 * @see #runAll(Class, int, Object...) ���� times=1, argsΪ�����飬��Ҫ�����д���ע��ķ������ǿղ�
	 *      Ҫ�����д���ע��ķ������ǿղ�
	 * @param clazz
	 *            ��������Ŀ����
	 */
	public static void runAll(Class<?> clazz) {
		runAll(clazz, 1);
	}

	/**
	 * ���ִ��clazz���еķ�����Ϊ methodName �ķ�������ʱ�����Դ��������
	 * ��clazz�����ж�������ķ�������ΪmethodNameʱ����ƥ���һ���ҵ��ķ�����
	 * 
	 * @param clazz
	 *            ��������Ŀ����
	 * @param methodName
	 *            Ŀ�귽����
	 * @param times
	 *            �ظ�ִ�д���
	 * @param args
	 *            �������
	 * @return
	 */
	public static Object run(Class<?> clazz, String methodName, int times, Object... args) {
		try {
			clazz.getDeclaredMethods();
			Method[] methods = clazz.getDeclaredMethods();
			Method method = null;
			for (Method m : methods) {
				if (m.getName().equals(methodName)) {
					method = m;
					break;
				}
			}
			if (method == null)
				throw new IllegalAccessException(clazz + " ���в�������Ϊ \"" + methodName + "\"�ķ���");
			return invoke(clazz, method, times, args);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	/**
	 * @see #run(Class, String, int, Object...) ����times = 1
	 * @param clazz
	 * @param methodName
	 * @param args
	 * @return
	 */
	public static Object run(Class<?> clazz, String methodName, Object... args) {
		return run(clazz, methodName, 1, args);
	}

	/**
	 * @see #run(Class, String, int, Object...) û�з�����Σ����÷��������Ϊvoid
	 * @param clazz
	 * @param methodName
	 * @param times
	 * @return
	 */
	public static Object run(Class<?> clazz, String methodName, int times) {
		return run(clazz, methodName, times, new Object[] {});
	}

	/**
	 * @see #run(Class, String, int, Object...) ���� argsΪ������ Object[]{}�� times=1
	 * @param clazz
	 * @param methodName
	 * @return
	 */
	public static Object run(Class<?> clazz, String methodName) {
		return run(clazz, methodName, 1, new Object[] {});
	}

	/**
	 * �������������������������clazz���з�����ΪmethodName�ķ���������������@{@link RunTimer}��ǣ����ʱ����ӡ
	 * @param clazz ����Ŀ����
	 * @param methodName
	 * @param times
	 * @param argsArr
	 * @return
	 */
	public static Object[] batchRun(Class<?> clazz, String methodName, int times, Object[] argsArr) {
		TimmerController tc = new TimmerController();
		try {
			clazz.getDeclaredMethods();
			Method[] methods = clazz.getDeclaredMethods();
			Method method = null;
			for (Method m : methods) {
				if (m.getName().equals(methodName)) {
					method = m;
					break;
				}
			}
			if (method == null)
				throw new IllegalAccessException(clazz + " ���в�������Ϊ \"" + methodName + "\"�ķ���");

			Object[] objs =  batchInvoke(clazz, method, times, tc, argsArr);
			tc.showTime(method);
			return objs;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	public static Object[] batchRun(Class<?> clazz, String methodName, int times, Object[][] argsArr) {
		try {
			clazz.getDeclaredMethods();
			Method[] methods = clazz.getDeclaredMethods();
			Method method = null;
			for (Method m : methods) {
				if (m.getName().equals(methodName)) {
					method = m;
					break;
				}
			}
			if (method == null)
				throw new IllegalAccessException(clazz + " ���в�������Ϊ \"" + methodName + "\"�ķ���");
			
			TimmerController tc = new TimmerController();
			Object[] objs =  batchInvoke(clazz, method, times, tc, argsArr);
			tc.showTime(method);
			return objs;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public static List<BatchRunResult> batchRunAll(Class<?> clazz, int times, Object[] argsArr){
		List<BatchRunResult> list = new ArrayList<>();
		
		try {
			Method[] methods = clazz.getDeclaredMethods();
			for (Method method : methods) {
				if(method.getDeclaredAnnotation(RunTimer.class) != null)
				{
					TimmerController tc = new TimmerController();
					Object[] objects = batchInvoke(clazz, method, times, tc, argsArr);
//					tc.showTime();
					BatchRunResult result = new BatchRunResult();
					result.setMethodName(method.getName());
					result.setNanoTime(tc.getNanoTime());
					result.setBatchResults(objects);
					result.setTimes(times);
					list.add(result);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		for (BatchRunResult result : list) {
			System.out.println(result);
		}
		return list;
	}
	
	public static List<BatchRunResult> batchRunAll(Class<?> clazz, int times,Object[][] argsArr){
		List<BatchRunResult> list = new ArrayList<>();
		try {
			Method[] methods = clazz.getDeclaredMethods();
			for (Method method : methods) {
				if(method.getDeclaredAnnotation(RunTimer.class) != null)
				{
					TimmerController tc = new TimmerController();
					Object[] objects = batchInvoke(clazz, method, times, tc, argsArr);
//					tc.showTime();
					BatchRunResult result = new BatchRunResult();
					result.setMethodName(method.getName());
					result.setNanoTime(tc.getNanoTime());
					result.setBatchResults(objects);
					result.setTimes(times);
					list.add(result);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		for (BatchRunResult result : list) {
			System.out.println(result);
		}
		return list;
	}
	
	
	/**
	 * ����ظ�ִ��clazz�е�method��������¼����ܹ���ʱ ע�⣺Ϊʹ�ⶨ��ʱ���׼ȷ�����ڼ�ʱǰ�ȵ�����������Ŀ�귽�����ٿ�ʼ��ʱ���á�
	 * 
	 * @param clazz
	 *            Ŀ��Class
	 * @param method
	 *            Ŀ�귽��
	 * @param times
	 *            �ظ�����
	 * @param args
	 *            �������
	 * @return ���һ��ִ��method�ķ���ֵ
	 * @throws InstantiationException
	 * @throws IllegalAccessException
	 * @throws InvocationTargetException
	 */
	private static Object invoke(Class<?> clazz, Method method, int times, Object... args)
			throws InstantiationException, IllegalAccessException, InvocationTargetException {
		System.gc();
		TimmerController tc = new TimmerController();

		System.out.println("Invoke method [" + method.getName() + "] " + times + " times...");
		Object test = clazz.newInstance();
		Object ret;
		ret = method.invoke(test, args);// �ȵ���2�γ�ʼ������
		ret = method.invoke(test, args);
		tc.setStart(System.nanoTime());
		while (times-- > 0) {
			ret = method.invoke(test, args);
		}
		tc.setEnd(System.nanoTime());
		if (method.getDeclaredAnnotation(RunTimer.class) != null)
			tc.showTime();
		return ret;
	}

	/**
	 * �����������������ִ��clazz�е�method��������¼����ܹ���ʱ ע�⣺Ϊʹ�ⶨ��ʱ���׼ȷ�����ڼ�ʱǰ�ȵ�����������Ŀ�귽�����ٿ�ʼ��ʱ���á�
	 * 
	 * @param clazz
	 *            Ŀ��Class
	 * @param method
	 *            Ŀ�귽��
	 * @param times
	 *            �ظ�����
	 * @param tc 
	 * @param argsArr
	 *            ���������������
	 * @return ����ִ��method�ķ���ֵ������������ظ�ִ�ж�Σ�ֻ��¼���һ��ִ�еķ���ֵ��
	 * @throws InstantiationException
	 * @throws IllegalAccessException
	 * @throws InvocationTargetException
	 */
	private static Object[] batchInvoke(Class<?> clazz, Method method, int times, TimmerController tc, Object[] argsArr)
			throws InstantiationException, IllegalAccessException, InvocationTargetException {
		System.gc();

		System.out.println("Invoke method [" + method.getName() + "] " + times + " times...");
		Object[] retArr = new Object[argsArr.length];
		Object test = clazz.newInstance();
		for(int i = 0; i < 10; i++)
			retArr[0] = method.invoke(test, argsArr[0]);// �ȵ���10�γ�ʼ������
		tc.setStart(System.nanoTime());
		for (int i = 0; i < argsArr.length; i++) {
			int t = times;
			while (t-- > 0) {
				retArr[i] = method.invoke(test, argsArr[i]);
			}
		}
		tc.setEnd(System.nanoTime());
		return retArr;
	}
	
	private static Object[] batchInvoke(Class<?> clazz, Method method, int times, TimmerController tc, Object[][] argsArr)
			throws InstantiationException, IllegalAccessException, InvocationTargetException {
		System.gc();
		
		System.out.println("Invoke method [" + method.getName() + "] " + times + " times...");
		Object[] retArr = new Object[argsArr.length];
		Object test = clazz.newInstance();
		for(int i = 0; i < 10; i++)
			retArr[0] = method.invoke(test, argsArr[0]);// �ȵ���10�γ�ʼ������
		System.out.println("Ԥ��ִ����ɣ���ʼ��ʽִ��...");
		tc.setStart(System.nanoTime());
		for (int i = 0; i < argsArr.length; i++) {
			int t = times;
			while (t-- > 0) {
				retArr[i] = method.invoke(test, argsArr[i]);
			}
		}
		tc.setEnd(System.nanoTime());
		return retArr;
	}
	
}

class TimmerController {
	private long start;
	private long end;

	public void setStart(long start) {
		this.start = start;
	}

	public long getNanoTime() {
		return end - start;
	}

	public void setEnd(long end) {
		this.end = end;
	}

	public long escapedNanos() {
		return end - start;
	}

	public void showTime() {
		System.out.printf("Time Escaped(ms): %.3f\n\n", (end - start) / 1000000.0);
	}
	public void showTime(Method method) {
		if (method.getDeclaredAnnotation(RunTimer.class) != null)
			System.out.printf("Time Escaped(ms): %.3f\n\n", (end - start) / 1000000.0);
	}
}
