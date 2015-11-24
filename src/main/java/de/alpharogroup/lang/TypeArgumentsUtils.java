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

import java.lang.reflect.Array;
import java.lang.reflect.GenericArrayType;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.lang.reflect.TypeVariable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import de.alpharogroup.check.Check;

/**
 * The Class TypeArgumentsUtils is a utility class for getting the type arguments a child class has
 * used to extend a generic base class. It is inspired from the article Reflecting generics by Ian
 * Robertson at <a href="http://www.artima.com/weblogs/viewpost.jsp?thread=208860"
 * >http://www.artima.com/weblogs/viewpost.jsp?thread=208860</a>. In the comments someone asked if
 * we are allowed to use the source code from the article. The answer of Ian Robertson is:
 * Absolutely, you may use this code. "Consider it open sourced".
 *
 */
public class TypeArgumentsUtils
{


	/**
	 * Get the underlying class for a type, or null if the type is a variable type.
	 *
	 * @param type
	 *            the type
	 * @return the underlying class
	 */
	private static Class<?> getClass(final Type type)
	{
		if (type instanceof Class)
		{
			return (Class<?>)type;
		}
		else if (type instanceof ParameterizedType)
		{
			return getClass(((ParameterizedType)type).getRawType());
		}
		else if (type instanceof GenericArrayType)
		{
			final Type componentType = ((GenericArrayType)type).getGenericComponentType();
			final Class<?> componentClass = getClass(componentType);
			if (componentClass != null)
			{
				return Array.newInstance(componentClass, 0).getClass();
			}
			else
			{
				return null;
			}
		}
		else
		{
			return null;
		}
	}

	/**
	 * Gets the first type argument from the childClass.
	 *
	 * @param <T>
	 *            the generic type of the baseClass
	 * @param baseClass
	 *            the base class
	 * @param childClass
	 *            the child class
	 * @return the first type argument
	 */
	public static <T> Class<?> getFirstTypeArgument(final Class<T> baseClass,
		final Class<? extends T> childClass)
	{
		return getTypeArgument(baseClass, childClass, 0);
	}

	/**
	 * Gets the type argument from the childClass at the given index or null if it does not exists.
	 *
	 * @param <T>
	 *            the generic type of the baseClass
	 * @param baseClass
	 *            the base class
	 * @param childClass
	 *            the child class
	 * @param index
	 *            the index of the type argument
	 * @return the type argument from the childClass at the given index or null if it does not
	 *         exists.
	 */
	public static <T> Class<?> getTypeArgument(final Class<T> baseClass,
		final Class<? extends T> childClass, final int index)
	{
		final List<Class<?>> typeArguments = getTypeArguments(baseClass, childClass);
		if (typeArguments != null && !typeArguments.isEmpty() && index < typeArguments.size())
		{
			return typeArguments.get(index);
		}
		return null;
	}

	/**
	 * Get the actual type arguments a child class has used to extend a generic base class.
	 *
	 * @param <T>
	 *            the generic type of the baseClass
	 * @param baseClass
	 *            the base class
	 * @param childClass
	 *            the child class
	 * @return a list of the raw classes for the actual type arguments.
	 */
	public static <T> List<Class<?>> getTypeArguments(final Class<T> baseClass,
		final Class<? extends T> childClass)
	{
		Check.get().notNull(baseClass, "baseClass").notNull(childClass, "childClass");
		final Map<Type, Type> resolvedTypes = new HashMap<>();
		Type type = childClass;
		// start walking up the inheritance hierarchy until we hit baseClass
		while (!getClass(type).equals(baseClass))
		{
			if (type instanceof Class)
			{
				// there is no useful information for us in raw types, so just
				// keep going.
				type = ((Class<?>)type).getGenericSuperclass();
			}
			else
			{
				final ParameterizedType parameterizedType = (ParameterizedType)type;
				final Class<?> rawType = (Class<?>)parameterizedType.getRawType();

				resolvedTypes.putAll(getTypeArgumentsAndParameters(type));

				if (!rawType.equals(baseClass))
				{
					type = rawType.getGenericSuperclass();
				}
			}
		}

		// finally, for each actual type argument provided to baseClass,
		// determine (if possible)
		// the raw class for that type argument.
		Type[] actualTypeArguments;
		if (type instanceof Class)
		{
			actualTypeArguments = ((Class<?>)type).getTypeParameters();
		}
		else
		{
			actualTypeArguments = ((ParameterizedType)type).getActualTypeArguments();
		}
		final List<Class<?>> typeArgumentsAsClasses = new ArrayList<>();
		// resolve types by chasing down type variables.
		for (Type baseType : actualTypeArguments)
		{
			while (resolvedTypes.containsKey(baseType))
			{
				baseType = resolvedTypes.get(baseType);
			}
			typeArgumentsAsClasses.add(getClass(baseType));
		}
		return typeArgumentsAsClasses;
	}

	/**
	 * Gets the type arguments and parameters.
	 *
	 * @param type
	 *            the type
	 * @return the type arguments and parameters
	 */
	private static Map<Type, Type> getTypeArgumentsAndParameters(final Type type)
	{
		final ParameterizedType parameterizedType = (ParameterizedType)type;
		final Class<?> rawType = (Class<?>)parameterizedType.getRawType();
		final Map<Type, Type> resolvedTypes = new HashMap<>();
		final Type[] actualTypeArguments = parameterizedType.getActualTypeArguments();
		final TypeVariable<?>[] typeParameters = rawType.getTypeParameters();
		for (int i = 0; i < actualTypeArguments.length; i++)
		{
			resolvedTypes.put(typeParameters[i], actualTypeArguments[i]);
		}
		return resolvedTypes;
	}
}
