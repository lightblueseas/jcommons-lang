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
package de.alpharogroup.lang;

import static org.testng.Assert.assertNull;
import static org.testng.AssertJUnit.assertEquals;
import static org.testng.AssertJUnit.assertNotNull;

import java.lang.reflect.Type;
import java.util.List;

import org.meanbean.factories.ObjectCreationException;
import org.meanbean.test.BeanTestException;
import org.meanbean.test.BeanTester;
import org.testng.annotations.Test;

import de.alpharogroup.BaseTestCase;
import de.alpharogroup.test.objects.Person;
import de.alpharogroup.test.objects.generics.GenericDao;
import de.alpharogroup.test.objects.generics.PersonDao;

/**
 * The unit test class for the class {@link TypeArgumentsExtensions}.
 * 
 * @version 1.0
 * @author Asterios Raptis
 */
public class TypeArgumentsExtensionsTest extends BaseTestCase
{
	List<String>[] array;
	/**
	 * Test method for {@link TypeArgumentsExtensions#getFirstTypeArgument(Class, Class)}.
	 */
	@Test(enabled = true)
	public void testGetFirstTypeArgument()
	{
		final Class<Person> expectedClass = Person.class;

		@SuppressWarnings("unchecked")
		final Class<Person> personClass = (Class<Person>)TypeArgumentsExtensions
			.getFirstTypeArgument(GenericDao.class, PersonDao.class);
		assertEquals(expectedClass, personClass);
	}

	/**
	 * Test method for {@link TypeArgumentsExtensions#getTypeArgument(Class, Class, int)}.
	 */
	@SuppressWarnings("unchecked")
	@Test(enabled = true)
	public void testGetTypeArgumentClassClassInt()
	{
		final Class<Integer> expectedClass = Integer.class;
		final Class<Integer> integerClass = (Class<Integer>)TypeArgumentsExtensions
			.getTypeArgument(GenericDao.class, PersonDao.class, 1);
		assertEquals(expectedClass, integerClass);
	}

	/**
	 * Test method for {@link TypeArgumentsExtensions#getTypeArgument(Class, int)}.
	 */
	@SuppressWarnings("unchecked")
	@Test(enabled = true)
	public void testGetTypeArgumentClassInt()
	{
		final Class<Person> expectedPersonClass = Person.class;
		Class<Person> personClass = (Class<Person>)TypeArgumentsExtensions
		.getTypeArgument(PersonDao.class, 0);
		assertEquals(expectedPersonClass, personClass);
	}

	/**
	 * Test method for {@link TypeArgumentsExtensions#getTypeArguments(Class, Class)}.
	 */
	@Test(enabled = true)
	public void testGetTypeArguments()
	{
		final List<Class<?>> typeArguments = TypeArgumentsExtensions
			.getTypeArguments(GenericDao.class, new PersonDao().getClass());
		assertNotNull(typeArguments);
		assertEquals(2, typeArguments.size());
		assertEquals(Person.class, typeArguments.get(0));
		assertEquals(Integer.class, typeArguments.get(1));
	}

	/**
	 * Test method for {@link TypeArgumentsExtensions#getTypeArguments(Class)}.
	 */
	@Test
	public void testGetTypeArgumentsClassOfQextendsT()
	{
		List<Class<?>> typeArguments = TypeArgumentsExtensions.getTypeArguments(PersonDao.class);
		assertNotNull(typeArguments);
		assertEquals(2, typeArguments.size());
		assertEquals(Person.class, typeArguments.get(0));
		assertEquals(Integer.class, typeArguments.get(1));
	}

	/**
	 * Test method for {@link TypeArgumentsExtensions}
	 */
	@Test(expectedExceptions = { BeanTestException.class, ObjectCreationException.class })
	public void testWithBeanTester()
	{
		final BeanTester beanTester = new BeanTester();
		beanTester.testBean(TypeArgumentsExtensions.class);
	}
	
	// ====================================================================== //
	
	/**
	 * Test method for {@link TypeArgumentsExtensions#getClass(Type)}.
	 * @throws SecurityException 
	 * @throws NoSuchFieldException 
	 * @throws NoSuchMethodException 
	 */
	@Test
	public void testGetClassType() throws NoSuchFieldException, SecurityException, NoSuchMethodException
	{
		Type type;		
		type = List.class.getMethod("toArray", Object[].class).getGenericReturnType();
		Class<?> class1 = TypeArgumentsExtensions.getClass(type);
		assertNull(class1);
	}

	/**
	 * Test method for {@link TypeArgumentsExtensions#getFirstTypeArgument(Class)}.
	 */
	@Test
	public void testGetFirstTypeArgumentClassOfQextendsT()
	{
		Class<?> expected;
		Class<?> actual;
		actual = TypeArgumentsExtensions.getFirstTypeArgument(Bar.class);
		expected = String.class;
		assertEquals(expected, actual);
	}

	/**
	 * Test method for {@link TypeArgumentsExtensions#getTypeArgumentsAndParameters(Type)}.
	 * @throws SecurityException 
	 * @throws NoSuchMethodException 
	 */
	@Test
	public void testGetTypeArgumentsAndParameters() throws NoSuchMethodException, SecurityException
	{
		// TODO implement unit test...	
//		ParameterizedType parameterizedType;	
//		
//		Map<Type, Type> typeArgumentsAndParameters = TypeArgumentsExtensions.getTypeArgumentsAndParameters(parameterizedType);
//		System.out.println(type);
	}
	
	static class Foo<T, E> {
		
	}
	
	static class Bar extends Foo<String, Integer> {
		
	}

}
