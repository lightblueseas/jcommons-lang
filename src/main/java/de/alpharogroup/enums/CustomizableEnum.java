/*
 * Copyright 2016 Alpha Ro Group UG (haftungsbeschrängt).
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package de.alpharogroup.enums;

import de.alpharogroup.check.Check;
import lombok.Getter;

/**
 * The class {@link CustomizableEnum} is for use if you have an enum but you want that the user can
 * set a custom value.
 *
 * @author astrapi69
 */
public class CustomizableEnum<E extends Enum<E>, T>
{

	/** The enumtype. */
	@Getter
	private final E enumtype;

	/** The value. */
	@Getter
	private final T value;

	/**
	 * Instantiates a new {@link CustomizableEnum}.
	 *
	 * @param enumtype
	 *            the type of the enum
	 * @param value
	 *            the value
	 */
	public CustomizableEnum(final E enumtype, final T value)
	{
		Check.get().notNull(value, "value").notNull(enumtype, "enumtype");
		this.enumtype = enumtype;
		this.value = value;
	}
}
