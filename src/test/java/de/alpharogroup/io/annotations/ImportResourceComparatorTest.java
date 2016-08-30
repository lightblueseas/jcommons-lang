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
