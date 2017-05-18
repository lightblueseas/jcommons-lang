package de.alpharogroup.lang.thread;

import java.util.Set;

import lombok.experimental.UtilityClass;

/**
 * The class {@link ThreadExtensions}.
 */
@UtilityClass
public class ThreadExtensions
{


	/**
	 * Finds all threads the are currently running.
	 *
	 * @return An array with all threads the are currently running.
	 */
	public static Thread[] resolveRunningThreads() {
		final Set<Thread> threadSet = Thread.getAllStackTraces().keySet();
		final Thread[] threadArray = threadSet.toArray(new Thread[threadSet.size()]);
		return threadArray;
	}
}
