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
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.beanutils.BeanUtils;

import de.alpharogroup.io.ChangedAttributeResult;
import de.alpharogroup.lang.object.CloneObjectExtensions;
import de.alpharogroup.lang.object.CompareObjectExtensions;
import de.alpharogroup.lang.object.CopyObjectExtensions;

/**
 * The Class ObjectExtensions provides methods to clone, copy and compare objects. It also provides
 * methods to find changed data between Objects.
 */
public final class ObjectExtensions
{

	/**
	 * Try to clone the given generic object.
	 *
	 * @param <T>
	 *            the generic type
	 * @param object
	 *            the object to clone
	 * @return The cloned object or null if the clone process failed.
	 *
	 * @deprecated use instead {@link CloneObjectExtensions#clone(Object)}
	 */
	@Deprecated
	public static <T> T clone(final T object)
	{
		return CloneObjectExtensions.clone(object);
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
	 * @deprecated use instead {@link CloneObjectExtensions#cloneObject(Object)}
	 */
	@Deprecated
	public static Object cloneObject(final Object object) throws NoSuchMethodException,
		SecurityException, IllegalAccessException, IllegalArgumentException,
		InvocationTargetException, ClassNotFoundException, InstantiationException, IOException
	{
		return CloneObjectExtensions.cloneObject(object);
	}

	/**
	 * Try to clone the given object quietly.
	 *
	 * @param object
	 *            The object to clone.
	 * @return The cloned object or null if the clone process failed.
	 * @deprecated use instead {@link CloneObjectExtensions#cloneObjectQuietly(Object)}
	 */
	@Deprecated
	public static Object cloneObjectQuietly(final Object object)
	{
		return CloneObjectExtensions.cloneObjectQuietly(object);
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
	 * @deprecated use instead {@link CompareObjectExtensions#compare(Object, Object)}
	 */
	@Deprecated
	public static boolean compare(final Object sourceOjbect, final Object objectToCompare)
		throws IllegalAccessException, InvocationTargetException, NoSuchMethodException
	{
		return CompareObjectExtensions.compare(sourceOjbect, objectToCompare);
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
	 * @deprecated use instead {@link CompareObjectExtensions#compareTo(Object, Object, String)}
	 */
	@Deprecated
	public static int compareTo(final Object sourceOjbect, final Object objectToCompare,
		final String property)
		throws IllegalAccessException, InvocationTargetException, NoSuchMethodException
	{
		return CompareObjectExtensions.compareTo(sourceOjbect, objectToCompare, property);
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
	 * @deprecated use instead
	 *             {@link CompareObjectExtensions#compareToQuietly(Object, Object, String)}
	 */
	@Deprecated
	public static int compareToQuietly(final Object sourceOjbect, final Object objectToCompare,
		final String property)
	{
		return CompareObjectExtensions.compareToQuietly(sourceOjbect, objectToCompare, property);
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
	 * @deprecated use instead {@link CopyObjectExtensions#copy(Object, Object)}
	 */
	@Deprecated
	public static final <DESTINATION, ORIGINAL> void copy(final DESTINATION destination,
		final ORIGINAL original) throws IllegalAccessException, InvocationTargetException
	{
		CopyObjectExtensions.copy(original, destination);
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
	 * @deprecated use instead {@link CopyObjectExtensions#copyQuietly(Object, Object)}
	 */
	@Deprecated
	public static final <DESTINATION, ORIGINAL> DESTINATION copyQuietly(
		final DESTINATION destination, final ORIGINAL original)
	{
		return CopyObjectExtensions.copyQuietly(original, destination);
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
		final Object objectToCompare)
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
		final List<ChangedAttributeResult> changedData = new ArrayList<>();
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
		final Object objectToCompare)
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
		final Map<Object, ChangedAttributeResult> changedData = new HashMap<>();
		for (final Object key : beanDescription.keySet())
		{
			final Object sourceAttribute = beanDescription.get(key);
			final Object changedAttribute = clonedBeanDescription.get(key);
			if (compareTo(sourceOjbect, objectToCompare, key.toString()) != 0)
			{
				changedData.put(key,
					new ChangedAttributeResult(key, sourceAttribute, changedAttribute));
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
		final Object objectToCompare)
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
		final Map<String, Integer> compareResult = new HashMap<>();
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
	 * @deprecated use instead {@link CopyObjectExtensions#isCopyable(Object, Object)}
	 */
	@Deprecated
	public static final <DESTINATION, ORIGINAL> boolean isCopyable(final DESTINATION destination,
		final ORIGINAL original)
	{
		return CopyObjectExtensions.isCopyable(original, destination);
	}

	/**
	 * Checks if the given object has the default value.
	 *
	 * @param <T>
	 *            the generic type
	 * @param object
	 *            the object
	 * @return true, if is default value
	 */
	public static final <T> boolean isDefaultValue(final T object)
	{
		if (object == null)
		{
			return true;
		}
		final Class<?> fooFieldClass = object.getClass();
		final ClassType classType = ClassExtensions.getClassType(fooFieldClass);
		if (ClassType.PRIMITIVE.equals(classType))
		{
			return DefaultValue.getDefaultValue(fooFieldClass).equals(object);
		}
		return false;
	}

	/**
	 * Checks if the given object has not the default value.
	 *
	 * @param <T>
	 *            the generic type
	 * @param object
	 *            the object
	 * @return true, if the given object has not the default value
	 */
	public static final <T> boolean isNotDefaultValue(final T object)
	{
		return !isDefaultValue(object);
	}

	/**
	 * Private constructor.
	 */
	private ObjectExtensions()
	{
	}

}
