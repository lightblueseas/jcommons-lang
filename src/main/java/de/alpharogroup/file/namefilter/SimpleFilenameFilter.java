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

/**
 * The Class SimpleFilenameFilter.
 *
 * @version 1.0
 * @author Asterios Raptis
 */
public class SimpleFilenameFilter implements FilenameFilter
{

	/** The filesuffix. */
	private String filesuffix;

	/** The accept dir. */
	private boolean acceptDir;

	/**
	 * Instantiates a new simple FilenameFilter.
	 *
	 * @param filesuffix
	 *            the filesuffix
	 * @param acceptDir
	 *            the accept dir
	 */
	public SimpleFilenameFilter(final String filesuffix, final boolean acceptDir)
	{
		if (null == filesuffix || filesuffix.equals(""))
		{
			throw new IllegalArgumentException("Argument filesuffix cant be null or empty. "
				+ "Please set the argument filesuffix.");
		}
		this.filesuffix = filesuffix.toLowerCase();
		this.acceptDir = acceptDir;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public boolean accept(final File dir, final String name)
	{
		final File currentFile = new File(dir, name);
		if (acceptDir)
		{
			if (currentFile.isDirectory())
			{
				return true;
			}
		}
		return name.toLowerCase().endsWith(this.filesuffix);
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
		buffer.append("[SimpleFilenameFilter:");
		buffer.append(" filesuffix: ");
		buffer.append(filesuffix);
		buffer.append(" acceptDir: ");
		buffer.append(acceptDir);
		buffer.append("]");
		return buffer.toString();
	}

}
