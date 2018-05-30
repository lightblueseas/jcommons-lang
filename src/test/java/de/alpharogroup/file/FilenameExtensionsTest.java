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
package de.alpharogroup.file;

import static org.testng.Assert.assertTrue;
import static org.testng.AssertJUnit.assertEquals;

import java.io.File;
import java.net.URISyntaxException;

import org.meanbean.factories.ObjectCreationException;
import org.meanbean.test.BeanTestException;
import org.meanbean.test.BeanTester;
import org.testng.annotations.Test;

import de.alpharogroup.lang.ClassExtensions;

/**
 * The unit test class for the class {@link FilenameExtensions}.
 */
public class FilenameExtensionsTest
{

	/**
	 * Test method for {@link FilenameExtensions#getFilenamePrefix(File)}.
	 * 
	 * @throws URISyntaxException
	 *             occurs by creation of the file with an uri.
	 */
	@Test
	public void testGetFilenamePrefix() throws URISyntaxException
	{
		String expected;
		String actual;
		actual = null;
		final String propertiesFilename = "de/alpharogroup/lang/resources.properties";

		final File file = ClassExtensions.getResourceAsFile(propertiesFilename);

		expected = "de/alpharogroup/lang/resources";
		actual = FilenameExtensions.getFilenamePrefix(file);
		assertTrue(actual.endsWith(expected));
	}

	/**
	 * Test method for {@link FilenameExtensions#getFilenameSuffix(File)}.
	 * 
	 * @throws URISyntaxException
	 *             occurs by creation of the file with an uri.
	 */
	@Test
	public void testGetFilenameSuffix() throws URISyntaxException
	{
		String expected;
		String actual;
		actual = null;
		final String propertiesFilename = "de/alpharogroup/lang/resources.properties";

		final File file = ClassExtensions.getResourceAsFile(propertiesFilename);

		expected = ".properties";
		actual = FilenameExtensions.getFilenameSuffix(file);
		assertEquals(expected, actual);
	}

	/**
	 * Test method for {@link FilenameExtensions#getFilenameWithoutExtension(File)}.
	 * 
	 * @throws URISyntaxException
	 *             occurs by creation of the file with an uri.
	 */
	@Test
	public void testGetFilenameWithoutExtensionFileNullCase() throws URISyntaxException
	{
		String expected;
		String actual;
		actual = null;
		final String propertiesFilename = "de/alpharogroup/lang/resources.properties";

		final File file = ClassExtensions.getResourceAsFile(propertiesFilename);
		File dir = file.getParentFile();
		expected = null;
		actual = FilenameExtensions.getFilenameWithoutExtension(dir);
		assertEquals(expected, actual);
	}

	/**
	 * Test method for {@link FilenameExtensions#getFilenameWithoutExtension(String)}.
	 */
	@Test
	public void testGetFilenameWithoutExtensionString()
	{
		String expected;
		String actual;
		final String fileName ="de/alpharogroup/lang/resources";
		actual = FilenameExtensions.getFilenameWithoutExtension(fileName);
		expected = fileName;
		assertEquals(expected, actual);
	}

	/**
	 * Test method for {@link FilenameExtensions#getFilenameWithoutExtension(File)}
	 *
	 * @throws URISyntaxException
	 *             occurs by creation of the file with an uri.
	 */
	@Test
	public void testGetFilenameWithoutExtensionFile() throws URISyntaxException
	{
		String expected;
		String actual;
		actual = null;
		final String propertiesFilename = "de/alpharogroup/lang/resources.properties";

		final File file = ClassExtensions.getResourceAsFile(propertiesFilename);

		expected = "resources";
		actual = FilenameExtensions.getFilenameWithoutExtension(file);
		assertEquals(expected, actual);
	}

	/**
	 * Test method for {@link FilenameExtensions}
	 */
	@Test(expectedExceptions = { BeanTestException.class, ObjectCreationException.class })
	public void testWithBeanTester()
	{
		final BeanTester beanTester = new BeanTester();
		beanTester.testBean(FilenameExtensions.class);
	}


}
