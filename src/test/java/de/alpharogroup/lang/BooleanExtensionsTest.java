package de.alpharogroup.lang;

import static org.testng.AssertJUnit.assertEquals;

import org.testng.annotations.Test;

/**
 * The unit test class for the class {@link BooleanExtensions}.
 */
public class BooleanExtensionsTest
{

	/**
	 * Test method for {@link BooleanExtensions#trueOrFalse(Object, Object, boolean...)}
	 *
	 * @throws Exception
	 *             the exception
	 */
	@Test
	public final void testTrueOrFalse() throws Exception
	{
		String expected;
		String actual;
		expected = "where";
		actual = BooleanExtensions.trueOrFalse("and", "where");
		assertEquals(expected, actual);

		expected = "and";
		actual = BooleanExtensions.trueOrFalse("and", "where", true);
		assertEquals(expected, actual);

		expected = "where";
		actual = BooleanExtensions.trueOrFalse("and", "where", false, false);
		assertEquals(expected, actual);

		expected = "and";
		actual = BooleanExtensions.trueOrFalse("and", "where", true, false);
		assertEquals(expected, actual);

		expected = "and";
		actual = BooleanExtensions.trueOrFalse("and", "where", true, false, false);
		assertEquals(expected, actual);

		expected = "and";
		actual = BooleanExtensions.trueOrFalse("and", "where", true, true, false);
		assertEquals(expected, actual);

		expected = "where";
		actual = BooleanExtensions.trueOrFalse("and", "where", false, false, false);
		assertEquals(expected, actual);
	}

}
