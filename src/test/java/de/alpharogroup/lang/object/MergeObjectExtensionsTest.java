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
package de.alpharogroup.lang.object;

import java.lang.reflect.InvocationTargetException;
import java.util.Map;

import org.testng.AssertJUnit;
import org.testng.annotations.Test;

import de.alpharogroup.io.ChangedAttributeResult;
import de.alpharogroup.test.objects.Employee;
import de.alpharogroup.test.objects.Gender;
import de.alpharogroup.test.objects.Person;
import lombok.experimental.ExtensionMethod;


/**
 * The class {@link MergeObjectExtensionsTest}.
 */
@ExtensionMethod(MergeObjectExtensions.class)
public class MergeObjectExtensionsTest
{

	/**
	 * Test method for {@link MergeObjectExtensions#merge(Object, Object)}.
	 *
	 * @throws InvocationTargetException
	 *             the invocation target exception
	 * @throws IllegalAccessException
	 *             the illegal access exception
	 */
	@Test
	public void testMerge() throws InvocationTargetException, IllegalAccessException
	{

		final Person person = Person.builder().gender(Gender.FEMALE).name("Anna").married(true)
			.about("About what...").nickname("beast").build();

		final Employee with = Employee.builder().person(person).id("23").build();

		Employee to = Employee.builder().build();
		to.merge(with);

		AssertJUnit.assertTrue("", to.getId().equals("23"));
		AssertJUnit.assertTrue("", to.getPerson().equals(person));

		to = Employee.builder().person(Person.builder().build()).build();
		to.merge(with);

		AssertJUnit.assertTrue("", to.getId().equals("23"));
		AssertJUnit.assertTrue("", to.getPerson().equals(person));


	}

	/**
	 * Test method for {@link MergeObjectExtensions#getChangedDataMap(Object, Object)}.
	 *
	 * @throws IllegalAccessException
	 *             the illegal access exception
	 * @throws InvocationTargetException
	 *             the invocation target exception
	 * @throws NoSuchMethodException
	 *             the no such method exception
	 */
	@Test(enabled = false)
	public void testGetChangedDataMap()
		throws IllegalAccessException, InvocationTargetException, NoSuchMethodException
	{
		final Person sourceOjbect = new Person();
		sourceOjbect.setGender(Gender.MALE);
		sourceOjbect.setName("obelix");

		final Person objectToCompare = (Person)CloneObjectExtensions
			.cloneObjectQuietly(sourceOjbect);

		Map<Object, ChangedAttributeResult> result = MergeObjectExtensions
			.getChangedDataMap(sourceOjbect, objectToCompare);
		AssertJUnit.assertTrue("Size should be 0 but is " + result.size(), result.size() == 0);
		// Change the gender from the objectToCompare...
		objectToCompare.setGender(Gender.FEMALE);
		// and get the changed data...
		result = MergeObjectExtensions.getChangedDataMap(sourceOjbect, objectToCompare);
		AssertJUnit.assertFalse("Size should be 1 but is " + result.size(), result.size() == 0);
		AssertJUnit.assertTrue("", result.containsKey("gender"));
		final ChangedAttributeResult changed = result.get("gender");
		final Object sourceAttribute = changed.getSourceAttribute();
		final Object changedAttribute = changed.getChangedAttribute();
		AssertJUnit.assertTrue("", sourceAttribute.equals(Gender.MALE.name()));
		AssertJUnit.assertTrue("", changedAttribute.equals(Gender.FEMALE.name()));
	}

}
