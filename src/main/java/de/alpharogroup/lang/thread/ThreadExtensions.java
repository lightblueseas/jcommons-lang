/**
 * The MIT License
 *
 * Copyright (C) 2015 Asterios Raptis
 *
 * Permission is hereby granted, free of charge, to any person obtaining
 * a copy of this software and associated documentation files (the
 * "Software"), to deal in the Software without restriction, including
 * without limitation the rights to use, copy, modify, merge, publish,
 * distribute, sublicense, and/or sell copies of the Software, and to
 * permit persons to whom the Software is furnished to do so, subject to
 * the following conditions:
 *
 * The above copyright notice and this permission notice shall be
 * included in all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND,
 * EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF
 * MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND
 * NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE
 * LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION
 * OF CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION
 * WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.
 */
package de.alpharogroup.lang.thread;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.concurrent.Callable;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ForkJoinPool;
import java.util.function.Supplier;

import lombok.experimental.UtilityClass;

/**
 * The class {@link ThreadExtensions}.
 */
@UtilityClass
public final class ThreadExtensions
{

	/**
	 * Creates a custom thread pool that are executed in parallel processes with the given number of
	 * the cpu cores
	 * 
	 * @param task
	 *            the {@link Callable} task to execute
	 * @param cpuCores
	 *            the number of the cpu cores to run with
	 * @param <T>
	 *            the generic type of the result
	 * @return the result of the given task
	 * @throws ExecutionException
	 *             if the computation threw an exception
	 * @throws InterruptedException
	 *             if the current thread is not a member of a ForkJoinPool and was interrupted while
	 *             waiting
	 */
	public static <T> T runCallableWithCpuCores(Callable<T> task, int cpuCores)
		throws ExecutionException, InterruptedException
	{
		ForkJoinPool forkJoinPool = new ForkJoinPool(cpuCores);
		return forkJoinPool.submit(task).get();
	}

	/**
	 * Creates a custom thread pool that are executed in parallel processes with the will run with
	 * the given number of the cpu cores
	 * 
	 * @param supplier
	 *            the {@link Supplier} task to execute
	 * @param cpuCores
	 *            the number of the cpu cores to run with
	 * @param <T>
	 *            the generic type of the result
	 * @return the result of the given task
	 * @throws ExecutionException
	 *             if the computation threw an exception
	 * @throws InterruptedException
	 *             if the current thread is not a member of a ForkJoinPool and was interrupted while
	 *             waiting
	 */
	public static <T> T runAsyncSupplierWithCpuCores(Supplier<T> supplier, int cpuCores)
		throws ExecutionException, InterruptedException
	{
		ForkJoinPool forkJoinPool = new ForkJoinPool(cpuCores);
		CompletableFuture<T> future = CompletableFuture.supplyAsync(supplier, forkJoinPool);
		return future.get();
	}

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
