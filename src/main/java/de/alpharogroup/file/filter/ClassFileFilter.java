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
package de.alpharogroup.file.filter;

import java.io.File;
import java.io.FileFilter;

import de.alpharogroup.file.FileExtension;

/**
 * The class ClassFileFilter accepts File-objects that are directories or end with the suffix
 * '.class'. Accepts directories to allow search files recursive in directories.
 *
 * @version 1.0
 * @author Asterios Raptis
 */
public class ClassFileFilter implements FileFilter
{

	/**
	 * {@inheritDoc}
	 */
	@Override
	public boolean accept(final File pathname)
	{
		// To allow recursive search we use file.isDirectory()-method.
		if (pathname.isDirectory())
		{
			return true;
		}
		else
		{
			final String fileName = pathname.getName().toLowerCase();
			if (fileName.endsWith(FileExtension.CLASS.getExtension()))
			{
				return true;
			}
		}
		return false;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public String toString()
	{
		final StringBuilder buffer = new StringBuilder();
		buffer.append("[ClassFileFilter:");
		buffer.append(FileExtension.CLASS.getExtension());
		buffer.append("]");
		return buffer.toString();
	}

}
