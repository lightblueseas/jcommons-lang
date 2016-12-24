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
import org.apache.log4j.Logger;

import de.alpharogroup.check.Check;
import de.alpharogroup.exception.ExceptionExtensions;
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

	/** The logger constant. */
	private static final Logger LOG = Logger.getLogger(MergeObjectExtensions.class.getName());

	/**
	 * Merge the given to object with the given 'with' object.
	 *
	 * @param <MERGE_IN>
	 *            the generic type of the object to merge in
	 * @param <WITH>
	 *            the generic type of the object to merge with
	 * @param mergeInObject
	 *            the object to merge in
	 * @param withObject
	 *            the object to merge with
	 * @return the merged object.
	 * @throws InvocationTargetException
	 *             if the property accessor method throws an exception
	 * @throws IllegalAccessException
	 *             if the caller does not have access to the property accessor method
	 * @throws IllegalArgumentException
	 *             if the <code>mergeInObject</code> or <code>withObject</code> argument is null or if
	 *             the <code>mergeInObject</code> property type is different from the source type and
	 *             the relevant converter has not been registered.
	 */
	public static final <MERGE_IN, WITH> MERGE_IN merge(final MERGE_IN mergeInObject, final WITH withObject)
		throws InvocationTargetException, IllegalAccessException, IllegalArgumentException
	{
		Check.get().notNull(mergeInObject, "mergeInObject").notNull(withObject, "withObject");

		final Class<?> toClass = mergeInObject.getClass();

		final PropertyDescriptor[] propertyDescriptors = PropertyUtils
			.getPropertyDescriptors(toClass);

		for (final PropertyDescriptor descriptor : propertyDescriptors)
		{
			mergeProperty(mergeInObject, withObject, descriptor);
		}
		return mergeInObject;
	}

	/**
	 * Merge quietly the given merge in object(destination) with the given 'with' object.
	 *
	 * @param <MERGE_IN>
	 *            the generic type of the object to merge in
	 * @param <WITH>
	 *            the generic type of the object to merge with
	 * @param mergeInObject
	 *            the object to merge in
	 * @param withObject
	 *            the object to merge with
	 * @return the merged object or null if the merge process failed.
	 */
	public static final <MERGE_IN, WITH> MERGE_IN mergeQuietly(final MERGE_IN mergeInObject, final WITH withObject)
	{
		try
		{
			return merge(mergeInObject, withObject);
		}
		catch (final InvocationTargetException e)
		{
			LOG.error("Error occured by try to merge the original object to destination object."
				+ "\noriginal object info:" + ExceptionExtensions.toString(withObject)
				+ "\ndestination object info:" + ExceptionExtensions.toString(mergeInObject)
				+ "\n Possible reason: if the property accessor method throws an exception", e);
			return null;
		}
		catch (final IllegalAccessException e)
		{
			LOG.error(
				"Error occured by try to merge the original object to destination object."
					+ "\noriginal object info:" + ExceptionExtensions.toString(withObject)
					+ "\ndestination object info:" + ExceptionExtensions.toString(mergeInObject)
					+ "\n Possible reason: a caller does not have access to the property accessor method",
				e);
			return null;
		}
		catch (final IllegalArgumentException e)
		{
			LOG.error("Error occured by try to merge the original object to destination object."
				+ "\noriginal object info:" + ExceptionExtensions.toString(withObject)
				+ "\ndestination object info:" + ExceptionExtensions.toString(mergeInObject)
				+ "\n Possible reason: if the destination or original argument is null or if"
				+ " the destination property type is different from the source type and the "
				+ "relevant converter has not been registered.", e);
			return null;
		}
	}

	/**
	 * Try first to merge quietly the given merge in object(destination) with the given 'with' object, if this fails try to copy.
	 *
	 * @param <MERGE_IN>
	 *            the generic type of the object to merge in
	 * @param <WITH>
	 *            the generic type of the object to merge with
	 * @param mergeInObject
	 *            the object to merge in
	 * @param withObject
	 *            the object to merge with
	 * @return the merged object or null if the merge process failed.
	 */
	public static final <MERGE_IN, WITH> MERGE_IN mergeOrCopyQuietly(final MERGE_IN mergeInObject, final WITH withObject)
	{
		MERGE_IN merged = mergeQuietly(mergeInObject, withObject);
		if (merged == null)
		{
			merged = CopyObjectExtensions.copyQuietly(withObject, mergeInObject);
		}
		return merged;
	}

	/**
	 * Merge the given property to the given 'to' object with the given 'with' object.
	 *
	 * @param <MERGE_IN>
	 *            the generic type of the object to merge in
	 * @param <WITH>
	 *            the generic type of the object to merge with
	 * @param mergeInObject
	 *            the object to merge in
	 * @param withObject
	 *            the object to merge with
	 * @param propertyDescriptor
	 *            the property descriptor
	 * @throws InvocationTargetException
	 *             if the property accessor method throws an exception
	 * @throws IllegalAccessException
	 *             if the caller does not have access to the property accessor method
	 * @throws IllegalArgumentException
	 *             if the <code>mergeInObject</code> or <code>withObject</code> argument is null or if
	 *             the <code>mergeInObject</code> property type is different from the source type and
	 *             the relevant converter has not been registered.
	 */
	public static final <MERGE_IN, WITH> void mergeProperty(final MERGE_IN mergeInObject, final WITH withObject,
		final PropertyDescriptor propertyDescriptor)
		throws IllegalAccessException, InvocationTargetException, IllegalArgumentException
	{
		if (PropertyUtils.isReadable(mergeInObject, propertyDescriptor.getName())
			&& PropertyUtils.isWriteable(mergeInObject, propertyDescriptor.getName()))
		{
			final Method getter = propertyDescriptor.getReadMethod();
			final Object value = getter.invoke(withObject);
			if (!value.isDefaultValue())
			{
				final Method setter = propertyDescriptor.getWriteMethod();
				setter.invoke(mergeInObject, value);
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
				changedData.add(ChangedAttributeResult.builder()
					.attributeName(key)
					.sourceAttribute(sourceAttribute)
					.changedAttribute(changedAttribute)
					.build());
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
					ChangedAttributeResult.builder()
					.attributeName(key)
					.sourceAttribute(sourceAttribute)
					.changedAttribute(changedAttribute)
					.build());
			}
		}
		return changedData;
	}

}
