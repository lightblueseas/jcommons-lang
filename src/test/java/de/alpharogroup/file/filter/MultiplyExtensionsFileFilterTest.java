package de.alpharogroup.file.filter;

import static org.testng.AssertJUnit.assertEquals;
import static org.testng.AssertJUnit.assertNotNull;

import java.io.File;
import java.io.FileFilter;
import java.io.IOException;
import java.net.URISyntaxException;
import java.util.Collection;

import org.testng.annotations.Test;

import de.alpharogroup.collections.list.ListExtensions;
import de.alpharogroup.evaluate.object.ToStringEvaluator;
import de.alpharogroup.lang.ClassExtensions;

/**
 * The unit test class for the class {@link MultiplyExtensionsFileFilter}.
 */
public class MultiplyExtensionsFileFilterTest
{


	/**
	 * Test method for {@link MultiplyExtensionsFileFilter} constructors
	 */
	@Test
	public final void testConstructors()
	{
		boolean acceptDir;
		String filesuffix;
		FileFilter fileFilter;
		Collection<String> fileExtensions;

		acceptDir = false;
		filesuffix = ".properties";
		fileExtensions = ListExtensions.newArrayList(filesuffix, ".txt");

		fileFilter = new MultiplyExtensionsFileFilter(acceptDir,
			fileExtensions.toArray(new String[fileExtensions.size()]));
		assertNotNull(fileFilter);

		fileFilter = new MultiplyExtensionsFileFilter(fileExtensions);
		assertNotNull(fileFilter);

		fileFilter = new MultiplyExtensionsFileFilter(
			fileExtensions.toArray(new String[fileExtensions.size()]));
		assertNotNull(fileFilter);
	}


	/**
	 * Test method for {@link MultiplyExtensionsFileFilter} constructors with null values
	 */
	@Test(expectedExceptions=IllegalArgumentException.class)
	public final void testConstructorsWithNullValues()
	{
		Collection<String> fileExtensions = null;
		new MultiplyExtensionsFileFilter(fileExtensions);
	}

	/**
	 * Test method for {@link MultiplyExtensionsFileFilter#accept(File)}
	 * 
	 * @throws URISyntaxException
	 *             occurs by creation of the file with an uri.
	 * @throws IOException
	 *             Signals that an I/O exception has occurred.
	 */
	@Test
	public final void testAccept() throws URISyntaxException
	{
		boolean expected;
		boolean actual;
		boolean acceptDir;
		String filesuffix;
		FileFilter fileFilter;
		String filename;
		String propertiesFilename;
		File file;
		File dir;
		Collection<String> fileExtensions;

		filesuffix = ".properties";
		fileExtensions = ListExtensions.newArrayList(filesuffix, ".txt");
		acceptDir = false;
		fileFilter = new MultiplyExtensionsFileFilter(fileExtensions, acceptDir);
		assertNotNull(fileFilter);

		filename = "resources.properties";

		propertiesFilename = "de/alpharogroup/lang/" + filename;

		file = ClassExtensions.getResourceAsFile(propertiesFilename);
		dir = file.getParentFile();

		actual = fileFilter.accept(file);
		expected = true;
		assertEquals(expected, actual);


		filesuffix = ".properties";
		acceptDir = true;
		fileFilter = new MultiplyExtensionsFileFilter(fileExtensions, acceptDir);
		assertNotNull(fileFilter);

		filename = "";

		actual = fileFilter.accept(dir);
		expected = true;
		assertEquals(expected, actual);
	}

	/**
	 * Test method for {@link MultiplyExtensionsFileFilter#toString()}
	 */
	@Test
	public final void testToString()
	{
		boolean expected;
		boolean actual;

		String filesuffix;
		boolean acceptDir;
		filesuffix = ".properties";
		acceptDir = false;
		Collection<String> fileExtensions;

		filesuffix = ".properties";
		fileExtensions = ListExtensions.newArrayList(filesuffix, ".txt");
		actual = ToStringEvaluator
			.evaluateConsistency(new MultiplyExtensionsFileFilter(fileExtensions, acceptDir));
		expected = true;
		assertEquals(expected, actual);
	}

}
