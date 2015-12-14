package de.alpharogroup.lang.object;

import java.io.IOException;
import java.io.Serializable;
import java.lang.reflect.Array;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.log4j.Logger;

import de.alpharogroup.io.SerializedObjectExtensions;
import de.alpharogroup.reflection.ReflectionExtensions;

/**
 * The class {@link CloneObjectExtensions} provide methods for clone an object.
 */
public final class CloneObjectExtensions
{

	/** The logger constant. */
	private static final Logger LOG = Logger.getLogger(CloneObjectExtensions.class.getName());
	/**
	 * Try to clone the given generic object.
	 *
	 * @param <T>
	 *            the generic type
	 * @param object
	 *            the object to clone
	 * @return The cloned object or null if the clone process failed.
	 */
	@SuppressWarnings("unchecked")
	public static <T> T clone(final T object)
	{
		return (T)cloneObjectQuietly(object);
	}


	/**
	 * Try to clone the given object.
	 *
	 * @param object
	 *            The object to clone.
	 * @return The cloned object or null if the clone process failed.
	 * @throws NoSuchMethodException
	 *             the no such method exception
	 * @throws SecurityException
	 *             Thrown if the security manager indicates a security violation.
	 * @throws IllegalAccessException
	 *             the illegal access exception
	 * @throws IllegalArgumentException
	 *             the illegal argument exception
	 * @throws InvocationTargetException
	 *             the invocation target exception
	 * @throws ClassNotFoundException
	 *             the class not found exception
	 * @throws InstantiationException
	 *             the instantiation exception
	 * @throws IOException
	 *             Signals that an I/O exception has occurred.
	 */
	public static Object cloneObject(final Object object) throws NoSuchMethodException,
		SecurityException, IllegalAccessException, IllegalArgumentException,
		InvocationTargetException, ClassNotFoundException, InstantiationException, IOException
	{
		Object clone = null;
		// Try to clone the object if it implements Serializable.
		if (object instanceof Serializable)
		{
			clone = SerializedObjectExtensions.copySerializedObject((Serializable)object);
			if (clone != null)
			{
				return clone;
			}
		}
		// Try to clone the object if it is Cloneble.
		if (clone == null && object instanceof Cloneable)
		{

			if (object.getClass().isArray())
			{
				final Class<?> componentType = object.getClass().getComponentType();
				if (componentType.isPrimitive())
				{
					int length = Array.getLength(object);
					clone = Array.newInstance(componentType, length);
					while (length-- > 0)
					{
						Array.set(clone, length, Array.get(object, length));
					}
				}
				else
				{
					clone = ((Object[])object).clone();
				}
				if (clone != null)
				{
					return clone;
				}
			}
			final Class<?> clazz = object.getClass();
			final Method cloneMethod = clazz.getMethod("clone", (Class[])null);
			clone = cloneMethod.invoke(object, (Object[])null);
			if (clone != null)
			{
				return clone;
			}
		}
		// Try to clone the object by copying all his properties with
		// the BeanUtils.copyProperties() method.
		if (clone == null)
		{
			clone = ReflectionExtensions.getNewInstance(object);
			BeanUtils.copyProperties(clone, object);
		}
		return clone;
	}

	/**
	 * Try to clone the given object quietly.
	 *
	 * @param object
	 *            The object to clone.
	 * @return The cloned object or null if the clone process failed.
	 */
	public static Object cloneObjectQuietly(final Object object)
	{
		Object clone = null;
		try
		{
			clone = cloneObject(object);
		}
		catch (final NoSuchMethodException e)
		{
			LOG.error("Try to clone the object with " + "reflection and call the clone method. "
				+ "Thrown exception: NoSuchMethodException", e);
		}
		catch (final SecurityException e)
		{
			LOG.error("Try to clone the object with " + "reflection and call the clone method. "
				+ "Thrown exception: SecurityException", e);
		}
		catch (final IllegalAccessException e)
		{
			LOG.error("Try to clone the object with "
				+ "org.apache.commons.beanutils.BeanUtils failed "
				+ "cause of IllegalAccessException. Could not found from ReflectionUtils.", e);
		}
		catch (final IllegalArgumentException e)
		{
			LOG.error("Try to clone the object with " + "reflection and call the clone method. "
				+ "Thrown exception: IllegalArgumentException", e);
		}
		catch (final InvocationTargetException e)
		{
			LOG.error("Try to clone the object with "
				+ "org.apache.commons.beanutils.BeanUtils failed "
				+ "cause of InvocationTargetException. Could not found from ReflectionUtils.", e);
		}
		catch (final ClassNotFoundException e)
		{
			LOG.error("Try to clone the object with "
				+ "org.apache.commons.beanutils.BeanUtils failed "
				+ "cause of ClassNotFoundException. Could not found from ReflectionUtils.", e);
		}
		catch (final InstantiationException e)
		{
			LOG.error("Try to clone the object with "
				+ "org.apache.commons.beanutils.BeanUtils failed "
				+ "cause of InstantiationException. Could not found from ReflectionUtils.", e);
		}
		catch (final IOException e)
		{
			LOG.error("Try to clone the object with "
				+ "SerializedObjectUtils.copySerializedObject((Serializable)object) "
				+ "cause of IOException.", e);
		}
		return clone;
	}
}
