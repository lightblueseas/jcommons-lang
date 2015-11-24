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
package de.alpharogroup.file.namefilter;

import java.io.File;
import java.io.FilenameFilter;
import java.util.ArrayList;
import java.util.Collection;

/**
 * The Class MultiplyExtensionsFilenameFilter accepts File-objects that end with one of the
 * extension in the collection.
 *
 * @version 1.0
 * @author Asterios Raptis
 */
public class MultiplyExtensionsFilenameFilter implements FilenameFilter
{

	/** The file extensions. */
	private Collection<String> fileExtensions;

	/** The accept dir. */
	private boolean acceptDir;

	/**
	 * Instantiates a new multiply extensions filename filter.
	 *
	 * @param fileExtensions
	 *            the file extensions
	 */
	public MultiplyExtensionsFilenameFilter(final Collection<String> fileExtensions)
	{
		if (null == fileExtensions || fileExtensions.isEmpty())
		{
			throw new IllegalArgumentException("Argument fileExtensions cant be null or empty. "
				+ "Please set the argument fileExtensions appropriate.");
		}
		this.fileExtensions = new ArrayList<>(fileExtensions.size());
		for (final String extension : fileExtensions)
		{
			this.fileExtensions.add(extension.toLowerCase());
		}
	}


	/**
	 * Instantiates a new multiply extensions filename filter.
	 *
	 * @param fileExtensions
	 *            the file extensions
	 * @param acceptDir
	 *            the accept dir
	 */
	public MultiplyExtensionsFilenameFilter(final Collection<String> fileExtensions,
		final boolean acceptDir)
	{
		if (null == fileExtensions || fileExtensions.isEmpty())
		{
			throw new IllegalArgumentException("Argument fileExtensions cant be null or empty. "
				+ "Please set the argument fileExtensions appropriate.");
		}
		this.fileExtensions = new ArrayList<>(fileExtensions.size());
		this.acceptDir = acceptDir;
		for (final String extension : fileExtensions)
		{
			this.fileExtensions.add(extension.toLowerCase());
		}
	}

	/**
	 * Accept.
	 *
	 * @param dir
	 *            the dir
	 * @param name
	 *            the name
	 * @return true, if accept {@inheritDoc}
	 * @see java.io.FilenameFilter#accept(java.io.File, java.lang.String)
	 */
	@Override
	public boolean accept(final File dir, final String name)
	{
		final File currentFile = new File(dir, name);
		if (acceptDir && currentFile.isDirectory())
		{
			return true;
		}
		for (final String extension : fileExtensions)
		{
			if (name.endsWith(extension))
			{
				return true;
			}
		}
		return false;
	}


	/**
	 * To string.
	 *
	 * @return the string {@inheritDoc}
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString()
	{
		final StringBuilder buffer = new StringBuilder();
		buffer.append("[MultiplyExtensionsFilenameFilter:");
		buffer.append(" fileExtensions: ");
		buffer.append(fileExtensions);
		buffer.append(" acceptDir: ");
		buffer.append(acceptDir);
		buffer.append("]");
		return buffer.toString();
	}

}
