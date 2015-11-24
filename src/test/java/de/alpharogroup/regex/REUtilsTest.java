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
package de.alpharogroup.regex;


import org.testng.AssertJUnit;
import org.testng.annotations.Test;

import de.alpharogroup.BaseTestCase;
import de.alpharogroup.string.StringUtils;

/**
 * Test class for the class REUtils.
 * 
 * @version 1.0
 * @author Asterios Raptis
 */
public class REUtilsTest extends BaseTestCase
{

	/**
	 * {@inheritDoc}
	 */
	@Override
	protected void setUp() throws Exception
	{
		super.setUp();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	protected void tearDown() throws Exception
	{
		super.tearDown();
	}

	/**
	 * Test method for
	 * {@link de.alpharogroup.regex.REUtils#replaceWildcardsWithRE(java.lang.String)}.
	 */
	@Test
	public void testReplaceWildcardsWithRE()
	{
		final String expected = "Hel.*W[a-zA-Z0-9._-]{1}rld!";
		final String queryString = "Hel*W?rld!";
		final String compare = REUtils.replaceWildcardsWithRE(queryString);
		result = expected.equals(compare);
		AssertJUnit.assertTrue("", result);
	}

	@Test
	public void testValidPhone()
	{
		// Split input with the pattern
		final String[] valideInput = { "0173/123450", "+49173/123450", "(+49)173/123450",
				"(+49) 173/123450", "0049173/123450", "0049173/1234-50", "0049 173/123450",
				"0049(0)173/123450", "(0049)173/123450", "(0049) 173/1234 50" };
		for (final String element : valideInput)
		{
			String strip = element;
			strip = StringUtils.replaceAll(strip, " ", "");
			strip = StringUtils.replaceAll(strip, "(", "");
			strip = StringUtils.replaceAll(strip, ")", "");
			strip = StringUtils.replaceAll(strip, " ", "");
			strip = StringUtils.replaceAll(strip, "-", "");
			result = strip.matches(REUtils.VALID_PHONE);
			AssertJUnit.assertTrue("", result);
		}

		final String[] invalideInput = { "(0049)17e/123450", };
		for (final String element : invalideInput)
		{
			String strip = element;
			strip = StringUtils.replaceAll(strip, " ", "");
			strip = StringUtils.replaceAll(strip, "(", "");
			strip = StringUtils.replaceAll(strip, ")", "");
			strip = StringUtils.replaceAll(strip, " ", "");
			strip = StringUtils.replaceAll(strip, "-", "");
			result = strip.matches(REUtils.VALID_PHONE);
			AssertJUnit.assertFalse("", result);
		}
	}

	/**
	 * Test method for {@link de.alpharogroup.regex.REUtils#wildcardCriterionSQL(java.lang.String)}.
	 */
	@Test
	public void testWildcardCriterionSQL()
	{
		final String expected = "Hel%W_rld!";
		final String query = "Hel*W?rld!";
		final String compare = REUtils.wildcardCriterionSQL(query);
		result = expected.equals(compare);
		AssertJUnit.assertTrue("", result);
	}

}
