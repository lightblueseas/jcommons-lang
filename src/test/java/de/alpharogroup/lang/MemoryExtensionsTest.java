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
package de.alpharogroup.lang;

import org.meanbean.factories.ObjectCreationException;
import org.meanbean.test.BeanTestException;
import org.meanbean.test.BeanTester;
import org.testng.AssertJUnit;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

/**
 * The unit test class for the class {@link de.alpharogroup.lang.MemoryExtensions}.
 * 
 * @version 1.0
 * @author Asterios Raptis
 */
public class MemoryExtensionsTest
{

	/** The result. */
	boolean result = false;

	/**
	 * Sets up method will be invoked before every unit test method in this class.
	 *
	 * @throws Exception
	 *             the exception
	 */
	@BeforeMethod
	protected void setUp() throws Exception
	{
		this.result = false;
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
	 * Test method for {@link MemoryExtensions#disposeUnusedMemory()}.
	 */
	@Test
	public void testDisposeUnusedMemory()
	{
		long before = MemoryExtensions.getFreeMemoryForAppInKB();
		MemoryExtensions.disposeUnusedMemory();
		long after = MemoryExtensions.getFreeMemoryForAppInKB();
		this.result = after <= before;
		AssertJUnit.assertTrue("", this.result);
	}

	/**
	 * Test method for {@link de.alpharogroup.lang.MemoryExtensions#getFreeMemoryForAppInKB()}.
	 */
	@Test(enabled = true)
	public void testGetFreeMemoryForAppInKB()
	{
		final long expected = (Runtime.getRuntime().totalMemory()
			- Runtime.getRuntime().freeMemory()) / 1024;
		final long compare = MemoryExtensions.getFreeMemoryForAppInKB();
		this.result = expected == compare;
		AssertJUnit.assertTrue("", this.result);
	}

	/**
	 * Test method for {@link de.alpharogroup.lang.MemoryExtensions#getTotalMemoryInKB()}.
	 */
	@Test
	public void testGetFreeMemoryInKB()
	{
		final long expected = Runtime.getRuntime().freeMemory() / 1024;
		final long compare = MemoryExtensions.getFreeMemoryInKB();
		this.result = expected == compare;
		AssertJUnit.assertTrue("", this.result);
	}

	/**
	 * Test method for {@link de.alpharogroup.lang.MemoryExtensions#getTotalMemoryInKB()}.
	 */
	@Test
	public void testGetTotalMemoryInKB()
	{
		final long expected = Runtime.getRuntime().totalMemory() / 1024;
		final long compare = MemoryExtensions.getTotalMemoryInKB();
		this.result = expected == compare;
		AssertJUnit.assertTrue("", this.result);
	}

	/**
	 * Test method for {@link MemoryExtensions}
	 */
	@Test(expectedExceptions = { BeanTestException.class, ObjectCreationException.class })
	public void testWithBeanTester()
	{
		final BeanTester beanTester = new BeanTester();
		beanTester.testBean(MemoryExtensions.class);
	}

}
