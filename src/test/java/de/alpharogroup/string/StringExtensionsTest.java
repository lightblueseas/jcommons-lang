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
package de.alpharogroup.string;

import static org.testng.Assert.assertNull;
import static org.testng.AssertJUnit.assertEquals;
import static org.testng.AssertJUnit.assertFalse;
import static org.testng.AssertJUnit.assertTrue;

import java.util.List;
import java.util.Map;

import org.meanbean.factories.ObjectCreationException;
import org.meanbean.test.BeanTestException;
import org.meanbean.test.BeanTester;
import org.testng.annotations.Test;

import de.alpharogroup.BaseTestCase;
import de.alpharogroup.collections.map.MapFactory;
import lombok.experimental.ExtensionMethod;

/**
 * The unit test class for the class StringExtensions.
 *
 * @version 1.0
 * @author Asterios Raptis
 */
@ExtensionMethod(StringExtensions.class)
public class StringExtensionsTest extends BaseTestCase
{

	/**
	 * Test method for {@link StringExtensions#toUnicodeChars(String, boolean)}
	 */
	@Test(enabled = true)
	public void testConvertCharsToUnicodeChars()
	{
		String expected = "\\u00f6";
		String actual = "ö".toUnicodeChars(true);
		assertEquals(expected, actual);

		expected = "\\u00F6";
		actual = "ö".toUnicodeChars(false);
		assertEquals(expected, actual);

		expected = "\\u00F6, \\u00DF \\u00E4";
		actual = "ö, ß ä".toUnicodeChars(false);
		assertEquals(expected, actual);
	}

	/**
	 * Test method for {@link StringExtensions#firstCharacterToLowerCase(String)}
	 */
	@Test
	public void testFirstCharacterToLowerCase()
	{
		String expected;
		String actual;
		expected = "userName";
		actual = StringExtensions.firstCharacterToLowerCase("UserName");
		assertEquals(actual, expected);
	}

	/**
	 * Test method for {@link StringExtensions#firstCharacterToUpperCase(String)}
	 */
	@Test
	public void testFirstCharacterToUpperCase()
	{
		String expected;
		String actual;
		expected = "UserName";
		actual = StringExtensions.firstCharacterToUpperCase("userName");
		assertEquals(actual, expected);
	}

	/**
	 * Test method for {@link StringExtensions#getAttributName(String)}
	 */
	@Test
	public void testGetAttributName()
	{
		final String expected = "indio";
		final String testString = "indio[5][1]";
		final String compare = StringExtensions.getAttributName(testString);
		actual = expected.equals(compare);
		assertTrue("", actual);
	}

	/**
	 * Test method for {@link StringExtensions#getIndex(String)}
	 */
	@Test
	public void testGetIndex()
	{
		final String expected = "5";
		final String testString = "indio[5][1]";
		final String compare = StringExtensions.getIndex(testString);
		actual = expected.equals(compare);
		assertTrue("", actual);
	}

	/**
	 * Test method for {@link StringExtensions#getItemNumber(String)}
	 */
	@Test
	public void testGetItemNumber()
	{
		final String expected = "1";
		final String testString = "indio[5][1]";
		final String compare = StringExtensions.getItemNumber(testString);
		actual = expected.equals(compare);
		assertTrue("", actual);
	}

	/**
	 * Test method for {@link StringExtensions#getStringAfterUnderscore(String)}
	 */
	@Test
	public void testGetStringAfterUnderscore()
	{
		final String expected = "value";
		final String element = "foobar_value";
		final String compare = StringExtensions.getStringAfterUnderscore(element);
		actual = expected.equals(compare);
		assertTrue("", actual);
	}

	/**
	 * Test method for {@link StringExtensions#getStringBeforeUnderscore(String)}
	 */
	@Test
	public void testGetStringBeforeUnderscore()
	{
		final String expected = "foobar";
		final String element = "foobar_value";
		final String compare = StringExtensions.getStringBeforeUnderscore(element);
		actual = expected.equals(compare);
		assertTrue("", actual);
	}

	/**
	 * Test method for {@link StringExtensions#isNullOrEmpty(String)}
	 */
	@Test
	public void testIsNullOrEmpty()
	{
		final String isNull = null;
		final String isEmpty = "";
		final String isNotNullOrEmpty = "foobar";

		actual = isNull.isNullOrEmpty();
		assertTrue("", actual);
		actual = isEmpty.isNullOrEmpty();
		assertTrue("", actual);
		actual = isNotNullOrEmpty.isNullOrEmpty();
		assertFalse("", actual);

	}

	/**
	 * Test method for {@link StringExtensions#isNumber(String)}
	 */
	@Test
	public void testIsNumber()
	{
		final String five = "5";
		actual = five.isNumber();
		assertTrue("", actual);

		final String notANumber = "foo";
		actual = notANumber.isNumber();
		assertFalse("", actual);

	}

	/**
	 * Test method for {@link StringExtensions#lastCharacterToUpperCase(String)}
	 */
	@Test
	public void testLastCharacterToUpperCase()
	{
		String expected;
		String actual;
		expected = "UserNamE";
		actual = StringExtensions.lastCharacterToUpperCase("UserName");
		assertEquals(actual, expected);

		expected = "E";
		actual = StringExtensions.lastCharacterToUpperCase("e");
		assertEquals(actual, expected);

		expected = " ";
		actual = StringExtensions.lastCharacterToUpperCase(" ");
		assertEquals(actual, expected);

		expected = "";
		actual = StringExtensions.lastCharacterToUpperCase("");
		assertEquals(actual, expected);

		expected = null;
		actual = StringExtensions.lastCharacterToUpperCase(null);
		assertEquals(actual, expected);
	}

	/**
	 * Test method for {@link StringExtensions#putQuotesToString(String)}
	 */
	@Test
	public void testPutQuotesToString()
	{
		final String expected = "\"Leonidas\"";
		final String withoutQuotes = "Leonidas";
		final String compare = withoutQuotes.addDoubleQuotationMarks();
		actual = expected.equals(compare);
		assertTrue("", actual);
	}

	/**
	 * Test method for {@link StringExtensions#readLine(String)}
	 */
	@Test
	public void testReadLine()
	{

		final String original = "This is a test: Aha :\n and than foo bar:";
		final String expected = "This is a test: Aha :";
		final String compare = StringExtensions.readLine(original);
		actual = expected.equals(compare);
		assertTrue("", actual);
	}

	/**
	 * Test method for {@link StringExtensions#removeQuotationMarks(String)}
	 */
	@Test
	public void testRemoveQuotesFromString()
	{
		final String expected = "Leonidas";
		final String withQuotes = "\"Leonidas\"";
		final String compare = withQuotes.removeQuotationMarks();
		actual = expected.equals(compare);
		assertTrue("", actual);
	}

	/**
	 * Test method for {@link StringExtensions#replaceAll(String, String, String)}
	 */
	@Test
	public void testReplaceAll()
	{

		final String original = "This is a test: Aha : and than foo bar:";
		final String expected = "This is a test; Aha ; and than foo bar;";
		final String compare = StringExtensions.replaceAll(original, ":", ";");
		actual = expected.equals(compare);
		assertTrue("", actual);
	}

	/**
	 * Test method for {@link StringExtensions#replaceAll(String, String[], String)}
	 */
	@Test
	public void testReplaceAllStringArray()
	{

		final String original = "(0049) 173/1234-50";
		final String expected = "0049173123450";
		final String[] array = { "(", ")", "-", "/", " " };
		final String compare = StringExtensions.replaceAll(original, array, "");
		actual = expected.equals(compare);
		assertTrue("", actual);
	}

	/**
	 * Test method for {@link StringExtensions#replaceEach(String, String, String)}
	 */
	@Test(enabled = true)
	public void testReplaceEach()
	{
		final String search = "\"com.foo.bar\"";
		final String replace = "\"bla.fasel\"";
		final String input = "Hickory Dickory Dock \"com.foo.bar\" mouse ran up the clock The \"com.foo.bar\" struck one The \"com.foo.bar\" ran down";
		final String expected = "Hickory Dickory Dock \"bla.fasel\" mouse ran up the clock The \"bla.fasel\" struck one The \"bla.fasel\" ran down";
		final String actual = StringExtensions.replaceEach(input, search, replace);
		assertTrue(expected.equals(actual));
	}

	/**
	 * Test method for {@link StringExtensions#replaceLast(String, String, String)}
	 */
	@Test
	public void testReplaceLast()
	{
		final String original = "This is a test: Aha : and than foo bar:";
		final String expected = "This is a test: Aha : and than foo bar;";
		final String actual = original.replaceLast(":", ";");
		assertEquals(expected, actual);
	}

	/**
	 * Test method for {@link StringExtensions#splitByFixedLength(String, int)}
	 */
	@Test
	public void testSplitByLength()
	{
		final String input = "HickoryDickoryDockxxxmousexranxupxthexclockxThexcom.foo.barxstruckxonexThexxyxranxdownBlogBarFooEEE";
		final List<String> output = input.splitByFixedLength(7);

		assertTrue(output.size() == 15);
		assertEquals(output.get(1), "Dickory");
	}

	/**
	 * Test method for {@link StringExtensions#toIntegerArray(String, String)}
	 */
	@Test
	public void testToIntegerArray()
	{
		final String stringArray1 = "11, 12, 13, 14, 15, 16, 17, 18";

		final int[] expectedIntArray = { 11, 12, 13, 14, 15, 16, 17, 18 };
		final int[] intArray = StringExtensions.toIntegerArray(stringArray1, ",");
		for (int i = 0; i < intArray.length; i++)
		{
			assertTrue(intArray[i] == expectedIntArray[i]);
		}
	}

	/**
	 * Test method for {@link StringExtensions#toUnicode(String, boolean)}
	 */
	@Test(enabled = true)
	public void testToUnicode()
	{
		String expected = "\\u00f6";
		String actual = "ö".toUnicode(true);
		assertEquals(expected, actual);

		expected = "\\u00F6";
		actual = "ö".toUnicode(false);
		assertEquals(expected, actual);

		expected = "\\u00F6\\u002C\\u0020\\u00DF\\u0020\\u00E4";
		actual = "ö, ß ä".toUnicode(false);
		assertEquals(expected, actual);
	}

	/**
	 * Test method for {@link StringExtensions}
	 */
	@Test(expectedExceptions = { BeanTestException.class, ObjectCreationException.class })
	public void testWithBeanTester()
	{
		final BeanTester beanTester = new BeanTester();
		beanTester.testBean(StringExtensions.class);
	}

	// =================================================================================== //


	/**
	 * Test method for {@link StringExtensions#addSingleQuotationMarks(String)}.
	 */
	@Test
	public void testAddSingleQuotationMarks()
	{
		String expected;
		String actual;
		actual = StringExtensions.addSingleQuotationMarks("foo");
		expected = "'foo'";
		assertEquals(expected, actual);
	}

	/**
	 * Test method for {@link StringExtensions#convertToBytearray(char[])}.
	 */
	@Test
	public void testConvertToBytearray()
	{		
		byte[] actualBytearray;
		byte expected;
		byte actual;
		char[] charArray = { 'a', 'b', 'c', 'd', 'e' };
		byte[] expectedBytearray = "abcde".getBytes();
		actualBytearray = StringExtensions.convertToBytearray(charArray);
		
		for (int i = 0; i < actualBytearray.length; i++)
		{
			expected = expectedBytearray[i];
			actual = actualBytearray[i];
			assertEquals(expected, actual);
		}

		actualBytearray = StringExtensions.convertToBytearray(null);
		assertNull(actualBytearray);
	}

	/**
	 * Test method for {@link StringExtensions#convertToCharArray(byte[])}.
	 */
	@Test
	public void testConvertToCharArray()
	{
		char[] actualCharArray;
		char expected;
		char actual;
		char[] expectedCharArray = { 'a', 'b', 'c', 'd', 'e' };
		byte[] bytearray = "abcde".getBytes();
		actualCharArray = StringExtensions.convertToCharArray(bytearray);
		
		for (int i = 0; i < actualCharArray.length; i++)
		{
			expected = expectedCharArray[i];
			actual = actualCharArray[i];
			assertEquals(expected, actual);
		}
		actualCharArray = StringExtensions.convertToCharArray(null);
		assertNull(actualCharArray);
	}

	/**
	 * Test method for {@link StringExtensions#convertUnicodeStringToCharacter(String)}.
	 */
	@Test
	public void testConvertUnicodeStringToCharacter()
	{
		Character expected;
		Character actual;
		String theUnicodeString = "\u03A9";
		actual = StringExtensions.convertUnicodeStringToCharacter(theUnicodeString);
		expected = Character.valueOf('\u03A9');
		assertEquals(expected, actual);
	}

	/**
	 * Test method for {@link StringExtensions#getValue(Map, String, String)}.
	 */
	@Test
	public void testGetValue()
	{
		String expected;
		String actual;
		Map<String, String> data;
		String key;
		String value;
		data = MapFactory.newHashMap();
		key = "foo";
		value = "bar";
		data.put(key, value);
		actual = StringExtensions.getValue(data, key, "bla");
		expected = value;
		assertEquals(expected, actual);
	}

	/**
	 * Test method for {@link StringExtensions#removeEmptyString(String[])}.
	 */
	@Test
	public void testRemoveEmptyString()
	{
		// TODO implement unit test...
	}

	/**
	 * Test method for {@link StringExtensions#removeNewlineCharacters(String)}.
	 */
	@Test
	public void testRemoveNewlineCharacters()
	{
		// TODO implement unit test...
	}

	/**
	 * Test method for {@link StringExtensions#toString(Object)}.
	 */
	@Test
	public void testToStringT()
	{
		// TODO implement unit test...
	}

}
