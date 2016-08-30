package de.alpharogroup.io.annotations;

import java.io.IOException;
import java.util.Map;

import org.testng.AssertJUnit;
import org.testng.annotations.Test;

/**
 * Test class for the class {@link ImportResourceComparator}.
 */
public class ImportResourceComparatorTest
{

	/**
	 * Test for method {@link ImportResourceComparator#compare(ImportResource, ImportResource)}
	 */
	@Test
	public void testCompare() throws ClassNotFoundException, IOException
	{
		final Map<Class<?>, ImportResource[]> resources = ImportResourcesExtensions
			.getImportResources("de.alpharogroup.io");
		final ImportResource[] somePageResources = resources.get(SomePage.class);
		AssertJUnit.assertNotNull(somePageResources);
		AssertJUnit.assertTrue(somePageResources.length == 3);
		final ImportResource cssResource = somePageResources[0];
		AssertJUnit.assertTrue(cssResource.index() == 1);

		final ImportResource jsResource = somePageResources[1];
		AssertJUnit.assertTrue(jsResource.index() == 2);

		final ImportResource jsResource2 = somePageResources[2];
		AssertJUnit.assertTrue(jsResource2.index() == 2);

		final ImportResourceComparator comparator = new ImportResourceComparator();
		// scenario: bigger index
		int actual = comparator.compare(jsResource, cssResource);
		int expected = 1;

		AssertJUnit.assertEquals(expected, actual);
		// scenario: smaller index
		actual = comparator.compare(cssResource, jsResource);
		expected = -1;

		AssertJUnit.assertEquals(expected, actual);
		// scenario: same index
		actual = comparator.compare(jsResource, jsResource2);
		expected = 0;

		AssertJUnit.assertEquals(expected, actual);

		// scenario: null second object
		actual = comparator.compare(cssResource, null);
		expected = 1;

		AssertJUnit.assertEquals(expected, actual);

		// scenario: null first object
		actual = comparator.compare(null, cssResource);
		expected = -1;

		AssertJUnit.assertEquals(expected, actual);

		// scenario: same object
		actual = comparator.compare(cssResource, cssResource);
		expected = 0;

		AssertJUnit.assertEquals(expected, actual);

		// scenario: null objects
		actual = comparator.compare(null, null);
		expected = 0;

		AssertJUnit.assertEquals(expected, actual);
	}

}
