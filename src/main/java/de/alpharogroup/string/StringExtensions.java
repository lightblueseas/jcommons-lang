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

public class StringExtensions
{

	/** A char array from the hexadecimal digits. */
	private static final char[] HEXADECIMAL_DIGITS = { '0', '1', '2', '3', '4', '5', '6', '7', '8',
			'9', 'A', 'B', 'C', 'D', 'E', 'F' };

	/**
	 * Puts to the given String at the start and end quotes.
	 *
	 * @param s
	 *            The String to handle.
	 * @return The String with quotes.
	 */
	public static final String addDoubleQuotationMarks(final String s)
	{
		return "\"" + s + "\"";
	}

	/**
	 * Puts to the given String at the start and end quotes.
	 *
	 * @param s
	 *            The String to handle.
	 * @return The String with quotes.
	 */
	public static final String addSingleQuotationMarks(final String s)
	{
		return "'" + s + "'";
	}


	/**
	 * Sets the first character from the given string to lower case and returns it. Example:<br>
	 * Given fieldName: UserName <br>
	 * Result: userName
	 *
	 * @param fieldName
	 *            The String to modify.
	 * @return The modified string.
	 */
	public static final String firstCharacterToLowerCase(final String fieldName)
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
	 */
	public static final String firstCharacterToUpperCase(final String fieldName)
	{
		String firstCharacter = fieldName.substring(0, 1);
		firstCharacter = firstCharacter.toUpperCase();
		final char[] fc = firstCharacter.toCharArray();
		final char[] fn = fieldName.toCharArray();
		fn[0] = fc[0];
		return new String(fn);
	}


	/**
	 * The method isNullOrEmpty(String) checks if the given String is empty or null.
	 *
	 * @param str
	 *            The String to check.
	 * @return true if the given String is empty or null otherwise false.
	 */
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
	 */
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
	 * Gets the last character of the given String object.
	 *
	 * @param str
	 *            the str
	 * @return the last character
	 */
	public static String lastCharacter(final String str)
	{
		if (str == null || str.length() == 0)
		{
			return "";
		}
		return str.substring(str.length() - 1);
	}

	/**
	 * Removes from the given String at the start and end quotes.
	 *
	 * @param s
	 *            The String to handle.
	 * @return The String without quotes.
	 */
	public static final String removeQuotationMarks(final String s)
	{
		return s.substring(1, s.length() - 1);
	}


	/**
	 * Replaces the last occurrence of a String within another String.
	 *
	 * @param original
	 *            The String to search.
	 * @param findString
	 *            The search pattern.
	 * @param replacement
	 *            Replace with that String.
	 * @return Returns a new String with the replaced Strings.
	 */
	public static final String replaceLast(final String original, final String findString,
		final String replacement)
	{
		final int index = original.lastIndexOf(findString);
		if (index == -1)
		{
			return original;
		}
		final StringBuffer originalFiltered = new StringBuffer();
		originalFiltered.append(original.substring(0, index));
		originalFiltered.append(replacement);
		originalFiltered.append(original.substring(index + findString.length()));
		final String result = originalFiltered.toString().trim();
		return result;
	}

	/**
	 * To hex.
	 *
	 * @param i
	 *            the i
	 * @return the char
	 */
	private static char toHex(final int i)
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

	/**
	 * Converts all characters from the given String to unicodes characters encoded like &#92;uxxxx.
	 *
	 * @param s
	 *            The String to convert.
	 * @param toLowerCase
	 *            If true the letters from the unicode characters are lower case.
	 * @return The converted String.
	 */
	public static String toUnicodeChars(final String s, final boolean toLowerCase)
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

}
