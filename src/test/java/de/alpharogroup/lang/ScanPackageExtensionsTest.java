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
package de.alpharogroup.lang;

import static org.testng.AssertJUnit.assertFalse;
import static org.testng.AssertJUnit.assertTrue;

import java.util.Set;

import org.meanbean.factories.ObjectCreationException;
import org.meanbean.test.BeanTestException;
import org.meanbean.test.BeanTester;
import org.testng.annotations.Test;

/**
 * The unit test class for the class {@link ScanPackageExtensions}.
 *
 * @version 1.0
 * @author Asterios Raptis
 */
public class ScanPackageExtensionsTest
{

	/**
	 * Test method for {@link ScanPackageExtensions#scanClassNames(String, boolean, boolean)}.
	 *
	 * @throws Exception
	 *             is thrown if any error occurs on the execution
	 */
	@Test(enabled = false)
	public void testScanNames() throws Exception
	{
		Set<String> list = ScanPackageExtensions.scanClassNames("de.alpharogroup.lang", false, true);
		assertTrue("Result should contain classes from same package.",
			list.contains("de.alpharogroup.lang.PackageExtensionsTest"));
		assertFalse("Result should not contain classes from subpackages.",
			list.contains("de.alpharogroup.lang.sub.SubPackageClass"));

		list = ScanPackageExtensions.scanClassNames("de.alpharogroup.lang", true, true);
		assertTrue("Result should contain classes from subpackages.",
			list.contains("de.alpharogroup.lang.sub.SubPackageClass"));

		list = ScanPackageExtensions.scanClassNames("org.apache.commons.beanutils", false, true);
		assertTrue("Result should contain classes from same package.",
			list.contains("org.apache.commons.beanutils.BeanMap"));
		assertFalse("Result should not contain classes from subpackages.",
			list.contains("org.apache.commons.beanutils.locale.BaseLocaleConverter"));
		assertFalse("Result should not contain classes from subpackages.", list
			.contains("org.apache.commons.beanutils.locale.converters.BigDecimalLocaleConverter"));

		list = ScanPackageExtensions.scanClassNames("org.apache.commons.beanutils.locale", true, true);
		assertTrue("Result should contain classes from same package.",
			list.contains("org.apache.commons.beanutils.locale.BaseLocaleConverter"));
		assertTrue("Result should contain classes from subpackages.", list
			.contains("org.apache.commons.beanutils.locale.converters.BigDecimalLocaleConverter"));

		// ......
		list = ScanPackageExtensions.scanClassNames("de.alpharogroup.lang", false, false);
		assertTrue("Result should contain classes from same package.",
			list.contains("PackageExtensionsTest"));
		assertFalse("Result should not contain classes from subpackages.",
			list.contains("SubPackageClass"));

		list = ScanPackageExtensions.scanClassNames("de.alpharogroup.lang", true, false);
		assertTrue("Result should contain classes from subpackages.",
			list.contains("SubPackageClass"));

		list = ScanPackageExtensions.scanClassNames("org.apache.commons.beanutils", false, false);
		assertTrue("Result should contain classes from same package.", list.contains("BeanMap"));
		assertFalse("Result should not contain classes from subpackages.",
			list.contains("BaseLocaleConverter"));
		assertFalse("Result should not contain classes from subpackages.",
			list.contains("BigDecimalLocaleConverter"));

		list = ScanPackageExtensions.scanClassNames("org.apache.commons.beanutils.locale", true, false);
		assertTrue("Result should contain classes from same package.",
			list.contains("BaseLocaleConverter"));
		assertTrue("Result should contain classes from subpackages.",
			list.contains("BigDecimalLocaleConverter"));

		list = ScanPackageExtensions.scanClassNames("de.alpharogroup", true, true);
		for (final String string : list)
		{
			System.out.println("<class name=\"" + string + "\"/>");
		}

	}

	/**
	 * Test method for {@link ScanPackageExtensions}
	 */
	@Test(expectedExceptions = { BeanTestException.class, ObjectCreationException.class })
	public void testWithBeanTester()
	{
		final BeanTester beanTester = new BeanTester();
		beanTester.testBean(ScanPackageExtensions.class);
	}

}
