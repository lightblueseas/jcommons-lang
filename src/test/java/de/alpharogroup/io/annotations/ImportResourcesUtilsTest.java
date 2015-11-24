/**
 * The MIT License
 *
 * Copyright (C) 2007 Asterios Raptis
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

import de.alpharogroup.io.OtherPage;

/**
 * Test class for the class ImportResourcesUtils.
 *
 * @author Asterios Raptis
 * @version 1.0
 */
public class ImportResourcesUtilsTest
{

	@Test
	public void testGetImportResources() throws IOException, ClassNotFoundException
	{
		final Map<Class<?>, ImportResource[]> resources = ImportResourcesUtils
			.getImportResources("de.alpharogroup.io");
		final ImportResource[] somePageResources = resources.get(SomePage.class);
		AssertJUnit.assertNotNull(somePageResources);
		AssertJUnit.assertTrue(somePageResources.length == 2);
		final ImportResource cssResource = somePageResources[0];
		AssertJUnit.assertTrue(cssResource.index() == 1);
		AssertJUnit.assertTrue(cssResource.resourceName().equals("SomePage.css"));
		AssertJUnit.assertTrue(cssResource.resourceType().equals("css"));
		final ImportResource jsResource = somePageResources[1];
		AssertJUnit.assertTrue(jsResource.index() == 2);
		AssertJUnit.assertTrue(jsResource.resourceName().equals("SomePage.js"));
		AssertJUnit.assertTrue(jsResource.resourceType().equals("js"));
		final ImportResource[] otherPageResources = resources.get(OtherPage.class);
		AssertJUnit.assertNotNull(otherPageResources);
		AssertJUnit.assertTrue(otherPageResources.length == 1);

	}
}
