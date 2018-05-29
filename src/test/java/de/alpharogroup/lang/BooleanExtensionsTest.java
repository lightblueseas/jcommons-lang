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

import static org.testng.AssertJUnit.assertEquals;

import org.meanbean.factories.ObjectCreationException;
import org.meanbean.test.BeanTestException;
import org.meanbean.test.BeanTester;
import org.testng.annotations.Test;

/**
 * The unit test class for the class {@link BooleanExtensions}.
 */
public class BooleanExtensionsTest
{

	/**
	 * Test method for {@link BooleanExtensions#trueOrFalse(Object, Object, boolean...)}
	 *
	 * @throws Exception
	 *             the exception
	 */
	@Test
	public final void testTrueOrFalse() throws Exception
	{
		String expected;
		String actual;
		expected = "where";
		actual = BooleanExtensions.trueOrFalse("and", "where");
		assertEquals(expected, actual);

		expected = "and";
		actual = BooleanExtensions.trueOrFalse("and", "where", true);
		assertEquals(expected, actual);

		expected = "where";
		actual = BooleanExtensions.trueOrFalse("and", "where", false, false);
		assertEquals(expected, actual);

		expected = "and";
		actual = BooleanExtensions.trueOrFalse("and", "where", true, false);
		assertEquals(expected, actual);

		expected = "and";
		actual = BooleanExtensions.trueOrFalse("and", "where", true, false, false);
		assertEquals(expected, actual);

		expected = "and";
		actual = BooleanExtensions.trueOrFalse("and", "where", true, true, false);
		assertEquals(expected, actual);

		expected = "where";
		actual = BooleanExtensions.trueOrFalse("and", "where", false, false, false);
		assertEquals(expected, actual);
	}

	/**
	 * Test method for {@link BooleanExtensions}
	 */
	@Test(expectedExceptions = { BeanTestException.class, ObjectCreationException.class })
	public void testWithBeanTester()
	{
		final BeanTester beanTester = new BeanTester();
		beanTester.testBean(BooleanExtensions.class);
	}

}
