package de.alpharogroup.lang;

import static org.testng.AssertJUnit.assertEquals;

import org.testng.annotations.Test;

import de.alpharogroup.test.objects.Person;

/**
 * The unit test class for the class {@link BeanExtensions}.
 */
public class BeanExtensionsTest
{

	/**
	 * Test method for {@link BeanExtensions#setPropertyQuietly(Object, String, Object)}
	 *
	 * @throws Exception
	 *             the exception
	 */
	@Test
	public final void testSetPropertyQuietly() throws Exception
	{
		String expected;
		String actual;
		final Person person = Person.builder().name("Alex").build();
		expected = "Leo";
		BeanExtensions.setPropertyQuietly(person, "name", "Leo");
		actual = person.getName();
		assertEquals(expected, actual);
	}

}
