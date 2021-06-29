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
package de.alpharogroup.io.annotations;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;

import org.apache.commons.lang3.ArrayUtils;

import de.alpharogroup.lang.AnnotationExtensions;
import lombok.experimental.UtilityClass;

/**
 * The class {@link ImportResourcesExtensions} contains extension methods for {@link ImportResource}
 * objects.
 * @deprecated moved to silly-io repository
 */
@UtilityClass
public final class ImportResourcesExtensions
{

	/**
	 * Gets a {@link Map} with {@link ImportResource} objects and the corresponding to the found
	 * class from the given package Name. The search is made recursive. The key from an entry of the
	 * map is the class where the {@link ImportResource} objects found and the value is an Array of
	 * the {@link ImportResource} objects that contains in the class.
	 *
	 * @param packageName
	 *            the package name
	 * @return the import resources
	 * @throws ClassNotFoundException
	 *             occurs if a given class cannot be located by the specified class loader
	 * @throws IOException
	 *             Signals that an I/O exception has occurred.
	 * 
	 * @throws URISyntaxException
	 *             is thrown if a string could not be parsed as a URI reference.
	 */
	public static Map<Class<?>, ImportResource[]> getImportResources(final String packageName)
		throws ClassNotFoundException, IOException, URISyntaxException
	{
		final Map<Class<?>, ImportResource[]> resourcesMap = new LinkedHashMap<>();

		final Class<ImportResources> importResourcesClass = ImportResources.class;
		final Class<ImportResource> importResourceClass = ImportResource.class;
		final Set<Class<?>> importResourcesClasses = AnnotationExtensions
			.getAllAnnotatedClasses(packageName, importResourcesClass);
		final Set<Class<?>> importResourceClasses = AnnotationExtensions
			.getAllAnnotatedClasses(packageName, importResourceClass);
		importResourcesClasses.addAll(importResourceClasses);
		for (final Class<?> annotatedClass : importResourcesClasses)
		{
			final ImportResources importResources = annotatedClass
				.getAnnotation(ImportResources.class);
			ImportResource[] importResourcesArray = null;
			ImportResource[] importResourceArray = null;
			if (importResources != null)
			{
				importResourcesArray = importResources.resources();
			}

			final ImportResource importResource = annotatedClass
				.getAnnotation(ImportResource.class);
			if (importResource != null)
			{
				importResourceArray = new ImportResource[1];
				importResourceArray[0] = importResource;
			}
			final ImportResource[] array = ArrayUtils.addAll(importResourceArray,
				importResourcesArray);
			Arrays.sort(array, new ImportResourceComparator());
			resourcesMap.put(annotatedClass, array);

		}
		return resourcesMap;
	}

}
