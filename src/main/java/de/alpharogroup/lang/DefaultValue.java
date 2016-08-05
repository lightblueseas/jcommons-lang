package de.alpharogroup.lang;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import de.alpharogroup.check.Check;
import lombok.experimental.UtilityClass;

/**
 * The class DefaultValue provide the default values of the primitive types, as defined by the JLS.
 */
@UtilityClass
public class DefaultValue
{

	/** The constant map with the default values. */
	@SuppressWarnings("serial")
	private static final Map<Class<?>, Object> DEFAULT_VALUE = Collections
		.unmodifiableMap(new HashMap<Class<?>, Object>()
		{
			{
				put(boolean.class, false);
				put(char.class, '\0');
				put(byte.class, (byte)0);
				put(short.class, (short)0);
				put(int.class, 0);
				put(long.class, 0L);
				put(float.class, 0f);
				put(double.class, 0d);
			}
		});

	/**
	 * Gets the default value from the given {@link Class}.
	 *
	 * @param <T>
	 *            the generic type
	 * @param classType
	 *            the class type
	 * @return the default value
	 */
	@SuppressWarnings("unchecked")
	public static <T> T getDefaultValue(final Class<T> classType)
	{
		Check.get().notNull(classType, "classType");
		final T defaultValue = (T)DEFAULT_VALUE.get(classType);
		return defaultValue;
	}
}
