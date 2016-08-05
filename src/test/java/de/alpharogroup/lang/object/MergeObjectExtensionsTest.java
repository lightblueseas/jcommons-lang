package de.alpharogroup.lang.object;

import java.lang.reflect.InvocationTargetException;

import org.testng.AssertJUnit;
import org.testng.annotations.Test;

import de.alpharogroup.test.objects.Employee;
import de.alpharogroup.test.objects.Gender;
import de.alpharogroup.test.objects.Person;
import lombok.experimental.ExtensionMethod;

@ExtensionMethod(MergeObjectExtensions.class)
public class MergeObjectExtensionsTest
{

	@Test
	public void testMerge() throws InvocationTargetException, IllegalAccessException
	{

		final Person person = Person.builder().gender(Gender.FEMALE).name("Anna").married(true)
			.about("Ha ha ha...").nickname("beast").build();

		final Employee with = Employee.builder().person(person).id("23").build();

		final Employee to = Employee.builder().build();
		to.merge(with);

		AssertJUnit.assertTrue("", to.getId().equals("23"));
		AssertJUnit.assertTrue("", to.getPerson().equals(person));


	}

}
