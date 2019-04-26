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
package de.alpharogroup.lang;

import java.io.File;
import java.io.FileFilter;
import java.io.IOException;
import java.util.LinkedHashSet;
import java.util.Set;

import org.apache.commons.lang3.StringUtils;

import de.alpharogroup.file.FileExtension;
import de.alpharogroup.file.FilenameExtensions;
import de.alpharogroup.file.filter.ClassFileFilter;
import de.alpharogroup.string.StringExtensions;
import lombok.experimental.UtilityClass;

/**
 * The class {@link ScanClassExtensions} provides extension methods for the scan {@link Class}
 * objects
 */
@UtilityClass
public final class ScanClassExtensions
{

	/**
	 * Equal the given class objects by they qualified class names.
	 *
	 * @param oneClass
	 *            one class for equation by the qualified class name.
	 * @param otherClass
	 *            the other class for equation by the qualified class name.
	 * @return s true if the qualified class names are equal otherwise false.
	 */
	public static boolean equalsByClassName(final Class<?> oneClass, final Class<?> otherClass)
	{
		final String oneNormalizedClassName = ScanClassExtensions
			.normalizeQualifiedClassName(oneClass.getName());
		final String otherNormalizedClassName = ScanClassExtensions
			.normalizeQualifiedClassName(otherClass.getName());
		if (otherNormalizedClassName.equals(oneNormalizedClassName))
		{
			return true;
		}
		return false;
	}

	/**
	 * Normalizes the given full qualified class name. This can be an entry from a jar file for
	 * instance.
	 *
	 * @param qualifiedClassname
	 *            the full qualified class name to normalize.
	 * @return The normalized class name.
	 */
	public static String normalizeQualifiedClassName(final String qualifiedClassname)
	{
		return normalizeSimpleClassName(qualifiedClassname).replaceAll("/", ".");
	}

	/**
	 * Normalizes the given simple class name.
	 *
	 * @param className
	 *            the class name to normalize.
	 * @return The normalized class name.
	 */
	public static String normalizeSimpleClassName(final String className)
	{
		String result = className;
		if (className.endsWith(FileExtension.CLASS.getExtension()))
		{
			result = StringExtensions.replaceLast(className, FileExtension.CLASS.getExtension(),
				"");
		}
		final int lastIndexOf$ = result.lastIndexOf("$");
		if (lastIndexOf$ != -1)
		{
			final String prefix = result.substring(0, lastIndexOf$);
			final String compilerClassName = result.substring(lastIndexOf$ + 1, result.length());
			if (StringUtils.isNumeric(compilerClassName))
			{
				return prefix;
			}
		}
		return result;
	}

	/**
	 * Scan for classes in the given directory.
	 *
	 * @param directory
	 *            the directory
	 * @param packagePath
	 *            the package path
	 * @return the list
	 * @throws ClassNotFoundException
	 *             occurs if a given class cannot be located by the specified class loader
	 */
	public static Set<Class<?>> scanClassesFromPackage(final File directory,
		final String packagePath) throws ClassNotFoundException
	{
		return scanClassesFromPackage(directory, packagePath, false);
	}

	/**
	 * Scan recursive for classes in the given directory.
	 *
	 * @param directory
	 *            the directory
	 * @param packagePath
	 *            the package path
	 * @param recursive
	 *            the recursive
	 * @return the list
	 * @throws ClassNotFoundException
	 *             is thrown if a class in the given path cannot be located.
	 */
	public static Set<Class<?>> scanClassesFromPackage(final File directory,
		final String packagePath, final boolean recursive) throws ClassNotFoundException
	{
		final Set<Class<?>> foundClasses = new LinkedHashSet<>();
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

			if (file.isDirectory() && recursive)
			{
				qualifiedClassname = packagePath + "." + file.getName();
				foundClasses.addAll(scanClassesFromPackage(file, qualifiedClassname, recursive));
			}
			else
			{
				if (!file.isDirectory())
				{
					final String filename = FilenameExtensions.getFilenameWithoutExtension(file);
					qualifiedClassname = packagePath + '.' + filename;
					foundClasses.add(ClassExtensions.forName(qualifiedClassname));
				}
			}
		}
		return foundClasses;
	}

	/**
	 * Scan class names from the given package name.
	 *
	 * @param packageName
	 *            the package name
	 * @return the Set with all class found in the given package name.
	 * @throws IOException
	 *             Signals that an I/O exception has occurred.
	 * @throws ClassNotFoundException
	 *             is thrown if a class in the given path cannot be located.
	 */
	public static Set<Class<?>> scanClassNames(final String packageName)
		throws IOException, ClassNotFoundException
	{
		return scanClassNames(packageName, false);
	}

	/**
	 * Scan class names from the given package name.
	 *
	 * @param packageName
	 *            the package name
	 * @param recursive
	 *            the recursive flag
	 * @return the Set with all class found in the given package name.
	 * @throws IOException
	 *             Signals that an I/O exception has occurred.
	 * @throws ClassNotFoundException
	 *             is thrown if a class in the given path cannot be located.
	 */
	public static Set<Class<?>> scanClassNames(final String packageName, final boolean recursive)
		throws IOException, ClassNotFoundException
	{
		final Set<Class<?>> foundClasses = new LinkedHashSet<>();
		final Set<String> qualifiedClassnames = ScanPackageExtensions.scanClassNames(packageName,
			recursive, true);
		for (final String qualifiedClassname : qualifiedClassnames)
		{
			foundClasses.add(ClassExtensions.forName(qualifiedClassname));
		}
		return foundClasses;
	}

}
