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
import java.util.Date;

import org.testng.AssertJUnit;
import org.testng.annotations.Test;

import de.alpharogroup.date.CreateDateExtensions;
import de.alpharogroup.date.DateDecorator;
import de.alpharogroup.date.SqlTimestampDecorator;
import de.alpharogroup.test.objects.Employee;
import de.alpharogroup.test.objects.Gender;
import de.alpharogroup.test.objects.Person;

/**
 * The class {@link CopyObjectExtensionsTest}.
 */
public class CopyObjectExtensionsTest
{

	/**
	 * Test method for {@link CopyObjectExtensions#copy(Object, Object)}.
	 *
	 * @throws IllegalAccessException the illegal access exception
	 * @throws InvocationTargetException the invocation target exception
	 */
	@Test(enabled = true)
	public void testCopy() throws IllegalAccessException, InvocationTargetException
	{
		final DateDecorator dateDecorator = new DateDecorator();
		final Date now = CreateDateExtensions.now();
		dateDecorator.setDate(now);

		final SqlTimestampDecorator timestampDecorator = new SqlTimestampDecorator();

		CopyObjectExtensions.copy(dateDecorator, timestampDecorator);

		final Person person = Person.builder().gender(Gender.FEMALE).name("Anna").married(true)
			.about("Ha ha ha...").nickname("beast").build();

		final Employee expected = Employee.builder().person(person).id("23").build();

		final Employee actual = new Employee();
		CopyObjectExtensions.copyQuietly(actual, expected);
		System.out.println(actual);

		AssertJUnit.assertEquals(actual.getId(), expected.getId());
		AssertJUnit.assertEquals(actual.getPerson(), expected.getPerson());

	}

}
