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

import org.testng.AssertJUnit;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

/**
 * Test class for the class BankaccountUtils.
 * 
 * @version 1.0
 * @author Asterios Raptis
 */
public class BankaccountUtilsTest
{

	/** The valide iban for tests. */
	private String valideIban;

	/** The wrong iban for tests. */
	private String invalideIban;

	/** The boolean for the result from the test. */
	private boolean result;

	/** The locale for Austria. */
	private String atLocale;

	/** The locale for Germany. */
	private String deLocale;

	/**
	 * Sets up method will be invoked before every unit test method in this class.
	 *
	 * @throws Exception
	 *             the exception
	 */
	@BeforeMethod
	protected void setUp() throws Exception
	{
		this.valideIban = "AT782032017000303657";
		this.invalideIban = "AT82032017000303657";
		this.atLocale = "AT";
		this.deLocale = "DE";
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
	 * Test method for {@link de.alpharogroup.iban.BankaccountUtils#isIbanNumber(java.lang.String)}.
	 * 
	 * @throws Exception
	 *             is thrown if an error occurs when checking the given ibanNumber
	 */
	@Test
	public void testIsIbanNumber() throws Exception
	{
		this.result = BankaccountUtils.isIbanNumber(this.valideIban);
		AssertJUnit.assertTrue("", this.result);
		this.result = BankaccountUtils.isIbanNumber(this.invalideIban);
		AssertJUnit.assertFalse("", this.result);

	}

	/**
	 * Test method for
	 * {@link de.alpharogroup.iban.BankaccountUtils#replaceCharsWithNumbers(java.lang.String)} .
	 * 
	 * @throws Exception
	 *             is thrown if an error occurs when tryin to replace the char with numbers
	 */
	@Test
	public void testReplaceCharsWithNumbers() throws Exception
	{
		String expected = "1029";
		String compare = BankaccountUtils.replaceCharsWithNumbers(this.atLocale);
		this.result = expected.equals(compare);
		AssertJUnit.assertTrue("", this.result);
		// -------------------
		expected = "1314";
		compare = BankaccountUtils.replaceCharsWithNumbers(this.deLocale);
		this.result = expected.equals(compare);
		AssertJUnit.assertTrue("", this.result);

	}

}
