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
package de.alpharogroup.exception;

import static org.testng.Assert.assertNotNull;
import static org.testng.AssertJUnit.assertEquals;
import static org.testng.AssertJUnit.assertTrue;

import java.io.IOException;

import org.meanbean.factories.ObjectCreationException;
import org.meanbean.test.BeanTestException;
import org.meanbean.test.BeanTester;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import de.alpharogroup.test.objects.Person;

/**
 * The unit test class for the class {@link ExceptionExtensions}.
 *
 * @version 1.0
 * @author Asterios Raptis
 */
public class ExceptionExtensionsTest
{

	/**
	 * Sets up method will be invoked before every unit test method in this class.
	 *
	 * @throws Exception
	 *             the exception
	 */
	@BeforeMethod
	protected void setUp() throws Exception
	{
	}

	/**
	 * Tear down method will be invoked after every unit test method in this class.
	 *
	 * @throws Exception
	 *             the exception
	 */
	@AfterMethod
	protected void tearDown() throws Exception
	{
	}

	/**
	 * Test method for {@link ExceptionExtensions#getStackTrace(Throwable)}
	 *
	 * @throws IOException
	 *             Signals that an I/O exception has occurred.
	 */
	@SuppressWarnings("null")
	@Test
	public void testGetStackTrace() throws IOException
	{
		String expected;
		String actual;
		actual = null;
		try
		{
			final Object objNull = null;
			objNull.getClass();
		}
		catch (final NullPointerException npe)
		{
			actual = ExceptionExtensions.getStackTrace(npe);
		}
		expected = "java.lang.NullPointerException";
		assertTrue(actual.startsWith(expected));

		// null case...
		actual = ExceptionExtensions.getStackTrace(null);
		expected = "throwable is null...";
		assertEquals(actual, expected);

	}

	/**
	 * Test method for {@link ExceptionExtensions#getStackTraceElements(Throwable)}
	 *
	 * @throws IOException
	 *             Signals that an I/O exception has occurred.
	 */
	@SuppressWarnings("null")
	@Test
	public void testGetStackTraceElements() throws IOException
	{
		String expected;
		String actual;
		actual = null;
		try
		{
			final Object objNull = null;
			objNull.getClass();
		}
		catch (final NullPointerException npe)
		{
			actual = ExceptionExtensions.getStackTraceElements(npe);
		}
		expected = "class java.lang.NullPointerException";
		assertTrue(actual.startsWith(expected));

		// null case...
		actual = ExceptionExtensions.getStackTraceElements(null);
		expected = "throwable is null...";
		assertEquals(actual, expected);

		try
		{
			final BeanTester beanTester = new BeanTester();
			beanTester.testBean(ExceptionExtensions.class);
		}
		catch (final Exception e)
		{
			actual = ExceptionExtensions.getStackTraceElements(e);
		}

		expected = "class org.meanbean.test.BeanTestException";
		assertTrue(actual.startsWith(expected));
	}

	/**
	 * Test method for {@link ExceptionExtensions#toString(Object)}
	 */
	@SuppressWarnings("deprecation")
	@Test
	public void testToStringObject()
	{
		String expected;
		String actual;
		actual = ExceptionExtensions.toString(Person.builder().build());
		assertNotNull(actual);

		actual = ExceptionExtensions.toString(null);
		assertNotNull(actual);
		expected = "Given object is null!!!";
		assertEquals(actual, expected);

	}

	/**
	 * Test method for {@link ExceptionExtensions}
	 */
	@Test(expectedExceptions = { BeanTestException.class, ObjectCreationException.class })
	public void testWithBeanTester()
	{
		final BeanTester beanTester = new BeanTester();
		beanTester.testBean(ExceptionExtensions.class);
	}

}
