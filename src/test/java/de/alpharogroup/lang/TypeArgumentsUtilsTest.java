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
package de.alpharogroup.lang;

import java.util.List;

import org.testng.AssertJUnit;
import org.testng.annotations.Test;

import de.alpharogroup.test.objects.Person;
import de.alpharogroup.test.objects.generics.GenericDao;
import de.alpharogroup.test.objects.generics.PersonDao;


public class TypeArgumentsUtilsTest
{

	@Test(enabled = true)
	public void testGetFirstTypeArgument()
	{
		final Class<Person> expectedClass = Person.class;

		@SuppressWarnings("unchecked")
		final Class<Person> personClass = (Class<Person>)TypeArgumentsUtils.getFirstTypeArgument(
			GenericDao.class, PersonDao.class);
		AssertJUnit.assertEquals(expectedClass, personClass);
	}

	@Test(enabled = true)
	public void testGetTypeArgument()
	{
		final Class<Integer> expectedClass = Integer.class;
		@SuppressWarnings("unchecked")
		final Class<Integer> integerClass = (Class<Integer>)TypeArgumentsUtils.getTypeArgument(
			GenericDao.class, PersonDao.class, 1);
		AssertJUnit.assertEquals(expectedClass, integerClass);
	}

	@Test(enabled = true)
	public void testGetTypeArguments()
	{
		final List<Class<?>> typeArguments = TypeArgumentsUtils.getTypeArguments(GenericDao.class,
			new PersonDao().getClass());
		AssertJUnit.assertNotNull(typeArguments);
		AssertJUnit.assertEquals(2, typeArguments.size());
		AssertJUnit.assertEquals(Person.class, typeArguments.get(0));
		AssertJUnit.assertEquals(Integer.class, typeArguments.get(1));
	}

}
