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
package de.alpharogroup.math;

import lombok.experimental.UtilityClass;

/**
 * Utility class for the use numbers.
 *
 * @version 1.0
 * @author Asterios Raptis
 */
@UtilityClass
public final class MathExtensions
{

	/**
	 * Gets the prime numbers for the given quantity.
	 *
	 * @param quantity
	 *            The quantity.
	 * @return An array from prime numbers.
	 */
	public static int[] getPrimeNumbers(final int quantity)
	{

		final int[] primes = new int[quantity];

		int i = 0;
		int number = 2;

		while (i < quantity)
		{

			final boolean isPrime = isPrime(number);
			if (isPrime)
			{
				primes[i++] = number;
			}
			number++;
		}

		return primes;
	}

	/**
	 * Checks the given double index is between the range from min and max.
	 *
	 * @param min
	 *            The minimum.
	 * @param max
	 *            The maximum.
	 * @param index
	 *            The index.
	 * @return Returns true if the index is betwenn the range from min and max otherwise false.
	 */
	public static boolean isBetween(final double min, final double max, final double index)
	{
		return isBetween(min, max, index, false, false);
	}

	/**
	 * Checks the given double index is between the range from min and max.
	 *
	 * @param min
	 *            The minimum.
	 * @param max
	 *            The maximum.
	 * @param index
	 *            The index.
	 * @param includeMin
	 *            if true than min value is included
	 * @param includeMax
	 *            if true than max value is included
	 * @return Returns true if the index is between the range from min and max considered the given
	 *         flags otherwise false.
	 */
	public static boolean isBetween(final double min, final double max, final double index,
		final boolean includeMin, final boolean includeMax)
	{
		if (includeMin && includeMax)
		{

			if (index >= min && index <= max)
			{
				return true;
			}
			else
			{
				return false;
			}
		}
		if (includeMin && !includeMax)
		{

			if (index >= min && index < max)
			{
				return true;
			}
			else
			{
				return false;
			}
		}
		if (!includeMin && includeMax)
		{

			if (index > min && index <= max)
			{
				return true;
			}
			else
			{
				return false;
			}
		}
		if (min < index && index < max)
		{
			return true;
		}
		else
		{
			return false;
		}
	}

	/**
	 * Checks the given float index is between the range from min and max.
	 *
	 * @param min
	 *            The minimum.
	 * @param max
	 *            The maximum.
	 * @param index
	 *            The index.
	 * @return Returns true if the index is betwenn the range from min and max otherwise false.
	 */
	public static boolean isBetween(final float min, final float max, final float index)
	{
		return isBetween(min, max, index, false, false);
	}

	/**
	 * Checks the given float index is between the range from min and max.
	 *
	 * @param min
	 *            The minimum.
	 * @param max
	 *            The maximum.
	 * @param index
	 *            The index.
	 * @param includeMin
	 *            if true than min value is included
	 * @param includeMax
	 *            if true than max value is included
	 * @return Returns true if the index is between the range from min and max considered the given
	 *         flags otherwise false.
	 */
	public static boolean isBetween(final float min, final float max, final float index,
		final boolean includeMin, final boolean includeMax)
	{
		if (includeMin && includeMax)
		{

			if (index >= min && index <= max)
			{
				return true;
			}
			else
			{
				return false;
			}
		}
		if (includeMin && !includeMax)
		{

			if (index >= min && index < max)
			{
				return true;
			}
			else
			{
				return false;
			}
		}
		if (!includeMin && includeMax)
		{

			if (index > min && index <= max)
			{
				return true;
			}
			else
			{
				return false;
			}
		}
		if (min < index && index < max)
		{
			return true;
		}
		else
		{
			return false;
		}
	}

	/**
	 * Checks the given int index is between the range from min and max.
	 *
	 * @param min
	 *            The minimum.
	 * @param max
	 *            The maximum.
	 * @param index
	 *            The index.
	 * @return Returns true if the index is betwenn the range from min and max otherwise false.
	 */
	public static boolean isBetween(final int min, final int max, final int index)
	{
		return isBetween(min, max, index, false, false);
	}

	/**
	 * Checks the given int index is between the range from min and max.
	 *
	 * @param min
	 *            The minimum.
	 * @param max
	 *            The maximum.
	 * @param index
	 *            The index.
	 * @param includeMin
	 *            if true than min value is included
	 * @param includeMax
	 *            if true than max value is included
	 * @return Returns true if the index is between the range from min and max considered the given
	 *         flags otherwise false.
	 */
	public static boolean isBetween(final int min, final int max, final int index,
		final boolean includeMin, final boolean includeMax)
	{
		if (includeMin && includeMax)
		{

			if (index >= min && index <= max)
			{
				return true;
			}
			else
			{
				return false;
			}
		}
		if (includeMin && !includeMax)
		{

			if (index >= min && index < max)
			{
				return true;
			}
			else
			{
				return false;
			}
		}
		if (!includeMin && includeMax)
		{

			if (index > min && index <= max)
			{
				return true;
			}
			else
			{
				return false;
			}
		}
		if (min < index && index < max)
		{
			return true;
		}
		else
		{
			return false;
		}
	}

	/**
	 * Checks the given long index is between the range from min and max.
	 *
	 * @param min
	 *            The minimum.
	 * @param max
	 *            The maximum.
	 * @param index
	 *            The index.
	 * @return Returns true if the index is betwenn the range from min and max otherwise false.
	 */
	public static boolean isBetween(final long min, final long max, final long index)
	{
		return isBetween(min, max, index, false, false);
	}

	/**
	 * Checks the given long index is between the range from min and max.
	 *
	 * @param min
	 *            The minimum.
	 * @param max
	 *            The maximum.
	 * @param index
	 *            The index.
	 * @param includeMin
	 *            if true than min value is included
	 * @param includeMax
	 *            if true than max value is included
	 * @return Returns true if the index is between the range from min and max considered the given
	 *         flags otherwise false.
	 */
	public static boolean isBetween(final long min, final long max, final long index,
		final boolean includeMin, final boolean includeMax)
	{
		if (includeMin && includeMax)
		{

			if (index >= min && index <= max)
			{
				return true;
			}
			else
			{
				return false;
			}
		}
		if (includeMin && !includeMax)
		{

			if (index >= min && index < max)
			{
				return true;
			}
			else
			{
				return false;
			}
		}
		if (!includeMin && includeMax)
		{

			if (index > min && index <= max)
			{
				return true;
			}
			else
			{
				return false;
			}
		}
		if (min < index && index < max)
		{
			return true;
		}
		else
		{
			return false;
		}
	}

	/**
	 * Checks the given number is in range from min and max.
	 *
	 * @param min
	 *            The minimum.
	 * @param max
	 *            The maximum.
	 * @param number
	 *            The index.
	 * @return Returns true if the number is in range from min and max otherwise false.
	 */
	public static boolean isInRange(final int min, final int max, final int number)
	{
		return isBetween(min, max, number);
	}

	/**
	 * Checks if the number is negative.
	 *
	 * @param number
	 *            The number.
	 * @return Returns true if the number is negative otherwise false.
	 */
	public static boolean isNegative(final double number)
	{
		return !isPositive(number);
	}

	/**
	 * Checks if the number is negative.
	 *
	 * @param number
	 *            The number.
	 * @return Returns true if the number is negative otherwise false.
	 */
	public static boolean isNegative(final float number)
	{
		return !isPositive(number);
	}

	/**
	 * Checks if the number is negative.
	 *
	 * @param number
	 *            The number.
	 * @return Returns true if the number is negative otherwise false.
	 */
	public static boolean isNegative(final int number)
	{
		return !isPositive(number);
	}

	/**
	 * Checks if the number is negative.
	 *
	 * @param number
	 *            The number.
	 * @return Returns true if the number is negative otherwise false.
	 */
	public static boolean isNegative(final long number)
	{
		return !isPositive(number);
	}

	/**
	 * Checks if the given {@link Integer} nextNumber is next to the given {@link Integer} number.
	 * <br>
	 * Example: <br>
	 * isNext(1, 2); =&gt; true;<br>
	 * isNext(1, 3); =&gt; false;<br>
	 * isNext(4, 3); =&gt; false;<br>
	 *
	 * @param number
	 *            the number
	 * @param nextNumber
	 *            the next number
	 * @return true, if the given {@link Integer} nextNumber is next to the given {@link Integer}
	 *         number otherwise false.
	 */
	public static boolean isNext(final Integer number, final Integer nextNumber)
	{
		final int next = number + 1;
		return next == nextNumber;
	}

	/**
	 * Checks if the number is positive.
	 *
	 * @param number
	 *            The number.
	 * @return Returns true if the number is positive otherwise false.
	 */
	public static boolean isPositive(final double number)
	{
		boolean positive = false;
		if (0.0d < number)
		{
			positive = true;
		}
		return positive;
	}

	/**
	 * Checks if the number is positive.
	 *
	 * @param number
	 *            The number.
	 * @return Returns true if the number is positive otherwise false.
	 */
	public static boolean isPositive(final float number)
	{
		boolean positive = false;
		if (0.0d < number)
		{
			positive = true;
		}
		return positive;
	}

	/**
	 * Checks if the number is positive.
	 *
	 * @param number
	 *            The number.
	 * @return Returns true if the number is positive otherwise false.
	 */
	public static boolean isPositive(final int number)
	{
		boolean positive = false;
		if (0 < number)
		{
			positive = true;
		}
		return positive;
	}

	/**
	 * Checks if the number is positive.
	 *
	 * @param number
	 *            The number.
	 * @return Returns true if the number is positive otherwise false.
	 */
	public static boolean isPositive(final long number)
	{
		boolean positive = false;
		if (0 < number)
		{
			positive = true;
		}
		return positive;
	}

	/**
	 * Checks if the given {@link Integer} previousNumber is previous to the given {@link Integer}
	 * number. <br>
	 * Example: <br>
	 * isPrevious(1, 2); =&gt; false;<br>
	 * isPrevious(1, 3); =&gt; false;<br>
	 * isPrevious(4, 3); =&gt; true;<br>
	 *
	 * @param number
	 *            the number
	 * @param previousNumber
	 *            the previous number
	 * @return true, if the given {@link Integer} previousNumber is previous to the given
	 *         {@link Integer} number otherwise false.
	 */
	public static boolean isPrevious(final Integer number, final Integer previousNumber)
	{
		final int previous = number - 1;
		return previous == previousNumber;
	}

	/**
	 * Checks if the given float is a prime number.
	 *
	 * @param n
	 *            The int to check.
	 * @return Returns true if the float is a prime number otherwise false.
	 */
	public static boolean isPrime(final double n)
	{
		double mod = 1;
		for (double i = 2, end = Math.sqrt(n); i <= end && mod != 0; i++)
		{
			mod = n % i;
		}
		return mod != 0;
	}

	/**
	 * Checks if the given float is a prime number.
	 *
	 * @param n
	 *            The int to check.
	 * @return Returns true if the float is a prime number otherwise false.
	 */
	public static boolean isPrime(final float n)
	{
		float mod = 1f;
		for (float i = 2f, end = (float)Math.sqrt(n); i <= end && mod != 0; i++)
		{
			mod = n % i;
		}
		return mod != 0;
	}

	/**
	 * Checks if the given int is a prime number.
	 *
	 * @param n
	 *            The int to check.
	 * @return Returns true if the int is a prime number otherwise false.
	 */
	public static boolean isPrime(final int n)
	{
		int mod = 1;
		for (int i = 2, end = (int)Math.sqrt(n); i <= end && mod != 0; i++)
		{
			mod = n % i;
		}
		return mod != 0;
	}

	/**
	 * Checks if the given long is a prime number.
	 *
	 * @param n
	 *            The int to check.
	 * @return Returns true if the long is a prime number otherwise false.
	 */
	public static boolean isPrime(final long n)
	{
		long mod = 1L;
		for (long i = 2L, end = (long)Math.sqrt(n); i <= end && mod != 0; i++)
		{
			mod = n % i;
		}
		return mod != 0;
	}

	/**
	 * Checks if the number is a prime number.
	 *
	 * @param number
	 *            The number.
	 * @return Returns true if the number is a prime number otherwise false.
	 */
	public static boolean isPrimeNumber(final int number)
	{
		int a = 1;
		for (int count = 2, telos = (int)Math.sqrt(number); count <= telos && a != 0; count++)
		{
			a = number % count;
		}
		return a != 0;
	}

	/**
	 * Prints all prime numbers till the given number.
	 *
	 * @param noOfPrimes
	 *            The limit.
	 * @return an array of all prime numbers till the given number.
	 */
	public static int[] printAllPrimeNumbersTill(final int noOfPrimes)
	{
		final int[] primes = getPrimeNumbers(noOfPrimes);
		for (final int prime : primes)
		{
			System.out.println(prime + " is a prime number");
		}
		return primes;
	}

}
