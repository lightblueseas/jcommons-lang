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

import static org.testng.AssertJUnit.assertEquals;
import static org.testng.AssertJUnit.assertNotNull;
import static org.testng.AssertJUnit.assertTrue;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.Map;

import org.testng.annotations.Test;

/**
 * The unit test class for the class {@link ImportResourceComparator}.
 */
public class ImportResourceComparatorTest
{

	/**
	 * Test for method {@link ImportResourceComparator#compare(ImportResource, ImportResource)}
	 * 
	 * @throws URISyntaxException
	 *             is thrown if a string could not be parsed as a URI reference.
	 */
	@Test
	public void testCompare() throws ClassNotFoundException, IOException, URISyntaxException
	{
		int actual;
		int expected;
		final Map<Class<?>, ImportResource[]> resources = ImportResourcesExtensions
			.getImportResources("de.alpharogroup.io");
		final ImportResource[] somePageResources = resources.get(TestPage.class);
		assertNotNull(somePageResources);

		expected = 3;
		actual = somePageResources.length;
		assertTrue(expected == actual);

		final ImportResource cssResource = somePageResources[0];
		expected = 1;
		actual = cssResource.index();
		assertTrue(expected == actual);

		final ImportResource jsResource = somePageResources[1];
		expected = 2;
		actual = jsResource.index();
		assertTrue(expected == actual);

		final ImportResource jsResource2 = somePageResources[2];
		expected = 2;
		actual = jsResource2.index();
		assertTrue(expected == actual);

		final ImportResourceComparator comparator = new ImportResourceComparator();

		// scenario: bigger index
		actual = comparator.compare(jsResource, cssResource);
		expected = 1;
		assertEquals(expected, actual);

		// scenario: smaller index
		actual = comparator.compare(cssResource, jsResource);
		expected = -1;
		assertEquals(expected, actual);

		// scenario: same index
		actual = comparator.compare(jsResource, jsResource2);
		expected = 0;
		assertEquals(expected, actual);

		// scenario: null second object
		actual = comparator.compare(cssResource, null);
		expected = 1;
		assertEquals(expected, actual);

		// scenario: null first object
		actual = comparator.compare(null, cssResource);
		expected = -1;
		assertEquals(expected, actual);

		// scenario: same object
		actual = comparator.compare(cssResource, cssResource);
		expected = 0;
		assertEquals(expected, actual);

		// scenario: null objects
		actual = comparator.compare(null, null);
		expected = 0;
		assertEquals(expected, actual);
	}

}
