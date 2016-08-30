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

import java.lang.reflect.InvocationTargetException;
import java.util.List;
import java.util.Map;

import de.alpharogroup.io.ChangedAttributeResult;
import de.alpharogroup.lang.object.CompareObjectExtensions;
import de.alpharogroup.lang.object.MergeObjectExtensions;
import lombok.experimental.UtilityClass;

/**
 * The class {@link ObjectExtensions} provides extension methods to check if the object is the
 * default value. It also provides methods to find changed data between Objects.
 */
@UtilityClass
public final class ObjectExtensions
{

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
	 *
	 * @deprecated use instead {@link MergeObjectExtensions#getChangedData(Object, Object)}
	 *
	 */
	@Deprecated
	public static List<ChangedAttributeResult> getChangedData(final Object sourceOjbect,
		final Object objectToCompare)
		throws IllegalAccessException, InvocationTargetException, NoSuchMethodException
	{
		return MergeObjectExtensions.getChangedData(sourceOjbect, objectToCompare);
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
	 * @deprecated use instead {@link MergeObjectExtensions#getChangedDataMap(Object, Object)}
	 */
	@Deprecated
	public static Map<Object, ChangedAttributeResult> getChangedDataMap(final Object sourceOjbect,
		final Object objectToCompare)
		throws IllegalAccessException, InvocationTargetException, NoSuchMethodException
	{
		return MergeObjectExtensions.getChangedDataMap(sourceOjbect, objectToCompare);
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
	 * @deprecated use instead {@link CompareObjectExtensions#getCompareToResult(Object, Object)}
	 */
	@Deprecated
	public static Map<String, Integer> getCompareToResult(final Object sourceOjbect,
		final Object objectToCompare)
		throws IllegalAccessException, InvocationTargetException, NoSuchMethodException
	{
		return CompareObjectExtensions.getCompareToResult(sourceOjbect, objectToCompare);
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

}
