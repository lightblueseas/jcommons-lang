/**
 * The MIT License
 *
 * Copyright (C) 2007 Asterios Raptis
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
package de.alpharogroup.file;

import java.io.File;

/**
 *
 * @author astrapi69
 */
public class FilenameUtils
{

	/**
	 * Gets the filename with the absolute path prefix.
	 * 
	 * @param file
	 *            the file.
	 * @return the filename prefix.
	 */
	public static String getFilenamePrefix(final File file)
	{
		final String fileName = file.getAbsolutePath();
		final int ext_index = fileName.lastIndexOf(".");
		final String fileNamePrefix;
		if (ext_index != -1)
		{
			fileNamePrefix = fileName.substring(0, ext_index);
		}
		else
		{
			fileNamePrefix = fileName;
		}
		return fileNamePrefix;
	}

	/**
	 * Gets the filename suffix or null if no suffix exists or the given file object is a directory.
	 * 
	 * @param file
	 *            the file.
	 * @return 's the filename suffix or null if no suffix exists or the given file object is a
	 *         directory.
	 */
	public static String getFilenameSuffix(final File file)
	{
		if (!file.isDirectory())
		{
			final String fileName = file.getAbsolutePath();
			final int ext_index = fileName.lastIndexOf(".");
			final String fileNameSuffix;
			if (ext_index != -1)
			{
				fileNameSuffix = fileName.substring(ext_index, fileName.length());
			}
			else
			{
				fileNameSuffix = null;
			}
			return fileNameSuffix;
		}
		return null;
	}

	/**
	 * Gets the filename without the extension or null if the given file object is a directory.
	 *
	 * @param file
	 *            the file.
	 * @return the filename without the extension or null if the given file object is a directory.
	 */
	public static String getFilenameWithoutExtension(final File file)
	{
		if (!file.isDirectory())
		{
			final String fileName = file.getName();
			return getFilenameWithoutExtension(fileName);
		}
		return null;
	}

	/**
	 * Gets the filename without the extension or null if the given file object is a directory.
	 *
	 * @param fileName
	 *            the file.
	 * @return the filename without the extension or null if the given file object is a directory.
	 */
	public static String getFilenameWithoutExtension(final String fileName)
	{
		final int ext_index = fileName.lastIndexOf(".");
		final String fileNamePrefix;
		if (ext_index != -1)
		{
			fileNamePrefix = fileName.substring(0, ext_index);
		}
		else
		{
			fileNamePrefix = fileName;
		}
		return fileNamePrefix;
	}

}
