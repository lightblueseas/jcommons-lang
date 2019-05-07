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
package de.alpharogroup.lang.model;

import static org.testng.Assert.assertNotNull;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.List;
import java.util.Map;

import javax.lang.model.element.ElementKind;
import javax.lang.model.element.Modifier;

import org.meanbean.test.BeanTester;
import org.testng.annotations.Test;

import de.alpharogroup.collections.list.ListFactory;
import de.alpharogroup.collections.map.MapFactory;
import de.alpharogroup.evaluate.object.verifier.ContractVerifier;

/**
 * The unit test class for the class {@link ClassModel}.
 */
public class ClassModelTest
{

	/**
	 * Test method for {@link ClassModel} constructors
	 */
	@Test
	public final void testConstructors()
	{
		ClassModel classModel = new ClassModel();
		assertNotNull(classModel);

		ElementKind kind = ElementKind.CLASS;

		/** The package name. */
		String packageName = "de.alpharogroup.lang.model";

		/** The imports. */
		List<String> imports = ListFactory.newArrayList(
			"import static org.testng.Assert.assertNotNull;", "import java.util.Map;");

		/** The class annotations. */
		List<ClassModel> classAnnotations = ListFactory.newArrayList();

		/** The modifiers. */
		List<Modifier> modifiers = ListFactory.newArrayList();

		/** The generic types. */
		List<String> genericTypes = ListFactory.newArrayList();

		/** The class name. */
		String className = "Foo";

		/** The extended class name. */
		String extendedClassName = "";

		/** The interface implementations. */
		List<String> interfaceImplementations = ListFactory.newArrayList();

		/** The methods. */
		Map<String, MethodModel> methods = MapFactory.newHashMap();

		classModel = new ClassModel(classAnnotations, className, extendedClassName, genericTypes,
			imports, interfaceImplementations, kind, methods, modifiers, packageName);
		assertNotNull(classModel);
		classModel = ClassModel.builder().build();
		assertNotNull(classModel);
	}

	/**
	 * Test method for {@link ClassModel}
	 */
	@Test
	public void testWithBeanTester()
	{
		final BeanTester beanTester = new BeanTester();
		beanTester.testBean(ClassModel.class);
	}

	/**
	 * Test method for {@link ClassModel#equals(Object)} , {@link ClassModel#hashCode()} and
	 * {@link ClassModel#toString()}
	 *
	 * @throws NoSuchMethodException
	 *             if an accessor method for this property cannot be found
	 * @throws IllegalAccessException
	 *             if the caller does not have access to the property accessor method
	 * @throws InvocationTargetException
	 *             if the property accessor method throws an exception
	 * @throws InstantiationException
	 *             if a new instance of the bean's class cannot be instantiated
	 * @throws IOException
	 *             Signals that an I/O exception has occurred
	 * @throws ClassNotFoundException
	 *             occurs if a given class cannot be located by the specified class loader
	 */
	@Test
	public void verifyEqualsHashcodeAndToStringContracts()
	{
		ContractVerifier.of(ClassModel.class).verify();
	}

}
