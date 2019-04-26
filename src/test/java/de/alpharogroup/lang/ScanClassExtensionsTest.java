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

import static org.testng.Assert.assertFalse;
import static org.testng.AssertJUnit.assertEquals;
import static org.testng.AssertJUnit.assertTrue;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.List;
import java.util.Set;

import org.meanbean.factories.ObjectCreationException;
import org.meanbean.test.BeanTestException;
import org.meanbean.test.BeanTester;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import de.alpharogroup.classes.inner.OuterClass;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

/**
 * The unit test class for the class {@link ScanClassExtensions}.
 */
public class ScanClassExtensionsTest
{

	/**
	 * The class {@link Bla} for unit tests purposes.
	 */
	class Bla implements Foo
	{

		@Override
		public String bar(String string)
		{
			return string + "!!!";
		}

	}

	/**
	 * The interface {@link Foo} for unit tests purposes.
	 */
	interface Foo
	{
		String bar(String string);
	}

	/**
	 * The class {@link InvocationHandlerHandler} for unit tests purposes.
	 */
	static class InvocationHandlerHandler<T> implements InvocationHandler
	{
		private final T original;

		public InvocationHandlerHandler(T original)
		{
			this.original = original;
		}

		@Override
		public Object invoke(Object proxy, Method method, Object[] args)
			throws IllegalAccessException, IllegalArgumentException, InvocationTargetException
		{
			Object object = method.invoke(original, args);
			return object;
		}
	}

	/**
	 * The class {@link MethodInterceptorHandler} for unit tests purposes.
	 */
	static class MethodInterceptorHandler<T> implements MethodInterceptor
	{
		private final T origin;

		public MethodInterceptorHandler(T origin)
		{
			this.origin = origin;
		}

		@Override
		public Object intercept(Object o, Method method, Object[] args, MethodProxy methodProxy)
			throws Throwable
		{
			Object object = method.invoke(origin, args);
			return object;
		}
	}

	/**
	 * The class {@link StaticNestedClass} for unit test purposes.
	 */
	static class StaticNestedClass
	{

		/**
		 * Static nested class method for unit test purposes.
		 */
		public static void staticNestedClassMethod()
		{
			final Runnable runnable = new Runnable()
			{
				@Override
				public void run()
				{
				};
			};
			System.out.println(runnable.getClass().getName());
			System.out.println("Is anonymous class:" + runnable.getClass().isAnonymousClass());
			System.out.println("Enclosing class:" + runnable.getClass().getEnclosingClass());
			System.out.println("Canonical Name:" + runnable.getClass().getCanonicalName());
			System.out.println("toString:" + runnable.getClass().toString());
			System.out.println(StaticNestedClass.class.getName());
			System.out.println("getEnclosingMethod():" + runnable.getClass().getEnclosingMethod());
			// Object[] objects = { runnable };
		}
	}

	/** The result. */
	private boolean result;

	/**
	 * Sets up method will be invoked before every unit test method
	 *
	 * @throws Exception
	 *             is thrown if an exception occurs
	 */
	@BeforeMethod
	public void setUp() throws Exception
	{
	}

	/**
	 * Tear down method will be invoked after every unit test method
	 *
	 * @throws Exception
	 *             is thrown if an exception occurs
	 */
	@AfterMethod
	public void tearDown() throws Exception
	{
	}

	/**
	 * Test method for {@link ScanClassExtensions#equalsByClassName(Class, Class)}
	 */
	@Test(enabled = true)
	public void testEqualsByClassName()
	{
		final OuterClass outerClass = new OuterClass();
		final OuterClass.InnerClass innerClass1 = outerClass.new InnerClass();
		final OuterClass.InnerClass innerClass2 = outerClass.new InnerClass();
		boolean actual = ScanClassExtensions.equalsByClassName(innerClass1.getClass(),
			innerClass2.getClass());
		assertTrue(actual);
		actual = ScanClassExtensions.equalsByClassName(outerClass.getClass(),
			innerClass2.getClass());
		assertFalse(actual);
	}

	/**
	 * Test method for {@link ScanClassExtensions#normalizeSimpleClassName(String)}
	 */
	@Test(enabled = true)
	public void testNormalizeClassName()
	{
		String expected = "Foo$Component";
		final String className = "Foo$Component$2.class";
		String actual = ScanClassExtensions.normalizeSimpleClassName(className);
		assertEquals(expected, actual);
		final String qualifiedClassname = "x/y/z." + className;
		expected = "x.y.z." + expected;
		actual = ScanClassExtensions.normalizeQualifiedClassName(qualifiedClassname);
		assertEquals(expected, actual);
	}

	/**
	 * Test method for {@link ScanClassExtensions#normalizeSimpleClassName(String)}
	 */
	@Test(enabled = true)
	public void testNormalizeSimpleClassName()
	{
		final String expected = "Foo$Component";
		final String className = "Foo$Component$2.class";
		final String actual = ScanClassExtensions.normalizeSimpleClassName(className);
		assertEquals(expected, actual);
	}

	/**
	 * Test method for {@link ScanClassExtensions#getDirectoriesFromResources(String, boolean)}
	 *
	 * @throws Exception
	 *             the exception
	 * @throws IOException
	 *             Signals that an I/O exception has occurred.
	 */
	@Test(enabled = true)
	public void testScanClassesFromPackage() throws Exception, IOException
	{
		final List<File> directories = ClassExtensions
			.getDirectoriesFromResources("de.alpharogroup.lang", true);
		final Set<Class<?>> foundClasses = ScanClassExtensions
			.scanClassesFromPackage(directories.get(0), "de.alpharogroup.lang");
		assertTrue(foundClasses.contains(ScanClassExtensionsTest.class));
		Set<Class<?>> list = null;
		list = ScanClassExtensions.scanClassNames("de.alpharogroup.lang");
		for (final Class<?> entry : list)
		{
			System.out.println(entry);
		}
	}

	/**
	 * Test method for {@link ScanClassExtensions}
	 */
	@Test(expectedExceptions = { BeanTestException.class, ObjectCreationException.class })
	public void testWithBeanTester()
	{
		final BeanTester beanTester = new BeanTester();
		beanTester.testBean(ScanClassExtensions.class);
	}

}
