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

import java.io.Serializable;

import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * The class {@link SerializedChangedAttributeResult} is a bean class that is used for compare
 * objects and see what changes are made.
 */
@Getter
@Setter
@EqualsAndHashCode
@ToString
@Builder(toBuilder = true)
public class SerializedChangedAttributeResult implements Serializable
{

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;

	/** The attribute name. */
	private Object attributeName;

	/** The source attribute. */
	private Object sourceAttribute;

	/** The changed attribute. */
	private Object changedAttribute;

	/**
	 * Instantiates a new changed attribute result.
	 *
	 * @param attributeName
	 *            the attribute name
	 * @param sourceAttribute
	 *            the source attribute
	 * @param changedAttribute
	 *            the changed attribute
	 */
	public SerializedChangedAttributeResult(final Object attributeName,
		final Object sourceAttribute, final Object changedAttribute)
	{
		if (!(attributeName instanceof Serializable) || !(sourceAttribute instanceof Serializable)
			|| !(changedAttribute instanceof Serializable))
		{
			throw new IllegalArgumentException(
				"Arguments should implement the Serializable interface.");
		}
		this.attributeName = attributeName;
		this.sourceAttribute = sourceAttribute;
		this.changedAttribute = changedAttribute;
	}

}
