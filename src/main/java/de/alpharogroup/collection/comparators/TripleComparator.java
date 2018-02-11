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
package de.alpharogroup.collection.comparators;

import java.util.Comparator;

import org.apache.commons.lang3.builder.CompareToBuilder;

import de.alpharogroup.collections.pairs.Triple;

/**
 * The class {@link TripleComparator} is a comparator for {@link Triple} objects.
 *
 * @param <L>
 *            The type of the left content of this Triple.
 * @param <M>
 *            The type of the middle content of this Triple.
 * @param <R>
 *            The type of the right content of this Triple.
 */
public class TripleComparator<L, M, R> implements Comparator<Triple<L, M, R>>
{

	/**
	 * {@inheritDoc}
	 */
	@Override
	public int compare(final Triple<L, M, R> o1, final Triple<L, M, R> o2)
	{
		return new CompareToBuilder().append(o1.getLeft(), o2.getLeft())
			.append(o1.getMiddle(), o2.getMiddle()).append(o1.getRight(), o2.getRight())
			.toComparison();
	}

}
