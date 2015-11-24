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

import org.testng.AssertJUnit;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

/**
 * Test class for the class MathUtils.
 * 
 * @version 1.0
 * @author Asterios Raptis
 */
public class MathUtilsTest
{

	boolean result;

	int zero = 0;

	int one = 1;

	// few prime numbers.

	int two = 2;

	int three = 3;

	int five = 5;

	int seven = 7;

	int eleven = 11;

	int thirtteen = 13;

	int seventeen = 17;

	int nineteen = 19;

	int twentythree = 23;

	// few that are no prime numbers.
	int four = 4;

	int six = 6;

	int eight = 8;

	int nine = 9;

	int ten = 10;

	int twelve = 12;

	int fourteen = 14;

	int fiveteen = 15;

	int sixteen = 16;

	int eightteen = 18;

	int twentyfour = 24;

	int twentyfive = 25;

	/**
	 * Sets up method will be invoked before every unit test method in this class.
	 *
	 * @throws Exception
	 *             the exception
	 */
	@BeforeMethod
	protected void setUp() throws Exception
	{
		this.result = false;
	}

	/**
	 * Tear down method will be invoked after every unit test method in this class.
	 *
	 * @throws Exception
	 *             the exception
	 */
	@AfterMethod
	protected void tearDown() throws Exception
	{
	}

	/**
	 * Test method for {@link de.alpharogroup.math.MathUtils#getPrimeNumbers(int)}.
	 */
	@Test
	public void testGetPrimeNumbers()
	{
		final int[] expected = { this.two, this.three, this.five, this.seven, this.eleven,
				this.thirtteen, this.seventeen, this.nineteen };
		final int[] compare = MathUtils.getPrimeNumbers(8);
		for (int i = 0; i < compare.length; i++)
		{
			this.result = expected[i] == compare[i];
			AssertJUnit.assertTrue("", this.result);
		}
	}

	/**
	 * Test method for {@link de.alpharogroup.math.MathUtils#isBetween(int, int, int)}.
	 */
	@Test
	public void testIsBetween()
	{
		final int min = -1;
		final int max = 10;
		int index = min;
		boolean isBetween = MathUtils.isBetween(min, max, index);
		AssertJUnit.assertFalse(isBetween);
		for (index = min + 1; index < max; index++)
		{
			isBetween = MathUtils.isBetween(min, max, index);
			AssertJUnit.assertTrue(isBetween);
		}
		index = max;
		isBetween = MathUtils.isBetween(min, max, index);
		AssertJUnit.assertFalse(isBetween);
	}

	/**
	 * Test method for {@link de.alpharogroup.math.MathUtils#isInRange(int, int, int)}.
	 */
	@Test
	public void testIsInRange()
	{

		boolean isInRange;

		isInRange = MathUtils.isInRange(this.thirtteen, this.twentyfour, 12);

		AssertJUnit.assertFalse("", isInRange);

		isInRange = MathUtils.isInRange(this.thirtteen, this.twentyfour, 13);

		AssertJUnit.assertFalse("", isInRange);

		isInRange = MathUtils.isInRange(this.thirtteen, this.twentyfour, 14);

		AssertJUnit.assertTrue("", isInRange);

		isInRange = MathUtils.isInRange(this.thirtteen, this.twentyfour, 23);

		AssertJUnit.assertTrue("", isInRange);

		isInRange = MathUtils.isInRange(this.thirtteen, this.twentyfour, 24);

		AssertJUnit.assertFalse("", isInRange);

		isInRange = MathUtils.isInRange(this.thirtteen, this.twentyfour, 25);

		AssertJUnit.assertFalse("", isInRange);
	}

	/**
	 * Test method for {@link de.alpharogroup.math.MathUtils#isNegative(int)}.
	 */
	@Test
	public void testIsNegative()
	{

		final int negative = -10;
		this.result = MathUtils.isNegative(negative);
		AssertJUnit.assertTrue("", this.result);

		final int ambisious = 0;
		this.result = MathUtils.isNegative(ambisious);
		AssertJUnit.assertTrue("", this.result);

		final int positive = this.one;
		this.result = MathUtils.isNegative(positive);
		AssertJUnit.assertFalse("", this.result);
	}

	/**
	 * Test method for {@link de.alpharogroup.math.MathUtils#isPositive(int)}.
	 */
	@Test
	public void testIsPositive()
	{
		final int negative = -10;
		this.result = MathUtils.isPositive(negative);
		AssertJUnit.assertFalse("", this.result);

		final int ambisious = this.zero;
		this.result = MathUtils.isPositive(ambisious);
		AssertJUnit.assertFalse("", this.result);

		final int positive = this.one;
		this.result = MathUtils.isPositive(positive);
		AssertJUnit.assertTrue("", this.result);

	}

	/**
	 * Test method for {@link de.alpharogroup.math.MathUtils#isPrime(int)}.
	 */
	@Test
	public void testIsPrime()
	{

		this.result = MathUtils.isPrime(this.zero);
		AssertJUnit.assertTrue("", this.result);

		this.result = MathUtils.isPrime(this.one);
		AssertJUnit.assertTrue("", this.result);

		this.result = MathUtils.isPrime(this.two);
		AssertJUnit.assertTrue("", this.result);

		this.result = MathUtils.isPrime(this.three);
		AssertJUnit.assertTrue("", this.result);

		this.result = MathUtils.isPrime(this.four);
		AssertJUnit.assertFalse("", this.result);

		this.result = MathUtils.isPrime(this.five);
		AssertJUnit.assertTrue("", this.result);

		this.result = MathUtils.isPrime(this.six);
		AssertJUnit.assertFalse("", this.result);

		this.result = MathUtils.isPrime(this.seven);
		AssertJUnit.assertTrue("", this.result);

		this.result = MathUtils.isPrime(this.eight);
		AssertJUnit.assertFalse("", this.result);

		this.result = MathUtils.isPrime(this.nine);
		AssertJUnit.assertFalse("", this.result);

		this.result = MathUtils.isPrime(this.ten);
		AssertJUnit.assertFalse("", this.result);

		this.result = MathUtils.isPrime(this.eleven);
		AssertJUnit.assertTrue("", this.result);

		this.result = MathUtils.isPrime(this.twelve);
		AssertJUnit.assertFalse("", this.result);

		this.result = MathUtils.isPrime(this.thirtteen);
		AssertJUnit.assertTrue("", this.result);

		this.result = MathUtils.isPrime(this.fourteen);
		AssertJUnit.assertFalse("", this.result);

		this.result = MathUtils.isPrime(this.fiveteen);
		AssertJUnit.assertFalse("", this.result);

		this.result = MathUtils.isPrime(this.sixteen);
		AssertJUnit.assertFalse("", this.result);

		this.result = MathUtils.isPrime(this.seventeen);
		AssertJUnit.assertTrue("", this.result);

		this.result = MathUtils.isPrime(this.eightteen);
		AssertJUnit.assertFalse("", this.result);

		this.result = MathUtils.isPrime(this.nineteen);
		AssertJUnit.assertTrue("", this.result);

	}

	/**
	 * Test method for {@link de.alpharogroup.math.MathUtils#isPrimeNumber(int)}.
	 */
	@Test
	public void testIsPrimeNumber()
	{

		this.result = MathUtils.isPrimeNumber(this.zero);
		AssertJUnit.assertTrue("", this.result);

		this.result = MathUtils.isPrimeNumber(this.one);
		AssertJUnit.assertTrue("", this.result);

		this.result = MathUtils.isPrimeNumber(this.two);
		AssertJUnit.assertTrue("", this.result);

		this.result = MathUtils.isPrimeNumber(this.three);
		AssertJUnit.assertTrue("", this.result);

		this.result = MathUtils.isPrimeNumber(this.four);
		AssertJUnit.assertFalse("", this.result);

		this.result = MathUtils.isPrimeNumber(this.five);
		AssertJUnit.assertTrue("", this.result);

		this.result = MathUtils.isPrimeNumber(this.six);
		AssertJUnit.assertFalse("", this.result);

		this.result = MathUtils.isPrimeNumber(this.seven);
		AssertJUnit.assertTrue("", this.result);

		this.result = MathUtils.isPrimeNumber(this.eight);
		AssertJUnit.assertFalse("", this.result);

		this.result = MathUtils.isPrimeNumber(this.nine);
		AssertJUnit.assertFalse("", this.result);

		this.result = MathUtils.isPrimeNumber(this.ten);
		AssertJUnit.assertFalse("", this.result);

		this.result = MathUtils.isPrimeNumber(this.eleven);
		AssertJUnit.assertTrue("", this.result);

		this.result = MathUtils.isPrimeNumber(this.twelve);
		AssertJUnit.assertFalse("", this.result);

		this.result = MathUtils.isPrimeNumber(this.thirtteen);
		AssertJUnit.assertTrue("", this.result);

		this.result = MathUtils.isPrimeNumber(this.fourteen);
		AssertJUnit.assertFalse("", this.result);

		this.result = MathUtils.isPrimeNumber(this.fiveteen);
		AssertJUnit.assertFalse("", this.result);

		this.result = MathUtils.isPrimeNumber(this.sixteen);
		AssertJUnit.assertFalse("", this.result);

		this.result = MathUtils.isPrimeNumber(this.seventeen);
		AssertJUnit.assertTrue("", this.result);

		this.result = MathUtils.isPrimeNumber(this.eightteen);
		AssertJUnit.assertFalse("", this.result);

		this.result = MathUtils.isPrimeNumber(this.nineteen);
		AssertJUnit.assertTrue("", this.result);

	}

	/**
	 * Test method for {@link de.alpharogroup.math.MathUtils#printAllPrimeNumbersTill(int)}.
	 */
	@Test
	public void testPrintAllPrimeNumbersTill()
	{
		final int[] expected = { this.two, this.three, this.five, this.seven, this.eleven,
				this.thirtteen, this.seventeen, this.nineteen };
		final int[] compare = MathUtils.printAllPrimeNumbersTill(8);
		for (int i = 0; i < compare.length; i++)
		{
			this.result = expected[i] == compare[i];
			AssertJUnit.assertTrue("", this.result);
		}
	}

}
