package de.alpharogroup.lang;

import org.testng.AssertJUnit;
import org.testng.annotations.Test;

/**
 * The Class DefaultValueTest.
 */
public class DefaultValueTest
{

	/**
	 * Test for {@link DefaultValue#getDefaultValue(Class)}
	 */
	@Test
	public void testGetDefaultValue()
	{
		AssertJUnit.assertEquals(false, DefaultValue.getDefaultValue(boolean.class).booleanValue());
		AssertJUnit.assertEquals('\0', DefaultValue.getDefaultValue(char.class).charValue());
		AssertJUnit.assertEquals(0, DefaultValue.getDefaultValue(byte.class).byteValue());
		AssertJUnit.assertEquals(0, DefaultValue.getDefaultValue(short.class).shortValue());
		AssertJUnit.assertEquals(0, DefaultValue.getDefaultValue(int.class).intValue());
		AssertJUnit.assertEquals(0, DefaultValue.getDefaultValue(long.class).longValue());
		AssertJUnit.assertEquals(0.0f, DefaultValue.getDefaultValue(float.class).floatValue());
		AssertJUnit.assertEquals(0.0d, DefaultValue.getDefaultValue(double.class).doubleValue());
		AssertJUnit.assertNull("", DefaultValue.getDefaultValue(void.class));
		AssertJUnit.assertNull("", DefaultValue.getDefaultValue(Object.class));
	}

}
