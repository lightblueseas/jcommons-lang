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
	public int compare(Triple<L, M, R> o1, Triple<L, M, R> o2)
	{
		return new CompareToBuilder().append(o1.getLeft(), o2.getLeft())
			.append(o1.getMiddle(), o2.getMiddle()).append(o1.getRight(), o2.getRight())
			.toComparison();
	}

}
