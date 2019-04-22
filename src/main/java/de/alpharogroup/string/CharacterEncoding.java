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
package de.alpharogroup.string;

import lombok.Getter;

/**
 * The enum {@link CharacterEncoding}
 * @deprecated use instead <code>java.nio.charset.StandardCharsets</code>
 */
public enum CharacterEncoding
{

	/** The UTF-16 character encoding. */
	UTF_16("UTF-16"),
	/** The UTF-8 character encoding. */
	UTF_8("UTF-8");

	/** The character encoding. */
	@Getter
	private final String encoding;

	/**
	 * Instantiates a new {@link CharacterEncoding}
	 *
	 * @param encoding
	 *            the encoding
	 */
	private CharacterEncoding(final String encoding)
	{
		this.encoding = encoding;
	}

}
