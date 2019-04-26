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

import static org.testng.Assert.assertNotNull;
import static org.testng.AssertJUnit.assertEquals;
import static org.testng.AssertJUnit.assertTrue;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.Reader;
import java.io.Writer;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.Date;

import org.apache.commons.io.FileUtils;
import org.meanbean.factories.ObjectCreationException;
import org.meanbean.test.BeanTestException;
import org.meanbean.test.BeanTester;
import org.testng.annotations.Test;

import de.alpharogroup.BaseTestCase;
import de.alpharogroup.date.CreateDateExtensions;
import de.alpharogroup.lang.ClassExtensions;
import de.alpharogroup.test.objects.Person;

/**
 * The unit test class for the class {@link StreamExtensions}.
 */
public class StreamExtensionsTest extends BaseTestCase
{
	/** The file. */
	final String propertiesFilename = "de/alpharogroup/lang/resources.properties";

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
		final File writeInMe = new File(".", "testGetByteArrayInputStream.dat");
		actual = SerializedObjectExtensions.writeSerializedObjectToFile(birthdayFromLeonardo,
			writeInMe);
		assertTrue("", actual);
		final InputStream is = writeInMe.toURI().toURL().openStream();
		final byte[] ba = StreamExtensions.getByteArray(is);
		assertTrue(ba.length > 0);
		final Object obj = SerializedObjectExtensions.toObject(ba);
		final Date readedObj = (Date)obj;
		assertEquals(birthdayFromLeonardo, readedObj);
		FileUtils.deleteQuietly(writeInMe);
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
		final File writeInMe = new File(".",
			"testGetByteArrayInputStreamByteArrayOutputStream.dat");
		actual = SerializedObjectExtensions.writeSerializedObjectToFile(birthdayFromLeonardo,
			writeInMe);
		assertTrue("", actual);
		final InputStream is = writeInMe.toURI().toURL().openStream();
		final byte[] ba = StreamExtensions.getByteArray(is,
			new ByteArrayOutputStream(is.available()));
		assertTrue(ba.length > 0);
		final Object obj = SerializedObjectExtensions.toObject(ba);
		final Date readedObj = (Date)obj;
		assertEquals(birthdayFromLeonardo, readedObj);
		FileUtils.deleteQuietly(writeInMe);
	}

	/**
	 * Test method for {@link StreamExtensions#getInputStream(File)}.
	 *
	 * @throws IOException
	 *             Signals that an I/O exception has occurred.
	 */
	@Test
	public void testGetInputStreamFile() throws IOException
	{
		final Date birthdayFromLeonardo = CreateDateExtensions.newDate(2012, 4, 19);
		final File writeInMe = new File(".", "testGetInputStreamFile.dat");
		actual = SerializedObjectExtensions.writeSerializedObjectToFile(birthdayFromLeonardo,
			writeInMe);
		assertTrue("", actual);
		InputStream inputStream = StreamExtensions.getInputStream(writeInMe);
		assertNotNull(inputStream);
		FileUtils.deleteQuietly(writeInMe);
	}

	/**
	 * Test method for {@link StreamExtensions#getInputStream(File, boolean)}.
	 *
	 * @throws IOException
	 *             Signals that an I/O exception has occurred.
	 */
	@Test
	public void testGetInputStreamFileBoolean() throws IOException
	{
		final File writeInMe = new File(".", "testGetInputStreamFileBoolean.dat");

		InputStream inputStream = StreamExtensions.getInputStream(writeInMe, true);
		assertNotNull(inputStream);
		FileUtils.deleteQuietly(writeInMe);
	}


	/**
	 * Test method for {@link StreamExtensions#getInputStream(File, boolean)} in case of the file
	 * not exists and the flag createFile is false.
	 *
	 * @throws IOException
	 *             Signals that an I/O exception has occurred.
	 */
	@Test(expectedExceptions = FileNotFoundException.class)
	public void testGetInputStreamFileBooleanFalse() throws IOException
	{
		StreamExtensions.getInputStream(new File(".", "testGetInputStreamFileBooleanFalse.dat"),
			false);
	}

	/**
	 * Test method for {@link StreamExtensions#getOutputStream(File)}.
	 *
	 * @throws IOException
	 *             Signals that an I/O exception has occurred.
	 */
	@Test
	public void testGetOutputStreamFile() throws IOException
	{
		File fileout = new File(".", "testGetOutputStreamFile.out");
		OutputStream outputStream = StreamExtensions.getOutputStream(fileout, true);
		assertNotNull(outputStream);

		FileUtils.deleteQuietly(fileout);
	}

	/**
	 * Test method for {@link StreamExtensions#getOutputStream(File, boolean)}.
	 *
	 * @throws IOException
	 *             Signals that an I/O exception has occurred.
	 */
	@Test(expectedExceptions = FileNotFoundException.class)
	public void testGetOutputStreamFileBooleanFalse() throws IOException
	{
		StreamExtensions.getOutputStream(new File(".", "testGetOutputStreamFileBooleanFalse.dat"),
			false);
	}

	/**
	 * Test method for {@link StreamExtensions#getReader(File)}.
	 *
	 * @throws IOException
	 *             Signals that an I/O exception has occurred.
	 */
	@Test
	public void testGetReaderFile() throws IOException
	{
		File inputFile = new File(".", "testGetReaderFile.in");
		Reader reader = StreamExtensions.getReader(inputFile, null, true);
		assertNotNull(reader);

		reader = StreamExtensions.getReader(inputFile, "UTF-8", true);
		assertNotNull(reader);

		FileUtils.deleteQuietly(inputFile);
	}

	/**
	 * Test method for {@link StreamExtensions#getReader(File, String, boolean)}.
	 *
	 * @throws IOException
	 *             Signals that an I/O exception has occurred.
	 */
	@Test(expectedExceptions = FileNotFoundException.class)
	public void testGetReaderFileStringBooleanFalse() throws IOException
	{
		StreamExtensions.getReader(new File(".", "testGetReaderFileStringBooleanFalse.out"), null,
			false);
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
	 * Test method for {@link StreamExtensions#getWriter(File)}.
	 *
	 * @throws IOException
	 *             Signals that an I/O exception has occurred.
	 * @throws URISyntaxException
	 *             if this URL is not formatted strictly according to to RFC2396 and cannot be
	 *             converted to a URI.
	 */
	@Test
	public void testGetWriterFile() throws IOException, URISyntaxException
	{
		final URL url = ClassExtensions.getResource(propertiesFilename);
		Writer writer = StreamExtensions.getWriter(new File(url.toURI()));
		assertNotNull(writer);

		File inputFile = new File(".", "testGetWriterFile.in");
		writer = StreamExtensions.getWriter(inputFile, "UTF-8", true);
		assertNotNull(writer);
		FileUtils.deleteQuietly(inputFile);
	}

	/**
	 * Test method for {@link StreamExtensions#getWriter(File, String, boolean)}.
	 *
	 * @throws IOException
	 *             Signals that an I/O exception has occurred.
	 */
	@Test(expectedExceptions = FileNotFoundException.class)
	public void testGetWriterFileStringBooleanFalse() throws IOException
	{
		StreamExtensions.getWriter(new File(".", "testGetWriterFileStringBooleanFalse.out"), null,
			false);
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

	/**
	 * Test method for
	 * {@link StreamExtensions#writeInputStreamToOutputStream(InputStream, OutputStream)}.
	 *
	 * @throws IOException
	 *             Signals that an I/O exception has occurred.
	 * @throws ClassNotFoundException
	 *             is thrown when a class is not found in the classloader or no definition for the
	 *             class with the specified name could be found.
	 */
	@Test
	public void testWriteInputStreamToOutputStreamInputStreamOutputStream()
		throws IOException, ClassNotFoundException
	{
		final Date birthdayFromLeonardo = CreateDateExtensions.newDate(2012, 4, 19);
		final File writeInMe = new File(".",
			"testWriteInputStreamToOutputStreamInputStreamOutputStream.in");
		actual = SerializedObjectExtensions.writeSerializedObjectToFile(birthdayFromLeonardo,
			writeInMe);
		InputStream inputStream = StreamExtensions.getInputStream(writeInMe, true);

		File fileout = new File(".",
			"testWriteInputStreamToOutputStreamInputStreamOutputStream.out");
		try (final OutputStream outputStream = StreamExtensions.getOutputStream(fileout, true);)
		{
			StreamExtensions.writeInputStreamToOutputStream(inputStream, outputStream);
		}

		final Object readedObjectFromFile = SerializedObjectExtensions
			.readSerializedObjectFromFile(fileout);
		assertEquals(readedObjectFromFile, birthdayFromLeonardo);
		FileUtils.deleteQuietly(fileout);
		FileUtils.deleteQuietly(writeInMe);
	}

}
