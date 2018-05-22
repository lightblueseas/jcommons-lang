package de.alpharogroup.lang.thread;

import static org.testng.AssertJUnit.assertEquals;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;

import org.meanbean.test.BeanTester;
import org.testng.annotations.Test;

import de.alpharogroup.test.objects.evaluations.EqualsHashCodeAndToStringEvaluator;
import de.alpharogroup.test.objects.evaluations.SilentEqualsHashCodeAndToStringEvaluator;

/**
 * The unit test class for the class {@link ThreadDataBean}.
 */
public class ThreadDataBeanTest
{

	/**
	 * Test method for {@link ThreadDataBean#equals(Object)} , {@link ThreadDataBean#hashCode()}
	 * and {@link ThreadDataBean#toString()}
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
			.evaluateEqualsHashcodeAndToString(ThreadDataBean.class);
		expected = true;
		assertEquals(expected, actual);
	}

	/**
	 * Test method for {@link ThreadDataBean#equals(Object)} , {@link ThreadDataBean#hashCode()}
	 * and {@link ThreadDataBean#toString()}
	 */
	@Test
	public void testEqualsHashcodeAndToStringWithClassSilently()
	{
		boolean expected;
		boolean actual;
		actual = SilentEqualsHashCodeAndToStringEvaluator
			.evaluateEqualsHashcodeAndToStringQuietly(ThreadDataBean.class);
		expected = true;
		assertEquals(expected, actual);
	}

	/**
	 * Test method for {@link ThreadDataBean}
	 */
	@Test
	public void testWithBeanTester()
	{
		final BeanTester beanTester = new BeanTester();
		beanTester.testBean(ThreadDataBean.class);
	}
}
