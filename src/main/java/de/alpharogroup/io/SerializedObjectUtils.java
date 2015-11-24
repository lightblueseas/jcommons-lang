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
package de.alpharogroup.io;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Logger;

import org.apache.commons.beanutils.BeanComparator;
import org.apache.commons.beanutils.BeanUtils;

import de.alpharogroup.file.FileConstants;

/**
 * Helper-class for make exact copys from serialized objects.
 *
 * @version 1.0
 * @author Asterios Raptis
 */
public final class SerializedObjectUtils
{

	/** The LOGGER. */
	protected static final Logger LOGGER = Logger.getLogger(SerializedObjectUtils.class.getName());

	/**
	 * Copys the given Object and returns the copy from the object or null if the object can't be
	 * serialized.
	 *
	 * @param <T>
	 *            the generic type of the given object
	 * @param orig
	 *            The object to copy.
	 * @return Returns a copy from the original object.
	 * @throws IOException
	 *             Signals that an I/O exception has occurred.
	 * @throws ClassNotFoundException
	 *             is thrown when a class is not found in the classloader or no definition for the
	 *             class with the specified name could be found.
	 */
	@SuppressWarnings("unchecked")
	public static <T extends Serializable> T copySerializedObject(final T orig) throws IOException,
		ClassNotFoundException
	{
		T object = null;
		ByteArrayOutputStream byteArrayOutputStream = null;
		ObjectOutputStream objectOutputStream = null;
		try
		{
			byteArrayOutputStream = new ByteArrayOutputStream();
			objectOutputStream = new ObjectOutputStream(byteArrayOutputStream);
			objectOutputStream.writeObject(orig);
			objectOutputStream.flush();
			objectOutputStream.close();
			final ByteArrayInputStream bis = new ByteArrayInputStream(
				byteArrayOutputStream.toByteArray());
			final ObjectInputStream ois = new ObjectInputStream(bis);
			object = (T)ois.readObject();
		}
		finally
		{
			StreamUtils.closeOutputStream(byteArrayOutputStream);
			StreamUtils.closeOutputStream(objectOutputStream);
		}
		return object;
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
	 *             the illegal access exception
	 * @throws InvocationTargetException
	 *             the invocation target exception
	 * @throws NoSuchMethodException
	 *             the no such method exception
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public static List<SerializedChangedAttributeResult> getChangedData(final Object sourceOjbect,
		final Object objectToCompare) throws IllegalAccessException, InvocationTargetException,
		NoSuchMethodException
	{
		final Map beanDescription = BeanUtils.describe(sourceOjbect);
		beanDescription.remove("class");
		final Map clonedBeanDescription = BeanUtils.describe(objectToCompare);
		clonedBeanDescription.remove("class");
		final List<SerializedChangedAttributeResult> changedData = new ArrayList<>();
		for (final Object key : beanDescription.keySet())
		{
			final BeanComparator comparator = new BeanComparator(key.toString());
			if (comparator.compare(sourceOjbect, objectToCompare) != 0)
			{
				final Object sourceAttribute = beanDescription.get(key);
				final Object changedAttribute = clonedBeanDescription.get(key);
				changedData.add(new SerializedChangedAttributeResult(key, sourceAttribute,
					changedAttribute));
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
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public static Map<Object, SerializedChangedAttributeResult> getChangedDataMap(
		final Object sourceOjbect, final Object objectToCompare) throws IllegalAccessException,
		InvocationTargetException, NoSuchMethodException
	{
		final Map beanDescription = BeanUtils.describe(sourceOjbect);
		beanDescription.remove("class");
		final Map clonedBeanDescription = BeanUtils.describe(objectToCompare);
		clonedBeanDescription.remove("class");
		final Map<Object, SerializedChangedAttributeResult> changedData = new HashMap<>();
		for (final Object key : beanDescription.keySet())
		{
			final BeanComparator comparator = new BeanComparator(key.toString());
			if (comparator.compare(sourceOjbect, objectToCompare) != 0)
			{
				final Object sourceAttribute = beanDescription.get(key);
				final Object changedAttribute = clonedBeanDescription.get(key);
				changedData.put(key, new SerializedChangedAttributeResult(key, sourceAttribute,
					changedAttribute));
			}
		}
		return changedData;
	}

	/**
	 * Reads the object from the given file.
	 *
	 * @param file
	 *            In that file is the object saved.
	 * @return The object in the file or null.
	 * @throws IOException
	 *             Signals that an I/O exception has occurred.
	 * @throws ClassNotFoundException
	 *             is thrown when a class is not found in the classloader or no definition for the
	 *             class with the specified name could be found.
	 */
	public static Object readSerializedObjectFromFile(final File file) throws IOException,
		ClassNotFoundException
	{
		Object object = null;
		FileInputStream fis = null;
		ObjectInputStream in = null;
		try
		{
			fis = new FileInputStream(file);
			in = new ObjectInputStream(fis);
			object = in.readObject();
			in.close();
		}
		finally
		{
			StreamUtils.closeInputStream(in);
			StreamUtils.closeInputStream(fis);
		}
		return object;
	}

	/**
	 * The Method toByteArray() serialize an Object to byte array.
	 *
	 * @param <T>
	 *            the generic type of the given object
	 * @param object
	 *            The Object to convert into a byte array.
	 * @return The byte array from the Object.
	 * @throws IOException
	 *             Signals that an I/O exception has occurred.
	 */
	public static <T> byte[] toByteArray(final T object) throws IOException
	{
		ByteArrayOutputStream byteArrayOutputStream = null;
		ObjectOutputStream objectOutputStream = null;
		try
		{

			byteArrayOutputStream = new ByteArrayOutputStream(FileConstants.KILOBYTE);

			byteArrayOutputStream.reset();
			objectOutputStream = new ObjectOutputStream(byteArrayOutputStream);
			objectOutputStream.writeObject(object);
			objectOutputStream.close();
			return byteArrayOutputStream.toByteArray();
		}
		finally
		{
			StreamUtils.closeOutputStream(byteArrayOutputStream);
			StreamUtils.closeOutputStream(objectOutputStream);
		}
	}

	/**
	 * The Method toObject() converts the given byte array into an Object.
	 *
	 * @param byteArray
	 *            The byte array to convert into an Object.
	 * @return The Object the was converted from the byte array.
	 * @throws IOException
	 *             Signals that an I/O exception has occurred.
	 * @throws ClassNotFoundException
	 *             is thrown when a class is not found in the classloader or no definition for the
	 *             class with the specified name could be found.
	 */
	public static Object toObject(final byte[] byteArray) throws IOException,
		ClassNotFoundException
	{
		Object object = null;
		ByteArrayInputStream byteArrayInputStream = null;
		ObjectInputStream objectInputStream = null;
		try
		{
			byteArrayInputStream = new ByteArrayInputStream(byteArray);
			objectInputStream = new ObjectInputStream(byteArrayInputStream);
			object = objectInputStream.readObject();
			objectInputStream.close();
		}
		finally
		{
			StreamUtils.closeInputStream(byteArrayInputStream);
			StreamUtils.closeInputStream(objectInputStream);
		}
		return object;
	}

	/**
	 * Writes the given object to the given File.
	 *
	 * @param obj
	 *            The object to write to the File.
	 * @param file
	 *            In this file will be the object saved.
	 * @return true if the object was written to the file otherwise false.
	 * @throws IOException
	 *             Signals that an I/O exception has occurred.
	 */
	public static boolean writeSerializedObjectToFile(final Object obj, final File file)
		throws IOException
	{
		final boolean written = true;
		FileOutputStream fos = null;
		ObjectOutputStream oos = null;
		try
		{
			fos = new FileOutputStream(file);
			oos = new ObjectOutputStream(fos);
			oos.writeObject(obj);
			oos.flush();
			oos.close();
		}
		finally
		{
			StreamUtils.closeOutputStream(oos);
			StreamUtils.closeOutputStream(fos);
		}
		return written;
	}

	/**
	 * Private Constructor.
	 */
	private SerializedObjectUtils()
	{
	}
}
