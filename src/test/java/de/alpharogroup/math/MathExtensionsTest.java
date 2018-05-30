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

import org.meanbean.factories.ObjectCreationException;
import org.meanbean.test.BeanTestException;
import org.meanbean.test.BeanTester;
import org.testng.AssertJUnit;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

/**
 * The unit test class for the class {@link MathExtensions}.
 */
public class MathExtensionsTest
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
	 * Test method for {@link de.alpharogroup.math.MathExtensions#getPrimeNumbers(int)}.
	 */
	@Test
	public void testGetPrimeNumbers()
	{
		final int[] expected = { this.two, this.three, this.five, this.seven, this.eleven,
				this.thirtteen, this.seventeen, this.nineteen };
		final int[] compare = MathExtensions.getPrimeNumbers(8);
		for (int i = 0; i < compare.length; i++)
		{
			this.result = expected[i] == compare[i];
			AssertJUnit.assertTrue("", this.result);
		}
	}

	/**
	 * Test method for {@link de.alpharogroup.math.MathExtensions#isBetween(int, int, int)}.
	 */
	@Test
	public void testIsBetween()
	{
		final int min = -1;
		final int max = 10;
		int index = min;
		boolean isBetween = MathExtensions.isBetween(min, max, index);
		AssertJUnit.assertFalse(isBetween);
		for (index = min + 1; index < max; index++)
		{
			isBetween = MathExtensions.isBetween(min, max, index);
			AssertJUnit.assertTrue(isBetween);
		}
		index = max;
		isBetween = MathExtensions.isBetween(min, max, index);
		AssertJUnit.assertFalse(isBetween);
	}

	/**
	 * Test method for {@link de.alpharogroup.math.MathExtensions#isInRange(int, int, int)}.
	 */
	@Test
	public void testIsInRange()
	{

		boolean isInRange;

		isInRange = MathExtensions.isInRange(this.thirtteen, this.twentyfour, 12);

		AssertJUnit.assertFalse("", isInRange);

		isInRange = MathExtensions.isInRange(this.thirtteen, this.twentyfour, 13);

		AssertJUnit.assertFalse("", isInRange);

		isInRange = MathExtensions.isInRange(this.thirtteen, this.twentyfour, 14);

		AssertJUnit.assertTrue("", isInRange);

		isInRange = MathExtensions.isInRange(this.thirtteen, this.twentyfour, 23);

		AssertJUnit.assertTrue("", isInRange);

		isInRange = MathExtensions.isInRange(this.thirtteen, this.twentyfour, 24);

		AssertJUnit.assertFalse("", isInRange);

		isInRange = MathExtensions.isInRange(this.thirtteen, this.twentyfour, 25);

		AssertJUnit.assertFalse("", isInRange);
	}

	/**
	 * Test method for {@link de.alpharogroup.math.MathExtensions#isNegative(int)}.
	 */
	@Test
	public void testIsNegative()
	{

		final int negative = -10;
		this.result = MathExtensions.isNegative(negative);
		AssertJUnit.assertTrue("", this.result);

		final int ambisious = 0;
		this.result = MathExtensions.isNegative(ambisious);
		AssertJUnit.assertTrue("", this.result);

		final int positive = this.one;
		this.result = MathExtensions.isNegative(positive);
		AssertJUnit.assertFalse("", this.result);
	}

	/**
	 * Test method for {@link de.alpharogroup.math.MathExtensions#isPositive(int)}.
	 */
	@Test
	public void testIsPositive()
	{
		final int negative = -10;
		this.result = MathExtensions.isPositive(negative);
		AssertJUnit.assertFalse("", this.result);

		final int ambisious = this.zero;
		this.result = MathExtensions.isPositive(ambisious);
		AssertJUnit.assertFalse("", this.result);

		final int positive = this.one;
		this.result = MathExtensions.isPositive(positive);
		AssertJUnit.assertTrue("", this.result);

	}

	/**
	 * Test method for {@link de.alpharogroup.math.MathExtensions#isPrime(int)}.
	 */
	@Test
	public void testIsPrime()
	{

		this.result = MathExtensions.isPrime(this.zero);
		AssertJUnit.assertTrue("", this.result);

		this.result = MathExtensions.isPrime(this.one);
		AssertJUnit.assertTrue("", this.result);

		this.result = MathExtensions.isPrime(this.two);
		AssertJUnit.assertTrue("", this.result);

		this.result = MathExtensions.isPrime(this.three);
		AssertJUnit.assertTrue("", this.result);

		this.result = MathExtensions.isPrime(this.four);
		AssertJUnit.assertFalse("", this.result);

		this.result = MathExtensions.isPrime(this.five);
		AssertJUnit.assertTrue("", this.result);

		this.result = MathExtensions.isPrime(this.six);
		AssertJUnit.assertFalse("", this.result);

		this.result = MathExtensions.isPrime(this.seven);
		AssertJUnit.assertTrue("", this.result);

		this.result = MathExtensions.isPrime(this.eight);
		AssertJUnit.assertFalse("", this.result);

		this.result = MathExtensions.isPrime(this.nine);
		AssertJUnit.assertFalse("", this.result);

		this.result = MathExtensions.isPrime(this.ten);
		AssertJUnit.assertFalse("", this.result);

		this.result = MathExtensions.isPrime(this.eleven);
		AssertJUnit.assertTrue("", this.result);

		this.result = MathExtensions.isPrime(this.twelve);
		AssertJUnit.assertFalse("", this.result);

		this.result = MathExtensions.isPrime(this.thirtteen);
		AssertJUnit.assertTrue("", this.result);

		this.result = MathExtensions.isPrime(this.fourteen);
		AssertJUnit.assertFalse("", this.result);

		this.result = MathExtensions.isPrime(this.fiveteen);
		AssertJUnit.assertFalse("", this.result);

		this.result = MathExtensions.isPrime(this.sixteen);
		AssertJUnit.assertFalse("", this.result);

		this.result = MathExtensions.isPrime(this.seventeen);
		AssertJUnit.assertTrue("", this.result);

		this.result = MathExtensions.isPrime(this.eightteen);
		AssertJUnit.assertFalse("", this.result);

		this.result = MathExtensions.isPrime(this.nineteen);
		AssertJUnit.assertTrue("", this.result);

	}

	/**
	 * Test method for {@link de.alpharogroup.math.MathExtensions#isPrimeNumber(int)}.
	 */
	@Test
	public void testIsPrimeNumber()
	{

		this.result = MathExtensions.isPrimeNumber(this.zero);
		AssertJUnit.assertTrue("", this.result);

		this.result = MathExtensions.isPrimeNumber(this.one);
		AssertJUnit.assertTrue("", this.result);

		this.result = MathExtensions.isPrimeNumber(this.two);
		AssertJUnit.assertTrue("", this.result);

		this.result = MathExtensions.isPrimeNumber(this.three);
		AssertJUnit.assertTrue("", this.result);

		this.result = MathExtensions.isPrimeNumber(this.four);
		AssertJUnit.assertFalse("", this.result);

		this.result = MathExtensions.isPrimeNumber(this.five);
		AssertJUnit.assertTrue("", this.result);

		this.result = MathExtensions.isPrimeNumber(this.six);
		AssertJUnit.assertFalse("", this.result);

		this.result = MathExtensions.isPrimeNumber(this.seven);
		AssertJUnit.assertTrue("", this.result);

		this.result = MathExtensions.isPrimeNumber(this.eight);
		AssertJUnit.assertFalse("", this.result);

		this.result = MathExtensions.isPrimeNumber(this.nine);
		AssertJUnit.assertFalse("", this.result);

		this.result = MathExtensions.isPrimeNumber(this.ten);
		AssertJUnit.assertFalse("", this.result);

		this.result = MathExtensions.isPrimeNumber(this.eleven);
		AssertJUnit.assertTrue("", this.result);

		this.result = MathExtensions.isPrimeNumber(this.twelve);
		AssertJUnit.assertFalse("", this.result);

		this.result = MathExtensions.isPrimeNumber(this.thirtteen);
		AssertJUnit.assertTrue("", this.result);

		this.result = MathExtensions.isPrimeNumber(this.fourteen);
		AssertJUnit.assertFalse("", this.result);

		this.result = MathExtensions.isPrimeNumber(this.fiveteen);
		AssertJUnit.assertFalse("", this.result);

		this.result = MathExtensions.isPrimeNumber(this.sixteen);
		AssertJUnit.assertFalse("", this.result);

		this.result = MathExtensions.isPrimeNumber(this.seventeen);
		AssertJUnit.assertTrue("", this.result);

		this.result = MathExtensions.isPrimeNumber(this.eightteen);
		AssertJUnit.assertFalse("", this.result);

		this.result = MathExtensions.isPrimeNumber(this.nineteen);
		AssertJUnit.assertTrue("", this.result);

	}

	/**
	 * Test method for {@link de.alpharogroup.math.MathExtensions#printAllPrimeNumbersTill(int)}.
	 */
	@Test
	public void testPrintAllPrimeNumbersTill()
	{
		final int[] expected = { this.two, this.three, this.five, this.seven, this.eleven,
				this.thirtteen, this.seventeen, this.nineteen };
		final int[] compare = MathExtensions.printAllPrimeNumbersTill(8);
		for (int i = 0; i < compare.length; i++)
		{
			this.result = expected[i] == compare[i];
			AssertJUnit.assertTrue("", this.result);
		}
	}

	/**
	 * Test method for {@link MathExtensions}
	 */
	@Test(expectedExceptions = { BeanTestException.class, ObjectCreationException.class })
	public void testWithBeanTester()
	{
		final BeanTester beanTester = new BeanTester();
		beanTester.testBean(MathExtensions.class);
	}

}
