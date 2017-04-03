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
