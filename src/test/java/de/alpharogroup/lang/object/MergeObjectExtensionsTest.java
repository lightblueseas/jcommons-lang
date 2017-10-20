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

import org.testng.AssertJUnit;
import org.testng.annotations.Test;

import de.alpharogroup.date.CreateDateExtensions;
import de.alpharogroup.date.DateDecorator;
import de.alpharogroup.date.SqlTimestampDecorator;
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

		Employee mergeInObject = Employee.builder().build();
		mergeInObject.merge(with);

		AssertJUnit.assertTrue("", mergeInObject.getId().equals("23"));
		AssertJUnit.assertTrue("", mergeInObject.getPerson().equals(person));

		mergeInObject = Employee.builder().id("22").person(Person.builder().build()).build();
		mergeInObject.merge(with);

		AssertJUnit.assertTrue("", mergeInObject.getId().equals("23"));
		AssertJUnit.assertTrue("", mergeInObject.getPerson().equals(person));

	}

	/**
	 * Test method for {@link MergeObjectExtensions#merge(Object, Object)}.
	 *
	 * @throws InvocationTargetException
	 *             the invocation target exception
	 * @throws IllegalAccessException
	 *             the illegal access exception
	 */
	@Test(enabled = true)
	public void testMergeOrCopyQuietly() throws InvocationTargetException, IllegalAccessException
	{
		final DateDecorator dateDecorator = DateDecorator.builder().date(CreateDateExtensions.now())
			.build();

		final SqlTimestampDecorator timestampDecorator = SqlTimestampDecorator.builder().build();

		timestampDecorator.mergeOrCopyQuietly(dateDecorator);

		AssertJUnit.assertTrue("Time should be equal.",
			timestampDecorator.getDate().getTime() == dateDecorator.getDate().getTime());
	}


	/**
	 * Test method for {@link MergeObjectExtensions#mergeQuietly(Object, Object)}.
	 *
	 * @throws InvocationTargetException
	 *             the invocation target exception
	 * @throws IllegalAccessException
	 *             the illegal access exception
	 */
	@Test
	public void testMergeQuietly()
	{

		final Person person = Person.builder().gender(Gender.FEMALE).name("Anna").married(true)
			.about("About what...").nickname("beast").build();

		final Employee with = Employee.builder().person(person).id("23").build();

		Employee mergeInObject = Employee.builder().build();
		mergeInObject.mergeQuietly(with);

		AssertJUnit.assertTrue("", mergeInObject.getId().equals("23"));
		AssertJUnit.assertTrue("", mergeInObject.getPerson().equals(person));

		mergeInObject = Employee.builder().id("22").person(Person.builder().build()).build();
		mergeInObject.mergeQuietly(with);

		AssertJUnit.assertTrue("", mergeInObject.getId().equals("23"));
		AssertJUnit.assertTrue("", mergeInObject.getPerson().equals(person));

	}

	/**
	 * Test method for {@link MergeObjectExtensions#merge(Object, Object)}.
	 *
	 * @throws InvocationTargetException
	 *             the invocation target exception
	 * @throws IllegalAccessException
	 *             the illegal access exception
	 */
	@Test(expectedExceptions = { IllegalArgumentException.class })
	public void testMergeThrowIllegalArgumentException()
		throws InvocationTargetException, IllegalAccessException
	{
		final DateDecorator dateDecorator = DateDecorator.builder().date(CreateDateExtensions.now())
			.build();

		final SqlTimestampDecorator timestampDecorator = SqlTimestampDecorator.builder().build();

		timestampDecorator.merge(dateDecorator);
	}

}
