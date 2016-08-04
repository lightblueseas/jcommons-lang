package de.alpharogroup.lang.object;

/**
 * The class {@link MergeObjectExtensions} provide extension methods for merge a source object with another
 *  object.
 */
public final class MergeObjectExtensions
{
  
	/**
	 * Merge the given to object with the given destination object.
	 *
	 * @param <DESTINATION>
	 *            the generic type of the destination object.
	 * @param <ORIGINAL>
	 *            the generic type of the original object.
	 * @param original
	 *            the original object.
	 * @param destination
	 *            the destination object.
	 * @return the destination object.
	 * @throws IllegalAccessException
	 *             if the caller does not have access to the property accessor method
	 * @throws InvocationTargetException
	 *             if the property accessor method throws an exception
	 * @throws IllegalArgumentException
	 *             if the <code>destination</code> or <code>original</code> argument is null or if
	 *             the <code>destination</code> property type is different from the source type and
	 *             the relevant converter has not been registered.
	 */
	public static final <ORIGINAL, DESTINATION> DESTINATION merge(final DESTINATION to, final ORIGINAL with)
			throws IllegalAccessException, InvocationTargetException, IllegalArgumentException
	{
		BeanUtils.copyProperties(destination, original);
		return destination;
	}
}
