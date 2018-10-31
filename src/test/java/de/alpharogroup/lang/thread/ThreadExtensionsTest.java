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

import static org.testng.AssertJUnit.assertEquals;

import java.util.List;

import org.meanbean.factories.ObjectCreationException;
import org.meanbean.test.BeanTestException;
import org.meanbean.test.BeanTester;
import org.testng.annotations.Test;

import de.alpharogroup.BaseTestCase;

import org.testng.annotations.Test;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.stream.Collectors;

/**
 * The unit test class for the class {@link ThreadExtensions}.
 */
public class ThreadExtensionsTest extends BaseTestCase
{

	/**
	 * Test method for {@link ThreadExtensions#newThreadData()}
	 */
	@Test
	public final void testNewThreadData()
	{
		List<ThreadDataBean> threadData = ThreadExtensions.newThreadData();
		actual = 0 < threadData.size();
		expected = true;
		assertEquals(expected, actual);
	}

	/**
	 * Test method for {@link ThreadExtensions#resolveRunningThreads()}
	 */
	@Test
	public final void testResolveRunningThreads()
	{
		Thread[] runningThreads = ThreadExtensions.resolveRunningThreads();
		actual = 0 < runningThreads.length;
		expected = true;
		assertEquals(expected, actual);
	}

	/**
	 * Test method for {@link ThreadExtensions}
	 */
	@Test(expectedExceptions = { BeanTestException.class, ObjectCreationException.class })
	public void testWithBeanTester()
	{
		final BeanTester beanTester = new BeanTester();
		beanTester.testBean(ThreadExtensions.class);
	}
	
	
    /**
     * Test method for {@link ThreadExtensions#runCallableWithCpuCores(Callable, int)}
     */
    @Test
    public void testRunCallableWithCpuCores() throws ExecutionException, InterruptedException {

        String actual;
        String expected;

        int cores = Runtime.getRuntime().availableProcessors();
        Map<Integer, String> map = new HashMap<>();
        map.put(1, "linode.com");
        map.put(2, "heroku.com");
        map.put(3, "heroku.uk");
        actual = ThreadExtensions.runCallableWithCpuCores(() ->
                        //parallel task here, for example
                        map.entrySet().stream()
                                .parallel()
                                .filter(x -> x.getValue().endsWith("com"))
                                .map(x -> x.getValue())
                                .collect(Collectors.joining()),
                cores);

        expected = "linode.comheroku.com";
        assertEquals(actual, expected);
    }

}
