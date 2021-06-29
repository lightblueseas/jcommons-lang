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
package de.alpharogroup.config;

import java.io.File;

import lombok.NonNull;
import lombok.experimental.UtilityClass;

/**
 * The class {@link ConfigurationExtensions} provides methods for get configuration issues.
 * @deprecated use instead SystemFileExtensions from file-worker repository
 */
@UtilityClass
public final class ConfigurationExtensions
{

	public static final String USER_HOME_PROPERTY_KEY = "user.home";

	public static final String JAVA_IO_TPMDIR_PROPERTY_KEY = "java.io.tmpdir";

	/**
	 * Gets the user application configuration file path.
	 *
	 * @param applicationName
	 *            the application name
	 * @param configFileName
	 *            the config file name
	 * @return the user application configuration file path
	 */
	public static String getUserApplicationConfigurationFilePath(
		@NonNull final String applicationName, @NonNull final String configFileName)
	{
		return System.getProperty(USER_HOME_PROPERTY_KEY) + File.separator + applicationName
			+ File.separator + configFileName;
	}

	/**
	 * Gets the specific temporary directory path for from the given arguments. It is indeded for
	 * any application temporary files
	 *
	 * @param applicationName
	 *            the application name
	 * @param fileName
	 *            the file name
	 * @return the specific temporary directory path from the given arguments
	 */
	public static String getTemporaryApplicationConfigurationFilePath(
		@NonNull final String applicationName, @NonNull final String fileName)
	{
		return System.getProperty(JAVA_IO_TPMDIR_PROPERTY_KEY) + File.separator + applicationName
			+ File.separator + fileName;
	}

}
