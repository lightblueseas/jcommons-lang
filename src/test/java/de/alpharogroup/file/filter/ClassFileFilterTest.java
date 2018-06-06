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
package de.alpharogroup.file.filter;

import static org.testng.AssertJUnit.assertEquals;
import static org.testng.AssertJUnit.assertNotNull;

import java.io.File;
import java.io.FileFilter;
import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.charset.Charset;

import org.apache.commons.io.FileUtils;
import org.testng.annotations.Test;

import de.alpharogroup.evaluate.object.ToStringEvaluator;
import de.alpharogroup.lang.ClassExtensions;

/**
 * The unit test class for the class {@link ClassFileFilter}.
 */
public class ClassFileFilterTest
{

	/**
	 * Test method for {@link ClassFileFilter#accept(File)}
	 * 
	 * @throws URISyntaxException
	 *             occurs by creation of the file with an uri.
	 * @throws IOException
	 *             Signals that an I/O exception has occurred.
	 */
	@Test
	public final void testAccept() throws URISyntaxException, IOException
	{
		boolean expected;
		boolean actual;
		FileFilter fileFilter;
		String filename;
		String filepath;
		File file;
		File dir;

		fileFilter = new ClassFileFilter();
		assertNotNull(fileFilter);

		filename = "resources.properties";

		filepath = "de/alpharogroup/lang/" + filename;

		file = ClassExtensions.getResourceAsFile(filepath);
		dir = file.getParentFile();

		actual = fileFilter.accept(file);
		expected = false;
		assertEquals(expected, actual);

		actual = fileFilter.accept(dir);
		expected = true;
		assertEquals(expected, actual);

		file = new File(".", "TestFind.class");
		FileUtils.writeStringToFile(file, "", Charset.defaultCharset());

		actual = fileFilter.accept(file);
		expected = true;
		assertEquals(expected, actual);
		try
		{
			file.deleteOnExit();
		}
		catch (final Exception e)
		{
			// ignore...
		}
	}

	@Test
	public final void testToString()
	{
		boolean expected;
		boolean actual;

		actual = ToStringEvaluator.evaluateConsistency(new ClassFileFilter());
		expected = true;
		assertEquals(expected, actual);
	}

}
