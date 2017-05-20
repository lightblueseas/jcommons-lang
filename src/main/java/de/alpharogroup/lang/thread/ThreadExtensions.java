package de.alpharogroup.lang.thread;

import java.util.ArrayList;
import java.util.List;
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
	public static Thread[] resolveRunningThreads()
	{
		final Set<Thread> threadSet = Thread.getAllStackTraces().keySet();
		final Thread[] threadArray = threadSet.toArray(new Thread[threadSet.size()]);
		return threadArray;
	}


	/**
	 * Finds all threads the are currently running and converts them to {@link ThreadDataBean} and
	 * puts them to a {@link List} that is returned.
	 *
	 * @return the new {@link List}
	 */
	public static List<ThreadDataBean> newThreadData()
	{
		final Thread[] thread = resolveRunningThreads();
		final List<ThreadDataBean> snapshotOfThreadDataBeans = new ArrayList<>(thread.length);
		for (int i = 0; i < thread.length; i++)
		{
			final Thread t = thread[i];
			snapshotOfThreadDataBeans.add(ThreadDataBean.of(t));
		}
		return snapshotOfThreadDataBeans;
	}
}
