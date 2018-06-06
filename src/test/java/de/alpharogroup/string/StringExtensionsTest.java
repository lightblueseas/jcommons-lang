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

import static org.testng.Assert.assertNotNull;
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
import de.alpharogroup.test.objects.Person;
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
		String theUnicodeString;
		theUnicodeString = "\u03A9";
		actual = StringExtensions.convertUnicodeStringToCharacter(theUnicodeString);
		expected = Character.valueOf('\u03A9');
		assertEquals(expected, actual);

		theUnicodeString = "\\u1F78";
		actual = StringExtensions.convertUnicodeStringToCharacter(theUnicodeString);
		expected = Character.valueOf('\u1F78');
		assertEquals(expected, actual);

		theUnicodeString = "\\u03BC";
		actual = StringExtensions.convertUnicodeStringToCharacter(theUnicodeString);
		expected = Character.valueOf('\u03BC');
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
		String argument;

		argument = "UserName";
		actual = StringExtensions.firstCharacterToLowerCase(argument);
		expected = "userName";
		assertEquals(actual, expected);

		argument = "";
		actual = StringExtensions.firstCharacterToLowerCase(argument);
		expected = "";
		assertEquals(actual, expected);

		argument = null;
		actual = StringExtensions.firstCharacterToLowerCase(argument);
		expected = null;
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
		String argument;

		argument = "userName";
		actual = StringExtensions.firstCharacterToUpperCase(argument);
		expected = "UserName";
		assertEquals(actual, expected);

		argument = "";
		actual = StringExtensions.firstCharacterToUpperCase(argument);
		expected = "";
		assertEquals(actual, expected);

		argument = null;
		actual = StringExtensions.firstCharacterToUpperCase(argument);
		expected = null;
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
		String defaultValue;
		data = MapFactory.newHashMap();
		key = "foo";
		value = "bar";
		defaultValue = "bla";
		data.put(key, value);
		actual = StringExtensions.getValue(data, key, defaultValue);
		expected = value;
		assertEquals(expected, actual);

		key = "fasel";
		actual = StringExtensions.getValue(data, key, defaultValue);
		expected = defaultValue;
		assertEquals(expected, actual);

		key = "fasel";
		value = "";
		data.put(key, value);
		actual = StringExtensions.getValue(data, key, defaultValue);
		expected = defaultValue;
		assertEquals(expected, actual);
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
		boolean expected;
		boolean actual;
		String argument;

		argument = "5";
		actual = argument.isNumber();
		expected = true;
		assertEquals(actual, expected);

		argument = "foo";
		actual = argument.isNumber();
		expected = false;
		assertEquals(actual, expected);

		argument = "";
		actual = argument.isNumber();
		expected = false;
		assertEquals(actual, expected);

		argument = null;
		actual = argument.isNumber();
		expected = false;
		assertEquals(actual, expected);
	}

	/**
	 * Test method for {@link StringExtensions#lastCharacter(String)}
	 */
	@Test
	public void testLastCharacter()
	{
		String expected;
		String actual;
		expected = "e";
		actual = StringExtensions.lastCharacter("UserName");
		assertEquals(actual, expected);

		expected = "e";
		actual = StringExtensions.lastCharacter("e");
		assertEquals(actual, expected);

		expected = " ";
		actual = StringExtensions.lastCharacter(" ");
		assertEquals(actual, expected);

		expected = "";
		actual = StringExtensions.lastCharacter("");
		assertEquals(actual, expected);

		expected = "";
		actual = StringExtensions.lastCharacter(null);
		assertEquals(actual, expected);
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
	 * Test method for {@link StringExtensions#addDoubleQuotationMarks(String)}
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
		String expected;
		String actual;
		String argument;

		argument = "This is a test: Aha :\n and than foo bar:";
		expected = "This is a test: Aha :";
		actual = StringExtensions.readLine(argument);
		assertEquals(actual, expected);

		argument = "This is a test: Aha :\r and than foo bar:";
		expected = "This is a test: Aha :";
		actual = StringExtensions.readLine(argument);
		assertEquals(actual, expected);
	}

	/**
	 * Test method for {@link StringExtensions#removeEmptyString(String[])}.
	 */
	@Test
	public void testRemoveEmptyString()
	{
		final String expected = "Hickory   Dickory Dock mouse ran up the clock The struck one The ran down";
		String[] words = expected.split("\\W");

		String[] actual = StringExtensions.removeEmptyString(words);
		assertNotNull(actual);
		assertTrue(words.length == 16);
		assertTrue(actual.length == 14);
	}

	/**
	 * Test method for {@link StringExtensions#removeNewlineCharacters(String)}.
	 */
	@Test
	public void testRemoveNewlineCharacters()
	{
		String expected;
		String actual;
		String subject = "Foo bar\r\n bla fasel\n";
		actual = StringExtensions.removeNewlineCharacters(subject);
		expected = "Foo bar bla fasel";
		assertEquals(expected, actual);
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

	// =================================================================================== //


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
		String expected;
		String actual;
		String argument;

		argument = "This is a test: Aha : and than foo bar:";
		actual = argument.replaceLast(":", ";");
		expected = "This is a test: Aha : and than foo bar;";
		assertEquals(expected, actual);

		argument = "This is a test: Aha : and than foo bar:";
		actual = argument.replaceLast(",", ";");
		expected = argument;
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
	 * Test method for {@link StringExtensions#toString(Object)}.
	 */
	@Test
	public void testToStringObject()
	{
		String expected;
		String actual;
		Person person = Person.builder().build();
		actual = StringExtensions.toString(person);
		assertTrue(actual.startsWith("de.alpharogroup.test.objects.Person@"));
		assertTrue(actual.endsWith("[name=,nickname=,gender=UNDEFINED,about=,married=false]"));

		actual = StringExtensions.toString(null);
		expected = "Given object is null!!!";
		assertEquals(expected, actual);
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
	 * Test method for {@link StringExtensions#toUnicodeChars(String, boolean)}
	 */
	@Test(enabled = true)
	public void testToUnicodeChars()
	{
		String expected;
		String actual;
		String argument;

		argument = "ö";
		actual = argument.toUnicodeChars(true);
		expected = "\\u00f6";
		assertEquals(expected, actual);

		actual = argument.toUnicodeChars(false);
		expected = "\\u00F6";
		assertEquals(expected, actual);

		argument = "ö, ß ä";
		actual = argument.toUnicodeChars(false);
		expected = "\\u00F6, \\u00DF \\u00E4";
		assertEquals(expected, actual);

		actual = "τὸ μὲν οὖν κατὰ τὴν Ἀράτου τοῦ νεωτέρου στρατηγίαν ἔτος ἐτύγχανε διεληλυθὸς περὶ τὴν τῆς Πλειάδος"
			.toUnicodeChars(false);
		expected = "\\u03C4\\u1F78 \\u03BC\\u1F72\\u03BD \\u03BF\\u1F56\\u03BD"
			+ " \\u03BA\\u03B1\\u03C4\\u1F70 \\u03C4\\u1F74\\u03BD "
			+ "\\u1F08\\u03C1\\u03AC\\u03C4\\u03BF\\u03C5 \\u03C4\\u03BF\\u1FE6"
			+ " \\u03BD\\u03B5\\u03C9\\u03C4\\u03AD\\u03C1\\u03BF\\u03C5 "
			+ "\\u03C3\\u03C4\\u03C1\\u03B1\\u03C4\\u03B7\\u03B3\\u03AF\\u03B1\\u03BD"
			+ " \\u1F14\\u03C4\\u03BF\\u03C2 "
			+ "\\u1F10\\u03C4\\u03CD\\u03B3\\u03C7\\u03B1\\u03BD\\u03B5 "
			+ "\\u03B4\\u03B9\\u03B5\\u03BB\\u03B7\\u03BB\\u03C5\\u03B8\\u1F78\\u03C2 "
			+ "\\u03C0\\u03B5\\u03C1\\u1F76 \\u03C4\\u1F74\\u03BD \\u03C4\\u1FC6\\u03C2"
			+ " \\u03A0\\u03BB\\u03B5\\u03B9\\u03AC\\u03B4\\u03BF\\u03C2";
		assertEquals(expected, actual);

		argument = "";
		actual = argument.toUnicodeChars(true);
		expected = "";
		assertEquals(expected, actual);

		argument = null;
		actual = argument.toUnicodeChars(true);
		expected = null;
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

}
