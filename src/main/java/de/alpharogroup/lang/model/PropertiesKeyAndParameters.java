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
package de.alpharogroup.lang.model;

import java.io.Serializable;

/**
 * Container for a properties key and possible parameters.
 */
public class PropertiesKeyAndParameters implements Serializable
{

	/**
	 * The Constant serialVersionUID.
	 */
	private static final long serialVersionUID = -4343658207707540375L;

	/** The key. */
	private String key;

	/** The parameters. */
	private Object[] parameters;

	/**
	 * {@inheritDoc}
	 */
	@Override
	public boolean equals(final Object o)
	{
		if (this == o)
		{
			return true;
		}
		if (o == null)
		{
			return false;
		}
		if (o.getClass() != getClass())
		{
			return false;
		}
		final PropertiesKeyAndParameters castedObj = (PropertiesKeyAndParameters)o;
		return (this.key == null ? castedObj.key == null : this.key.equals(castedObj.key))
			&& java.util.Arrays.equals(this.parameters, castedObj.parameters);
	}

	/**
	 * Gets the key.
	 *
	 * @return the key
	 */
	public String getKey()
	{
		return key;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public int hashCode()
	{
		int hashCode = 1;
		hashCode = 31 * hashCode + (key == null ? 0 : key.hashCode());
		for (int i0 = 0; parameters != null && i0 < parameters.length; i0++)
		{
			hashCode = 31 * hashCode + (parameters == null ? 0 : parameters[i0].hashCode());
		}
		return hashCode;
	}

	/**
	 * Sets the key.
	 *
	 * @param key
	 *            the new key
	 */
	public void setKey(final String key)
	{
		this.key = key;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public String toString()
	{
		final StringBuilder buffer = new StringBuilder();
		buffer.append("[PropertiesKeyAndParameters:");
		buffer.append(" key: ");
		buffer.append(key);
		if (parameters != null && 0 < parameters.length)
		{
			buffer.append(": Parameters { ");
			for (int i0 = 0; parameters != null && i0 < parameters.length; i0++)
			{
				buffer.append(" parameters[").append(i0).append("]: ").append(parameters[i0]);
			}
			buffer.append(" } ");
		}
		buffer.append("]");
		return buffer.toString();
	}

}
