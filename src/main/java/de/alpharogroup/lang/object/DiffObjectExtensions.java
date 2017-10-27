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

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.beanutils.BeanUtils;

import de.alpharogroup.collections.list.ListExtensions;
import de.alpharogroup.io.ChangedAttributeResult;
import lombok.experimental.UtilityClass;

/**
 * The class {@link DiffObjectExtensions} can find changed data from objects in other words the
 * difference of them.
 * @deprecated use instead the same name inteface in the new project jobject-diff. Will be removed in the next release.
 */
@Deprecated
@UtilityClass
public class DiffObjectExtensions
{

	/**
	 * Compares the given two objects and gets the changed data.
	 *
	 * @param sourceOjbect
	 *            the source ojbect
	 * @param objectToCompare
	 *            the object to compare
	 * @return the changed data
	 * @throws IllegalAccessException
	 *             Thrown if this method or object is enforcing java language access control and the
	 *             underlying method or object is inaccessible.
	 * @throws InvocationTargetException
	 *             Thrown if the property accessor method throws an exception
	 * @throws NoSuchMethodException
	 *             Thrown if this {@code Method} object is enforcing Java language access control
	 *             and the underlying method is inaccessible.
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
				changedData.put(key, ChangedAttributeResult.builder().attributeName(key)
					.sourceAttribute(sourceAttribute).changedAttribute(changedAttribute).build());
			}
		}
		return changedData;
	}

	/**
	 * Gets the changed data in a list.
	 *
	 * @param sourceOjbect
	 *            the source ojbect
	 * @param objectToCompare
	 *            the object to compare
	 * @return the changed data
	 * @throws IllegalAccessException
	 *             Thrown if this method or object is enforcing java language access control and the
	 *             underlying method or object is inaccessible.
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
				changedData.add(ChangedAttributeResult.builder().attributeName(key)
					.sourceAttribute(sourceAttribute).changedAttribute(changedAttribute).build());
			}
		}
		return changedData;
	}

	/**
	 * Gets the changed data recursively in a list from the given source object and the object to
	 * compare.
	 *
	 * @param sourceOjbect
	 *            the source ojbect
	 * @param objectToCompare
	 *            the object to compare
	 * @param changedData
	 *            the changed data in a list. This can be initially null, the list will be than
	 *            created.
	 * @param parent
	 *            the parent of the changed data. This is initially null.
	 * @return the list with the changed data
	 * @throws IllegalArgumentException
	 *             Thrown if an illegal argument is given
	 * @throws IllegalAccessException
	 *             Thrown if this method or object is enforcing java language access control and the
	 *             underlying method or object is inaccessible.
	 */
	public static List<ChangedAttributeResult> getChangedDataWithReflection(
		final Object sourceOjbect, final Object objectToCompare,
		List<ChangedAttributeResult> changedData, final ChangedAttributeResult parent)
		throws IllegalArgumentException, IllegalAccessException
	{
		if (changedData == null)
		{
			changedData = ListExtensions.newArrayList();
		}
		if (sourceOjbect == null || objectToCompare == null
			|| !sourceOjbect.getClass().equals(objectToCompare.getClass()))
		{
			return changedData;
		}
		final Field[] fields = sourceOjbect.getClass().getDeclaredFields();
		for (final Field field : fields)
		{
			if (Modifier.isStatic(field.getModifiers()) && Modifier.isFinal(field.getModifiers()))
			{
				continue;
			}
			field.setAccessible(true);
			final Object sourceFieldValue = field.get(sourceOjbect);
			final Object toCompareFieldValue = field.get(objectToCompare);
			if ((sourceFieldValue == null && toCompareFieldValue != null)
				|| (toCompareFieldValue == null && sourceFieldValue != null)
				|| (sourceFieldValue != null && !sourceFieldValue.equals(toCompareFieldValue)))
			{
				final ChangedAttributeResult changedAttribute = ChangedAttributeResult.builder()
					.parent(parent).attributeName(field.getName()).sourceAttribute(sourceFieldValue)
					.changedAttribute(toCompareFieldValue).build();
				changedData.add(changedAttribute);
				getChangedDataWithReflection(sourceFieldValue, toCompareFieldValue, changedData,
					changedAttribute);
			}
		}
		return changedData;
	}
}
