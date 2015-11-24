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
package de.alpharogroup.io;

import java.io.OutputStream;
import java.io.Serializable;

import de.alpharogroup.string.StringUtils;

/**
 * The Class StringOutputStream.
 */
public class StringOutputStream extends OutputStream implements Serializable
{

	/**
	 * The serialVersionUID.
	 */
	private static final long serialVersionUID = 1L;

	/** The string builder. */
	private StringBuilder stringBuilder = null;

	/**
	 * Instantiates a new string output stream.
	 */
	public StringOutputStream()
	{
		super();
		stringBuilder = new StringBuilder();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void close()
	{
		stringBuilder = null;

	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public String toString()
	{
		return stringBuilder.toString();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void write(final byte[] b)
	{
		stringBuilder.append(StringUtils.convertToCharArray(b));
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void write(final byte[] b, int off, final int len)
	{
		if (off < 0 || len < 0 || off + len > b.length)
		{
			throw new IndexOutOfBoundsException("StringOutputStream.write: index out of bounds.");
		}
		final byte[] bytes = new byte[len];
		for (int i = 0; i < len; i++)
		{
			bytes[i] = b[off];
			off++;
		}
		stringBuilder.append(StringUtils.convertToCharArray(bytes));
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void write(final int b)
	{
		stringBuilder.append((char)b);
	}
}
