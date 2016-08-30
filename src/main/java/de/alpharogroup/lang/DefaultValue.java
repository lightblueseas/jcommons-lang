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
package de.alpharogroup.lang;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import de.alpharogroup.check.Check;
import lombok.experimental.UtilityClass;

/**
 * The class DefaultValue provide the default values of the primitive types, as defined by the JLS.
 */
@UtilityClass
public class DefaultValue
{

	/** The constant map with the default values. */
	@SuppressWarnings("serial")
	private static final Map<Class<?>, Object> DEFAULT_VALUE = Collections
		.unmodifiableMap(new HashMap<Class<?>, Object>()
		{
			{
				put(boolean.class, false);
				put(char.class, '\0');
				put(byte.class, (byte)0);
				put(short.class, (short)0);
				put(int.class, 0);
				put(long.class, 0L);
				put(float.class, 0f);
				put(double.class, 0d);
			}
		});

	/**
	 * Gets the default value from the given {@link Class}.
	 *
	 * @param <T>
	 *            the generic type
	 * @param classType
	 *            the class type
	 * @return the default value
	 */
	@SuppressWarnings("unchecked")
	public static <T> T getDefaultValue(final Class<T> classType)
	{
		Check.get().notNull(classType, "classType");
		final T defaultValue = (T)DEFAULT_VALUE.get(classType);
		return defaultValue;
	}
}
