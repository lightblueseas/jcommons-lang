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

import java.util.Collection;

/**
 * A class implements the {@link Summarizer} interface indicates that it can merge single or a
 * collection of objects in with each other.
 *
 * @param <T>
 *            the type of objects that may be merged
 */
public interface Summarizer<T>
{

	/**
	 * Merge the given <code>collection</code> with the <code>objects</code> that can be merged and
	 * return the resulted merged <code>objects</code> in a <code>collection</code>.
	 *
	 * @param collection
	 *            the <code>collection</code> with the <code>objects</code> to merge
	 * @return the collection
	 */
	Collection<T> merge(Collection<T> collection);

	/**
	 * Merge the given <code>object</code> with <code>other</code> object.
	 *
	 * @param object
	 *            the object to merge in
	 * @param other
	 *            the other object to merge with
	 * @return the merged object or null if it isn't possible
	 */
	T merge(T object, T other);
}
