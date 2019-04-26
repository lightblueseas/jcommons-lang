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
package de.alpharogroup.regex;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import de.alpharogroup.string.StringExtensions;
import lombok.experimental.UtilityClass;

/**
 * Utility class for the use with regular expressions.
 *
 * @version 1.0
 * @author Asterios Raptis
 * @deprecated use instead the same named class in new project silly-strings
 */
@Deprecated
@UtilityClass
public final class RegExExtensions
{

	/**
	 * Constant for the wildcard asterisk "*". Current value:"*"
	 */
	public static final String WILDCARD_ASTERISK = "*";

	/**
	 * Constant for the wildcard interrogation mark "?". Current value:"?"
	 */
	public static final String WILDCARD_QUESTION_MARK = "?";

	/**
	 * Regular expression class for the valid characters in a filename. Current
	 * value:"[a-zA-Z0-9._-]"
	 */
	public static final String VALID_CHARS_IN_FILENAME = "[a-zA-Z0-9._-]";

	/**
	 * Regular expression for exactly on character in the filename. Current
	 * value:"[a-zA-Z0-9._-]{1}"
	 */
	public static final String WILDCARD_ONECHAR_RE = VALID_CHARS_IN_FILENAME + "{1}";

	/**
	 * Regular expression for many characters in the filename. Current value:".*"
	 */
	public static final String WILDCARD_MANYCHARS_RE = ".*";

	public static final String VALID_EMAIL = "([\\w\\-\\.\\+_]+@[\\w\\-\\._]{2,}\\.+[\\w]{2,4})?";


	public static final String VALID_PHONE = "^[a-zA-Z .,;:/\\-]*[ ]*[(]{0,1}[ ]*[+]{0,1}[ ]*[0-9]{0,2}[ ]*[)]{0,1}[ ]*[0-9]*[ ]*[/\\-]{0,1}[ ]*[ ]*[0-9]*[ ]*[a-zA-Z .,;:\\/-]*$";


	/**
	 * Replaces the normal wildcards in the given String with the corresponding regular expression
	 * wildcards.
	 *
	 * @param queryString
	 *            The String to format.
	 * @return Returns the formatted String as a regular expression for search.
	 */
	public static String replaceWildcardsWithRE(final String queryString)
	{
		final String stern = StringExtensions.replaceAll(queryString, WILDCARD_ASTERISK,
			WILDCARD_MANYCHARS_RE);
		final String regexp = StringExtensions.replaceAll(stern, WILDCARD_QUESTION_MARK,
			WILDCARD_ONECHAR_RE);
		return regexp;
	}

	/**
	 * Replaces the query with the wildcards asterisk "*" and interrogation mark "?" with the
	 * corresponding wildcard from SQL.
	 *
	 * @param query
	 *            The String to replace the wildcards.
	 * @return The String with the new wildcards from SQL "%" and "_".
	 */
	public static String wildcardCriterionSQL(final String query)
	{

		final String newQuery = query.replace('*', '%').replace('?', '_');
		return newQuery;
	}

	/**
	 * Checks if the given regular expression pattern is matching with the given text.
	 *
	 * @param regexPattern
	 *            the regular expression pattern
	 * @param text
	 *            the text to check if it matches
	 * @return true if the given text is matching otherwise false
	 */
	public static boolean isMatching(String regexPattern, String text)
	{
		return 0 < countMatches(regexPattern, text);
	}

	/**
	 * Count how many times the given text is matching and returns the result.
	 *
	 * @param regexPattern
	 *            the regular expression pattern
	 * @param text
	 *            the text to check if it matches
	 * @return the count of how many times the given text is matching
	 */
	public static int countMatches(String regexPattern, String text)
	{
		Matcher matcher = Pattern.compile(regexPattern).matcher(text);
		int matches = 0;
		while (matcher.find())
		{
			matches++;
		}
		return matches;
	}

}
