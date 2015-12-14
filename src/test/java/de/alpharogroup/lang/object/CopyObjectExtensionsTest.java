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
