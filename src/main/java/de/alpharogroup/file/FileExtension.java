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
package de.alpharogroup.file;

import lombok.Getter;

/**
 * The enum {@link FileExtension} for file extensions.
 */
public enum FileExtension
{

	/** The file extension for backup files. */
	BACKUP(".bak"),

	/** The file extension for class files. */
	CLASS(".class"),

	/** The file extension for java files. */
	JAVA(".java"),

	/** The file extension for properties files. */
	PROPERTIES(".properties"),

	/** The file extension for txt files. */
	TXT(".txt"),

	/** The file extension for velocity template files. */
	VELOCITY_TEMPLATE(".vm");

	/** The file extension. */
	@Getter
	private final String extension;

	/**
	 * Instantiates a new {@link FileExtension} 
	 *
	 * @param extension the file extension
	 */
	FileExtension(final String extension)
	{
		this.extension = extension;
	}

}
