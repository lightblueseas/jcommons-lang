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

/**
 * The Class ChangedAttributeResult.
 */
public class ChangedAttributeResult
{

	/** The attribute name. */
	private Object attributeName;

	/** The source attribute. */
	private Object sourceAttribute;

	/** The changed attribute. */
	private Object changedAttribute;

	/**
	 * Instantiates a new changed attribute result.
	 */
	public ChangedAttributeResult()
	{
		super();
	}

	/**
	 * Instantiates a new changed attribute result.
	 *
	 * @param sourceAttribute
	 *            the source attribute
	 * @param changedAttribute
	 *            the changed attribute
	 */
	public ChangedAttributeResult(final Object sourceAttribute, final Object changedAttribute)
	{
		this();
		this.sourceAttribute = sourceAttribute;
		this.changedAttribute = changedAttribute;
	}

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
	public ChangedAttributeResult(final Object attributeName, final Object sourceAttribute,
		final Object changedAttribute)
	{
		this(sourceAttribute, changedAttribute);
		this.attributeName = attributeName;
	}

	/**
	 * Gets the attribute name.
	 *
	 * @return the attribute name
	 */
	public Object getAttributeName()
	{
		return attributeName;
	}

	/**
	 * Gets the changed attribute.
	 *
	 * @return the changed attribute
	 */
	public Object getChangedAttribute()
	{
		return changedAttribute;
	}

	/**
	 * Gets the source attribute.
	 *
	 * @return the source attribute
	 */
	public Object getSourceAttribute()
	{
		return sourceAttribute;
	}

	/**
	 * Sets the attribute name.
	 *
	 * @param attributeName
	 *            the new attribute name
	 */
	public void setAttributeName(final Object attributeName)
	{
		this.attributeName = attributeName;
	}

	/**
	 * Sets the changed attribute.
	 *
	 * @param changedAttribute
	 *            the new changed attribute
	 */
	public void setChangedAttribute(final Object changedAttribute)
	{
		this.changedAttribute = changedAttribute;
	}

	/**
	 * Sets the source attribute.
	 *
	 * @param sourceAttribute
	 *            the new source attribute
	 */
	public void setSourceAttribute(final Object sourceAttribute)
	{
		this.sourceAttribute = sourceAttribute;
	}

}
