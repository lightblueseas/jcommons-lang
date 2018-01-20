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
package de.alpharogroup.enums;

import de.alpharogroup.check.Check;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;
import lombok.experimental.FieldDefaults;

/**
 * The class {@link CustomizableEnum} can decorate an enum with a new value that may be extended in
 * a future release.
 */
@Getter
@Builder
@EqualsAndHashCode
@ToString
@FieldDefaults(level = AccessLevel.PRIVATE)
public final class CustomizableEnum<E extends Enum<E>, T>
{

	/** The enumtype. */
	final E enumtype;

	/** The value. */
	final T value;

	/**
	 * Instantiates a new {@link CustomizableEnum}.
	 *
	 * @param enumtype
	 *            the type of the enum
	 * @param value
	 *            the value
	 */
	public CustomizableEnum(final E enumtype, final T value)
	{
		if (enumtype == null)
		{
			Check.get().notNull(value, "value");
		}
		this.enumtype = enumtype;
		this.value = value;
	}

	/**
	 * Returns the name of the decorated enum or if it is a new value it will return the result of
	 * the toString method of value
	 *
	 * @return the name of the decorated enum or if it is a new value it will return the result of
	 *         the toString method of value
	 */
	public String name()
	{
		if (enumtype != null)
		{
			return enumtype.name();
		}
		return value.toString();
	}
}
