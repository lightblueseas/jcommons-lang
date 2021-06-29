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
package de.alpharogroup.io.annotations;

import java.io.Serializable;
import java.util.Comparator;

import de.alpharogroup.comparators.ComparatorExtensions;

/**
 * The class {@link ImportResourceComparator} compares two given {@link ImportResource} objects
 * based on the index.
 * @deprecated moved to silly-io repository
 */
public class ImportResourceComparator implements Comparator<ImportResource>, Serializable
{

	/**
	 * The serialVersionUID.
	 */
	private static final long serialVersionUID = 6972397205717174979L;

	/**
	 * {@inheritDoc}
	 */
	@Override
	public int compare(final ImportResource object, final ImportResource compareWithObject)
	{
		final Integer nullCheck = ComparatorExtensions.nullCheck(object, compareWithObject);
		if (nullCheck != null)
		{
			return nullCheck;
		}
		return ComparatorExtensions.compare(object.index(), compareWithObject.index());
	}

}
