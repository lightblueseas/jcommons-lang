package de.alpharogroup.file.namefilter;

import static org.testng.AssertJUnit.assertEquals;
import static org.testng.AssertJUnit.assertNotNull;

import java.io.File;
import java.io.FilenameFilter;
import java.util.Collection;

import org.testng.annotations.Test;

import de.alpharogroup.collections.list.ListExtensions;
import de.alpharogroup.lang.ClassExtensions;
import de.alpharogroup.test.objects.evaluations.ToStringEvaluator;

public class MultiplyExtensionsFilenameFilterTest
{

	@Test
	public final void testAccept() throws Exception
	{
		boolean expected;
		boolean actual;
		boolean acceptDir;
		String filesuffix;
		FilenameFilter filenameFilter;
		String filename;
		String propertiesFilename;
		File file;
		File dir;
		Collection<String> fileExtensions;
		
		filesuffix = ".properties";
		fileExtensions = ListExtensions.newArrayList(filesuffix, ".txt");
		acceptDir = false;
		filenameFilter = new MultiplyExtensionsFilenameFilter(fileExtensions, acceptDir);
		assertNotNull(filenameFilter);		

		filename = "resources.properties";

		propertiesFilename = "de/alpharogroup/lang/" + filename;

		file = ClassExtensions.getResourceAsFile(propertiesFilename);
		dir = file.getParentFile();

		actual = filenameFilter.accept(dir, filename);
		expected = true;
		assertEquals(expected, actual);
		

		filesuffix = ".properties";
		acceptDir = true;
		filenameFilter = new MultiplyExtensionsFilenameFilter(fileExtensions, acceptDir);
		assertNotNull(filenameFilter);

		filename = "";

		actual = filenameFilter.accept(dir, filename);
		expected = true;
		assertEquals(expected, actual);
		
	}

	@Test
	public final void testToString() throws Exception
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
			.evaluateConsistency(new MultiplyExtensionsFilenameFilter(fileExtensions, acceptDir));
		expected = true;
		assertEquals(expected, actual);
	}

}
