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

import java.io.File;
import java.io.FileFilter;
import java.io.IOException;
import java.lang.annotation.Annotation;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import de.alpharogroup.file.FilenameUtils;
import de.alpharogroup.file.filter.ClassFileFilter;

/**
 * The Class AnnotationUtils.
 *
 * @author Asterios Raptis
 */
public final class AnnotationUtils
{

	/**
	 * Gets all annotated classes that belongs from the given package path and the given annotation
	 * class.
	 *
	 * @param packagePath
	 *            the package path
	 * @param annotationClass
	 *            the annotation class
	 * @return the all classes
	 * @throws ClassNotFoundException
	 *             the class not found exception
	 * @throws IOException
	 *             Signals that an I/O exception has occurred.
	 */
	public static Set<Class<?>> getAllAnnotatedClasses(final String packagePath,
		final Class<? extends Annotation> annotationClass) throws ClassNotFoundException,
		IOException
	{
		final List<File> directories = ClassExtensions.getDirectoriesFromResources(packagePath, true);
		final Set<Class<?>> classes = new HashSet<>();
		for (final File directory : directories)
		{
			classes.addAll(scanForAnnotatedClasses(directory, packagePath, annotationClass));
		}
		return classes;
	}

	/**
	 * Gets all annotated classes that belongs from the given package path and the given list with
	 * annotation classes.
	 *
	 * @param packagePath
	 *            the package path
	 * @param annotationClasses
	 *            the list with the annotation classes
	 * @return the all classes
	 * @throws ClassNotFoundException
	 *             the class not found exception
	 * @throws IOException
	 *             Signals that an I/O exception has occurred.
	 */
	public static Set<Class<?>> getAllAnnotatedClassesFromSet(final String packagePath,
		final Set<Class<? extends Annotation>> annotationClasses) throws ClassNotFoundException,
		IOException
	{
		final List<File> directories = ClassExtensions.getDirectoriesFromResources(packagePath, true);
		final Set<Class<?>> classes = new HashSet<>();
		for (final File directory : directories)
		{
			classes
				.addAll(scanForAnnotatedClassesFromSet(directory, packagePath, annotationClasses));
		}
		return classes;
	}

	/**
	 * Gets all the classes from the class loader that belongs to the given package path.
	 *
	 * @param packagePath
	 *            the package path
	 *
	 * @return the all classes
	 *
	 * @throws ClassNotFoundException
	 *             the class not found exception
	 * @throws IOException
	 *             Signals that an I/O exception has occurred.
	 */
	public static Set<Class<?>> getAllClasses(final String packagePath)
		throws ClassNotFoundException, IOException
	{
		return getAllAnnotatedClasses(packagePath, null);
	}

	/**
	 * Gets all the classes from the class loader that belongs to the given package path.
	 *
	 * @param packagePath
	 *            the package path
	 * @param annotationClasses
	 *            the annotation classes
	 * @return the all classes
	 * @throws ClassNotFoundException
	 *             the class not found exception
	 * @throws IOException
	 *             Signals that an I/O exception has occurred.
	 */
	public static Set<Class<?>> getAllClasses(final String packagePath,
		final Set<Class<? extends Annotation>> annotationClasses) throws ClassNotFoundException,
		IOException
	{
		return getAllAnnotatedClassesFromSet(packagePath, annotationClasses);
	}

	/**
	 * Search for the given annotationClass in the given componentClass and return it if search was
	 * successful.
	 *
	 * @param <T>
	 *            the generic type
	 * @param componentClass
	 *            the component class
	 * @param annotationClass
	 *            the annotation class
	 * @return the annotation
	 */
	public static <T extends Annotation> T getAnnotation(final Class<?> componentClass,
		final Class<T> annotationClass)
	{
		T annotation = componentClass.getAnnotation(annotationClass);
		if (annotation != null)
		{
			return annotation;
		}
		for (final Class<?> ifc : componentClass.getInterfaces())
		{
			annotation = getAnnotation(ifc, annotationClass);
			if (annotation != null)
			{
				return annotation;
			}
		}
		if (!Annotation.class.isAssignableFrom(componentClass))
		{
			for (final Annotation ann : componentClass.getAnnotations())
			{
				annotation = getAnnotation(ann.annotationType(), annotationClass);
				if (annotation != null)
				{
					return annotation;
				}
			}
		}
		final Class<?> superClass = componentClass.getSuperclass();
		if (superClass == null || superClass.equals(Object.class))
		{
			return null;
		}
		return getAnnotation(superClass, annotationClass);
	}

	/**
	 * Checks if is annotation present through making a lookup if the given annotation class is
	 * present in the given class or in one of the super classes.
	 *
	 * @param componentClass
	 *            the component class
	 * @param annotationClass
	 *            the annotation class
	 * @return true, if is annotation present
	 */
	public static boolean isAnnotationPresentInSuperClasses(final Class<?> componentClass,
		final Class<? extends Annotation> annotationClass)
	{
		if (componentClass.isAnnotationPresent(annotationClass))
		{
			return true;
		}
		Class<?> superClass = componentClass.getSuperclass();
		while (superClass != null)
		{
			if (superClass.isAnnotationPresent(annotationClass))
			{
				return true;
			}
			superClass = superClass.getSuperclass();
		}
		return false;
	}

	/**
	 * Checks if is annotation present through making a lookup if the given annotation class is
	 * present in the given class or in one of the super classes.
	 *
	 * @param componentClass
	 *            the component class
	 * @param annotationClass
	 *            the annotation class
	 * @return true, if is annotation present
	 */
	public static boolean isAnnotationPresentInSuperClassesOrInterfaces(
		final Class<?> componentClass, final Class<? extends Annotation> annotationClass)
	{
		return getAnnotation(componentClass, annotationClass) != null;
	}

	/**
	 * Scan recursive for annotated classes in the given directory.
	 *
	 * @param directory
	 *            the directory
	 * @param packagePath
	 *            the package path
	 * @param annotationClass
	 *            the annotation class
	 * @return the list
	 * @throws ClassNotFoundException
	 *             the class not found exception
	 */
	public static Set<Class<?>> scanForAnnotatedClasses(final File directory,
		final String packagePath, final Class<? extends Annotation> annotationClass)
		throws ClassNotFoundException
	{
		final Set<Class<?>> foundClasses = new HashSet<>();
		if (!directory.exists())
		{
			return foundClasses;
		}
		// define the include filefilter for class files...
		final FileFilter includeFileFilter = new ClassFileFilter();
		final File[] files = directory.listFiles(includeFileFilter);
		for (final File file : files)
		{
			String qualifiedClassname = null;
			if (file.isDirectory())
			{
				qualifiedClassname = packagePath + "." + file.getName();
				foundClasses.addAll(scanForAnnotatedClasses(file, qualifiedClassname,
					annotationClass));
			}
			else
			{
				final String filename = FilenameUtils.getFilenameWithoutExtension(file);
				qualifiedClassname = packagePath + '.' + filename;
				Class<?> foundClass = null;
				try
				{
					foundClass = Class.forName(qualifiedClassname);
					if (null != annotationClass)
					{
						if (foundClass.isAnnotationPresent(annotationClass))
						{
							foundClasses.add(foundClass);
						}
					}
					else
					{
						foundClasses.add(foundClass);
					}

				}
				catch (final Throwable throwable)
				{
					foundClass = Class.forName(qualifiedClassname, false,
						ClassExtensions.getClassLoader());
					if (null != annotationClass)
					{
						if (foundClass.isAnnotationPresent(annotationClass))
						{
							foundClasses.add(foundClass);
						}
					}
					else
					{
						foundClasses.add(foundClass);
					}
				}
			}
		}
		return foundClasses;
	}

	/**
	 * Scan recursive for annotated classes in the given directory.
	 *
	 * @param directory
	 *            the directory
	 * @param packagePath
	 *            the package path
	 * @param annotationClasses
	 *            the list with the annotation classes
	 * @return the list
	 * @throws ClassNotFoundException
	 *             the class not found exception
	 */
	public static Set<Class<?>> scanForAnnotatedClassesFromSet(final File directory,
		final String packagePath, final Set<Class<? extends Annotation>> annotationClasses)
		throws ClassNotFoundException
	{
		final Set<Class<?>> foundClasses = new HashSet<>();
		if (!directory.exists())
		{
			return foundClasses;
		}
		// define the include filefilter for class files...
		final FileFilter includeFileFilter = new ClassFileFilter();

		final File[] files = directory.listFiles(includeFileFilter);
		for (final File file : files)
		{
			String qualifiedClassname = null;
			if (file.isDirectory())
			{
				qualifiedClassname = packagePath + "." + file.getName();
				foundClasses.addAll(scanForAnnotatedClassesFromSet(file, qualifiedClassname,
					annotationClasses));
			}
			else
			{
				final String filename = FilenameUtils.getFilenameWithoutExtension(file);
				qualifiedClassname = packagePath + '.' + filename;
				Class<?> foundClass = null;
				try
				{
					foundClass = Class.forName(qualifiedClassname);
					if (null != annotationClasses)
					{
						for (final Class<? extends Annotation> annotationClass : annotationClasses)
						{
							if (foundClass.isAnnotationPresent(annotationClass))
							{
								foundClasses.add(foundClass);
							}
						}
					}
					else
					{
						foundClasses.add(foundClass);
					}
				}
				catch (final Throwable throwable)
				{
					foundClass = Class.forName(qualifiedClassname, false,
						ClassExtensions.getClassLoader());
					if (null != annotationClasses)
					{
						for (final Class<? extends Annotation> annotationClass : annotationClasses)
						{
							if (foundClass.isAnnotationPresent(annotationClass))
							{
								foundClasses.add(foundClass);
							}
						}
					}
					else
					{
						foundClasses.add(foundClass);
					}
				}
			}
		}
		return foundClasses;
	}

	/**
	 * Scan recursive for classes in the given directory.
	 *
	 * @param directory
	 *            the directory
	 * @param packagePath
	 *            the package path
	 * @return the list
	 * @throws ClassNotFoundException
	 *             the class not found exception
	 */
	public static Set<Class<?>> scanForClasses(final File directory, final String packagePath)
		throws ClassNotFoundException
	{
		return AnnotationUtils.scanForAnnotatedClasses(directory, packagePath, null);
	}

	/**
	 * Private constructor.
	 */
	private AnnotationUtils()
	{
		super();
	}
}
