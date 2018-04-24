package de.alpharogroup.config;

import static org.testng.Assert.assertEquals;

import java.io.File;

import org.testng.annotations.Test;

/**
 * The class {@link ConfigurationExtensionsTest}.
 */
public class ConfigurationExtensionsTest
{

	/**
	 * Test get user application configuration file path.
	 */
	@Test
	public void testGetUserApplicationConfigurationFilePath()
	{
		String actual;
		String expected;
		actual = ConfigurationExtensions.getUserApplicationConfigurationFilePath("foo", ".config");
		expected = System.getProperty(ConfigurationExtensions.USER_HOME_PROPERTY_KEY) + File.separator + "foo" + File.separator
			+ ".config";
		assertEquals(actual, expected);		
	}

}
