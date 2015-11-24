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
package de.alpharogroup.lang;

import java.lang.reflect.InvocationTargetException;

import lombok.experimental.UtilityClass;

import org.apache.commons.beanutils.BeanUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


/**
 * The Class BeanExtensions provides methods for populating JavaBeans properties. Uses internally
 * the commons-beanutils classes.
 */
@UtilityClass
public final class BeanExtensions
{

	/** The Constant LOGGER. */
	protected static final Logger LOGGER = LoggerFactory.getLogger(BeanExtensions.class);

	/**
	 * <p>
	 * Set the specified property value, performing type conversions as required to conform to the
	 * type of the destination property quietly.
	 * </p>
	 *
	 * @param bean
	 *            Bean on which setting is to be performed
	 * @param name
	 *            Property name (can be nested/indexed/mapped/combo)
	 * @param value
	 *            Value to be set
	 *
	 * @see BeanUtils#setProperty
	 */
	public static void setPropertyQuietly(final Object bean, final String name, final Object value)
	{
		try
		{
			BeanUtils.setProperty(bean, name, value);
		}
		catch (IllegalAccessException | InvocationTargetException e)
		{
			LOGGER.error("Bean failed to set property.", e);
		}
	}

}
