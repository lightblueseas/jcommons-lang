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


import java.io.File;
import java.io.IOException;
import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.net.URISyntaxException;
import java.util.HashSet;
import java.util.Set;

import org.apache.log4j.Logger;
import org.meanbean.factories.ObjectCreationException;
import org.meanbean.test.BeanTestException;
import org.meanbean.test.BeanTester;
import org.testng.AssertJUnit;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

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
 * The unit test class for the class {@link de.alpharogroup.lang.AnnotationExtensions}.
 *
 * @author Asterios Raptis
 * @version 1.0
 */
public class AnnotationExtensionsTest
{

	/** The Constant LOGGER. */
	protected static final Logger LOGGER = Logger.getLogger(AnnotationExtensionsTest.class);

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
	 * Test get all annotated classes.
	 *
	 * @throws ClassNotFoundException
	 *             the class not found exception
	 * @throws IOException
	 *             Signals that an I/O exception has occurred.
	 */
	@Test(enabled = false)
	public void testGetAllAnnotatedClasses() throws ClassNotFoundException, IOException
	{
		final Set<Class<?>> classes = AnnotationExtensions
			.getAllAnnotatedClasses("de.alpharogroup.test.objects", TestAnnotation.class);
		LOGGER.info(classes);
		AssertJUnit.assertTrue("Size should be 3 but is " + classes.size() + ".",
			classes.size() == 3);
		AssertJUnit.assertTrue("Set should contain class object OtherAnnotatedClass.class.",
			classes.contains(OtherAnnotatedClass.class));
		AssertJUnit.assertTrue("Set should contain class object AnnotatedClass.class.",
			classes.contains(AnnotatedClass.class));
		AssertJUnit.assertTrue("Set should contain class object AnnotatedInterface.class.",
			classes.contains(AnnotatedInterface.class));
		AssertJUnit.assertFalse("Set should not contain class object SomeClass.class.",
			classes.contains(SomeClass.class));
	}

	/**
	 * Test get all annotated classes set.
	 *
	 * @throws ClassNotFoundException
	 *             the class not found exception
	 * @throws IOException
	 *             Signals that an I/O exception has occurred.
	 */
	@Test(enabled = false)
	public void testGetAllAnnotatedClassesSet() throws ClassNotFoundException, IOException
	{
		final Set<Class<? extends Annotation>> allAnotations = new HashSet<>();
		allAnotations.add(TestAnnotation.class);
		allAnotations.add(AnotherTestAnnotation.class);
		final Set<Class<?>> classes = AnnotationExtensions
			.getAllAnnotatedClassesFromSet("de.alpharogroup.test.objects", allAnotations);

		AssertJUnit.assertTrue("Size should be 4  but is " + classes.size() + ".",
			classes.size() == 4);
		AssertJUnit.assertTrue("Set should contain class object AnnotatedInterface.class.",
			classes.contains(AnnotatedInterface.class));
		AssertJUnit.assertTrue("Set should contain class object OtherAnnotatedClass.class.",
			classes.contains(OtherAnnotatedClass.class));
		AssertJUnit.assertTrue("Set should contain class object AnnotatedClass.class.",
			classes.contains(AnnotatedClass.class));
		AssertJUnit.assertTrue("Set should contain class object AnotherAnnotatedClass.class.",
			classes.contains(AnotherAnnotatedClass.class));
		AssertJUnit.assertTrue("Set should contain class object OtherAnnotatedClass.class.",
			classes.contains(OtherAnnotatedClass.class));
		AssertJUnit.assertFalse("Set should not contain class object SomeClass.class.",
			classes.contains(SomeClass.class));
	}

	/**
	 * Test get all classes string set.
	 *
	 * @throws ClassNotFoundException
	 *             the class not found exception
	 * @throws IOException
	 *             Signals that an I/O exception has occurred.
	 */
	@Test(enabled = false)
	public void testGetAllClassesStringSet() throws ClassNotFoundException, IOException
	{
		final Set<Class<? extends Annotation>> allAnotations = new HashSet<>();
		allAnotations.add(TestAnnotation.class);
		allAnotations.add(AnotherTestAnnotation.class);

		final Set<Class<?>> classes = AnnotationExtensions
			.getAllClasses("de.alpharogroup.test.objects", allAnotations);
		AssertJUnit.assertTrue("Size should be 4  but is " + classes.size() + ".",
			classes.size() == 4);
		AssertJUnit.assertTrue("Set should contain class object AnnotatedInterface.class.",
			classes.contains(AnnotatedInterface.class));
		AssertJUnit.assertTrue("Set should contain class object OtherAnnotatedClass.class.",
			classes.contains(OtherAnnotatedClass.class));
		AssertJUnit.assertTrue("Set should contain class object AnnotatedClass.class.",
			classes.contains(AnnotatedClass.class));
		AssertJUnit.assertTrue("Set should contain class object AnotherAnnotatedClass.class.",
			classes.contains(AnotherAnnotatedClass.class));
		AssertJUnit.assertFalse("Set should not contain class object SomeClass.class.",
			classes.contains(SomeClass.class));
	}

	/**
	 * Test is annotation present in super classes.
	 */
	@Test(enabled = true)
	public void testIsAnnotationPresentInSuperClasses()
	{
		boolean result = AnnotationExtensions
			.isAnnotationPresentInSuperClasses(SubAnnotatedClass.class, TestAnnotation.class);
		AssertJUnit.assertTrue(
			"If an Annotation is present in the super class then it should return true!", result);
		result = AnnotationExtensions.isAnnotationPresentInSuperClassesOrInterfaces(
			ClassExtendsAnnotatedInterface.class, TestAnnotation.class);
		AssertJUnit.assertTrue(
			"If an Annotation is present in the super class then it should return true!", result);
	}

	/**
	 * Test scan for annotated classes.
	 *
	 * @throws ClassNotFoundException
	 *             the class not found exception
	 * @throws IOException
	 *             Signals that an I/O exception has occurred.
	 * @throws URISyntaxException
	 *             occurs by creation of the file with an uri.
	 */
	@Test(enabled = false)
	public void testScanForAnnotatedClasses()
		throws ClassNotFoundException, IOException, URISyntaxException
	{
		File directory = ClassExtensions.getResourceAsFile("AnnotatedClass.class",
			new AnnotatedClass());
		directory = directory.getParentFile();
		final Set<Class<?>> classes = AnnotationExtensions.scanForAnnotatedClasses(directory,
			"de.alpharogroup.test.objects.annotations", TestAnnotation.class);
		AssertJUnit.assertTrue("Size should be 3  but is " + classes.size() + ".",
			classes.size() == 3);
		AssertJUnit.assertTrue("Set should contain class object AnnotatedInterface.class.",
			classes.contains(AnnotatedInterface.class));
		AssertJUnit.assertTrue("Set should contain class object AnnotatedClass.class.",
			classes.contains(AnnotatedClass.class));
		AssertJUnit.assertTrue("Set should contain class object OtherAnnotatedClass.class.",
			classes.contains(OtherAnnotatedClass.class));
		AssertJUnit.assertFalse("Set should not contain class object SomeClass.class.",
			classes.contains(SomeClass.class));
	}


	/**
	 * Test scan for annotated classes from set.
	 *
	 * @throws ClassNotFoundException
	 *             the class not found exception
	 * @throws IOException
	 *             Signals that an I/O exception has occurred.
	 * @throws URISyntaxException
	 *             occurs by creation of the file with an uri.
	 */
	@Test(enabled = false)
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

		AssertJUnit.assertTrue("Size should be 4  but is " + classes.size() + ".",
			classes.size() == 4);
		AssertJUnit.assertTrue("Set should contain class object OtherAnnotatedClass.class.",
			classes.contains(OtherAnnotatedClass.class));
		AssertJUnit.assertTrue("Set should contain class object AnnotatedInterface.class.",
			classes.contains(AnnotatedInterface.class));
		AssertJUnit.assertTrue("Set should contain class object AnnotatedClass.class.",
			classes.contains(AnnotatedClass.class));
		AssertJUnit.assertTrue("Set should contain class object AnotherAnnotatedClass.class.",
			classes.contains(AnotherAnnotatedClass.class));
		AssertJUnit.assertFalse("Set should not contain class object SomeClass.class.",
			classes.contains(SomeClass.class));
	}

	@Test(enabled = true)
	public void testSetAnnotationValue() throws NoSuchFieldException, SecurityException,
		IllegalArgumentException, IllegalAccessException, NoSuchMethodException
	{
		final TestTypeAnnotation typeAnnotation = AnnotatedTestClass.class
			.getAnnotation(TestTypeAnnotation.class);
		String expected = "type test value";
		String actual = typeAnnotation.value();
		AssertJUnit.assertEquals(expected, actual);
		String newValue = "type test new value";
		String oldValue = (String)AnnotationExtensions.setAnnotationValue(typeAnnotation, "value",
			newValue);
		AssertJUnit.assertEquals(expected, oldValue);

		actual = typeAnnotation.value();
		expected = newValue;

		AssertJUnit.assertEquals(expected, actual);

		final Field nameField = AnnotatedTestClass.class.getDeclaredField("name");
		final TestFieldAnnotation testFieldAnnotation = nameField
			.getAnnotation(TestFieldAnnotation.class);
		expected = "name test value";
		actual = testFieldAnnotation.value();
		AssertJUnit.assertEquals(expected, actual);
		newValue = "name test new value";
		oldValue = (String)AnnotationExtensions.setAnnotationValue(testFieldAnnotation, "value",
			newValue);
		AssertJUnit.assertEquals(expected, oldValue);

		actual = testFieldAnnotation.value();
		expected = newValue;

		AssertJUnit.assertEquals(expected, actual);


		final Method method = AnnotatedTestClass.class.getDeclaredMethod("getFullname");
		final TestMethodAnnotation testMethodAnnotation = method
			.getAnnotation(TestMethodAnnotation.class);
		expected = "method test value";
		actual = testMethodAnnotation.value();
		AssertJUnit.assertEquals(expected, actual);
		newValue = "method test new value";
		oldValue = (String)AnnotationExtensions.setAnnotationValue(testMethodAnnotation, "value",
			newValue);
		AssertJUnit.assertEquals(expected, oldValue);

		actual = testMethodAnnotation.value();
		expected = newValue;

		AssertJUnit.assertEquals(expected, actual);

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

}
