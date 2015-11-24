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

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.Map;

import lombok.experimental.ExtensionMethod;

import org.apache.commons.beanutils.BeanComparator;
import org.testng.AssertJUnit;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import de.alpharogroup.date.CreateDateUtils;
import de.alpharogroup.date.DateDecorator;
import de.alpharogroup.date.SqlTimestampDecorator;
import de.alpharogroup.io.ChangedAttributeResult;
import de.alpharogroup.test.objects.A;
import de.alpharogroup.test.objects.Employee;
import de.alpharogroup.test.objects.Gender;
import de.alpharogroup.test.objects.Person;

/**
 * The unit class for the class {@link ObjectExtensions}.
 */
@ExtensionMethod(ObjectExtensions.class)
public class ObjectExtensionsTest
{

	/**
	 * Sets the up.
	 *
	 * @throws Exception
	 *             the exception
	 */
	@BeforeMethod
	public void setUp() throws Exception
	{
	}

	/**
	 * Tear down.
	 *
	 * @throws Exception
	 *             the exception
	 */
	@AfterMethod
	public void tearDown() throws Exception
	{
	}

	/**
	 * Test generic clone method.
	 */
	@Test(enabled = false)
	public void testClone()
	{
		final Date past = CreateDateUtils.newDate(2009, 3, 26, 10, 37, 04);
		final Date otherCopy = ObjectExtensions.clone(past);

		boolean result = past.equals(otherCopy);
		AssertJUnit.assertTrue("Cloned object should be equal with the source object.", result);

		final String aString = "Hy there...";

		final String clonedString = ObjectExtensions.clone(aString);

		result = aString.equals(clonedString);
		AssertJUnit.assertTrue("Cloned object should be equal with the source object.", result);

		final A a = new A();
		a.setA("a");

		final A anotherCopy = ObjectExtensions.clone(a);

		result = a.equals(anotherCopy);
		AssertJUnit.assertTrue("Cloned object should be equal with the source object.", result);
	}

	/**
	 * Test clone object.
	 */
	@Test(enabled = true)
	public void testCloneObject()
	{
		final Date past = CreateDateUtils.newDate(2009, 3, 26, 10, 37, 04);
		Object otherCopy = past.cloneObjectQuietly();

		boolean result = past.equals(otherCopy);
		AssertJUnit.assertTrue("Cloned object should be equal with the source object.", result);

		final String aString = "Hy there...";

		otherCopy = ObjectExtensions.cloneObjectQuietly(aString);

		result = aString.equals(otherCopy);
		AssertJUnit.assertTrue("Cloned object should be equal with the source object.", result);

		final A a = new A();
		a.setA("a");

		final Object anotherCopy = ObjectExtensions.cloneObjectQuietly(a);

		result = a.equals(anotherCopy);
		AssertJUnit.assertTrue("Cloned object should be equal with the source object.", result);
	}

	/**
	 * Test compare.
	 *
	 * @throws IllegalAccessException
	 *             the illegal access exception
	 * @throws InvocationTargetException
	 *             the invocation target exception
	 * @throws NoSuchMethodException
	 *             the no such method exception
	 */
	@Test(enabled = false)
	public void testCompare() throws IllegalAccessException, InvocationTargetException,
		NoSuchMethodException
	{
		final Person sourceOjbect = new Person();
		sourceOjbect.setGender(Gender.MALE);
		sourceOjbect.setName("obelix");

		final Person objectToCompare = (Person)ObjectExtensions.cloneObjectQuietly(sourceOjbect);

		boolean result = ObjectExtensions.compare(sourceOjbect, objectToCompare);
		AssertJUnit.assertTrue("Cloned object should be equal with the source object.", result);

		objectToCompare.setGender(Gender.FEMALE);
		result = ObjectExtensions.compare(sourceOjbect, objectToCompare);
		AssertJUnit.assertFalse(
			"Object to compare should be not equal with the source object because it has changed.",
			result);
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Test(enabled = false)
	public void testCompareTo() throws IllegalAccessException, InvocationTargetException,
		NoSuchMethodException
	{
		final List<Person> persons = new ArrayList<>();
		final Person obelix = new Person();
		obelix.setGender(Gender.MALE);
		obelix.setName("obelix");

		final Person asterix = new Person();
		asterix.setGender(Gender.MALE);
		asterix.setName("asterix");

		final Person miraculix = new Person();
		miraculix.setGender(Gender.MALE);
		miraculix.setName("miraculix");

		final int i = ObjectExtensions.compareTo(asterix, obelix, "name");

		System.out.println(i);

		persons.add(obelix);
		persons.add(asterix);
		persons.add(miraculix);
		System.out.println("Unsorted Persons:");
		System.out.println(persons.toString());
		final Comparator defaultComparator = new BeanComparator("name");
		Collections.sort(persons, defaultComparator);
		System.out.println("Sorted Persons by name:");
		System.out.println(persons.toString());
		Collections.reverse(persons);
		System.out.println("Sorted Persons by name reversed:");
		System.out.println(persons.toString());
	}

	@Test(enabled = true)
	public void testCopy() throws IllegalAccessException, InvocationTargetException
	{
		final DateDecorator dateDecorator = new DateDecorator();
		final Date now = CreateDateUtils.now();
		dateDecorator.setDate(now);

		final SqlTimestampDecorator timestampDecorator = new SqlTimestampDecorator();

		ObjectExtensions.copy(dateDecorator, timestampDecorator);

		final Person person = Person.builder().gender(Gender.FEMALE).name("Anna").married(true)
			.about("Ha ha ha...").nickname("beast").build();

		final Employee expected = Employee.builder().person(person).id("23").build();

		final Employee actual = new Employee();
		ObjectExtensions.copyQuietly(actual, expected);
		System.out.println(actual);

		AssertJUnit.assertEquals(actual.getId(), expected.getId());
		AssertJUnit.assertEquals(actual.getPerson(), expected.getPerson());

	}

	/**
	 * Test get changed data.
	 *
	 * @throws IllegalAccessException
	 *             the illegal access exception
	 * @throws InvocationTargetException
	 *             the invocation target exception
	 * @throws NoSuchMethodException
	 *             the no such method exception
	 */
	@Test(enabled = false)
	public void testGetChangedData() throws IllegalAccessException, InvocationTargetException,
		NoSuchMethodException
	{
		final Person sourceOjbect = new Person();
		sourceOjbect.setGender(Gender.MALE);
		sourceOjbect.setName("obelix");

		final Person objectToCompare = (Person)ObjectExtensions.cloneObjectQuietly(sourceOjbect);

		Map<Object, ChangedAttributeResult> result = ObjectExtensions.getChangedDataMap(
			sourceOjbect, objectToCompare);
		AssertJUnit.assertTrue("Size should be 0 but is " + result.size(), result.size() == 0);
		// Change the gender from the objectToCompare...
		objectToCompare.setGender(Gender.FEMALE);
		// and get the changed data...
		result = ObjectExtensions.getChangedDataMap(sourceOjbect, objectToCompare);
		AssertJUnit.assertFalse("Size should be 1 but is " + result.size(), result.size() == 0);
		AssertJUnit.assertTrue("", result.containsKey("gender"));
		final ChangedAttributeResult changed = result.get("gender");
		final Object sourceAttribute = changed.getSourceAttribute();
		final Object changedAttribute = changed.getChangedAttribute();
		AssertJUnit.assertTrue("", sourceAttribute.equals(Gender.MALE.name()));
		AssertJUnit.assertTrue("", changedAttribute.equals(Gender.FEMALE.name()));
	}

}
