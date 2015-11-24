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
package de.alpharogroup.math;

/**
 * Utility class for the use numbers.
 * 
 * @version 1.0
 * @author Asterios Raptis
 */
public class MathUtils
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
	 * Checks the given index is between the range from min and max.
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
	public static boolean isNegative(final int number)
	{
		return !isPositive(number);
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
	 * Checks if the given int is a prime number.
	 * 
	 * @param n
	 *            The int to check.
	 * @return Returns true if the int is a prime number otherwise false.
	 */
	static boolean isPrime(final int n)
	{
		int mod = 1;
		for (int i = 2, end = (int)Math.sqrt(n); i <= end && mod != 0; i++)
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
