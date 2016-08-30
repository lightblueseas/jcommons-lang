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
package de.alpharogroup.lang.object;

import java.beans.PropertyDescriptor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.beanutils.PropertyUtils;

import de.alpharogroup.check.Check;
import de.alpharogroup.io.ChangedAttributeResult;
import de.alpharogroup.lang.ObjectExtensions;
import lombok.experimental.ExtensionMethod;
import lombok.experimental.UtilityClass;

/**
 * The class {@link MergeObjectExtensions} provide extension methods for merge a source object with
 * another object.
 */
@UtilityClass
@ExtensionMethod(ObjectExtensions.class)
public final class MergeObjectExtensions
{

	/**
	 * Merge the given to object with the given 'with' object.
	 *
	 * @param <TO>
	 *            the generic type of the object to merge in
	 * @param <WITH>
	 *            the generic type of the object to merge with
	 * @param toObject
	 *            the object to merge in
	 * @param withObject
	 *            the object to merge with
	 * @throws InvocationTargetException
	 *             if the property accessor method throws an exception
	 * @throws IllegalAccessException
	 *             if the caller does not have access to the property accessor method
	 */
	public static final <TO, WITH> void merge(final TO toObject, final WITH withObject)
		throws InvocationTargetException, IllegalAccessException
	{
		Check.get().notNull(toObject, "toObject").notNull(withObject, "toObject");

		final Class<?> toClass = toObject.getClass();

		final PropertyDescriptor[] propertyDescriptors = PropertyUtils
			.getPropertyDescriptors(toClass);

		for (final PropertyDescriptor descriptor : propertyDescriptors)
		{
			mergeProperty(toObject, withObject, descriptor);
		}
	}

	/**
	 * Merge the given property to the given 'to' object with the given 'with' object.
	 *
	 * @param <TO>
	 *            the generic type of the object to merge in
	 * @param <WITH>
	 *            the generic type of the object to merge with
	 * @param toObject
	 *            the object to merge in
	 * @param withObject
	 *            the object to merge with
	 * @param propertyDescriptor
	 *            the property descriptor
	 * @throws InvocationTargetException
	 *             if the property accessor method throws an exception
	 * @throws IllegalAccessException
	 *             if the caller does not have access to the property accessor method
	 */
	public static final <TO, WITH> void mergeProperty(final TO toObject, final WITH withObject,
		final PropertyDescriptor propertyDescriptor)
		throws IllegalAccessException, InvocationTargetException
	{
		if (PropertyUtils.isReadable(toObject, propertyDescriptor.getName())
			&& PropertyUtils.isWriteable(toObject, propertyDescriptor.getName()))
		{
			final Method getter = propertyDescriptor.getReadMethod();
			final Object value = getter.invoke(withObject);
			if (!value.isDefaultValue())
			{
				final Method setter = propertyDescriptor.getWriteMethod();
				setter.invoke(toObject, value);
			}
		}
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
	 *             Thrown if this {@code Method} object is enforcing Java language access control
	 *             and the underlying method is inaccessible.
	 * @throws InvocationTargetException
	 *             Thrown if the property accessor method throws an exception
	 * @throws NoSuchMethodException
	 *             Thrown if this {@code Method} object is enforcing Java language access control
	 *             and the underlying method is inaccessible.
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
			if (CompareObjectExtensions.compareTo(sourceOjbect, objectToCompare,
				key.toString()) != 0)
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
			if (CompareObjectExtensions.compareTo(sourceOjbect, objectToCompare,
				key.toString()) != 0)
			{
				changedData.put(key,
					new ChangedAttributeResult(key, sourceAttribute, changedAttribute));
			}
		}
		return changedData;
	}

}
