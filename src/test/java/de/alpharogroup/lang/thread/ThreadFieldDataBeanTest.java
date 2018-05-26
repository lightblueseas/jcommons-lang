package de.alpharogroup.lang.thread;

import static org.testng.AssertJUnit.assertEquals;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;

import org.meanbean.test.BeanTester;
import org.testng.annotations.Test;

import de.alpharogroup.evaluate.object.EqualsHashCodeAndToStringEvaluator;
import de.alpharogroup.evaluate.object.SilentEqualsHashCodeAndToStringEvaluator;

/**
 * The unit test class for the class {@link ThreadFieldDataBean}.
 */
public class ThreadFieldDataBeanTest
{

	/**
	 * Test method for {@link ThreadFieldDataBean#equals(Object)} , {@link ThreadFieldDataBean#hashCode()}
	 * and {@link ThreadFieldDataBean#toString()}
	 *
	 * @throws NoSuchMethodException
	 *             if an accessor method for this property cannot be found
	 * @throws IllegalAccessException
	 *             if the caller does not have access to the property accessor method
	 * @throws InvocationTargetException
	 *             if the property accessor method throws an exception
	 * @throws InstantiationException
	 *             if a new instance of the bean's class cannot be instantiated
	 * @throws IOException
	 *             Signals that an I/O exception has occurred
	 */
	@Test
	public void testEqualsHashcodeAndToStringWithClass() throws NoSuchMethodException,
		IllegalAccessException, InvocationTargetException, InstantiationException, IOException
	{
		boolean expected;
		boolean actual;
		actual = EqualsHashCodeAndToStringEvaluator
			.evaluateEqualsHashcodeAndToString(ThreadFieldDataBean.class);
		expected = true;
		assertEquals(expected, actual);
	}

	/**
	 * Test method for {@link ThreadFieldDataBean#equals(Object)} , {@link ThreadFieldDataBean#hashCode()}
	 * and {@link ThreadFieldDataBean#toString()}
	 */
	@Test
	public void testEqualsHashcodeAndToStringWithClassSilently()
	{
		boolean expected;
		boolean actual;
		actual = SilentEqualsHashCodeAndToStringEvaluator
			.evaluateEqualsHashcodeAndToStringQuietly(ThreadFieldDataBean.class);
		expected = true;
		assertEquals(expected, actual);
	}

	/**
	 * Test method for {@link ThreadFieldDataBean}
	 */
	@Test
	public void testWithBeanTester()
	{
		final BeanTester beanTester = new BeanTester();
		beanTester.testBean(ThreadFieldDataBean.class);
	}
}
