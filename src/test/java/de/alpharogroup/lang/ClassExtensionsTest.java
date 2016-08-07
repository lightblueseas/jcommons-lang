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

import org.testng.AssertJUnit;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import de.alpharogroup.classes.inner.OuterClass;
import de.alpharogroup.runtime.compiler.JavaSourceCompiler;
import de.alpharogroup.test.objects.Person;
import de.alpharogroup.test.objects.PremiumMember;
import de.alpharogroup.test.objects.TestAnnotation;
import de.alpharogroup.test.objects.annotations.AnnotatedInterface;
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
		AssertJUnit.assertTrue(actual);

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
		AssertJUnit.assertEquals(expected, actual);

		actual = ClassExtensions.getClassType(OuterClass.InnerClass.class);
		expected = ClassType.MEMBER;
		AssertJUnit.assertEquals(expected, actual);

		actual = ClassExtensions.getClassType(StaticNestedClass.class);
		expected = ClassType.MEMBER;
		AssertJUnit.assertEquals(expected, actual);

		actual = ClassExtensions.getClassType(new Runnable()
		{
			@Override
			public void run()
			{
			};
		}.getClass());
		expected = ClassType.ANONYMOUS;
		AssertJUnit.assertEquals(expected, actual);

		actual = ClassExtensions.getClassType(Brands.class);
		expected = ClassType.ENUM;
		AssertJUnit.assertEquals(expected, actual);

		actual = ClassExtensions.getClassType(TestAnnotation.class);
		expected = ClassType.ANNOTATION;
		AssertJUnit.assertEquals(expected, actual);

		final String[] foo = { "foo" };
		actual = ClassExtensions.getClassType(foo.getClass());
		expected = ClassType.ARRAY;
		AssertJUnit.assertEquals(expected, actual);

		actual = ClassExtensions.getClassType(ArrayList.class);
		expected = ClassType.COLLECTION;
		AssertJUnit.assertEquals(expected, actual);

		actual = ClassExtensions.getClassType(HashMap.class);
		expected = ClassType.MAP;
		AssertJUnit.assertEquals(expected, actual);

		actual = ClassExtensions.getClassType(AnnotatedInterface.class);
		expected = ClassType.INTERFACE;
		AssertJUnit.assertEquals(expected, actual);

		actual = ClassExtensions.getClassType(int.class);
		expected = ClassType.PRIMITIVE;
		AssertJUnit.assertEquals(expected, actual);

		final JavaSourceCompiler<Runnable> runtimeCompiler = new JavaSourceCompiler<>();
		final String source = "public final class FooRunnable implements Runnable { public void run() { System.out.println(\"Foo bar\"); } } ";
		final Class<Runnable> clazz = runtimeCompiler.compile(null, "FooRunnable", source);

		actual = ClassExtensions.getClassType(clazz);
		expected = ClassType.DEFAULT;
		AssertJUnit.assertEquals(expected, actual);
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
		AssertJUnit.assertTrue("", this.result);
	}

	@Test
	public void testGetManifestURL()
	{
		String actual = ClassExtensions.getManifestUrl(Object.class);
		AssertJUnit.assertTrue(actual.toString().startsWith("jar:file:"));
		AssertJUnit.assertTrue(actual.toString().endsWith("/jre/lib/rt.jar!/META-INF/MANIFEST.MF"));

		actual = ClassExtensions.getManifestUrl(ClassExtensions.class);
		AssertJUnit.assertTrue(actual.toString().startsWith("file:"));
		AssertJUnit.assertTrue(
			actual.toString().endsWith("/jcommons-lang/target/classes/META-INF/MANIFEST.MF"));
		// Get manifest file from zip4j-*.jar
		// actual = ClassExtensions.getManifestUrl(ZipFile.class);
		// AssertJUnit.assertNotNull(actual);
		// AssertJUnit.assertTrue(actual.toString().startsWith("jar:file:"));
		// AssertJUnit.assertTrue(actual.toString().endsWith("/net/lingala/zip4j/zip4j/1.3.2/zip4j-1.3.2.jar!/META-INF/MANIFEST.MF"));
	}

	@Test
	public void testGetJarPath()
	{
		String actual = ClassExtensions.getJarPath(Object.class);
		AssertJUnit.assertTrue(actual.toString().endsWith("/jre/lib/rt.jar"));

		actual = ClassExtensions.getJarPath(ClassExtensions.class);
		AssertJUnit.assertNull(actual);
		// Get manifest file from zip4j-*.jar
		// actual = ClassExtensions.getJarPath(ZipFile.class);
		// AssertJUnit.assertNotNull(actual);
		// AssertJUnit.assertTrue(actual.toString().endsWith("/net/lingala/zip4j/zip4j/1.3.2/zip4j-1.3.2.jar"));
	}

	@Test
	public void testGetPath()
	{
		String expected = "/java/lang/Object.class";
		String actual = ClassExtensions.getPath(Object.class);
		AssertJUnit.assertEquals(expected, actual);

		expected = "/java/lang/Class.class";
		actual = ClassExtensions.getPath(Class.class);
		AssertJUnit.assertEquals(expected, actual);
	}

	@Test(enabled = true)
	public void testGetResource()
	{
		final String propertiesFilename = "de/alpharogroup/lang/resources.properties";
		final URL url = ClassExtensions.getResource(propertiesFilename);
		this.result = url != null;
		AssertJUnit.assertTrue("", this.result);

	}

	@Test(enabled = true)
	public void testGetResourceAsFileString() throws URISyntaxException
	{
		final String propertiesFilename = "de/alpharogroup/lang/resources.properties";

		final File file = ClassExtensions.getResourceAsFile(propertiesFilename);
		this.result = file != null;
		AssertJUnit.assertTrue("File should not be null", this.result);
		AssertJUnit.assertTrue("File should exist.", file.exists());
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
		AssertJUnit.assertTrue("", this.result);
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
		AssertJUnit.assertTrue("", this.result);
	}

	@Test(enabled = true)
	public void testGetRessource()
	{
		final String propertiesFilename = "resources.properties";

		final URL url = ClassExtensions.getResource(ClassExtensionsTest.class, propertiesFilename);

		this.result = url != null;
		AssertJUnit.assertTrue("", this.result);
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
		AssertJUnit.assertTrue("InputStream should not be null", this.result);
		final Properties prop = new Properties();
		prop.load(is);
		this.result = prop.size() == 3;
		AssertJUnit.assertTrue("Size of prop should be 3.", this.result);
	}


	@Test(enabled = true)
	public void testGetSuperClass()
	{
		Class<?> actual = ClassExtensions.getBaseClass(PremiumMember.class);
		AssertJUnit.assertEquals(Person.class, actual);
		actual = ClassExtensions.getBaseClass(Object.class);
		AssertJUnit.assertEquals(Object.class, actual);

	}

	@Test
	public void testGetURL()
	{
		final URL actual = ClassExtensions.getURL(Object.class);
		AssertJUnit.assertTrue(actual.toString().startsWith("jar:file:"));
		AssertJUnit.assertTrue(actual.toString().endsWith("/java/lang/Object.class"));
	}

	@Test(enabled = true)
	public void testNormalizeClassName()
	{
		String expected = "Foo$Component";
		final String className = "Foo$Component$2.class";
		String actual = ClassExtensions.normalizeSimpleClassName(className);
		AssertJUnit.assertEquals(expected, actual);
		final String qualifiedClassname = "x/y/z." + className;
		expected = "x.y.z." + expected;
		actual = ClassExtensions.normalizeQualifiedClassName(qualifiedClassname);
		AssertJUnit.assertEquals(expected, actual);
	}

	@Test(enabled = true)
	public void testNormalizeSimpleClassName()
	{
		final String expected = "Foo$Component";
		final String className = "Foo$Component$2.class";
		final String actual = ClassExtensions.normalizeSimpleClassName(className);
		AssertJUnit.assertEquals(expected, actual);

	}

	@Test(enabled = true)
	public void testScanClassesFromPackage() throws Exception, IOException
	{
		final List<File> directories = ClassExtensions
			.getDirectoriesFromResources("de.alpharogroup.lang", true);
		final Set<Class<?>> foundClasses = ClassExtensions
			.scanClassesFromPackage(directories.get(0), "de.alpharogroup.lang");
		AssertJUnit.assertTrue("", foundClasses.contains(ClassExtensionsTest.class));
		Set<Class<?>> list = null;
		list = ClassExtensions.scanClassNames("de.alpharogroup.lang");
		for (final Class<?> entry : list)
		{
			System.out.println(entry);
		}
		System.out.println("#####################################");
		list = ClassExtensions.scanClassNames("org.apache.commons.beanutils");
		for (final Class<?> entry : list)
		{
			System.out.println(entry);
		}
		System.out.println("#####################################");
		list = ClassExtensions.scanClassNames("org.apache.commons.beanutils", true);
		for (final Class<?> entry : list)
		{
			System.out.println(entry);
		}
	}


}
