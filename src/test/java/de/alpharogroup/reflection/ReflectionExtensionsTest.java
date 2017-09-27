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
package de.alpharogroup.reflection;

import org.testng.AssertJUnit;
import org.testng.annotations.Test;

import de.alpharogroup.test.objects.A;
import de.alpharogroup.test.objects.Person;


public class ReflectionExtensionsTest
{

	public void testGetNewInstance()
	{
	}

	@Test
	public void testNewInstanceClassOfT()
		throws InstantiationException, IllegalAccessException, ClassNotFoundException
	{
		final Class<Person> clazz = Person.class;
		final Person actual = ReflectionExtensions.newInstance(clazz);
		AssertJUnit.assertNotNull(actual);
	}

	@Test
	public void testNewInstanceT()
		throws InstantiationException, IllegalAccessException, ClassNotFoundException
	{
		final A a = new A();
		final A actual = ReflectionExtensions.newInstance(a);
		AssertJUnit.assertNotNull(actual);
	}
	
	/**
	 * Test for {@link ReflectionExtensions#setFieldValue(Object, String, Object)}.
	 *
	 * @throws NoSuchFieldException
	 *             is thrown if no such field exists.
	 * @throws SecurityException
	 *             is thrown if a security manager says no.
	 * @throws IllegalArgumentException
	 *             is thrown if an illegal or inappropriate argument has been
	 *             passed to a method.
	 * @throws IllegalAccessException
	 *             is thrown if an illegal on create an instance or access a
	 *             method.
	 */
	@Test
	public void testSetFieldValue() throws NoSuchFieldException, SecurityException, IllegalArgumentException, IllegalAccessException
	{
		String expected;
		String actual;
		Person person = Person.builder()
				.name("Alex")
				.build();
		expected = "Leo";
		ReflectionExtensions.setFieldValue(person, "name", "Leo");
		actual = person.getName();
		AssertJUnit.assertEquals(expected, actual);
	}
	
	/**
	 * Test for {@link ReflectionExtensions#copyFieldValue(Object, Object, String)}.
	 *
	 * @throws NoSuchFieldException
	 *             is thrown if no such field exists.
	 * @throws SecurityException
	 *             is thrown if a security manager says no.
	 * @throws IllegalArgumentException
	 *             is thrown if an illegal or inappropriate argument has been
	 *             passed to a method.
	 * @throws IllegalAccessException
	 *             is thrown if an illegal on create an instance or access a
	 *             method.
	 */
	@Test
	public void testCopyFieldValue() throws NoSuchFieldException, SecurityException, IllegalArgumentException, IllegalAccessException 
	{
		String expected;
		String actual;
		Person alex = Person.builder()
				.name("Alex")
				.build();
		Person nik = Person.builder()
				.name("Nik")
				.build();
		expected = "Alex";
		ReflectionExtensions.copyFieldValue(alex, nik, "name");
		actual = nik.getName();
		AssertJUnit.assertEquals(expected, actual);
		
	}

}
