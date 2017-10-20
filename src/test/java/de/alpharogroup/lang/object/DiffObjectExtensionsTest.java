package de.alpharogroup.lang.object;

import java.lang.reflect.InvocationTargetException;
import java.util.Map;

import org.testng.AssertJUnit;
import org.testng.annotations.Test;

import de.alpharogroup.io.ChangedAttributeResult;
import de.alpharogroup.test.objects.Gender;
import de.alpharogroup.test.objects.Person;

public class DiffObjectExtensionsTest
{

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

		Map<Object, ChangedAttributeResult> result = DiffObjectExtensions
			.getChangedDataMap(sourceOjbect, objectToCompare);
		AssertJUnit.assertTrue("Size should be 0 but is " + result.size(), result.size() == 0);
		// Change the gender from the objectToCompare...
		objectToCompare.setGender(Gender.FEMALE);
		// and get the changed data...
		result = DiffObjectExtensions.getChangedDataMap(sourceOjbect, objectToCompare);
		AssertJUnit.assertFalse("Size should be 1 but is " + result.size(), result.size() == 0);
		AssertJUnit.assertTrue("", result.containsKey("gender"));
		final ChangedAttributeResult changed = result.get("gender");
		final Object sourceAttribute = changed.getSourceAttribute();
		final Object changedAttribute = changed.getChangedAttribute();
		AssertJUnit.assertTrue("", sourceAttribute.equals(Gender.MALE.name()));
		AssertJUnit.assertTrue("", changedAttribute.equals(Gender.FEMALE.name()));
	}

}
