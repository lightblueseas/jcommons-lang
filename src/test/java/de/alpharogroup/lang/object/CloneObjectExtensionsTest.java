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
package de.alpharogroup.lang.object;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.Date;

import org.testng.AssertJUnit;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import de.alpharogroup.date.CreateDateExtensions;
import de.alpharogroup.test.objects.A;
import lombok.experimental.ExtensionMethod;

@ExtensionMethod(CloneObjectExtensions.class)
public class CloneObjectExtensionsTest
{

	/**
	 * Sets the up.
	 *
	 * @throws Exception
	 *             the exception
	 */
	@BeforeMethod
	public void setUp() throws Exception
	{
	}

	/**
	 * Tear down.
	 *
	 * @throws Exception
	 *             the exception
	 */
	@AfterMethod
	public void tearDown() throws Exception
	{
	}

	/**
	 * Test generic clone method.
	 * 
	 * @throws IOException
	 * @throws InstantiationException
	 * @throws ClassNotFoundException
	 * @throws InvocationTargetException
	 * @throws IllegalArgumentException
	 * @throws IllegalAccessException
	 * @throws SecurityException
	 * @throws NoSuchMethodException
	 */
	@Test(enabled = false)
	public void testClone() throws NoSuchMethodException, SecurityException, IllegalAccessException,
		IllegalArgumentException, InvocationTargetException, ClassNotFoundException,
		InstantiationException, IOException
	{
		final Date past = CreateDateExtensions.newDate(2009, 3, 26, 10, 37, 04);
		final Date otherCopy = CloneObjectExtensions.clone(past);

		boolean result = past.equals(otherCopy);
		AssertJUnit.assertTrue("Cloned object should be equal with the source object.", result);

		final String aString = "Hy there...";

		final String clonedString = CloneObjectExtensions.clone(aString);

		result = aString.equals(clonedString);
		AssertJUnit.assertTrue("Cloned object should be equal with the source object.", result);

		final A a = new A();
		a.setA("a");

		final A anotherCopy = CloneObjectExtensions.clone(a);

		result = a.equals(anotherCopy);
		AssertJUnit.assertTrue("Cloned object should be equal with the source object.", result);
	}

	/**
	 * Test clone object.
	 */
	@Test(enabled = true)
	public void testCloneObject()
	{
		final Date past = CreateDateExtensions.newDate(2009, 3, 26, 10, 37, 04);
		// this is possible through the extension method of lombok :-) ...
		Object otherCopy = past.cloneObjectQuietly();

		boolean result = past.equals(otherCopy);
		AssertJUnit.assertTrue("Cloned object should be equal with the source object.", result);

		final String aString = "Hy there...";

		otherCopy = CloneObjectExtensions.cloneObjectQuietly(aString);

		result = aString.equals(otherCopy);
		AssertJUnit.assertTrue("Cloned object should be equal with the source object.", result);

		final A a = new A();
		a.setA("a");

		final Object anotherCopy = CloneObjectExtensions.cloneObjectQuietly(a);

		result = a.equals(anotherCopy);
		AssertJUnit.assertTrue("Cloned object should be equal with the source object.", result);
	}

}
