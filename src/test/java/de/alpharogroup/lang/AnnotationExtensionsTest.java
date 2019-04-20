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

import static org.testng.Assert.assertNotNull;
import static org.testng.AssertJUnit.assertEquals;
import static org.testng.AssertJUnit.assertFalse;
import static org.testng.AssertJUnit.assertTrue;

import java.io.File;
import java.io.IOException;
import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.net.URISyntaxException;
import java.util.HashSet;
import java.util.Set;

import org.meanbean.factories.ObjectCreationException;
import org.meanbean.test.BeanTestException;
import org.meanbean.test.BeanTester;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import de.alpharogroup.collections.set.SetFactory;
import de.alpharogroup.test.objects.annotations.AnotherTestAnnotation;
import de.alpharogroup.test.objects.annotations.TestAnnotation;
import de.alpharogroup.test.objects.annotations.TestFieldAnnotation;
import de.alpharogroup.test.objects.annotations.TestMethodAnnotation;
import de.alpharogroup.test.objects.annotations.TestTypeAnnotation;
import de.alpharogroup.test.objects.annotations.classes.AnnotatedClass;
import de.alpharogroup.test.objects.annotations.classes.AnnotatedTestClass;
import de.alpharogroup.test.objects.annotations.classes.ClassExtendsAnnotatedInterface;
import de.alpharogroup.test.objects.annotations.classes.SubAnnotatedClass;
import de.alpharogroup.test.objects.annotations.foobar.AnotherAnnotatedClass;
import de.alpharogroup.test.objects.annotations.foobar.OtherAnnotatedClass;
import de.alpharogroup.test.objects.annotations.foobar.SomeClass;
import de.alpharogroup.test.objects.annotations.interfaces.AnnotatedInterface;

/**
 * The unit test class for the class {@link AnnotationExtensions}.
 *
 * @author Asterios Raptis
 * @version 1.0
 */
@TestAnnotation
public class AnnotationExtensionsTest
{

	/**
	 * Sets the up method.
	 *
	 * @throws Exception
	 *             the exception
	 */
	@BeforeMethod
	public void setUp() throws Exception
	{
	}

	/**
	 * Tear down method.
	 *
	 * @throws Exception
	 *             the exception
	 */
	@AfterMethod
	public void tearDown() throws Exception
	{
	}

	/**
	 * Test method for {@link AnnotationExtensions#getAllAnnotatedClasses(String, Class)}
	 *
	 * @throws ClassNotFoundException
	 *             occurs if a given class cannot be located by the specified class loader
	 * @throws IOException
	 *             Signals that an I/O exception has occurred.
	 *
	 * @throws URISyntaxException
	 *             is thrown if a string could not be parsed as a URI reference.
	 */
	@Test(enabled = true)
	public void testGetAllAnnotatedClasses()
		throws ClassNotFoundException, IOException, URISyntaxException
	{
		String packagePath = "de.alpharogroup.lang";
		Class<? extends Annotation> annotationClass = TestAnnotation.class;
		final Set<Class<?>> classes = AnnotationExtensions.getAllAnnotatedClasses(packagePath,
			annotationClass);
		assertTrue("Size should be 1 but is " + classes.size() + ".", classes.size() == 1);
		assertTrue("Set should contain class object AnnotationExtensionsTest.class.",
			classes.contains(AnnotationExtensionsTest.class));
	}

	/**
	 * Test method for {@link AnnotationExtensions#getAllAnnotatedClassesFromSet(String, Set)}.
	 *
	 * @throws ClassNotFoundException
	 *             occurs if a given class cannot be located by the specified class loader
	 * @throws IOException
	 *             Signals that an I/O exception has occurred.
	 *
	 * @throws URISyntaxException
	 *             is thrown if a string could not be parsed as a URI reference.
	 */
	@Test(enabled = true)
	public void testGetAllAnnotatedClassesFromSet()
		throws ClassNotFoundException, IOException, URISyntaxException
	{
		String packagePath = "de.alpharogroup.lang";
		Set<Class<? extends Annotation>> annotationClasses = SetFactory
			.newHashSet(TestAnnotation.class);
		final Set<Class<?>> classes = AnnotationExtensions
			.getAllAnnotatedClassesFromSet(packagePath, annotationClasses);
		assertTrue("Size should be 1 but is " + classes.size() + ".", classes.size() == 1);
		assertTrue("Set should contain class object AnnotationExtensionsTest.class.",
			classes.contains(AnnotationExtensionsTest.class));
	}

	/**
	 * Test get all annotated classes set.
	 *
	 * @throws ClassNotFoundException
	 *             occurs if a given class cannot be located by the specified class loader
	 * @throws IOException
	 *             Signals that an I/O exception has occurred.
	 *
	 * @throws URISyntaxException
	 *             is thrown if a string could not be parsed as a URI reference.
	 */
	@Test(enabled = false) // TODO fix...
	public void testGetAllAnnotatedClassesSet()
		throws ClassNotFoundException, IOException, URISyntaxException
	{
		final Set<Class<? extends Annotation>> allAnotations = new HashSet<>();
		allAnotations.add(TestAnnotation.class);
		allAnotations.add(AnotherTestAnnotation.class);
		final Set<Class<?>> classes = AnnotationExtensions
			.getAllAnnotatedClassesFromSet("de.alpharogroup.lang", allAnotations);

		assertTrue("Size should be 2 but is " + classes.size() + ".", classes.size() == 2);
		assertTrue("Set should contain class object AnnotationExtensionsTest.class.",
			classes.contains(AnnotationExtensionsTest.class));
	}

	/**
	 * Test method for {@link AnnotationExtensions#getAllClasses(String)}.
	 *
	 * @throws ClassNotFoundException
	 *             occurs if a given class cannot be located by the specified class loader
	 * @throws IOException
	 *             Signals that an I/O exception has occurred.
	 * @throws URISyntaxException
	 *             is thrown if a string could not be parsed as a URI reference.
	 */
	@Test(enabled = true)
	public void testGetAllClassesString()
		throws ClassNotFoundException, IOException, URISyntaxException
	{
		String packagePath = "de.alpharogroup.lang.thread";
		Set<Class<?>> allClasses = AnnotationExtensions.getAllClasses(packagePath);
		assertNotNull(allClasses);
		assertTrue(allClasses.size() == 8);
	}

	/**
	 * Test get all classes string set.
	 *
	 * @throws ClassNotFoundException
	 *             occurs if a given class cannot be located by the specified class loader
	 * @throws IOException
	 *             Signals that an I/O exception has occurred.
	 *
	 * @throws URISyntaxException
	 *             is thrown if a string could not be parsed as a URI reference.
	 */
	@Test(enabled = false) // TODO fix...
	public void testGetAllClassesStringSet()
		throws ClassNotFoundException, IOException, URISyntaxException
	{
		final Set<Class<? extends Annotation>> allAnotations = new HashSet<>();
		allAnotations.add(TestAnnotation.class);
		allAnotations.add(AnotherTestAnnotation.class);

		final Set<Class<?>> classes = AnnotationExtensions.getAllClasses("de.alpharogroup.lang",
			allAnotations);
		assertTrue("Size should be 2 but is " + classes.size() + ".", classes.size() == 2);
		assertTrue("Set should contain class object AnnotationExtensionsTest.class.",
			classes.contains(AnnotationExtensionsTest.class));
	}

	/**
	 * Test method for {@link AnnotationExtensions#getAllClasses(String, Set)}.
	 */
	@Test(enabled = false)
	public void testGetAllClassesStringSetOfClassOfQextendsAnnotation()
	{
		// TODO implement unit test...
	}

	/**
	 * Test method for {@link AnnotationExtensions#getAnnotation(Class, Class)}.
	 */
	@Test(enabled = false)
	public void testGetAnnotation()
	{
		// TODO implement unit test...
	}

	/**
	 * Test method for {@link AnnotationExtensions#isAnnotationPresentInSuperClasses(Class, Class)}
	 */
	@Test(enabled = true)
	public void testIsAnnotationPresentInSuperClasses()
	{
		boolean expected;
		boolean actual;

		actual = AnnotationExtensions.isAnnotationPresentInSuperClasses(SubAnnotatedClass.class,
			TestAnnotation.class);
		expected = true;
		assertEquals("If an Annotation is present in the super class then it should return true!",
			expected, actual);
	}

	/**
	 * Test method for
	 * {@link AnnotationExtensions#isAnnotationPresentInSuperClassesOrInterfaces(Class, Class)}.
	 */
	@Test(enabled = true)
	public void testIsAnnotationPresentInSuperClassesOrInterfaces()
	{
		boolean expected;
		boolean actual;

		actual = AnnotationExtensions.isAnnotationPresentInSuperClassesOrInterfaces(
			SubAnnotatedClass.class, TestAnnotation.class);
		expected = true;
		assertEquals("If an Annotation is present in the super class then it should return true!",
			expected, actual);

		actual = AnnotationExtensions.isAnnotationPresentInSuperClassesOrInterfaces(
			ClassExtendsAnnotatedInterface.class, TestAnnotation.class);
		expected = true;
		assertEquals("If an Annotation is present in the super class then it should return true!",
			expected, actual);
	}

	/**
	 * Test method for {@link AnnotationExtensions#scanForAnnotatedClasses(File, String, Class)}
	 *
	 * @throws ClassNotFoundException
	 *             occurs if a given class cannot be located by the specified class loader
	 * @throws IOException
	 *             Signals that an I/O exception has occurred.
	 *
	 * @throws URISyntaxException
	 *             is thrown if a string could not be parsed as a URI reference. occurs by creation
	 *             of the file with an uri.
	 */
	@Test(enabled = false) // TODO set to true
	public void testScanForAnnotatedClasses()
		throws ClassNotFoundException, IOException, URISyntaxException
	{
		File directory = ClassExtensions.getResourceAsFile("AnnotatedClass.class",
			new AnnotatedClass());
		directory = directory.getParentFile();
		final Set<Class<?>> classes = AnnotationExtensions.scanForAnnotatedClasses(directory,
			"de.alpharogroup.test.objects.annotations", TestAnnotation.class);
		assertTrue("Size should be 3  but is " + classes.size() + ".", classes.size() == 3);
		assertTrue("Set should contain class object AnnotatedInterface.class.",
			classes.contains(AnnotatedInterface.class));
		assertTrue("Set should contain class object AnnotatedClass.class.",
			classes.contains(AnnotatedClass.class));
		assertTrue("Set should contain class object OtherAnnotatedClass.class.",
			classes.contains(OtherAnnotatedClass.class));
		assertFalse("Set should not contain class object SomeClass.class.",
			classes.contains(SomeClass.class));
	}

	/**
	 * Test method for
	 * {@link AnnotationExtensions#scanForAnnotatedClassesFromSet(File, String, Set)}.
	 *
	 * @throws ClassNotFoundException
	 *             occurs if a given class cannot be located by the specified class loader
	 * @throws IOException
	 *             Signals that an I/O exception has occurred.
	 *
	 * @throws URISyntaxException
	 *             is thrown if a string could not be parsed as a URI reference. occurs by creation
	 *             of the file with an uri.
	 */
	@Test(enabled = false) // TODO set to true
	public void testScanForAnnotatedClassesFromSet()
		throws ClassNotFoundException, IOException, URISyntaxException
	{
		File directory = ClassExtensions.getResourceAsFile("AnnotatedClass.class",
			new AnnotatedClass());
		directory = directory.getParentFile();
		final Set<Class<? extends Annotation>> allAnotations = new HashSet<>();
		allAnotations.add(TestAnnotation.class);
		allAnotations.add(AnotherTestAnnotation.class);
		final Set<Class<?>> classes = AnnotationExtensions.scanForAnnotatedClassesFromSet(directory,
			"de.alpharogroup.test.objects.annotations", allAnotations);

		assertTrue("Size should be 4  but is " + classes.size() + ".", classes.size() == 4);
		assertTrue("Set should contain class object OtherAnnotatedClass.class.",
			classes.contains(OtherAnnotatedClass.class));
		assertTrue("Set should contain class object AnnotatedInterface.class.",
			classes.contains(AnnotatedInterface.class));
		assertTrue("Set should contain class object AnnotatedClass.class.",
			classes.contains(AnnotatedClass.class));
		assertTrue("Set should contain class object AnotherAnnotatedClass.class.",
			classes.contains(AnotherAnnotatedClass.class));
		assertFalse("Set should not contain class object SomeClass.class.",
			classes.contains(SomeClass.class));
	}

	/**
	 * Test method for {@link AnnotationExtensions#scanForClasses(File, String)}.
	 *
	 * @throws URISyntaxException
	 *             occurs by creation of the file with an uri.
	 * @throws ClassNotFoundException
	 *             occurs if a given class cannot be located by the specified class loader
	 * @throws IOException
	 *             Signals that an I/O exception has occurred.
	 */
	@Test(enabled = false) // TODO fix...
	public void testScanForClasses() throws URISyntaxException, ClassNotFoundException, IOException
	{
		File directory = ClassExtensions.getResourceAsFile("AnnotationExtensionsTest.class", this);
		directory = directory.getParentFile();
		String packagePath = "de.alpharogroup.lang";
		Set<Class<?>> classes = AnnotationExtensions.scanForClasses(directory, packagePath);
		assertNotNull(classes);
		assertTrue("Size should be 27 but is " + classes.size() + ".", classes.size() == 27);
	}

	/**
	 * Test method for {@link AnnotationExtensions#setAnnotationValue(Annotation, String, Object)}
	 */
	@Test(enabled = true)
	public void testSetAnnotationValue() throws NoSuchFieldException, SecurityException,
		IllegalArgumentException, IllegalAccessException, NoSuchMethodException
	{
		final TestTypeAnnotation typeAnnotation = AnnotatedTestClass.class
			.getAnnotation(TestTypeAnnotation.class);
		String expected = "type test value";
		String actual = typeAnnotation.value();
		assertEquals(expected, actual);
		String newValue = "type test new value";
		String oldValue = (String)AnnotationExtensions.setAnnotationValue(typeAnnotation, "value",
			newValue);
		assertEquals(expected, oldValue);

		actual = typeAnnotation.value();
		expected = newValue;

		assertEquals(expected, actual);

		final Field nameField = AnnotatedTestClass.class.getDeclaredField("name");
		final TestFieldAnnotation testFieldAnnotation = nameField
			.getAnnotation(TestFieldAnnotation.class);
		expected = "name test value";
		actual = testFieldAnnotation.value();
		assertEquals(expected, actual);
		newValue = "name test new value";
		oldValue = (String)AnnotationExtensions.setAnnotationValue(testFieldAnnotation, "value",
			newValue);
		assertEquals(expected, oldValue);

		actual = testFieldAnnotation.value();
		expected = newValue;

		assertEquals(expected, actual);


		final Method method = AnnotatedTestClass.class.getDeclaredMethod("getFullname");
		final TestMethodAnnotation testMethodAnnotation = method
			.getAnnotation(TestMethodAnnotation.class);
		expected = "method test value";
		actual = testMethodAnnotation.value();
		assertEquals(expected, actual);
		newValue = "method test new value";
		oldValue = (String)AnnotationExtensions.setAnnotationValue(testMethodAnnotation, "value",
			newValue);
		assertEquals(expected, oldValue);

		actual = testMethodAnnotation.value();
		expected = newValue;

		assertEquals(expected, actual);

	}

	/**
	 * Test method for {@link AnnotationExtensions}
	 */
	@Test(expectedExceptions = { BeanTestException.class, ObjectCreationException.class })
	public void testWithBeanTester()
	{
		final BeanTester beanTester = new BeanTester();
		beanTester.testBean(AnnotationExtensions.class);
	}
	// ======================================================================================== //

}
