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
package de.alpharogroup.shell;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertNotNull;

import java.io.IOException;

import org.meanbean.factories.ObjectCreationException;
import org.meanbean.test.BeanTestException;
import org.meanbean.test.BeanTester;
import org.testng.annotations.Test;

/**
 * The unit test class for the class {@link LinuxShellExecutor}.
 */
public class LinuxShellExecutorTest
{

	/**
	 * Test method for {@link LinuxShellExecutor#execute(boolean, String...)}
	 *
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	@Test
	public void testExecute() throws IOException, InterruptedException
	{
		String actual;
		actual = LinuxShellExecutor.execute(true, "ls -al");
		assertNotNull(actual);
	}
	
	/**
	 * Test method for {@link LinuxShellExecutor#toString(java.io.InputStream)}
	 *
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	@Test
	public void testToString() throws IOException
	{
		String expected;
		String actual;
		expected = "";
		actual = LinuxShellExecutor.toString(null);
		assertEquals(actual, expected);
	}

	/**
	 * Test method for {@link LinuxShellExecutor}
	 */
	@Test(expectedExceptions = { BeanTestException.class, ObjectCreationException.class })
	public void testWithBeanTester()
	{
		final BeanTester beanTester = new BeanTester();
		beanTester.testBean(LinuxShellExecutor.class);
	}

}
