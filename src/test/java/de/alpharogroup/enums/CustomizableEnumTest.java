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
package de.alpharogroup.enums;

import static org.testng.AssertJUnit.assertEquals;
import static org.testng.AssertJUnit.assertNotSame;
import static org.testng.AssertJUnit.assertTrue;

import org.meanbean.factories.ObjectCreationException;
import org.meanbean.test.BeanTestException;
import org.meanbean.test.BeanTester;
import org.testng.annotations.Test;

import de.alpharogroup.test.objects.enums.Brands;
import de.alpharogroup.test.objects.enums.Gender;
import de.alpharogroup.test.objects.evaluations.EqualsEvaluator;
import de.alpharogroup.test.objects.evaluations.HashcodeEvaluator;
import de.alpharogroup.test.objects.evaluations.ToStringEvaluator;

/**
 * The unit test class for the class {@link CustomizableEnum}.
 */
public class CustomizableEnumTest
{

	/**
	 * Test method for {@link CustomizableEnum)}.
	 */
	@Test
	public void testCustomizableEnum()
	{
		String expected;
		String actual;
		Brands ferrari = Brands.FERRARI;
		CustomizableEnum<Brands, String> cenum = CustomizableEnum.<Brands, String> builder()
			.enumtype(ferrari).build();
		actual = cenum.name();
		expected = ferrari.name();
		assertEquals(actual, expected);
	}

	/**
	 * Test method for {@link CustomizableEnum)}.
	 */
	@Test
	public void testCustomizableEnumNewValue()
	{
		String expected;
		String actual;
		String newEnumValue = "JAGUAR";
		CustomizableEnum<Brands, String> cenum = CustomizableEnum.<Brands, String> builder()
			.value(newEnumValue).build();
		actual = cenum.name();
		expected = newEnumValue;
		assertEquals(actual, expected);
	}

	/**
	 * Test method for {@link CustomizableEnum)}.
	 */
	@Test
	public void testCustomizableEnumNewValueWithEnumValue()
	{
		String expected;
		String actual;
		String newEnumValue = "JAGUAR";
		Brands ferrari = Brands.FERRARI;
		CustomizableEnum<Brands, String> cenum = CustomizableEnum.<Brands, String> builder()
			.value(newEnumValue).enumtype(ferrari).build();

		actual = cenum.name();
		expected = ferrari.name();
		assertEquals(actual, expected);
	}

	/**
	 * Test method for {@link CustomizableEnum)}.
	 */
	@Test(expectedExceptions = IllegalArgumentException.class)
	public void testCustomizableEnumNullCheck()
	{
		CustomizableEnum.<Brands, String> builder().build();
	}

	/**
	 * Test method for {@link CustomizableEnum#equals(Object)}
	 */
	@Test
	public void testEqualsObject()
	{
		Brands ferrari = Brands.FERRARI;
		final CustomizableEnum<Brands, String> expected = CustomizableEnum
			.<Brands, String> builder().enumtype(ferrari).build();
		final CustomizableEnum<Gender, String> actual = CustomizableEnum.<Gender, String> builder()
			.enumtype(Gender.MALE).build();

		assertNotSame(expected, actual);
		final CustomizableEnum<Brands, String> brandEnum = new CustomizableEnum<>(ferrari, null);
		assertEquals(expected, brandEnum);
		assertTrue(
			EqualsEvaluator.evaluateReflexivityNonNullSymmetricAndConsistency(expected, brandEnum));
		assertTrue(EqualsEvaluator.evaluateReflexivityNonNullSymmetricConsistencyAndTransitivity(
			expected, brandEnum, new CustomizableEnum<Brands, String>(Brands.FERRARI, null)));
	}

	/**
	 * Test method for {@link CustomizableEnum#hashCode()}
	 */
	@Test
	public void testHashcode()
	{
		boolean expected;
		boolean actual;
		Brands ferrari = Brands.FERRARI;
		final CustomizableEnum<Brands, String> integerBox = CustomizableEnum
			.<Brands, String> builder().enumtype(ferrari).build();
		CustomizableEnum<Gender, String> stringBox = CustomizableEnum.<Gender, String> builder()
			.enumtype(Gender.MALE).build();
		actual = HashcodeEvaluator.evaluateEquality(integerBox,
			new CustomizableEnum<>(ferrari, null));
		expected = true;
		assertEquals(expected, actual);

		expected = true;
		actual = HashcodeEvaluator.evaluateUnequality(integerBox, stringBox);
		assertEquals(expected, actual);

		actual = HashcodeEvaluator.evaluateConsistency(integerBox);
		expected = true;
		assertEquals(expected, actual);
	}

	/**
	 * Test method for {@link CustomizableEnum#toString()}
	 */
	@Test
	public void testToString()
	{
		boolean expected;
		boolean actual;
		actual = ToStringEvaluator.evaluate(CustomizableEnum.class);
		expected = true;
		assertEquals(expected, actual);

		Brands ferrari = Brands.FERRARI;
		final CustomizableEnum<Brands, String> integerBox = CustomizableEnum
			.<Brands, String> builder().enumtype(ferrari).build();
		actual = ToStringEvaluator.evaluateConsistency(integerBox);
		expected = true;
		assertEquals(expected, actual);
	}

	/**
	 * Test method for {@link CustomizableEnum} with {@link BeanTester}
	 */
	@Test(expectedExceptions = { BeanTestException.class, ObjectCreationException.class,
			NoSuchMethodException.class })
	public void testWithBeanTester()
	{
		final BeanTester beanTester = new BeanTester();
		beanTester.testBean(CustomizableEnum.class);
	}

}
