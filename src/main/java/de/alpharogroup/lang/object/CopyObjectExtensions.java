package de.alpharogroup.lang.object;

import java.lang.reflect.InvocationTargetException;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.log4j.Logger;

import de.alpharogroup.exception.ExceptionExtensions;
import lombok.experimental.UtilityClass;

/**
 * The class {@link CopyObjectExtensions} provide methods for copy an original object to a given
 * destination object.
 */
@UtilityClass
public final class CopyObjectExtensions
{

	/** The logger constant. */
	private static final Logger LOG = Logger.getLogger(CopyObjectExtensions.class.getName());

	/**
	 * Copy the given original object to the given destination object.
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
	public static final <ORIGINAL, DESTINATION> DESTINATION copy(final ORIGINAL original,
		final DESTINATION destination)
		throws IllegalAccessException, InvocationTargetException, IllegalArgumentException
	{
		BeanUtils.copyProperties(destination, original);
		return destination;
	}

	/**
	 * Copy quietly the given original object to the given destination object.
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
	 */
	public static final <ORIGINAL, DESTINATION> DESTINATION copyQuietly(final ORIGINAL original,
		final DESTINATION destination)
	{
		try
		{
			copy(original, destination);
		}
		catch (final IllegalAccessException e)
		{
			LOG.error(
				"Error occured by try to copy the original object to destination object."
					+ "\noriginal object info:" + ExceptionExtensions.toString(original)
					+ "\ndestination object info:" + ExceptionExtensions.toString(destination)
					+ "\n Possible reason: a caller does not have access to the property accessor method",
				e);
			return null;
		}
		catch (final InvocationTargetException e)
		{
			LOG.error("Error occured by try to copy the original object to destination object."
				+ "\noriginal object info:" + ExceptionExtensions.toString(original)
				+ "\ndestination object info:" + ExceptionExtensions.toString(destination)
				+ "\n Possible reason: if the property accessor method throws an exception", e);
			return null;
		}
		catch (final IllegalArgumentException e)
		{
			LOG.error("Error occured by try to copy the original object to destination object."
				+ "\noriginal object info:" + ExceptionExtensions.toString(original)
				+ "\ndestination object info:" + ExceptionExtensions.toString(destination)
				+ "\n Possible reason: if the destination or original argument is null or if"
				+ " the destination property type is different from the source type and the "
				+ "relevant converter has not been registered.", e);
			return null;
		}
		return destination;
	}

	/**
	 * Checks if is copyable and copies if its possible otherwise it returns false.
	 *
	 * @param <DESTINATION>
	 *            the generic type of the destination object.
	 * @param <ORIGINAL>
	 *            the generic type of the original object.
	 * @param original
	 *            the original object.
	 * @param destination
	 *            the destination object.
	 * @return true, if is copyable otherwise false.
	 */
	public static final <DESTINATION, ORIGINAL> boolean isCopyable(final ORIGINAL original,
		final DESTINATION destination)
	{
		return copyQuietly(original, destination) != null;
	}

}
