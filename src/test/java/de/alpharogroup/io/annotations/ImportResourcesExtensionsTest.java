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

import org.meanbean.factories.ObjectCreationException;
import org.meanbean.test.BeanTestException;
import org.meanbean.test.BeanTester;
import org.testng.annotations.Test;

import de.alpharogroup.io.OtherPage;

/**
 * The unit test class for the class {@link ImportResourcesExtensions}.
 *
 * @author Asterios Raptis
 * @version 1.0
 */
public class ImportResourcesExtensionsTest
{

	/**
	 * Test for method {@link ImportResourcesExtensions#getImportResources(String)}
	 * 
	 * @throws URISyntaxException
	 *             is thrown if a string could not be parsed as a URI reference. 
	 */
	@Test
	public void testGetImportResources() throws IOException, ClassNotFoundException, URISyntaxException
	{
		int expectedLength;
		int actualLength;
		int expectedIndex;
		int actualIndex;
		String expected;
		String actual;
		final Map<Class<?>, ImportResource[]> resources = ImportResourcesExtensions
			.getImportResources("de.alpharogroup.io");
		final ImportResource[] somePageResources = resources.get(TestPage.class);

		assertNotNull(somePageResources);
		expectedLength = 3;
		actualLength = somePageResources.length;
		assertTrue(expectedLength == actualLength);

		final ImportResource cssResource = somePageResources[0];
		expectedIndex = 1;
		actualIndex = cssResource.index();
		assertTrue(expectedIndex == actualIndex);

		expected = "TestPage.css";
		actual = cssResource.resourceName();
		assertEquals(expected, actual);

		expected = "css";
		actual = cssResource.resourceType();
		assertEquals(expected, actual);

		final ImportResource jsResource = somePageResources[1];

		expectedIndex = 2;
		actualIndex = jsResource.index();
		assertTrue(expectedIndex == actualIndex);

		expected = "TestPage.js";
		actual = jsResource.resourceName();
		assertEquals(expected, actual);

		expected = "js";
		actual = jsResource.resourceType();
		assertEquals(expected, actual);

		final ImportResource jsResource2 = somePageResources[2];

		expectedIndex = 2;
		actualIndex = jsResource2.index();
		assertTrue(expectedIndex == actualIndex);

		expected = "TestPanel.js";
		actual = jsResource2.resourceName();
		assertEquals(expected, actual);

		expected = "js";
		actual = jsResource2.resourceType();
		assertEquals(expected, actual);

		final ImportResource[] otherPageResources = resources.get(OtherPage.class);
		assertNotNull(otherPageResources);

		expectedLength = 1;
		actualLength = otherPageResources.length;
		assertTrue(expectedLength == actualLength);

	}

	/**
	 * Test method for {@link ImportResourcesExtensions}
	 */
	@Test(expectedExceptions = { BeanTestException.class, ObjectCreationException.class })
	public void testWithBeanTester()
	{
		final BeanTester beanTester = new BeanTester();
		beanTester.testBean(ImportResourcesExtensions.class);
	}
}
