package de.alpharogroup.lang.object;

import java.util.Date;

import org.testng.AssertJUnit;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import de.alpharogroup.date.CreateDateExtensions;
import de.alpharogroup.test.objects.A;
import lombok.experimental.ExtensionMethod;

@ExtensionMethod(CloneObjectExtensions.class)
public class CloneObjectExtensionsTest
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
		final Date past = CreateDateExtensions.newDate(2009, 3, 26, 10, 37, 04);
		final Date otherCopy = CloneObjectExtensions.clone(past);

		boolean result = past.equals(otherCopy);
		AssertJUnit.assertTrue("Cloned object should be equal with the source object.", result);

		final String aString = "Hy there...";

		final String clonedString = CloneObjectExtensions.clone(aString);

		result = aString.equals(clonedString);
		AssertJUnit.assertTrue("Cloned object should be equal with the source object.", result);

		final A a = new A();
		a.setA("a");

		final A anotherCopy = CloneObjectExtensions.clone(a);

		result = a.equals(anotherCopy);
		AssertJUnit.assertTrue("Cloned object should be equal with the source object.", result);
	}

	/**
	 * Test clone object.
	 */
	@Test(enabled = true)
	public void testCloneObject()
	{
		final Date past = CreateDateExtensions.newDate(2009, 3, 26, 10, 37, 04);
		// this is possible through the extension method of lombok :-) ...
		Object otherCopy = past.cloneObjectQuietly();

		boolean result = past.equals(otherCopy);
		AssertJUnit.assertTrue("Cloned object should be equal with the source object.", result);

		final String aString = "Hy there...";

		otherCopy = CloneObjectExtensions.cloneObjectQuietly(aString);

		result = aString.equals(otherCopy);
		AssertJUnit.assertTrue("Cloned object should be equal with the source object.", result);

		final A a = new A();
		a.setA("a");

		final Object anotherCopy = CloneObjectExtensions.cloneObjectQuietly(a);

		result = a.equals(anotherCopy);
		AssertJUnit.assertTrue("Cloned object should be equal with the source object.", result);
	}

}
