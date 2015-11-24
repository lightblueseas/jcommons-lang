/**
 * The MIT License
 *
 * Copyright (C) 2007 Asterios Raptis
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

import java.io.IOException;
import java.io.Serializable;
import java.lang.reflect.Array;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.beanutils.BeanComparator;
import org.apache.commons.beanutils.BeanUtils;
import org.apache.log4j.Logger;

import de.alpharogroup.io.ChangedAttributeResult;
import de.alpharogroup.io.SerializedObjectUtils;
import de.alpharogroup.reflection.ReflectionUtils;

/**
 * The Class ObjectExtensions provides methods to clone, copy and compare objects. It also provides
 * methods to find changed data between Objects.
 */
public final class ObjectExtensions
{

	/** The Constant logger. */
	private static final Logger logger = Logger.getLogger(ObjectExtensions.class.getName());

	/**
	 * Try to clone the given generic object.
	 *
	 * @param <T>
	 *            the generic type
	 * @param object
	 *            the object to clone
	 * @return The cloned object or null if the clone process failed.
	 */
	@SuppressWarnings("unchecked")
	public static <T> T clone(final T object)
	{
		return (T)cloneObjectQuietly(object);
	}


	/**
	 * Try to clone the given object.
	 *
	 * @param object
	 *            The object to clone.
	 * @return The cloned object or null if the clone process failed.
	 * @throws NoSuchMethodException
	 *             the no such method exception
	 * @throws SecurityException
	 *             Thrown if the security manager indicates a security violation.
	 * @throws IllegalAccessException
	 *             the illegal access exception
	 * @throws IllegalArgumentException
	 *             the illegal argument exception
	 * @throws InvocationTargetException
	 *             the invocation target exception
	 * @throws ClassNotFoundException
	 *             the class not found exception
	 * @throws InstantiationException
	 *             the instantiation exception
	 * @throws IOException
	 *             Signals that an I/O exception has occurred.
	 */
	public static Object cloneObject(final Object object) throws NoSuchMethodException,
		SecurityException, IllegalAccessException, IllegalArgumentException,
		InvocationTargetException, ClassNotFoundException, InstantiationException, IOException
	{
		Object clone = null;
		// Try to clone the object if it implements Serializable.
		if (object instanceof Serializable)
		{
			clone = SerializedObjectUtils.copySerializedObject((Serializable)object);
			if (clone != null)
			{
				return clone;
			}
		}
		// Try to clone the object if it is Cloneble.
		if (clone == null && object instanceof Cloneable)
		{

			if (object.getClass().isArray())
			{
				final Class<?> componentType = object.getClass().getComponentType();
				if (componentType.isPrimitive())
				{
					int length = Array.getLength(object);
					clone = Array.newInstance(componentType, length);
					while (length-- > 0)
					{
						Array.set(clone, length, Array.get(object, length));
					}
				}
				else
				{
					clone = ((Object[])object).clone();
				}
				if (clone != null)
				{
					return clone;
				}
			}
			final Class<?> clazz = object.getClass();
			final Method cloneMethod = clazz.getMethod("clone", (Class[])null);
			clone = cloneMethod.invoke(object, (Object[])null);
			if (clone != null)
			{
				return clone;
			}
		}
		// Try to clone the object by copying all his properties with
		// the BeanUtils.copyProperties() method.
		if (clone == null)
		{
			clone = ReflectionUtils.getNewInstance(object);
			BeanUtils.copyProperties(clone, object);
		}
		return clone;
	}

	/**
	 * Try to clone the given object quietly.
	 *
	 * @param object
	 *            The object to clone.
	 * @return The cloned object or null if the clone process failed.
	 */
	public static Object cloneObjectQuietly(final Object object)
	{
		Object clone = null;
		try
		{
			clone = cloneObject(object);
		}
		catch (final NoSuchMethodException e)
		{
			logger.error("Try to clone the object with " + "reflection and call the clone method. "
				+ "Thrown exception: NoSuchMethodException", e);
		}
		catch (final SecurityException e)
		{
			logger.error("Try to clone the object with " + "reflection and call the clone method. "
				+ "Thrown exception: SecurityException", e);
		}
		catch (final IllegalAccessException e)
		{
			logger.error("Try to clone the object with "
				+ "org.apache.commons.beanutils.BeanUtils failed "
				+ "cause of IllegalAccessException. Could not found from ReflectionUtils.", e);
		}
		catch (final IllegalArgumentException e)
		{
			logger.error("Try to clone the object with " + "reflection and call the clone method. "
				+ "Thrown exception: IllegalArgumentException", e);
		}
		catch (final InvocationTargetException e)
		{
			logger.error("Try to clone the object with "
				+ "org.apache.commons.beanutils.BeanUtils failed "
				+ "cause of InvocationTargetException. Could not found from ReflectionUtils.", e);
		}
		catch (final ClassNotFoundException e)
		{
			logger.error("Try to clone the object with "
				+ "org.apache.commons.beanutils.BeanUtils failed "
				+ "cause of ClassNotFoundException. Could not found from ReflectionUtils.", e);
		}
		catch (final InstantiationException e)
		{
			logger.error("Try to clone the object with "
				+ "org.apache.commons.beanutils.BeanUtils failed "
				+ "cause of InstantiationException. Could not found from ReflectionUtils.", e);
		}
		catch (final IOException e)
		{
			logger.error("Try to clone the object with "
				+ "SerializedObjectUtils.copySerializedObject((Serializable)object) "
				+ "cause of IOException.", e);
		}
		return clone;
	}

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
			logger.error("BeanUtils.describe(sourceOjbect) throws an exception...", e);
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
			logger.error("BeanUtils.describe(objectToCompare) throws an exception...", e);
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

	/**
	 * Copy the given original object to the given destination object.
	 *
	 * @param <DESTINATION>
	 *            the generic type of the destination object.
	 * @param <ORIGINAL>
	 *            the generic type of the original object.
	 * @param destination
	 *            the destination object.
	 * @param original
	 *            the original object.
	 * @throws IllegalAccessException
	 *             the illegal access exception
	 * @throws InvocationTargetException
	 *             the invocation target exception
	 */
	public static final <DESTINATION, ORIGINAL> void copy(final DESTINATION destination,
		final ORIGINAL original) throws IllegalAccessException, InvocationTargetException
	{
		BeanUtils.copyProperties(destination, original);
	}

	/**
	 * Copy quietly the given original object to the given destination object.
	 *
	 * @param <DESTINATION>
	 *            the generic type of the destination object.
	 * @param <ORIGINAL>
	 *            the generic type of the original object.
	 * @param destination
	 *            the destination object.
	 * @param original
	 *            the original object.
	 * @return the destination object.
	 */
	public static final <DESTINATION, ORIGINAL> DESTINATION copyQuietly(
		final DESTINATION destination, final ORIGINAL original)
	{
		try
		{
			copy(destination, original);
		}
		catch (final IllegalAccessException e)
		{
			return null;
		}
		catch (final InvocationTargetException e)
		{
			return null;
		}
		return destination;
	}

	/**
	 * Gets the changed data.
	 *
	 * @param sourceOjbect
	 *            the source ojbect
	 * @param objectToCompare
	 *            the object to compare
	 * @return the changed data
	 * @throws IllegalAccessException
	 *             the illegal access exception
	 * @throws InvocationTargetException
	 *             the invocation target exception
	 * @throws NoSuchMethodException
	 *             the no such method exception
	 */
	@SuppressWarnings("rawtypes")
	public static List<ChangedAttributeResult> getChangedData(final Object sourceOjbect,
		final Object objectToCompare) throws IllegalAccessException, InvocationTargetException,
		NoSuchMethodException
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
		final List<ChangedAttributeResult> changedData = new ArrayList<ChangedAttributeResult>();
		for (final Object key : beanDescription.keySet())
		{
			if (compareTo(sourceOjbect, objectToCompare, key.toString()) != 0)
			{
				final Object sourceAttribute = beanDescription.get(key);
				final Object changedAttribute = clonedBeanDescription.get(key);
				changedData.add(new ChangedAttributeResult(key, sourceAttribute, changedAttribute));
			}
		}
		return changedData;
	}

	/**
	 * Compares the given two objects and gets the changed data.
	 *
	 * @param sourceOjbect
	 *            the source ojbect
	 * @param objectToCompare
	 *            the object to compare
	 * @return the changed data
	 * @throws IllegalAccessException
	 *             the illegal access exception
	 * @throws InvocationTargetException
	 *             the invocation target exception
	 * @throws NoSuchMethodException
	 *             the no such method exception
	 */
	@SuppressWarnings("rawtypes")
	public static Map<Object, ChangedAttributeResult> getChangedDataMap(final Object sourceOjbect,
		final Object objectToCompare) throws IllegalAccessException, InvocationTargetException,
		NoSuchMethodException
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
		final Map<Object, ChangedAttributeResult> changedData = new HashMap<Object, ChangedAttributeResult>();
		for (final Object key : beanDescription.keySet())
		{
			final Object sourceAttribute = beanDescription.get(key);
			final Object changedAttribute = clonedBeanDescription.get(key);
			if (compareTo(sourceOjbect, objectToCompare, key.toString()) != 0)
			{
				changedData.put(key, new ChangedAttributeResult(key, sourceAttribute,
					changedAttribute));
			}
		}
		return changedData;
	}

	/**
	 * Gets the compare to result.
	 *
	 * @param sourceOjbect
	 *            the source ojbect
	 * @param objectToCompare
	 *            the object to compare
	 * @return the compare to result
	 * @throws IllegalAccessException
	 *             the illegal access exception
	 * @throws InvocationTargetException
	 *             the invocation target exception
	 * @throws NoSuchMethodException
	 *             the no such method exception
	 */
	@SuppressWarnings("rawtypes")
	public static Map<String, Integer> getCompareToResult(final Object sourceOjbect,
		final Object objectToCompare) throws IllegalAccessException, InvocationTargetException,
		NoSuchMethodException
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
		final Map<String, Integer> compareResult = new HashMap<String, Integer>();
		for (final Object key : beanDescription.keySet())
		{
			compareResult.put(key.toString(),
				Integer.valueOf(compareTo(sourceOjbect, objectToCompare, key.toString())));
		}
		return compareResult;
	}

	/**
	 * Checks if is copyable and copies if its possible otherwise it returns false.
	 *
	 * @param <DESTINATION>
	 *            the generic type of the destination object.
	 * @param <ORIGINAL>
	 *            the generic type of the original object.
	 * @param destination
	 *            the destination object.
	 * @param original
	 *            the original object.
	 * @return true, if is copyable otherwise false.
	 */
	public static final <DESTINATION, ORIGINAL> boolean isCopyable(final DESTINATION destination,
		final ORIGINAL original)
	{
		return copyQuietly(destination, original) != null;
	}

	/**
	 * Private constructor.
	 */
	private ObjectExtensions()
	{
		super();
	}

}
