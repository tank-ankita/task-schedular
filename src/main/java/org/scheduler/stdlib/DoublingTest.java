package org.scheduler.stdlib;

import java.util.function.BiConsumer;
import java.util.function.Consumer;
import java.util.function.Function;

public class DoublingTest {
	private static double time;
	private static long ops;
	/**
	 * During a doubling test, the provided code may call {@code incOps} and/or {@code addOps}.
	 * If either is called, then the number of operations so recorded will be reported by {@code run}.
	 */
	public static void incOps () { ops++; }
	/**
	 * During a doubling test, the provided code may call {@code incOps} and/or {@code addOps}.
	 * If either is called, then the number of operations so recorded will be reported by {@code run}.
	 */
	public static void addOps (int count) {
		if (count < 1) throw new IllegalArgumentException ();
		ops += count;
	}
	/**
	 * See fully parameterized {@code run} for a description.
	 * In this abbreviated form, {@code numTestsPerValue} is 1.
	 * {@code setup} does nothing, therefore no value is provided to {@code timed}.
	 */
	public static void run (int initialValue, int numValues, Consumer<Integer> timed) {
			run (initialValue, numValues, 1, x -> {}, timed);
	}
	/**
	 * See fully parameterized {@code run} for a description.
	 * In this abbreviated form, {@code numTestsPerValue} is 1.
	 * No value is passed from {@code setup} to {@code timed}.
	 */
	public static void run (int initialValue, int numValues, Consumer<Integer> setup, Consumer<Integer> timed) {
		run (initialValue, numValues, 1, setup, timed);
	}
	/**
	 * See fully parameterized {@code run} for a description.
	 * In this abbreviated form, no value is passed from {@code setup} to {@code timed}.
	 */
	public static void run (int initialValue, int numValues, int numTestsPerValue, Consumer<Integer> setup, Consumer<Integer> timed) {
		run (initialValue, numValues, numTestsPerValue, (Integer x) -> { setup.accept (x); return null; }, (x,t) -> timed.accept (x));
	}
	/**
	 * See fully parameterized {@code run} for a description.
	 * In this abbreviated form, {@code numTestsPerValue} is 1.
	 */
	public static <T> void run (int initialValue, int numValues, Function<Integer,T> setup, BiConsumer<Integer,T> timed) {
		run (initialValue, numValues, 1, setup, timed);
	}
	/**
	 * See fully parameterized {@code run} for a description.
	 * In this abbreviated form, the integer value is not passed to {@code timed}.
	 */
	public static <T> void run (int initialValue, int numValues, int numTestsPerValue, Function<Integer,T> setup, Consumer<T> timed) {
		run (initialValue, numValues, numTestsPerValue, setup, (x,t) -> timed.accept (t));
	}
	/**
	 * See fully parameterized {@code run} for a description.
	 * In this abbreviated form, {@code numTestsPerValue} is 1.
	 * The integer value is not passed to {@code timed}.
	 */
	public static <T> void run (int initialValue, int numValues, Function<Integer,T> setup, Consumer<T> timed) {
		run (initialValue, numValues, 1, setup, timed);
	}
	/**
	 * Run the function {@code timed} with increasing large values for the first integer parameter (N).
	 * Running time is reported to the console.
	 * Initial value of N is given by {@code initialValue}.
	 * N doubles each time the test is run.
	 * The number of different values for N is given by {@code numValues}.
	 * If {@code numTestsPerValue} is larger than one, then the test will be run multiple times for each N, and the average time reported.
	 * The {@code setup} function is called before {@code timed}, and the result (of type T) is provided to {@code timed} as the second parameter.
	 * {@code setup} is called once before each call to {@code timed}.
	 */
	public static <T> void run (int initialValue, int numValues, int numTestsPerValue, Function<Integer,T> setup, BiConsumer<Integer,T> timed) {
		int value = initialValue;
		runOnce (value, numTestsPerValue, setup, timed);
		if (ops != 0) {
			System.out.format("N=%,13d, ops=%,13d,             seconds=%5.3f\n", value, ops/numTestsPerValue, time/numTestsPerValue);
		} else {
			System.out.format("N=%,13d, seconds=%5.3f\n", value, time/numTestsPerValue);
		}
		double prevOps = ops;
		double prevTime = time;
		for (int i=1; i<numValues; i++) {
			value *= 2;
			runOnce (value, numTestsPerValue, setup, timed);
			if (ops != 0) {
				System.out.format("N=%,13d, ops=%,13d ratio=%5.1f, seconds=%5.3f ratio=%5.3f\n", value, ops/numTestsPerValue, ops/prevOps, time/numTestsPerValue, time/prevTime);
			} else {
				System.out.format("N=%,13d, seconds=%5.3f ratio=%5.3f\n", value, time/numTestsPerValue, time/prevTime);
			}
			prevOps = ops;
			prevTime = time;
		}
	}
	private static <T> void runOnce (int value, int numTestsPerValue, Function<Integer,T> setup, BiConsumer<Integer,T> timed) {
		ops = 0;
		time = 0;
		for (int testNum = 0; testNum < numTestsPerValue; testNum++) {
			T setupResult = setup.apply (value);
			Stopwatch sw = new Stopwatch ();
			timed.accept (value, setupResult);
			time += sw.elapsedTime ();
		}
	}

	// Now repeat everything with Long, rather than Integer

	/**
	 * See fully parameterized {@code runLong} for a description.
	 * In this abbreviated form, {@code numTestsPerValue} is 1.
	 * {@code setup} does nothing, therefore no value is provided to {@code timed}.
	 */
	public static void runLong (long initialValue, int numValues, Consumer<Long> timed) {
		runLong (initialValue, numValues, 1, x -> {}, timed);
	}
	/**
	 * See fully parameterized {@code runLong} for a description.
	 * In this abbreviated form, {@code numTestsPerValue} is 1.
	 * No value is passed from {@code setup} to {@code timed}.
	 */
	public static void runLong (long initialValue, int numValues, Consumer<Long> setup, Consumer<Long> timed) {
		runLong (initialValue, numValues, 1, setup, timed);
	}
	/**
	 * See fully parameterized {@code runLong} for a description.
	 * In this abbreviated form, no value is passed from {@code setup} to {@code timed}.
	 */
	public static void runLong (long initialValue, int numValues, int numTestsPerValue, Consumer<Long> setup, Consumer<Long> timed) {
		runLong (initialValue, numValues, numTestsPerValue, (Long x) -> { setup.accept (x); return null; }, (x,t) -> timed.accept (x));
	}
	/**
	 * See fully parameterized {@code runLong} for a description.
	 * In this abbreviated form, {@code numTestsPerValue} is 1.
	 */
	public static <T> void runLong (long initialValue, int numValues, Function<Long,T> setup, BiConsumer<Long,T> timed) {
		runLong (initialValue, numValues, 1, setup, timed);
	}
	/**
	 * See fully parameterized {@code runLong} for a description.
	 * In this abbreviated form, the integer value is not passed to {@code timed}.
	 */
	public static <T> void runLong (long initialValue, int numValues, int numTestsPerValue, Function<Long,T> setup, Consumer<T> timed) {
		runLong (initialValue, numValues, numTestsPerValue, setup, (x,t) -> timed.accept (t));
	}
	/**
	 * See fully parameterized {@code runLong} for a description.
	 * In this abbreviated form, {@code numTestsPerValue} is 1.
	 * The integer value is not passed to {@code timed}.
	 */
	public static <T> void runLong (long initialValue, int numValues, Function<Long,T> setup, Consumer<T> timed) {
		runLong (initialValue, numValues, 1, setup, timed);
	}
	/**
	 * Run the function {@code timed} with increasing large values for the first integer parameter (N).
	 * Running time is reported to the console.
	 * Initial value of N is given by {@code initialValue}.
	 * N doubles each time the test is runLong.
	 * The number of different values for N is given by {@code numValues}.
	 * If {@code numTestsPerValue} is larger than one, then the test will be runLong multiple times for each N, and the average time reported.
	 * The {@code setup} function is called before {@code timed}, and the result (of type T) is provided to {@code timed} as the second parameter.
	 * {@code setup} is called once before each call to {@code timed}.
	 */
	public static <T> void runLong (long initialValue, int numValues, int numTestsPerValue, Function<Long,T> setup, BiConsumer<Long,T> timed) {
		long value = initialValue;
		runOnce (value, numTestsPerValue, setup, timed);
		if (ops != 0) {
			System.out.format("N=%,13d, ops=%,13d,             seconds=%5.3f\n", value, ops/numTestsPerValue, time/numTestsPerValue);
		} else {
			System.out.format("N=%,13d, seconds=%5.3f\n", value, time/numTestsPerValue);
		}
		double prevOps = ops;
		double prevTime = time;
		for (int i=1; i<numValues; i++) {
			value *= 2;
			runOnce (value, numTestsPerValue, setup, timed);
			if (ops != 0) {
				System.out.format("N=%,13d, ops=%,13d ratio=%5.1f, seconds=%5.3f ratio=%5.3f\n", value, ops/numTestsPerValue, ops/prevOps, time/numTestsPerValue, time/prevTime);
			} else {
				System.out.format("N=%,13d, seconds=%5.3f ratio=%5.3f\n", value, time/numTestsPerValue, time/prevTime);
			}
			prevOps = ops;
			prevTime = time;
		}
	}
	private static <T> void runOnce (long value, int numTestsPerValue, Function<Long,T> setup, BiConsumer<Long,T> timed) {
		ops = 0;
		time = 0;
		for (int testNum = 0; testNum < numTestsPerValue; testNum++) {
			T setupResult = setup.apply (value);
			Stopwatch sw = new Stopwatch ();
			timed.accept (value, setupResult);
			time += sw.elapsedTime ();
		}
	}
}
