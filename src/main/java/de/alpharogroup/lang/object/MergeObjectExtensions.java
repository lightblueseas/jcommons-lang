package de.alpharogroup.lang.object;

import java.beans.PropertyDescriptor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import org.apache.commons.beanutils.PropertyUtils;

import de.alpharogroup.check.Check;
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

}
