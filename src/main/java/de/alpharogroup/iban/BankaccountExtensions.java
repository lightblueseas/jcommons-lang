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
package de.alpharogroup.iban;

import java.math.BigInteger;
import java.util.Map;

import de.alpharogroup.collections.MapExtensions;
import de.alpharogroup.resourcebundle.locale.LocaleResolver;
import de.alpharogroup.string.StringExtensions;
import de.alpharogroup.string.StringUtils;
import lombok.experimental.ExtensionMethod;

/**
 * Utility class for bank accounts.
 *
 * @version 1.0
 * @author Asterios Raptis
 */
@ExtensionMethod(StringExtensions.class)
public class BankaccountExtensions
{

	/**
	 * Array to replace a character with a number. Is needed for the iban number.
	 */
	static final String[][] REPLACE_CHAR_WITH_NUMBER = { { "A", "10" }, { "B", "11" },
			{ "C", "12" }, { "D", "13" }, { "E", "14" }, { "F", "15" }, { "G", "16" },
			{ "H", "17" }, { "I", "18" }, { "J", "19" }, { "K", "20" }, { "L", "21" },
			{ "M", "22" }, { "N", "23" }, { "O", "24" }, { "P", "25" }, { "Q", "26" },
			{ "R", "27" }, { "S", "28" }, { "T", "29" }, { "U", "30" }, { "V", "31" },
			{ "W", "32" }, { "X", "33" }, { "Y", "34" }, { "Z", "35" } };

	/**
	 * Checks if the ibannumber is valid.
	 *
	 * @param ibanNumber
	 *            The iban number.
	 * @return true if the ibannumber is valide otherwise false.
	 * @throws Exception
	 *             is thrown if an error occurs when checking the given ibanNumber
	 */
	public static boolean isIbanNumber(final String ibanNumber) throws Exception
	{
		String ibanWOWS = ibanNumber;
		// Removes the whitespaces
		ibanWOWS = StringUtils.replaceAll(ibanWOWS, " ", "");

		// if ibanNumber is not between 5 and 34
		if (ibanWOWS.length() > 34 || ibanWOWS.length() < 5)
		{
			return false;
		}
		else
		{
			final String locale = ibanWOWS.substring(0, 2).toUpperCase();
			final String checksum = ibanWOWS.substring(2, 4).toUpperCase();
			final String bankAccountNumber = ibanWOWS.substring(4).toUpperCase();
			// is not a number
			if (!checksum.isNumber())
			{
				return false;
			}
			// locale is not valid
			if (!LocaleResolver.isISOCountryCode(locale))
			{
				return false;
			}
			// check the checksum
			final String adaptation = bankAccountNumber + replaceCharsWithNumbers(locale) + "00";
			final long cs = Long.valueOf(checksum);
			final BigInteger bd = new BigInteger(adaptation);
			final BigInteger ninetySeven = new BigInteger("97");
			final int v = bd.mod(ninetySeven).intValue();
			final int endsum = 98 - v;
			if (endsum != cs)
			{
				// checksum is corrupt
				return false;
			}
		}
		return true;
	}

	/**
	 * Replaces Characters with numbers.
	 *
	 * @param locale
	 *            The locale as String.
	 * @return The formatted string.
	 * @throws Exception
	 *             is thrown if an error occurs when tryin to replace the char with numbers
	 */
	public static String replaceCharsWithNumbers(final String locale) throws Exception
	{
		String retString = null;
		if (null != locale && locale.length() != 2)
		{
			throw new Exception("Invalide locale.");
		}
		final String chr1 = locale.substring(0, 1);
		final String chr2 = locale.substring(1, 2);
		final Map<String, String> map = MapExtensions.toGenericMap(REPLACE_CHAR_WITH_NUMBER);
		final String chr1Number = map.get(chr1);
		final String chr2Number = map.get(chr2);
		retString = chr1Number + chr2Number;
		return retString;
	}
}
