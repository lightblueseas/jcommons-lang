/**
 * The MIT License
 *
 * Copyright (C) 2007 Asterios Raptis
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
package de.alpharogroup.io;

import java.io.File;
import java.io.IOException;
import java.util.Date;

import org.testng.AssertJUnit;
import org.testng.annotations.Test;

import de.alpharogroup.BaseTestCase;
import de.alpharogroup.date.CreateDateUtils;

/**
 * Test class for the class SerializedObjectUtils.
 *
 * @version 1.0
 * @author Asterios Raptis
 */
public class SerializedObjectUtilsTest extends BaseTestCase
{

	/**
	 * {@inheritDoc}
	 */
	@Override
	protected void setUp() throws Exception
	{
		super.setUp();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	protected void tearDown() throws Exception
	{
		super.tearDown();
	}

	/**
	 * Test method for
	 * {@link de.alpharogroup.io.SerializedObjectUtils#copySerializedObject(java.io.Serializable)} .
	 * 
	 * @throws IOException
	 *             Signals that an I/O exception has occurred.
	 * @throws ClassNotFoundException
	 *             is thrown when a class is not found in the classloader or no definition for the
	 *             class with the specified name could be found.
	 */
	@Test
	public void testCopySerializedObject() throws ClassNotFoundException, IOException
	{
		final String age = "Im too young!";
		final String copy = SerializedObjectUtils.copySerializedObject(age);
		this.result = age.equals(copy);
		AssertJUnit.assertTrue("", this.result);
	}

	/**
	 * Test method for
	 * {@link de.alpharogroup.io.SerializedObjectUtils#readSerializedObjectFromFile(java.io.File)} .
	 * 
	 * @throws IOException
	 *             Signals that an I/O exception has occurred.
	 * @throws ClassNotFoundException
	 *             is thrown when a class is not found in the classloader or no definition for the
	 *             class with the specified name could be found.
	 */
	@Test(enabled = true)
	public void testReadSerializedObjectFromFile() throws IOException, ClassNotFoundException
	{
		final Date birthdayFromNiko = CreateDateUtils.newDate(2007, 11, 8);
		final File writeInMe = new File(".", "testWriteSerializedObjectToFile.dat");
		this.result = SerializedObjectUtils
			.writeSerializedObjectToFile(birthdayFromNiko, writeInMe);
		AssertJUnit.assertTrue("", this.result);
		final Object readedObjectFromFile = SerializedObjectUtils
			.readSerializedObjectFromFile(writeInMe);
		final Date readedObj = (Date)readedObjectFromFile;
		this.result = birthdayFromNiko.equals(readedObj);
		AssertJUnit.assertTrue("", this.result);
		try
		{
			writeInMe.deleteOnExit();
		}
		catch (final Exception e)
		{
			// ignore...
		}
	}

	/**
	 * Test method for
	 * {@link de.alpharogroup.io.SerializedObjectUtils#writeSerializedObjectToFile(java.lang.Object, java.io.File)}
	 * 
	 * @throws IOException
	 *             Signals that an I/O exception has occurred.
	 * @throws ClassNotFoundException
	 *             is thrown when a class is not found in the classloader or no definition for the
	 *             class with the specified name could be found.
	 */
	@Test(enabled = true)
	public void testWriteSerializedObjectToFile() throws IOException, ClassNotFoundException
	{

		final Date birthdayFromNiko = CreateDateUtils.newDate(2007, 11, 8);
		final File writeInMe = new File(".", "testWriteSerializedObjectToFile.dat");
		this.result = SerializedObjectUtils
			.writeSerializedObjectToFile(birthdayFromNiko, writeInMe);
		AssertJUnit.assertTrue("", this.result);
		final Object readedObjectFromFile = SerializedObjectUtils
			.readSerializedObjectFromFile(writeInMe);
		final Date readedObj = (Date)readedObjectFromFile;
		this.result = birthdayFromNiko.equals(readedObj);
		AssertJUnit.assertTrue("", this.result);

	}

}
