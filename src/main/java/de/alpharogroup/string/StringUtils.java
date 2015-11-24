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

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.codec.DecoderException;

import de.alpharogroup.check.Check;

/**
 * Utility class for the use of String object.
 *
 * @author Asterios Raptis
 * @version 1.0
 */
public final class StringUtils
{

	/** A char array from the hexadecimal digits. */
	private static final char[] HEXADECIMAL_DIGITS = { '0', '1', '2', '3', '4', '5', '6', '7', '8',
			'9', 'A', 'B', 'C', 'D', 'E', 'F' };

	/**
	 * Converts all characters from the given String to unicodes characters encoded like &#92;uxxxx.
	 * Note: empty characters and symbols are not converted. See test for examples.
	 *
	 * @param s
	 *            The String to convert.
	 * @param toLowerCase
	 *            If true the letters from the unicode characters are lower case.
	 * @return The converted String.
	 * @deprecated use instead {@link StringExtensions#toUnicodeChars(String, boolean)}
	 */
	@Deprecated
	public static String convertCharsToUnicodeChars(final String s, final boolean toLowerCase)
	{
		if (s == null || s.length() == 0)
		{
			return s;
		}
		final int length = s.length();
		final int sbLength = length * 2;
		final StringBuilder sb = new StringBuilder(sbLength);
		for (int i = 0; i < length; i++)
		{
			final char c = s.charAt(i);
			if (c > 61 && c < 127)
			{
				if (c == '\\')
				{
					sb.append('\\');
					sb.append('\\');
					continue;
				}
				sb.append(c);
				continue;
			}
			switch (c)
			{
				case '\f' :
					sb.append('\\');
					sb.append('f');
					break;
				case '\n' :
					sb.append('\\');
					sb.append('n');
					break;
				case '\r' :
					sb.append('\\');
					sb.append('r');
					break;
				case '\t' :
					sb.append('\\');
					sb.append('t');
					break;
				case ' ' :
					if (i == 0)
					{
						sb.append('\\');
					}
					sb.append(' ');
					break;
				case ':' :
				case '#' :
				case '=' :
				case '!' :
					sb.append('\\');
					sb.append(c);
					break;
				default :
					if (c < 0x0020 || c > 0x007e)
					{
						sb.append('\\');
						sb.append('u');
						sb.append(toHex(c >> 12 & 0xF));
						sb.append(toHex(c >> 8 & 0xF));
						sb.append(toHex(c >> 4 & 0xF));
						sb.append(toHex(c & 0xF));
					}
					else
					{
						sb.append(c);
					}
			}
		}
		final String returnString = sb.toString();
		if (toLowerCase)
		{
			return returnString.toLowerCase();
		}
		else
		{
			return returnString;
		}
	}

	/**
	 * Converts the given chararray to a bytearray.
	 *
	 * @param source
	 *            the source.
	 * @return the byte[]
	 */
	public static final byte[] convertToBytearray(final char[] source)
	{
		final byte[] result = new byte[source.length];
		for (int i = 0; i < source.length; i++)
		{
			result[i] = (byte)source[i];
		}
		return result;
	}

	/**
	 * Convert the given byte array to char array.
	 *
	 * @param source
	 *            the source.
	 * @return the char[]
	 */
	public static char[] convertToCharArray(final byte[] source)
	{
		if (source == null)
		{
			return null;
		}
		final char[] result = new char[source.length];
		for (int i = 0; i < source.length; i++)
		{
			result[i] = (char)source[i];
		}
		return result;
	}

	/**
	 * Converts the given unicode String object to character object. <br>
	 * Example: the given unicode String object is "&#92;u03A9" <br>
	 * the return character object would be "&#937;".
	 *
	 * @param theUnicodeString
	 *            the the unicode string
	 * @return the converted character object.
	 */
	public static Character convertUnicodeStringToCharacter(final String theUnicodeString)
	{
		char current;
		final int length = theUnicodeString.length();
		final StringBuilder sb = new StringBuilder(length);

		for (int outerCount = 0; outerCount < length;)
		{
			current = theUnicodeString.charAt(outerCount++);
			if (current == '\\')
			{
				current = theUnicodeString.charAt(outerCount++);
				if (current == 'u')
				{
					int value = 0;
					for (int i = 0; i < 4; i++)
					{
						current = theUnicodeString.charAt(outerCount++);
						switch (current)
						{
							case '0' :
							case '1' :
							case '2' :
							case '3' :
							case '4' :
							case '5' :
							case '6' :
							case '7' :
							case '8' :
							case '9' :
								value = (value << 4) + current - '0';
								break;
							case 'a' :
							case 'b' :
							case 'c' :
							case 'd' :
							case 'e' :
							case 'f' :
								value = (value << 4) + 10 + current - 'a';
								break;
							case 'A' :
							case 'B' :
							case 'C' :
							case 'D' :
							case 'E' :
							case 'F' :
								value = (value << 4) + 10 + current - 'A';
								break;
							default :
								throw new MalformedUnicodeException("Given String object :::"
									+ theUnicodeString
									+ "::: is not a well formed unicode String object.\n"
									+ "Format for a well formed unicode String object:'\\uxxxx'.");
						}
					}
					sb.append((char)value);
				}
				else
				{
					if (current == 't')
					{
						current = '\t';
					}
					else if (current == 'r')
					{
						current = '\r';
					}
					else if (current == 'n')
					{
						current = '\n';
					}
					else if (current == 'f')
					{
						current = '\f';
					}
					sb.append(current);
				}
			}
			else
			{
				sb.append(current);
			}
		}
		final String result = sb.toString();
		final char c = result.charAt(0);
		return c;
	}

	/**
	 * Decode hex.
	 *
	 * @param data
	 *            the data
	 * @return the byte[]
	 * @throws DecoderException
	 *             the decoder exception
	 */
	public static byte[] decodeHex(final char[] data) throws DecoderException
	{
		return org.apache.commons.codec.binary.Hex.decodeHex(data);
	}

	/**
	 * Encode hex.
	 *
	 * @param data
	 *            the data
	 * @return the char[]
	 */
	public static char[] encodeHex(final byte[] data)
	{
		return org.apache.commons.codec.binary.Hex.encodeHex(data, true);
	}

	/**
	 * Gets the Attribut-name without brackets. For example: If "indio[5][1]" is the given String
	 * than the name is "indio" and the first index is "5" and the second is "1". Than the Method
	 * returns "indio".
	 *
	 * @param name
	 *            The name with brackets.
	 * @return The name without brackets.
	 */
	public static final String getAttributName(final String name)
	{
		final int indexStart = name.indexOf("[");
		final String returnName = name.substring(0, indexStart);
		return returnName;
	}

	/**
	 * Gets the first index from the brackets. For example: If "indio[5][1]" is the given String
	 * than the name is "indio" and the first index is "5" and the second is "1". Than the Method
	 * returns "5".
	 *
	 * @param name
	 *            The name from the Attribute
	 * @return the index from the Attribute
	 */
	public static final String getIndex(final String name)
	{
		final int firstIndexStart = name.indexOf("[");
		final int firstIndexEnd = name.indexOf("]");
		final String index = name.substring(firstIndexStart + 1, firstIndexEnd);
		return index;
	}

	/**
	 * Gets the ItemNumber from the String name. For example: If "indio[5][1]" is the given String
	 * than the name is "indio" and the first index is "5" and the second is "1". Than the Method
	 * returns "1".
	 *
	 * @param name
	 *            the name
	 * @return the ItemNumber
	 */
	public static final String getItemNumber(final String name)
	{
		final int lastIndexStart = name.lastIndexOf("[");
		final int lastIndexEnd = name.lastIndexOf("]");
		final String itemNumber = name.substring(lastIndexStart + 1, lastIndexEnd);
		return itemNumber;
	}

	/**
	 * Gets the last character of the given String object.
	 *
	 * @param str
	 *            the str
	 * @return the last character
	 * @deprecated use instead {@link StringExtensions#lastCharacter(String)}
	 */
	@Deprecated
	public static String getLastCharacter(final String str)
	{
		if (str == null || str.length() == 0)
		{
			return "";
		}
		return str.substring(str.length() - 1);
	}

	/**
	 * The Method getStringAfterUnderscore(String) gets the substring after the first underscore.
	 * For example:Assume that element=foobar_value then the result string is "value".
	 *
	 * @param element
	 *            The element to get the substing.
	 * @return The result String.
	 */
	public static final String getStringAfterUnderscore(final String element)
	{
		String returnString = null;
		final int i = element.indexOf("_");
		returnString = element.substring(i + 1, element.length());
		return returnString;
	}

	/**
	 * The Method getStringBeforeUnderscore(String) gets the substring before the first underscore.
	 * For example:Assume that element=foobar_value then the result string is "foobar".
	 *
	 * @param element
	 *            The element to get the substing.
	 * @return The result String.
	 */
	public static final String getStringBeforeUnderscore(final String element)
	{
		Check.get().notNull(element, "string").notEmpty(element, "string");
		return element.substring(0, element.indexOf("_"));
	}

	/**
	 * Gets the value from the given map and if it does not exist or is empty the given default
	 * value will be returned.
	 *
	 * @param data
	 *            the data
	 * @param key
	 *            the key
	 * @param defaultValue
	 *            the default value
	 * @return the value
	 */
	public static String getValue(final Map<String, String> data, final String key,
		final String defaultValue)
	{
		String value = data.get(key);
		if (!(value != null && !value.isEmpty()))
		{
			value = defaultValue;
		}
		return value;
	}

	/**
	 * The method isEmpty(String) checks if the given String is empty or null.
	 *
	 * @param str
	 *            The String to check.
	 * @return true if the given String is empty or null otherwise false.
	 * @deprecated use instead {@link StringExtensions#isNullOrEmpty(String)}
	 */
	@Deprecated
	public static final boolean isNullOrEmpty(final String str)
	{
		return str == null || str.length() == 0;
	}

	/**
	 * Checks if the given String is an Number.
	 *
	 * @param testString
	 *            The String to check.
	 * @return true if the given String is a number otherwise false.
	 * @deprecated use instead {@link StringExtensions#isNumber(String)}
	 */
	@Deprecated
	public static final boolean isNumber(final String testString)
	{
		if (null == testString)
		{
			return false;
		}
		for (int i = 0; i < testString.length(); i++)
		{
			if (Character.isDigit(testString.charAt(i)) == false)
			{
				return false;
			}
		}
		return true;
	}

	/**
	 * Puts to the given String at the start and end quotes.
	 *
	 * @param s
	 *            The String to handle.
	 * @return The String with quotes.
	 * @deprecated use instead {@link StringExtensions#addDoubleQuotationMarks(String)}
	 */
	@Deprecated
	public static final String putQuotesToString(final String s)
	{
		return "\"" + s + "\"";
	}

	/**
	 * Reads the given String till it finds a carriage return and returns it.
	 *
	 * @param stringWithCarriageReturns
	 *            The String.
	 * @return Return the String till the carriage return.
	 */
	public static final String readLine(final String stringWithCarriageReturns)
	{
		String returnString = "";
		int index = 0;
		index = stringWithCarriageReturns.indexOf(System.getProperty("line.separator"));
		if (index < 0)
		{
			index = stringWithCarriageReturns.indexOf("\n");
		}
		if (index < 0)
		{
			index = stringWithCarriageReturns.indexOf("\r");
		}
		returnString = stringWithCarriageReturns.substring(0, index);
		return returnString;

	}

	/**
	 * Removes empty Strings from the given string array and gives it back. For instance if you use
	 * the String.split("\\W") method you get all the words from the given String as an string
	 * array. But this array contains empty Strings too. This method removes this empty strings from
	 * that array.
	 *
	 * @param words
	 *            The string array to remove empty strings.
	 * @return An string array without empty Strings.
	 */
	public static String[] removeEmptyString(final String[] words)
	{
		final List<String> al = new ArrayList<String>();
		for (int i = 0; i < words.length; i++)
		{
			if (!words[i].isEmpty())
			{
				al.add(words[i]);
			}
		}
		return al.toArray(new String[al.size()]);
	}

	/**
	 * Removes the newline characters from the given String.
	 *
	 * @param subject
	 *            the subject
	 * @return the string
	 */
	public static String removeNewlineCharacters(String subject)
	{
		if (subject.contains("\n"))
		{
			subject = subject.replace("\n", "");
		}
		if (subject.contains("\r"))
		{
			subject = subject.replace("\r", "");
		}
		return subject;
	}

	/**
	 * Removes from the given String the first and the last character.
	 *
	 * @param s
	 *            The String to handle.
	 * @return The String with the without the first and the last character.
	 * @deprecated use instead {@link StringExtensions#addDoubleQuotationMarks(String)}
	 */
	@Deprecated
	public static final String removeQuotationMarks(final String s)
	{
		return s.substring(1, s.length() - 1);

	}


	/**
	 * Replaces all occurrences of a String within another String.
	 *
	 * @param original
	 *            The String to search.
	 * @param findString
	 *            The search pattern.
	 * @param replaceWith
	 *            Replace with that String.
	 * @return Returns a new String with the replaced Strings.
	 */
	public static final String replaceAll(final String original, final String findString,
		final String replaceWith)
	{
		final StringBuffer originalFiltered = new StringBuffer();
		int next = 0;
		int count = 0;
		final int length = findString.length();

		while (count > -1)
		{
			count = original.indexOf(findString, next);

			if (count > -1)
			{
				originalFiltered.append(original.substring(next, count));
				originalFiltered.append(replaceWith);
				next = count + length;
			}
		}

		originalFiltered.append(original.substring(next, original.length()));

		return originalFiltered.toString();
	}

	/**
	 * Replaces all occurrences of a String within another String.
	 *
	 * @param original
	 *            The String to search.
	 * @param findString
	 *            An array with search patterns.
	 * @param replaceWith
	 *            Replace with that String.
	 * @return Returns a new String with the replaced Strings.
	 */
	public static final String replaceAll(final String original, final String[] findString,
		final String replaceWith)
	{
		String result = original;
		for (final String element : findString)
		{
			result = replaceAll(result, element, replaceWith);
		}
		return result;
	}

	/**
	 * Replace each occurences from the search pattern(regex) with the given replace String of the
	 * given input String.
	 *
	 * @param input
	 *            the input String that will be changed.
	 * @param searchRegexPattern
	 *            the search regex pattern
	 * @param replace
	 *            the String to replace with.
	 * @return the resulted string
	 */
	public static String replaceEach(final String input, final String searchRegexPattern,
		final String replace)
	{
		final Pattern pattern = Pattern.compile(searchRegexPattern);
		final Matcher matcher = pattern.matcher(input);
		return matcher.replaceAll(replace);
	}

	/**
	 * Sets the first character from the given string to lower case and returns it. Example:<br>
	 * Given fieldName: UserName <br>
	 * Result: userName
	 *
	 * @param fieldName
	 *            The String to modify.
	 * @return The modified string.
	 * @deprecated use instead {@link StringExtensions#firstCharacterToLowerCase(String)}
	 */
	@Deprecated
	public static final String setFirstCharacterToLowerCase(final String fieldName)
	{
		String firstCharacter = fieldName.substring(0, 1);
		firstCharacter = firstCharacter.toLowerCase();
		final char[] fc = firstCharacter.toCharArray();
		final char[] fn = fieldName.toCharArray();
		fn[0] = fc[0];
		return new String(fn);
	}


	/**
	 * Sets the first character from the given string to upper case and returns it. Example:<br>
	 * Given fieldName: userName <br>
	 * Result: UserName
	 *
	 * @param fieldName
	 *            The String to modify.
	 * @return The modified string.
	 * @deprecated use instead {@link StringExtensions#firstCharacterToUpperCase(String)}
	 */
	@Deprecated
	public static final String setFirstCharacterToUpperCase(final String fieldName)
	{
		String firstCharacter = fieldName.substring(0, 1);
		firstCharacter = firstCharacter.toUpperCase();
		final char[] fc = firstCharacter.toCharArray();
		final char[] fn = fieldName.toCharArray();
		fn[0] = fc[0];
		return new String(fn);
	}


	/**
	 * To hex.
	 *
	 * @param i
	 *            the i
	 * @return the char
	 */
	public static char toHex(final int i)
	{
		return HEXADECIMAL_DIGITS[i & 0xF];
	}

	/**
	 * Converts all characters from the given String to unicodes characters encoded like &#92;uxxxx.
	 *
	 * @param toUnicode
	 *            The String to convert.
	 * @param toLowerCase
	 *            If true the letters from the unicode characters are lower case.
	 * @return The converted String.
	 */
	public static String toUnicode(final String toUnicode, final boolean toLowerCase)
	{
		final StringBuilder sb = new StringBuilder();
		for (int i = 0; i < toUnicode.length(); i++)
		{
			String hex = Integer.toHexString(toUnicode.codePointAt(i));
			if (toLowerCase)
			{
				hex = hex.toLowerCase();
			}
			else
			{
				hex = hex.toUpperCase();
			}
			final String hexWithZeros = "0000" + hex;
			final String hexCodeWithLeadingZeros = hexWithZeros
				.substring(hexWithZeros.length() - 4);
			sb.append("\\u" + hexCodeWithLeadingZeros);
		}
		return sb.toString();
	}

}
