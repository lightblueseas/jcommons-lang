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
package de.alpharogroup.string;

import lombok.experimental.ExtensionMethod;

import org.testng.AssertJUnit;
import org.testng.annotations.Test;

import de.alpharogroup.BaseTestCase;

/**
 * Test class for the class StringUtils.
 *
 * @version 1.0
 * @author Asterios Raptis
 */
@ExtensionMethod(StringExtensions.class)
public class StringUtilsTest extends BaseTestCase
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

	@Test(enabled = true)
	public void testConvertCharsToUnicodeChars()
	{
		String expected = "\\u00f6";
		String actual = "ö".toUnicodeChars(true);
		AssertJUnit.assertEquals(expected, actual);

		expected = "\\u00F6";
		actual = "ö".toUnicodeChars(false);
		AssertJUnit.assertEquals(expected, actual);

		expected = "\\u00F6, \\u00DF \\u00E4";
		actual = "ö, ß ä".toUnicodeChars(false);
		AssertJUnit.assertEquals(expected, actual);
	}

	@Test(enabled = false)
	public void testConvertToBytearray()
	{
		throw new RuntimeException("Test not implemented");

	}

	/**
	 * Test method for {@link de.alpharogroup.string.StringUtils#getAttributName(java.lang.String)}.
	 */
	@Test
	public void testGetAttributName()
	{
		final String expected = "indio";
		final String testString = "indio[5][1]";
		final String compare = StringUtils.getAttributName(testString);
		result = expected.equals(compare);
		AssertJUnit.assertTrue("", result);
	}

	/**
	 * Test method for {@link de.alpharogroup.string.StringUtils#getIndex(java.lang.String)}.
	 */
	@Test
	public void testGetIndex()
	{
		final String expected = "5";
		final String testString = "indio[5][1]";
		final String compare = StringUtils.getIndex(testString);
		result = expected.equals(compare);
		AssertJUnit.assertTrue("", result);
	}

	/**
	 * Test method for {@link de.alpharogroup.string.StringUtils#getItemNumber(java.lang.String)}.
	 */
	@Test
	public void testGetItemNumber()
	{
		final String expected = "1";
		final String testString = "indio[5][1]";
		final String compare = StringUtils.getItemNumber(testString);
		result = expected.equals(compare);
		AssertJUnit.assertTrue("", result);
	}

	/**
	 * Test method for
	 * {@link de.alpharogroup.string.StringUtils#getStringAfterUnderscore(java.lang.String)}.
	 */
	@Test
	public void testGetStringAfterUnderscore()
	{
		final String expected = "value";
		final String element = "foobar_value";
		final String compare = StringUtils.getStringAfterUnderscore(element);
		result = expected.equals(compare);
		AssertJUnit.assertTrue("", result);
	}

	/**
	 * Test method for
	 * {@link de.alpharogroup.string.StringUtils#getStringBeforeUnderscore(java.lang.String)}.
	 */
	@Test
	public void testGetStringBeforeUnderscore()
	{
		final String expected = "foobar";
		final String element = "foobar_value";
		final String compare = StringUtils.getStringBeforeUnderscore(element);
		result = expected.equals(compare);
		AssertJUnit.assertTrue("", result);
	}

	/**
	 * Test method for {@link de.alpharogroup.string.StringUtils#isNullOrEmpty(java.lang.String)}.
	 */
	@Test
	public void testIsNullOrEmpty()
	{
		final String isNull = null;
		final String isEmpty = "";
		final String isNotNullOrEmpty = "foobar";

		result = isNull.isNullOrEmpty();
		AssertJUnit.assertTrue("", result);
		result = isEmpty.isNullOrEmpty();
		AssertJUnit.assertTrue("", result);
		result = isNotNullOrEmpty.isNullOrEmpty();
		AssertJUnit.assertFalse("", result);

	}

	/**
	 * Test method for {@link de.alpharogroup.string.StringUtils#isNumber(java.lang.String)}.
	 */
	@Test
	public void testIsNumber()
	{
		final String five = "5";
		result = five.isNumber();
		AssertJUnit.assertTrue("", result);

		final String notANumber = "foo";
		result = notANumber.isNumber();
		AssertJUnit.assertFalse("", result);

	}

	/**
	 * Test method for
	 * {@link de.alpharogroup.string.StringUtils#putQuotesToString(java.lang.String)}.
	 */
	@Test
	public void testPutQuotesToString()
	{
		final String expected = "\"Leonidas\"";
		final String withoutQuotes = "Leonidas";
		final String compare = withoutQuotes.addDoubleQuotationMarks();
		result = expected.equals(compare);
		AssertJUnit.assertTrue("", result);
	}

	/**
	 * Test method for {@link de.alpharogroup.string.StringUtils#readLine(java.lang.String)}.
	 */
	@Test
	public void testReadLine()
	{

		final String original = "This is a test: Aha :\n and than foo bar:";
		final String expected = "This is a test: Aha :";
		final String compare = StringUtils.readLine(original);
		result = expected.equals(compare);
		AssertJUnit.assertTrue("", result);
	}

	/**
	 * Test method for {@link de.alpharogroup.string.StringExtensions#removeQuotationMarks(String)}.
	 */
	@Test
	public void testRemoveQuotesFromString()
	{
		final String expected = "Leonidas";
		final String withQuotes = "\"Leonidas\"";
		final String compare = withQuotes.removeQuotationMarks();
		result = expected.equals(compare);
		AssertJUnit.assertTrue("", result);
	}

	/**
	 * Test method for
	 * {@link de.alpharogroup.string.StringUtils#replaceAll(java.lang.String, java.lang.String, java.lang.String)}
	 * .
	 */
	@Test
	public void testReplaceAll()
	{

		final String original = "This is a test: Aha : and than foo bar:";
		final String expected = "This is a test; Aha ; and than foo bar;";
		final String compare = StringUtils.replaceAll(original, ":", ";");
		result = expected.equals(compare);
		AssertJUnit.assertTrue("", result);
	}

	/**
	 * Test method for
	 * {@link de.alpharogroup.string.StringUtils#replaceAll(java.lang.String, java.lang.String, java.lang.String)}
	 * .
	 */
	@Test
	public void testReplaceAllStringArray()
	{

		final String original = "(0049) 173/1234-50";
		final String expected = "0049173123450";
		final String[] array = { "(", ")", "-", "/", " " };
		final String compare = StringUtils.replaceAll(original, array, "");
		result = expected.equals(compare);
		AssertJUnit.assertTrue("", result);
	}

	@Test(enabled = true)
	public void testReplaceEach()
	{
		final String search = "\"com.foo.bar\"";
		final String replace = "\"bla.fasel\"";
		final String input = "Hickory Dickory Dock \"com.foo.bar\" mouse ran up the clock The \"com.foo.bar\" struck one The \"com.foo.bar\" ran down";
		final String expected = "Hickory Dickory Dock \"bla.fasel\" mouse ran up the clock The \"bla.fasel\" struck one The \"bla.fasel\" ran down";
		final String result = StringUtils.replaceEach(input, search, replace);
		AssertJUnit.assertTrue(expected.equals(result));
	}

	/**
	 * Test method for
	 * {@link de.alpharogroup.string.StringExtensions#replaceLast(java.lang.String, java.lang.String, java.lang.String)}
	 * .
	 */
	@Test
	public void testReplaceLast()
	{
		final String original = "This is a test: Aha : and than foo bar:";
		final String expected = "This is a test: Aha : and than foo bar;";
		final String actual = original.replaceLast(":", ";");
		AssertJUnit.assertEquals(expected, actual);
	}

	/**
	 * Test method for
	 * {@link de.alpharogroup.string.StringUtils#setFirstCharacterToUpperCase(java.lang.String)} .
	 */
	@Test
	public void testSetFirstCharacterToUpperCase()
	{
		final String fieldName = "name";
		final String expected = "Name";
		final String compare = fieldName.firstCharacterToUpperCase();
		result = expected.equals(compare);
		AssertJUnit.assertTrue("", result);
	}

	@Test(enabled = true)
	public void testToUnicode()
	{
		String expected = "\\u00f6";
		String actual = "ö".toUnicode(true);
		AssertJUnit.assertEquals(expected, actual);

		expected = "\\u00F6";
		actual = "ö".toUnicode(false);
		AssertJUnit.assertEquals(expected, actual);

		expected = "\\u00F6\\u002C\\u0020\\u00DF\\u0020\\u00E4";
		actual = "ö, ß ä".toUnicode(false);
		AssertJUnit.assertEquals(expected, actual);
	}

}
