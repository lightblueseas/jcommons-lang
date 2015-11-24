package de.alpharogroup.lang;

import java.io.File;
import java.io.FileFilter;
import java.io.IOException;
import java.io.InputStream;
import java.net.URISyntaxException;
import java.net.URL;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

import lombok.experimental.ExtensionMethod;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;

import de.alpharogroup.file.FileExtension;
import de.alpharogroup.file.FilenameUtils;
import de.alpharogroup.file.filter.ClassFileFilter;
import de.alpharogroup.string.StringExtensions;

/**
 * The Class ClassExtensions provides extension methods for the class {@link Class}.
 */
@ExtensionMethod(StringExtensions.class)
public final class ClassExtensions
{

	/** The Constant LOGGER. */
	protected static final Logger LOGGER = Logger.getLogger(ClassExtensions.class);

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
		final String oneNormalizedClassName = ClassExtensions.normalizeQualifiedClassName(oneClass
			.getName());
		final String otherNormalizedClassName = ClassExtensions
			.normalizeQualifiedClassName(otherClass.getName());
		if (otherNormalizedClassName.equals(oneNormalizedClassName))
		{
			return true;
		}
		return false;
	}

	/**
	 * Look up the class in the "current" ClassLoader.
	 *
	 * @param className
	 *            The class name to load
	 * @return the class
	 * @throws ClassNotFoundException
	 *             if the Class was not found.
	 */
	public static Class<?> forName(final String className) throws ClassNotFoundException
	{
		final ClassLoader classLoader = getClassLoader();
		Class<?> clazz = null;
		try
		{
			clazz = Class.forName(className);
		}
		catch (final Throwable throwable)
		{
			clazz = Class.forName(className, true, classLoader);
		}
		return clazz;
	}

	/**
	 * Gets the parent base class from the given child class.
	 *
	 * @param childClass
	 *            the child class
	 * @return the parent base class from the given child class.
	 */
	public static Class<?> getBaseClass(final Class<?> childClass)
	{
		if (childClass == null)
		{
			return childClass;
		}
		Class<?> superClass = childClass.getSuperclass();
		while ((superClass != null) && !superClass.getSuperclass().equals(Object.class))
		{
			superClass = superClass.getSuperclass();
		}
		if (superClass == null)
		{
			return childClass;
		}
		return superClass;
	}

	/**
	 * Gets the current class loader.
	 *
	 * @return 's the current class loader
	 */
	public static ClassLoader getClassLoader()
	{
		return ClassExtensions.getClassLoader(null);
	}

	/**
	 * Gets the ClassLoader from the given object.
	 *
	 * @param obj
	 *            The object.
	 * @return the ClassLoader from the given object.
	 */
	public static ClassLoader getClassLoader(final Object obj)
	{
		ClassLoader classLoader = null;
		if (null != obj)
		{
			if (isDerivate(Thread.currentThread().getContextClassLoader(), obj.getClass()
				.getClassLoader()))
			{
				classLoader = obj.getClass().getClassLoader();
			}
			else
			{
				classLoader = Thread.currentThread().getContextClassLoader();
			}
			if (isDerivate(classLoader, ClassLoader.getSystemClassLoader()))
			{
				classLoader = ClassLoader.getSystemClassLoader();
			}
		}
		else
		{
			if (isDerivate(Thread.currentThread().getContextClassLoader(),
				ClassLoader.getSystemClassLoader()))
			{
				classLoader = ClassLoader.getSystemClassLoader();
			}
			else
			{
				classLoader = Thread.currentThread().getContextClassLoader();
			}
		}
		return classLoader;
	}

	/**
	 * Gets the classname and concats the suffix ".class" from the class.
	 *
	 * @param clazz
	 *            The class.
	 * @return The classname and concats the suffix ".class".
	 */
	public static String getClassnameWithSuffix(final Class<?> clazz)
	{
		String className = clazz.getName();
		className = className.substring(className.lastIndexOf('.') + 1)
			+ FileExtension.CLASS.getExtension();
		return className;
	}

	/**
	 * Gets the classname and concats the suffix ".class" from the object.
	 *
	 * @param obj
	 *            The object.
	 * @return The classname and concats the suffix ".class".
	 */
	public static String getClassnameWithSuffix(final Object obj)
	{
		return getClassnameWithSuffix(obj.getClass());
	}

	/**
	 * Gets the directories from the given path.
	 *
	 * @param path
	 *            the path
	 * @param isPackage
	 *            If the Flag is true than the given path is a package.
	 * @return the directories from resources
	 * @throws IOException
	 *             Signals that an I/O exception has occurred.
	 */
	public static List<File> getDirectoriesFromResources(String path, final boolean isPackage)
		throws IOException
	{
		if (isPackage)
		{
			path = path.replace('.', '/');
		}
		final List<URL> resources = ClassExtensions.getResources(path);
		final List<File> dirs = new ArrayList<>();
		for (final URL resource : resources)
		{
			dirs.add(new File(URLDecoder.decode(resource.getFile(), "UTF-8")));
		}
		return dirs;
	}

	/**
	 * If the given class is in a JAR, WAR or EAR file than the manifest url as String is returned.
	 *
	 * @param clazz
	 *            The class.
	 * @return the manifest url as String if the given class is in a JAR, WAR or EAR file.
	 */
	public static String getManifestUrl(final Class<?> clazz)
	{
		String manifestUrl = null;
		final String path = ClassExtensions.getPath(clazz);
		final URL classUrl = ClassExtensions.getResource(path);
		if (classUrl != null)
		{
			final String classUrlString = classUrl.toString();
			if ((classUrlString.startsWith("jar:") && (classUrlString.indexOf(path) > 0))
				|| (classUrlString.startsWith("war:") && (classUrlString.indexOf(path) > 0))
				|| (classUrlString.startsWith("ear:") && (classUrlString.indexOf(path) > 0)))
			{
				manifestUrl = classUrlString.replace(path, "/META-INF/MANIFEST.MF");
			}
		}
		return manifestUrl;
	}

	/**
	 * Returns the name of the given class or null if the given class is null.
	 *
	 * @param clazz
	 *            The class.
	 *
	 * @return The name of the given class.
	 */
	public static String getName(final Class<?> clazz)
	{
		return getName(clazz, false);
	}

	/**
	 * Returns the name of the given class or null if the given class is null. If the given flag
	 * 'simple' is true the simple name (without the package) will be returned.
	 *
	 * @param clazz
	 *            The class
	 * @param simple
	 *            The flag if the simple name should be returned.
	 *
	 * @return The name of the given class or if the given flag 'simple' is true the simple name
	 *         (without the package) will be returned.
	 */
	public static String getName(Class<?> clazz, final boolean simple)
	{
		String name = null;
		if (clazz != null)
		{
			while (clazz.isAnonymousClass())
			{
				clazz = clazz.getSuperclass();
			}
			if (simple)
			{
				name = clazz.getSimpleName();
			}
			else
			{
				name = clazz.getName();
			}
		}
		return name;
	}

	/**
	 * Gets the path from the given class. For instance /java/lang/Object.class if the given class
	 * is from {@code Object}
	 *
	 * @param clazz
	 *            The class.
	 * @return the path from the given class.
	 */
	public static String getPath(final Class<?> clazz)
	{
		final String path = "/" + clazz.getName().replaceAll("\\.", "/")
			+ FileExtension.CLASS.getExtension();
		return path;
	}

	/**
	 * Finds the absolute path from the object.
	 *
	 * @param obj
	 *            The object.
	 * @return The absolute path from the object.
	 */
	public static String getPathFromObject(final Object obj)
	{
		if (obj == null)
		{
			return null;
		}
		final String pathFromObject = obj.getClass()
			.getResource(ClassExtensions.getClassnameWithSuffix(obj)).getPath();
		return pathFromObject;
	}

	/**
	 * Gives the url from the path back.
	 *
	 * @param clazz
	 *            The class-object.
	 * @param path
	 *            The path.
	 * @return 's the url from the path.
	 */
	public static URL getResource(final Class<?> clazz, final String path)
	{
		URL url = clazz.getResource(path);
		if (null == url)
		{
			url = ClassExtensions.getClassLoader().getResource(path);
		}
		return url;
	}

	/**
	 * Gives the URL from the resource. Wrapes the Class.getResource(String)-method.
	 *
	 * @param name
	 *            The name from the resource.
	 * @return The resource or null if the resource does not exists.
	 */
	public static URL getResource(final String name)
	{
		String path = name;
		if (name.startsWith("/"))
		{
			path = name.substring(1, name.length());
		}
		final URL url = ClassExtensions.getClassLoader().getResource(path);
		return url;
	}

	/**
	 * Gives the URL from the resource. Wrapes the Class.getResource(String)-method.
	 *
	 * @param <T>
	 *            the generic type
	 * @param name
	 *            The name from the resource.
	 * @param obj
	 *            The Object.
	 * @return The resource or null if the resource does not exists.
	 */
	public static <T> URL getResource(final String name, final T obj)
	{
		final Class<?> clazz = obj.getClass();
		URL url = clazz.getResource(name);
		if (url == null)
		{
			url = getResource(clazz, name);
		}
		return url;
	}

	/**
	 * Gives the resource as a file Object.
	 *
	 * @param name
	 *            The name from the file.
	 * @return The file or null if the file does not exists.
	 * @throws URISyntaxException
	 *             occurs by creation of the file with an uri.
	 */
	public static File getResourceAsFile(final String name) throws URISyntaxException
	{
		File file = null;
		URL url = getResource(name);
		if (null == url)
		{
			url = ClassExtensions.getClassLoader().getResource(name);
			if (null != url)
			{
				file = new File(url.toURI());
			}
		}
		else
		{
			file = new File(url.toURI());
		}
		return file;
	}

	/**
	 * Gives the resource as a file Object.
	 *
	 * @param name
	 *            The name from the file.
	 * @param obj
	 *            The Object.
	 * @return The file or null if the file does not exists.
	 * @throws URISyntaxException
	 *             occurs by creation of the file with an uri.
	 */
	public static File getResourceAsFile(final String name, final Object obj)
		throws URISyntaxException
	{
		File file = null;
		URL url = getResource(name, obj);
		if (null == url)
		{
			url = ClassExtensions.getClassLoader(obj).getResource(name);
			if (null != url)
			{
				file = new File(url.toURI());
			}
		}
		else
		{
			file = new File(url.toURI());

		}
		return file;
	}

	/**
	 * This method call the getResourceAsStream from the ClassLoader. You can use this method to
	 * read files from jar-files.
	 *
	 * @param clazz
	 *            the clazz
	 * @param uri
	 *            The uri as String.
	 * @return The InputStream from the uri.
	 */
	public static InputStream getResourceAsStream(final Class<?> clazz, final String uri)
	{
		InputStream is = clazz.getResourceAsStream(uri);
		if (null == is)
		{
			is = ClassExtensions.getClassLoader().getResourceAsStream(uri);
		}
		return is;
	}

	/**
	 * Gives the Inputstream from the resource. Wrapes the Class.getResourceAsStream(String)-method.
	 *
	 * @param name
	 *            The name from the resource.
	 * @return The resource or null if the resource does not exists.
	 */
	public static InputStream getResourceAsStream(final String name)
	{
		final ClassLoader loader = ClassExtensions.getClassLoader();
		final InputStream inputStream = loader.getResourceAsStream(name);
		return inputStream;
	}

	/**
	 * Gives the Inputstream from the resource. Wrapes the Class.getResourceAsStream(String)-method.
	 *
	 * @param name
	 *            The name from the resource.
	 * @param obj
	 *            The Object.
	 * @return The resource or null if the resource does not exists.
	 */
	public static InputStream getResourceAsStream(final String name, final Object obj)
	{
		InputStream inputStream = obj.getClass().getResourceAsStream(name);
		if (null == inputStream)
		{
			final ClassLoader loader = ClassExtensions.getClassLoader(obj);
			inputStream = loader.getResourceAsStream(name);
		}
		return inputStream;
	}

	/**
	 * Gets a list with urls from the given path for all resources.
	 *
	 * @param path
	 *            The base path.
	 * @return The resources.
	 * @throws IOException
	 *             Signals that an I/O exception has occurred.
	 */
	public static List<URL> getResources(final String path) throws IOException
	{
		final ClassLoader classLoader = ClassExtensions.getClassLoader();
		final List<URL> list = Collections.list(classLoader.getResources(path));
		return list;
	}

	/**
	 * Returns the simple name of the given class or null if the given class is null.
	 *
	 * @param clazz
	 *            The class.
	 *
	 * @return The simple name of the given class.
	 */
	public static String getSimpleName(final Class<?> clazz)
	{
		return getName(clazz, true);
	}

	/**
	 * Returns the URL from the given class.
	 *
	 * @param clazz
	 *            The class.
	 * @return the URL from the given class.
	 */
	public static URL getURL(final Class<?> clazz)
	{
		return ClassExtensions.getResource(ClassExtensions.getPath(clazz));
	}


	/**
	 * Compares the two given ClassLoader objects and returns true if compare is a derivate of
	 * source.
	 *
	 * @param source
	 *            the source
	 * @param compare
	 *            the compare
	 * @return true, if compare is a derivate of source.
	 */
	public static boolean isDerivate(final ClassLoader source, ClassLoader compare)
	{
		if (source == compare)
		{
			return true;
		}
		if (compare == null)
		{
			return false;
		}
		if (source == null)
		{
			return true;
		}
		while (null != compare)
		{
			compare = compare.getParent();
			if (source == compare)
			{
				return true;
			}
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
			result = className.replaceLast(FileExtension.CLASS.getExtension(), "");
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
	 *             the class not found exception
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
					final String filename = FilenameUtils.getFilenameWithoutExtension(file);
					qualifiedClassname = packagePath + '.' + filename;
					foundClasses.add(forName(qualifiedClassname));
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
	public static Set<Class<?>> scanClassNames(final String packageName) throws IOException,
		ClassNotFoundException
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
		final Set<String> qualifiedClassnames = PackageUtils.scanClassNames(packageName, recursive,
			true);
		for (final String qualifiedClassname : qualifiedClassnames)
		{
			foundClasses.add(forName(qualifiedClassname));
		}
		return foundClasses;
	}

}
