package de.alpharogroup.lang.thread;

import static org.testng.AssertJUnit.assertEquals;

import java.util.List;

import org.meanbean.factories.ObjectCreationException;
import org.meanbean.test.BeanTestException;
import org.meanbean.test.BeanTester;
import org.testng.annotations.Test;

import de.alpharogroup.BaseTestCase;

/**
 * The unit test class for the class {@link ThreadExtensions}.
 */
public class ThreadExtensionsTest extends BaseTestCase
{

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
	 * Test method for {@link ThreadExtensions}
	 */
	@Test(expectedExceptions = { BeanTestException.class, ObjectCreationException.class })
	public void testWithBeanTester()
	{
		final BeanTester beanTester = new BeanTester();
		beanTester.testBean(ThreadExtensions.class);
	}

}
