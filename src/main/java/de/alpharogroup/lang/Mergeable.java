package de.alpharogroup.lang;

/**
 * A class implements the {@link Mergeable} interface indicates that it can be merged with an other
 * object of its type.
 *
 * @param <T>
 *            the type of objects that this object may be merged with
 */
public interface Mergeable<T>
{
	/**
	 * Merge the given <code>object</code> with <code>this object</code>.
	 *
	 * @param object
	 *            the object to merge with this one
	 */
	public void merge(T object);
}
