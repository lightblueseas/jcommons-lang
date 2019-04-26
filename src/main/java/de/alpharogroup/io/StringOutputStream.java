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
package de.alpharogroup.io;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.Serializable;
import java.nio.charset.Charset;

import lombok.Setter;

/**
 * The class {@link StringOutputStream}
 *
 * @author Asterios Raptis
 * @deprecated use instead the same class in new project silly-strings
 */
@Deprecated
public class StringOutputStream extends OutputStream implements Serializable
{

	/**
	 * The serialVersionUID.
	 */
	private static final long serialVersionUID = 1L;

	/** The buffer. */
	private final ByteArrayOutputStream byteBuffer = new ByteArrayOutputStream();

	/** The charset. */
	@Setter
	private Charset charset;


	/**
	 * {@inheritDoc}
	 */
	@Override
	public void close() throws IOException
	{
		byteBuffer.close();
	}

	/**
	 * Gets the charset that is used for write in this {@link StringOutputStream} object. Note: if
	 * not set the default charset of "UTF-8" will be taken.
	 *
	 * @return the charset that is used for write in this {@link StringOutputStream} object.
	 */
	public Charset getCharset()
	{
		if (charset == null)
		{
			charset = Charset.forName("UTF-8");
		}
		return charset;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public String toString()
	{
		return new String(byteBuffer.toByteArray(), getCharset());
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void write(final byte[] b) throws IOException
	{
		byteBuffer.write(b);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void write(final byte[] b, final int off, final int len)
	{
		byteBuffer.write(b, off, len);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void write(final int b)
	{
		byteBuffer.write(b);
	}

	/**
	 * Write the given {@link String} object to this {@link StringOutputStream} object.
	 *
	 * @param value
	 *            the value
	 * @throws IOException
	 *             Signals that an I/O exception has occurred.
	 */
	public void write(final String value) throws IOException
	{
		write(value.getBytes());
	}


}
