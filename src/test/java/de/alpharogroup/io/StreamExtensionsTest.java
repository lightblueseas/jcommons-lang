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
package de.alpharogroup.io;

import static org.testng.AssertJUnit.assertEquals;
import static org.testng.AssertJUnit.assertTrue;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.Reader;
import java.io.Writer;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.Date;

import org.meanbean.factories.ObjectCreationException;
import org.meanbean.test.BeanTestException;
import org.meanbean.test.BeanTester;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import de.alpharogroup.date.CreateDateExtensions;
import de.alpharogroup.lang.ClassExtensions;
import de.alpharogroup.test.objects.Person;

/**
 * The unit test class for the class {@link StreamExtensions}.
 */
public class StreamExtensionsTest
{
	/** The file. */
	final String propertiesFilename = "de/alpharogroup/lang/resources.properties";
	boolean result;

	/**
	 * Sets up method will be invoked before every unit test method in this class.
	 */
	@BeforeMethod
	protected void setUp()
	{
	}

	/**
	 * Test close input stream.
	 */
	@Test(enabled = true)
	public void testCloseInputStream()
	{
		final InputStream is = ClassExtensions.getResourceAsStream(propertiesFilename);
		result = StreamExtensions.closeInputStream(is);
		assertTrue("", result);
	}

	/**
	 * Test close output stream.
	 * 
	 * @throws IOException
	 *             Signals that an I/O exception has occurred.
	 * @throws URISyntaxException
	 *             if this URL is not formatted strictly according to to RFC2396 and cannot be
	 *             converted to a URI.
	 */
	@Test(enabled = true)
	public void testCloseOutputStream() throws IOException, URISyntaxException
	{
		final URL url = ClassExtensions.getResource(propertiesFilename);
		final OutputStream os = StreamExtensions.getOutputStream(new File(url.toURI()));
		this.result = StreamExtensions.closeOutputStream(os);
		assertTrue("", this.result);
	}

	/**
	 * Test close reader.
	 * 
	 * @throws IOException
	 *             Signals that an I/O exception has occurred.
	 * @throws URISyntaxException
	 *             if this URL is not formatted strictly according to to RFC2396 and cannot be
	 *             converted to a URI.
	 */
	@Test(enabled = true)
	public void testCloseReader() throws IOException, URISyntaxException
	{
		final URL url = ClassExtensions.getResource(propertiesFilename);
		final Reader reader = StreamExtensions.getReader(new File(url.toURI()));
		this.result = StreamExtensions.closeReader(reader);
		assertTrue("", this.result);
	}

	/**
	 * Test close writer.
	 * 
	 * @throws IOException
	 *             Signals that an I/O exception has occurred.
	 * @throws URISyntaxException
	 *             if this URL is not formatted strictly according to to RFC2396 and cannot be
	 *             converted to a URI.
	 */
	@Test(enabled = true)
	public void testCloseWriter() throws IOException, URISyntaxException
	{
		final URL url = ClassExtensions.getResource(propertiesFilename);
		final Writer writer = StreamExtensions.getWriter(new File(url.toURI()));
		this.result = StreamExtensions.closeWriter(writer);
		assertTrue("", this.result);
	}

	/**
	 * Test get byte array input stream.
	 * 
	 * @throws IOException
	 *             Signals that an I/O exception has occurred.
	 * @throws ClassNotFoundException
	 *             is thrown when a class is not found in the classloader or no definition for the
	 *             class with the specified name could be found.
	 */
	@Test(enabled = true)
	public void testGetByteArrayInputStream() throws IOException, ClassNotFoundException
	{
		final Date birthdayFromLeonardo = CreateDateExtensions.newDate(2012, 4, 19);
		final File writeInMe = new File(".", "testWriteSerializedObjectToFile.dat");
		result = SerializedObjectExtensions.writeSerializedObjectToFile(birthdayFromLeonardo,
			writeInMe);
		assertTrue("", result);
		final InputStream is = writeInMe.toURI().toURL().openStream();
		final byte[] ba = StreamExtensions.getByteArray(is);
		assertTrue(ba.length > 0);
		final Object obj = SerializedObjectExtensions.toObject(ba);
		final Date readedObj = (Date)obj;
		assertEquals(birthdayFromLeonardo, readedObj);
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
	 * Test get byte array input stream byte array output stream.
	 * 
	 * @throws IOException
	 *             Signals that an I/O exception has occurred.
	 * @throws ClassNotFoundException
	 *             Signals that an ClassNotFoundException has occurred. if a class was not found.
	 */
	@Test(enabled = true)
	public void testGetByteArrayInputStreamByteArrayOutputStream()
		throws IOException, ClassNotFoundException
	{
		final Date birthdayFromLeonardo = CreateDateExtensions.newDate(2012, 4, 19);
		final File writeInMe = new File(".", "testWriteSerializedObjectToFile.dat");
		result = SerializedObjectExtensions.writeSerializedObjectToFile(birthdayFromLeonardo,
			writeInMe);
		assertTrue("", result);
		final InputStream is = writeInMe.toURI().toURL().openStream();
		final byte[] ba = StreamExtensions.getByteArray(is,
			new ByteArrayOutputStream(is.available()));
		assertTrue(ba.length > 0);
		final Object obj = SerializedObjectExtensions.toObject(ba);
		final Date readedObj = (Date)obj;
		assertEquals(birthdayFromLeonardo, readedObj);
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
	 * Test get serial version uid.
	 */
	@SuppressWarnings("unchecked")
	@Test(enabled = true)
	public void testGetSerialVersionUID()
	{
		final Class<Person> personClass = (Class<Person>)new Person().getClass();
		final long serialVersionUID = StreamExtensions.getSerialVersionUID(personClass);
		assertTrue("serialVersionUID should be 1L.", serialVersionUID == 1L);
	}

	/**
	 * Test method for {@link StreamExtensions}
	 */
	@Test(expectedExceptions = { BeanTestException.class, ObjectCreationException.class })
	public void testWithBeanTester()
	{
		final BeanTester beanTester = new BeanTester();
		beanTester.testBean(StreamExtensions.class);
	}

}
