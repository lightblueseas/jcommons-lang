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

import static org.testng.AssertJUnit.assertTrue;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.Charset;

import org.apache.commons.io.FileUtils;
import org.testng.annotations.Test;

/**
 * The unit test class for the class {@link StringOutputStream}
 *
 * @version 1.0
 * @author Asterios Raptis
 */
public class StringOutputStreamTest
{

	/**
	 * Test method for {@link StringOutputStream#toString()}
	 *
	 * @throws IOException
	 *             Signals that an I/O exception has occurred.
	 */
	@Test
	public void testToString() throws IOException
	{
		final String expected = "Thu Apr 19 00:00:00 CEST 2012";
		final File writeInMe = new File(".", "testToString.log");
		FileUtils.writeStringToFile(writeInMe, expected, Charset.defaultCharset());
		final InputStream inputStream = writeInMe.toURI().toURL().openStream();
		final StringOutputStream stringOutput = new StringOutputStream();

		final byte[] buffer = new byte[8192];
		int readLength;
		while ((readLength = inputStream.read(buffer, 0, buffer.length)) != -1)
		{
			stringOutput.write(buffer, 0, readLength);
		}

		final String actual = stringOutput.toString();
		stringOutput.close();
		assertTrue(actual.startsWith(expected));
		FileUtils.deleteQuietly(writeInMe);
	}

	/**
	 * Test method for {@link StringOutputStream#write(byte[])}
	 *
	 * @throws IOException
	 *             Signals that an I/O exception has occurred.
	 */
	@Test
	public void testWriteByteArray() throws IOException
	{
		final String expected = "Thu Apr 19 00:00:00 CEST 2012";
		final File writeInMe = new File(".", "testWriteByteArray.log");
		FileUtils.writeStringToFile(writeInMe, expected, Charset.defaultCharset());
		final InputStream inputStream = writeInMe.toURI().toURL().openStream();
		final StringOutputStream stringOutput = new StringOutputStream();

		final byte[] buffer = new byte[8192];
		while ((inputStream.read(buffer)) != -1)
		{
			stringOutput.write(buffer);
		}

		final String actual = stringOutput.toString();
		stringOutput.close();
		assertTrue(actual.startsWith(expected));
		FileUtils.deleteQuietly(writeInMe);
	}

	/**
	 * Test method for {@link StringOutputStream#write(int)}
	 *
	 * @throws IOException
	 *             Signals that an I/O exception has occurred.
	 */
	@Test
	public void testWriteInt() throws IOException
	{
		final String expected = "Thu Apr 19 00:00:00 CEST 2012";
		final File writeInMe = new File(".", "testWriteInt.log");
		FileUtils.writeStringToFile(writeInMe, expected, Charset.forName("UTF-8"));
		final InputStream inputStream = writeInMe.toURI().toURL().openStream();
		final StringOutputStream stringOutput = new StringOutputStream();
		stringOutput.setCharset(Charset.forName("UTF-8"));
		int readLength;
		while ((readLength = inputStream.read()) != -1)
		{
			stringOutput.write(readLength);
		}

		final String actual = stringOutput.toString();
		stringOutput.close();
		assertTrue(actual.startsWith(expected));
		FileUtils.deleteQuietly(writeInMe);
	}

	/**
	 * Test method for {@link StringOutputStream#write(byte[])}
	 *
	 * @throws IOException
	 *             Signals that an I/O exception has occurred.
	 */
	@Test
	public void testWriteString() throws IOException
	{
		final String expected = "Thu Apr 19 00:00:00 CEST 2012";
		final File writeInMe = new File(".", "testWriteString.log");
		FileUtils.writeStringToFile(writeInMe, expected, Charset.forName("UTF-8"));
		final InputStream inputStream = writeInMe.toURI().toURL().openStream();
		final StringOutputStream stringOutput = new StringOutputStream();
		stringOutput.setCharset(Charset.forName("UTF-8"));

		final byte[] buffer = new byte[8192];
		while ((inputStream.read(buffer, 0, buffer.length)) != -1)
		{
			stringOutput.write(new String(buffer, Charset.forName("UTF-8")));
		}

		final String actual = stringOutput.toString();
		stringOutput.close();
		assertTrue(actual.startsWith(expected));
		FileUtils.deleteQuietly(writeInMe);
	}

}
