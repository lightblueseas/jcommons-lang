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

import static org.testng.AssertJUnit.assertEquals;
import static org.testng.AssertJUnit.assertNull;
import static org.testng.AssertJUnit.assertTrue;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Properties;
import java.util.Set;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import de.alpharogroup.classes.inner.OuterClass;
import de.alpharogroup.runtime.compiler.JavaSourceCompiler;
import de.alpharogroup.test.objects.Member;
import de.alpharogroup.test.objects.Person;
import de.alpharogroup.test.objects.PremiumMember;
import de.alpharogroup.test.objects.annotations.TestAnnotation;
import de.alpharogroup.test.objects.annotations.interfaces.AnnotatedInterface;
import de.alpharogroup.test.objects.enums.Brands;

public class ClassExtensionsTest
{

	static class StaticNestedClass
	{
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

	@BeforeMethod
	public void setUp() throws Exception
	{
	}

	@AfterMethod
	public void tearDown() throws Exception
	{
	}


	@Test(enabled = true)
	public void testEqualsByClassName()
	{
		final OuterClass outerClass = new OuterClass();
		final OuterClass.InnerClass innerClass1 = outerClass.new InnerClass();
		final OuterClass.InnerClass innerClass2 = outerClass.new InnerClass();
		final boolean actual = ClassExtensions.equalsByClassName(innerClass1.getClass(),
			innerClass2.getClass());
		assertTrue(actual);

	}

	@Test(enabled = true)
	public void testForName() throws ClassNotFoundException
	{
		final Class<?> expected = this.getClass();
		final String classname = "de.alpharogroup.lang.ClassExtensionsTest";
		final Class<?> actual = ClassExtensions.forName(classname);

		assertEquals(expected, actual);

	}

	@Test(enabled = true)
	public void testGetBaseClass()
	{
		Class<?> expected;
		Class<?> actual;

		expected = null;
		actual = ClassExtensions.getBaseClass(null);
		assertEquals(expected, actual);

		expected = Object.class;
		actual = ClassExtensions.getBaseClass(Object.class);
		assertEquals(expected, actual);

		expected = Person.class;
		actual = ClassExtensions.getBaseClass(PremiumMember.class);
		assertEquals(expected, actual);

		expected = Person.class;
		actual = ClassExtensions.getBaseClass(Member.class);
		assertEquals(expected, actual);

		expected = Person.class;
		actual = ClassExtensions.getBaseClass(Person.class);
		assertEquals(expected, actual);
	}

	/**
	 * Test method for {@link ClassExtensions#getCallingMethodName(StackTraceElement[])}.
	 */
	@Test
	public void testGetCallingMethodName()
	{
		String expected;
		String actual;
		actual = ClassExtensions.getCallingMethodName(Thread.currentThread().getStackTrace());
		expected = "invoke0";
		assertEquals(expected, actual);
	}

	/**
	 * Test method for.
	 *
	 * {@link de.alpharogroup.lang.ClassExtensions#getClassnameWithSuffix(java.lang.Object)}.
	 */
	@Test(enabled = true)
	public void testGetClassnameWithSuffix()
	{
		final String expected = "ClassExtensionsTest.class";
		final String classname = ClassExtensions.getClassnameWithSuffix(this);
		this.result = expected.equals(classname);
		assertTrue("", this.result);
	}

	/**
	 * Test method for {@link de.alpharogroup.lang.ClassExtensions#getClassType(Class)}.
	 *
	 * @throws IllegalAccessException
	 * @throws InstantiationException
	 */
	@Test(enabled = true)
	public void testGetClassType() throws InstantiationException, IllegalAccessException
	{
		ClassType actual = ClassExtensions.getClassType(OuterClass.class);
		ClassType expected = ClassType.DEFAULT;
		assertEquals(expected, actual);

		actual = ClassExtensions.getClassType(OuterClass.InnerClass.class);
		expected = ClassType.MEMBER;
		assertEquals(expected, actual);

		actual = ClassExtensions.getClassType(StaticNestedClass.class);
		expected = ClassType.MEMBER;
		assertEquals(expected, actual);

		actual = ClassExtensions.getClassType(new Runnable()
		{
			@Override
			public void run()
			{
			};
		}.getClass());
		expected = ClassType.ANONYMOUS;
		assertEquals(expected, actual);

		actual = ClassExtensions.getClassType(Brands.class);
		expected = ClassType.ENUM;
		assertEquals(expected, actual);

		actual = ClassExtensions.getClassType(TestAnnotation.class);
		expected = ClassType.ANNOTATION;
		assertEquals(expected, actual);

		final String[] foo = { "foo" };
		actual = ClassExtensions.getClassType(foo.getClass());
		expected = ClassType.ARRAY;
		assertEquals(expected, actual);

		actual = ClassExtensions.getClassType(ArrayList.class);
		expected = ClassType.COLLECTION;
		assertEquals(expected, actual);

		actual = ClassExtensions.getClassType(HashMap.class);
		expected = ClassType.MAP;
		assertEquals(expected, actual);

		actual = ClassExtensions.getClassType(AnnotatedInterface.class);
		expected = ClassType.INTERFACE;
		assertEquals(expected, actual);

		actual = ClassExtensions.getClassType(int.class);
		expected = ClassType.PRIMITIVE;
		assertEquals(expected, actual);

		final JavaSourceCompiler<Runnable> runtimeCompiler = new JavaSourceCompiler<>();
		final String source = "package de.alpharogroup.lang;public final class FooRunnable implements Runnable { public void run() { System.out.println(\"Foo bar\"); } } ";
		final Class<Runnable> clazz = runtimeCompiler.compile("de.alpharogroup.lang", "FooRunnable",
			source);

		actual = ClassExtensions.getClassType(clazz);
		expected = ClassType.DEFAULT;
		assertEquals(expected, actual);
	}

	/**
	 * Test method for {@link ClassExtensions#getCurrentMethodName(StackTraceElement[])}.
	 */
	@Test
	public void testGetCurrentMethodName()
	{
		String expected;
		String actual;
		actual = ClassExtensions.getCurrentMethodName(Thread.currentThread().getStackTrace());
		expected = "testGetCurrentMethodName";
		assertEquals(expected, actual);
	}

	@Test
	public void testGetJarPath()
	{
		String actual = ClassExtensions.getJarPath(Object.class);
		assertTrue(actual.toString().endsWith("/jre/lib/rt.jar"));

		actual = ClassExtensions.getJarPath(ClassExtensions.class);
		assertNull(actual);
		// Get manifest file from zip4j-*.jar
		// actual = ClassExtensions.getJarPath(ZipFile.class);
		// assertNotNull(actual);
		// assertTrue(actual.toString().endsWith("/net/lingala/zip4j/zip4j/1.3.2/zip4j-1.3.2.jar"));
	}

	@Test
	public void testGetManifestURL()
	{
		String actual = ClassExtensions.getManifestUrl(Object.class);
		assertTrue(actual.toString().startsWith("jar:file:"));
		assertTrue(actual.toString().endsWith("/jre/lib/rt.jar!/META-INF/MANIFEST.MF"));

		actual = ClassExtensions.getManifestUrl(ClassExtensions.class);
		assertTrue(actual.toString().startsWith("file:"));
		assertTrue(
			actual.toString().endsWith("/jcommons-lang/target/classes/META-INF/MANIFEST.MF"));
		// Get manifest file from zip4j-*.jar
		// actual = ClassExtensions.getManifestUrl(ZipFile.class);
		// assertNotNull(actual);
		// assertTrue(actual.toString().startsWith("jar:file:"));
		// assertTrue(actual.toString().endsWith("/net/lingala/zip4j/zip4j/1.3.2/zip4j-1.3.2.jar!/META-INF/MANIFEST.MF"));
	}

	@Test
	public void testGetPath()
	{
		String expected = "/java/lang/Object.class";
		String actual = ClassExtensions.getPath(Object.class);
		assertEquals(expected, actual);

		expected = "/java/lang/Class.class";
		actual = ClassExtensions.getPath(Class.class);
		assertEquals(expected, actual);
	}

	@Test(enabled = true)
	public void testGetResource()
	{
		final String propertiesFilename = "de/alpharogroup/lang/resources.properties";
		final URL url = ClassExtensions.getResource(propertiesFilename);
		this.result = url != null;
		assertTrue("", this.result);

	}

	@Test(enabled = true)
	public void testGetResourceAsFileString() throws URISyntaxException
	{
		final String propertiesFilename = "de/alpharogroup/lang/resources.properties";

		final File file = ClassExtensions.getResourceAsFile(propertiesFilename);
		this.result = file != null;
		assertTrue("File should not be null", this.result);
		assertTrue("File should exist.", file.exists());
	}

	/**
	 * Test method for.
	 *
	 * @throws IOException
	 *             Signals that an I/O exception has occurred.
	 *             {@link de.alpharogroup.lang.ClassExtensions#getResourceAsStream(java.lang.String)}
	 *             .
	 */
	@Test(enabled = true)
	public void testGetResourceAsStreamString() throws IOException
	{
		final String propertiesFilename = "de/alpharogroup/lang/resources.properties";

		final InputStream is = ClassExtensions.getResourceAsStream(propertiesFilename);
		this.result = is != null;
		assertTrue("", this.result);
		final Properties prop = new Properties();
		prop.load(is);
		this.result = prop.size() == 3;
	}

	@Test
	public void testGetResourceString()
	{

	}


	@Test(enabled = true)
	public void testGetResourceStringObject()
	{

		final String propertiesFilename = "resources.properties";

		final ClassExtensionsTest obj = new ClassExtensionsTest();
		final URL url = ClassExtensions.getResource(propertiesFilename, obj);

		this.result = url != null;
		assertTrue("", this.result);
	}


	@Test(enabled = true)
	public void testGetRessource()
	{
		final String propertiesFilename = "resources.properties";

		final URL url = ClassExtensions.getResource(ClassExtensionsTest.class, propertiesFilename);

		this.result = url != null;
		assertTrue("", this.result);
	}

	/**
	 * Test method for
	 * {@link de.alpharogroup.lang.ClassExtensions#getResourceAsStream(java.lang.Class, java.lang.String)}
	 * .
	 *
	 * @throws IOException
	 *             Signals that an I/O exception has occurred.
	 */
	@Test(enabled = false)
	public void testGetRessourceAsStream() throws IOException
	{
		final String propertiesFilename = "resources.properties";
		final String pathFromObject = PackageExtensions.getPackagePathWithSlash(this);
		final String path = pathFromObject + propertiesFilename;

		final ClassExtensionsTest obj = new ClassExtensionsTest();
		final InputStream is = ClassExtensions.getResourceAsStream(obj.getClass(), path);
		this.result = is != null;
		assertTrue("InputStream should not be null", this.result);
		final Properties prop = new Properties();
		prop.load(is);
		this.result = prop.size() == 3;
		assertTrue("Size of prop should be 3.", this.result);
	}

	@Test
	public void testGetURL()
	{
		final URL actual = ClassExtensions.getURL(Object.class);
		assertTrue(actual.toString().startsWith("jar:file:"));
		assertTrue(actual.toString().endsWith("/java/lang/Object.class"));
	}

	@Test(enabled = true)
	public void testNormalizeClassName()
	{
		String expected = "Foo$Component";
		final String className = "Foo$Component$2.class";
		String actual = ClassExtensions.normalizeSimpleClassName(className);
		assertEquals(expected, actual);
		final String qualifiedClassname = "x/y/z." + className;
		expected = "x.y.z." + expected;
		actual = ClassExtensions.normalizeQualifiedClassName(qualifiedClassname);
		assertEquals(expected, actual);
	}

	@Test(enabled = true)
	public void testNormalizeSimpleClassName()
	{
		final String expected = "Foo$Component";
		final String className = "Foo$Component$2.class";
		final String actual = ClassExtensions.normalizeSimpleClassName(className);
		assertEquals(expected, actual);

	}

	@Test(enabled = true)
	public void testScanClassesFromPackage() throws Exception, IOException
	{
		final List<File> directories = ClassExtensions
			.getDirectoriesFromResources("de.alpharogroup.lang", true);
		final Set<Class<?>> foundClasses = ClassExtensions
			.scanClassesFromPackage(directories.get(0), "de.alpharogroup.lang");
		assertTrue("", foundClasses.contains(ClassExtensionsTest.class));
		Set<Class<?>> list = null;
		list = ClassExtensions.scanClassNames("de.alpharogroup.lang");
		for (final Class<?> entry : list)
		{
			System.out.println(entry);
		}
	}

}
