package com.lee.timer;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

public class TimerUtils {
	/**
	 * 传入一组参数，执行clazz类中所有带有@{@link RunTimer}注解的方法，可多次执行，并计时 注意：
	 * 要执行此方法，要求所有带有@RunTimer注解的方法都具有相同的形参形式，比如全部为空参
	 * 
	 * @param clazz
	 *            待搜索的目标类
	 * @param times
	 *            重复执行的次数
	 * @param args
	 *            方法的入参
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
	 * @see #runAll(Class, int, Object...) 其中 times=1
	 * @param clazz
	 *            待搜索的目标类
	 * @param args
	 *            方法入参
	 */
	public static void runAll(Class<?> clazz, Object... args) {
		runAll(clazz, 1, args);
	}

	/**
	 * @see #runAll(Class, int, Object...) 其中args为空数组，即要求所有带有注解的方法都是空参
	 * @param clazz
	 *            待搜索的目标类
	 * @param times
	 *            重复执行次数
	 */
	public static void runAll(Class<?> clazz, int times) {
		runAll(clazz, times, new Object[] {});
	}

	/**
	 * @see #runAll(Class, int, Object...) 其中 times=1, args为空数组，即要求所有带有注解的方法都是空参
	 *      要求所有带有注解的方法都是空参
	 * @param clazz
	 *            待搜索的目标类
	 */
	public static void runAll(Class<?> clazz) {
		runAll(clazz, 1);
	}

	/**
	 * 多次执行clazz类中的方法名为 methodName 的方法并计时，可以传入参数。
	 * 当clazz类中有多个方法的方法名都为methodName时，仅匹配第一个找到的方法。
	 * 
	 * @param clazz
	 *            待搜索的目标类
	 * @param methodName
	 *            目标方法名
	 * @param times
	 *            重复执行次数
	 * @param args
	 *            方法入参
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
				throw new IllegalAccessException(clazz + " 类中不存在名为 \"" + methodName + "\"的方法");
			return invoke(clazz, method, times, args);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	/**
	 * @see #run(Class, String, int, Object...) 其中times = 1
	 * @param clazz
	 * @param methodName
	 * @param args
	 * @return
	 */
	public static Object run(Class<?> clazz, String methodName, Object... args) {
		return run(clazz, methodName, 1, args);
	}

	/**
	 * @see #run(Class, String, int, Object...) 没有方法入参，即该方法的入参为void
	 * @param clazz
	 * @param methodName
	 * @param times
	 * @return
	 */
	public static Object run(Class<?> clazz, String methodName, int times) {
		return run(clazz, methodName, times, new Object[] {});
	}

	/**
	 * @see #run(Class, String, int, Object...) 其中 args为空数组 Object[]{}， times=1
	 * @param clazz
	 * @param methodName
	 * @return
	 */
	public static Object run(Class<?> clazz, String methodName) {
		return run(clazz, methodName, 1, new Object[] {});
	}

	/**
	 * 采用批量参数，多次批量调用clazz类中方法名为methodName的方法。若方法带有@{@link RunTimer}标记，则计时并打印
	 * @param clazz 搜索目标类
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
				throw new IllegalAccessException(clazz + " 类中不存在名为 \"" + methodName + "\"的方法");

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
				throw new IllegalAccessException(clazz + " 类中不存在名为 \"" + methodName + "\"的方法");
			
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
	 * 多次重复执行clazz中的method方法并记录输出总共用时 注意：为使测定的时间更准确，会在计时前先单独调用两次目标方法，再开始计时调用。
	 * 
	 * @param clazz
	 *            目标Class
	 * @param method
	 *            目标方法
	 * @param times
	 *            重复次数
	 * @param args
	 *            方法入参
	 * @return 最后一次执行method的返回值
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
		ret = method.invoke(test, args);// 先调用2次初始化环境
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
	 * 传入批量参数，多次执行clazz中的method方法并记录输出总共用时 注意：为使测定的时间更准确，会在计时前先单独调用两次目标方法，再开始计时调用。
	 * 
	 * @param clazz
	 *            目标Class
	 * @param method
	 *            目标方法
	 * @param times
	 *            重复次数
	 * @param tc 
	 * @param argsArr
	 *            批量方法入参数组
	 * @return 批量执行method的返回值。若单组参数重复执行多次，只记录最后一次执行的返回值。
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
			retArr[0] = method.invoke(test, argsArr[0]);// 先调用10次初始化环境
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
			retArr[0] = method.invoke(test, argsArr[0]);// 先调用10次初始化环境
		System.out.println("预热执行完成，开始正式执行...");
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
