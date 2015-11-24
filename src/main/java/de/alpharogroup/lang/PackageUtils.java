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
import java.net.URL;
import java.net.URLDecoder;
import java.util.Enumeration;
import java.util.LinkedHashSet;
import java.util.Set;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;

import lombok.experimental.ExtensionMethod;
import de.alpharogroup.file.FilenameUtils;
import de.alpharogroup.file.filter.ClassFileFilter;
import de.alpharogroup.string.StringExtensions;

/**
 * The Class PackageUtils.
 */
@ExtensionMethod(StringExtensions.class)
public final class PackageUtils
{

	/**
	 * Determines the package name from the given class object.
	 *
	 * @param clazz
	 *            The class object.
	 *
	 * @return The package name from the given class object.
	 */
	public static String getPackageName(final Class<?> clazz)
	{
		if (clazz == null)
		{
			return null;
		}
		final String packageName = clazz.getPackage().getName();
		return packageName;
	}

	/**
	 * Determines the package name from the given String(this must be the fully qualified class name
	 * without the file extension).
	 *
	 * @param qualifiedClassName
	 *            The fully qualified class name without the file extension. For instance:
	 *            xy.ab.Test =&gt; xy.ab
	 *
	 * @return The package name from the given String.
	 */
	public static String getPackageName(final String qualifiedClassName)
	{
		if (qualifiedClassName == null)
		{
			return null;
		}
		final String packageName = qualifiedClassName.substring(0,
			qualifiedClassName.lastIndexOf("."));
		return packageName;
	}

	/**
	 * Determines the package name from the given class object and adds a dot at the end.
	 *
	 * @param clazz
	 *            The class object.
	 *
	 * @return The package name from the given class object.
	 */
	public static String getPackageNameWithDot(final Class<?> clazz)
	{
		if (clazz == null)
		{
			return null;
		}
		final String packageName = clazz.getPackage().getName() + ".";
		return packageName;
	}

	/**
	 * Determines the package path from the given class object.
	 *
	 * @param clazz
	 *            The class object.
	 *
	 * @return The package path from the given class object.
	 */
	public static String getPackagePath(final Class<?> clazz)
	{
		if (clazz == null)
		{
			return null;
		}
		final String packagePath = getPackageName(clazz).replace('.', '/') + "/";
		return packagePath;
	}


	/**
	 * Determines the package path from the given object.
	 *
	 * @param object
	 *            The object.
	 *
	 * @return The package path from the given object.
	 */
	public static String getPackagePath(final Object object)
	{
		if (object == null)
		{
			return null;
		}
		return getPackagePath(object.getClass());
	}

	/**
	 * Determines the package path from the given object and adds a slash at the front.
	 *
	 * @param clazz
	 *            the clazz
	 * @return The package path from the given object with the added slash at the front.
	 */
	public static String getPackagePathWithSlash(final Class<?> clazz)
	{
		if (clazz == null)
		{
			return null;
		}
		final String packagePath = "/" + getPackagePath(clazz);
		return packagePath;
	}

	/**
	 * Determines the package path from the given object and adds a slash at the front.
	 *
	 * @param object
	 *            The object.
	 *
	 * @return The package path from the given object with the added slash at the front.
	 */
	public static String getPackagePathWithSlash(final Object object)
	{
		if (object == null)
		{
			return null;
		}
		return getPackagePathWithSlash(object.getClass());
	}

	/**
	 * Scan class names from the given package name.
	 *
	 * @param packageName
	 *            the package name
	 * @return the list
	 * @throws IOException
	 *             Signals that an I/O exception has occurred.
	 */
	public static Set<String> scanClassNames(final String packageName) throws IOException
	{
		return scanClassNames(packageName, false, true);
	}

	/**
	 * Scan class names from the given package name.
	 *
	 * @param packageName
	 *            the package name
	 * @param recursive
	 *            the recursive flag
	 * @param qualifiedClassnames
	 *            the flag if the class names should be qualified class names.
	 * @return the list
	 * @throws IOException
	 *             Signals that an I/O exception has occurred.
	 */
	public static Set<String> scanClassNames(final String packageName, final boolean recursive,
		final boolean qualifiedClassnames) throws IOException
	{
		final ClassLoader classLoader = ClassExtensions.getClassLoader();
		final Set<String> classNames = new LinkedHashSet<>();
		final String packagePath = packageName.replace(".", "/");
		final URL packageURL = classLoader.getResource(packagePath);
		if (packageURL == null)
		{
			return classNames;
		}
		if (packageURL.getProtocol().equals("jar") || packageURL.getProtocol().equals("ear")
			|| packageURL.getProtocol().equals("war"))
		{
			String fileName = URLDecoder.decode(packageURL.getFile(), "UTF-8");
			fileName = fileName.substring(5, fileName.indexOf("!"));
			final JarFile jarFile = new JarFile(fileName);
			final Enumeration<JarEntry> jarEntries = jarFile.entries();
			while (jarEntries.hasMoreElements())
			{
				final JarEntry entry = jarEntries.nextElement();
				final boolean isDir = entry.isDirectory();
				final String entryName = entry.getName();
				if (isDir && recursive)
				{
					String entryPath = entryName.replace("/", ".");
					if (entryPath.lastCharacter().equals("."))
					{
						entryPath = entryPath.substring(0, entryPath.length() - 1);
					}
					if (entryPath.startsWith(packageName)
						&& packageName.length() < entryPath.length())
					{
						classNames
							.addAll(scanClassNames(entryPath, recursive, qualifiedClassnames));
					}
				}
				else
				{
					if (!isDir && entryName.startsWith(packagePath))
					{
						final String relativePath = entryName.substring(packagePath.length() + 1,
							entryName.length());
						if (!relativePath.contains("/") && !relativePath.contains("$"))
						{
							final String filenameWithoutExtension = FilenameUtils
								.getFilenameWithoutExtension(entryName.replace("/", "."));
							if (qualifiedClassnames)
							{
								classNames.add(filenameWithoutExtension);
							}
							else
							{
								final String className = FilenameUtils
									.getFilenameWithoutExtension(relativePath);
								classNames.add(className);
							}
						}
					}
				}
			}
			jarFile.close();
		}
		else
		{
			final String folderName = URLDecoder.decode(packageURL.getFile(), "UTF-8");
			final File folder = new File(folderName);
			// define the include filefilter for class files...
			final FileFilter includeFileFilter = new ClassFileFilter();
			final File[] files = folder.listFiles(includeFileFilter);
			for (final File file : files)
			{
				String qualifiedClassname = null;
				if (file.isDirectory() && recursive)
				{
					qualifiedClassname = packagePath + "." + file.getName();
					classNames.addAll(scanClassNames(qualifiedClassname, recursive,
						qualifiedClassnames));
				}
				else
				{
					if (!file.isDirectory())
					{
						final String filenameWithoutExtension = FilenameUtils
							.getFilenameWithoutExtension(file);

						if (qualifiedClassnames)
						{
							qualifiedClassname = packageName + '.' + filenameWithoutExtension;
							classNames.add(qualifiedClassname.replace("/", "."));
						}
						else
						{
							classNames.add(filenameWithoutExtension);
						}
					}
				}
			}
		}
		return classNames;
	}

	/**
	 * Private constructor.
	 */
	private PackageUtils()
	{
	}


}
