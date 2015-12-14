package de.alpharogroup.lang.object;

import java.lang.reflect.InvocationTargetException;
import java.util.Map;

import org.apache.commons.beanutils.BeanComparator;
import org.apache.commons.beanutils.BeanUtils;
import org.apache.log4j.Logger;

/**
 * The class {@link CompareObjectExtensions} provide methods for compare an object with another given object.
 */
public final class CompareObjectExtensions
{

	/** The logger constant. */
	private static final Logger LOG = Logger.getLogger(CompareObjectExtensions.class.getName());

	/**
	 * Compares the given two objects.
	 *
	 * @param sourceOjbect
	 *            the source ojbect
	 * @param objectToCompare
	 *            the object to compare
	 * @return true, if successful otherwise false
	 * @throws IllegalAccessException
	 *             the illegal access exception
	 * @throws InvocationTargetException
	 *             the invocation target exception
	 * @throws NoSuchMethodException
	 *             the no such method exception
	 */
	@SuppressWarnings("rawtypes")
	public static boolean compare(final Object sourceOjbect, final Object objectToCompare)
		throws IllegalAccessException, InvocationTargetException, NoSuchMethodException
	{
		if (sourceOjbect == null || objectToCompare == null
			|| !sourceOjbect.getClass().equals(objectToCompare.getClass()))
		{
			throw new IllegalArgumentException("Object should not be null and be the same type.");
		}
		final Map beanDescription = BeanUtils.describe(sourceOjbect);
		beanDescription.remove("class");
		final Map clonedBeanDescription = BeanUtils.describe(objectToCompare);
		clonedBeanDescription.remove("class");
		for (final Object key : beanDescription.keySet())
		{
			if (compareTo(sourceOjbect, objectToCompare, key.toString()) != 0)
			{
				return false;
			}
		}
		return true;
	}

	/**
	 * Compares the given two objects and returns the result as int.
	 *
	 * @param sourceOjbect
	 *            the source ojbect
	 * @param objectToCompare
	 *            the object to compare
	 * @return true, if successful otherwise false
	 * @throws IllegalAccessException
	 *             the illegal access exception
	 * @throws InvocationTargetException
	 *             the invocation target exception
	 * @throws NoSuchMethodException
	 *             the no such method exception
	 */
	@SuppressWarnings("rawtypes")
	public static int compareTo(final Object sourceOjbect, final Object objectToCompare)
		throws IllegalAccessException, InvocationTargetException, NoSuchMethodException
	{
		if (sourceOjbect == null || objectToCompare == null
			|| !sourceOjbect.getClass().equals(objectToCompare.getClass()))
		{
			throw new IllegalArgumentException("Object should not be null and be the same type.");
		}
		final Map beanDescription = BeanUtils.describe(sourceOjbect);
		beanDescription.remove("class");
		final Map clonedBeanDescription = BeanUtils.describe(objectToCompare);
		clonedBeanDescription.remove("class");
		int result = 0;
		for (final Object key : beanDescription.keySet())
		{
			result = compareTo(sourceOjbect, objectToCompare, key.toString());
			if (result == 0)
			{
				continue;
			}
		}
		return result;
	}

	/**
	 * Compares the given object over the given property.
	 *
	 * @param sourceOjbect
	 *            the source ojbect
	 * @param objectToCompare
	 *            the object to compare
	 * @param property
	 *            the property
	 * @return the int
	 * @throws IllegalAccessException
	 *             the illegal access exception
	 * @throws InvocationTargetException
	 *             the invocation target exception
	 * @throws NoSuchMethodException
	 *             the no such method exception
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public static int compareTo(final Object sourceOjbect, final Object objectToCompare,
		final String property) throws IllegalAccessException, InvocationTargetException,
		NoSuchMethodException
	{
		final Map<?, ?> beanDescription = BeanUtils.describe(sourceOjbect);
		beanDescription.remove("class");
		final Map<?, ?> clonedBeanDescription = BeanUtils.describe(objectToCompare);
		clonedBeanDescription.remove("class");
		final Object sourceAttribute = beanDescription.get(property);
		final Object changedAttribute = clonedBeanDescription.get(property);
		if (sourceAttribute == null && changedAttribute == null)
		{
			return 0;
		}
		if (sourceAttribute != null && changedAttribute == null)
		{
			return 1;
		}
		else if (sourceAttribute == null && changedAttribute != null)
		{
			return -1;
		}
		return new BeanComparator(property).compare(sourceOjbect, objectToCompare);
	}

	/**
	 * Compares the given object over the given property quietly.
	 *
	 * @param sourceOjbect
	 *            the source ojbect
	 * @param objectToCompare
	 *            the object to compare
	 * @param property
	 *            the property
	 * @return the int
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public static int compareToQuietly(final Object sourceOjbect, final Object objectToCompare,
		final String property)
	{
		Map<?, ?> beanDescription = null;
		try
		{
			beanDescription = BeanUtils.describe(sourceOjbect);
		}
		catch (IllegalAccessException | InvocationTargetException | NoSuchMethodException e)
		{
			LOG.error("BeanUtils.describe(sourceOjbect) throws an exception...", e);
			return 0;
		}
		beanDescription.remove("class");
		Map<?, ?> clonedBeanDescription = null;
		try
		{
			clonedBeanDescription = BeanUtils.describe(objectToCompare);
		}
		catch (IllegalAccessException | InvocationTargetException | NoSuchMethodException e)
		{
			LOG.error("BeanUtils.describe(objectToCompare) throws an exception...", e);
			return 0;
		}
		clonedBeanDescription.remove("class");
		final Object sourceAttribute = beanDescription.get(property);
		final Object changedAttribute = clonedBeanDescription.get(property);
		if (sourceAttribute == null && changedAttribute == null)
		{
			return 0;
		}
		if (sourceAttribute != null && changedAttribute == null)
		{
			return 1;
		}
		else if (sourceAttribute == null && changedAttribute != null)
		{
			return -1;
		}
		return new BeanComparator(property).compare(sourceOjbect, objectToCompare);
	}
}
