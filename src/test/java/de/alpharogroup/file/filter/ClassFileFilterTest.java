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

import de.alpharogroup.lang.ClassExtensions;
import de.alpharogroup.test.objects.evaluations.ToStringEvaluator;

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
