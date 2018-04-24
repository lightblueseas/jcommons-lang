package de.alpharogroup.config;

import java.io.File;

import lombok.NonNull;
import lombok.experimental.UtilityClass;

/**
 * The class {@link ConfigurationExtensions}.
 */
@UtilityClass
public class ConfigurationExtensions
{
	
	public static final String USER_HOME_PROPERTY_KEY = "user.home";

	/**
	 * Gets the user application configuration file path.
	 *
	 * @param applicationName the application name
	 * @param configFileName the config file name
	 * @return the user application configuration file path
	 */
	public static String getUserApplicationConfigurationFilePath(
		@NonNull final String applicationName, @NonNull final String configFileName)
	{
		return System.getProperty(USER_HOME_PROPERTY_KEY) + File.separator + applicationName + File.separator
			+ configFileName;
	}

}
