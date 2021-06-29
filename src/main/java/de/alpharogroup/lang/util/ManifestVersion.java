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
package de.alpharogroup.lang.util;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.jar.Attributes;
import java.util.jar.Manifest;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.FieldDefaults;

/**
 * Simple bean to hold information about the version of a Manifest. Can be used for JAR, EAR and WAR
 * manifest files.
 * @deprecated moved to jobj-core repository
 */
@Getter
@Setter
@EqualsAndHashCode
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder(toBuilder = true)
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ManifestVersion implements Serializable
{

	/** The serialVersionUID. */
	private static final long serialVersionUID = 1L;

	/** The last modified holds the {@code LocalDateTime} when the last build was. */
	LocalDateTime lastModified;

	/** The manifest. */
	Manifest manifest;

	/** The title. */
	String title;

	/** The version number. */
	String version;

	/**
	 * Gets the manifest attribute value from the given {@code Attributes.Name} object.
	 *
	 * @param name
	 *            the name
	 * @return the manifest attribute or an empty String if the manifest attribute value is null
	 */
	public String getManifestAttribute(final Attributes.Name name)
	{
		if (getManifest() != null)
		{
			final Attributes attributes = getManifest().getMainAttributes();
			final Object value = attributes.get(name);
			if (value != null)
			{
				return value.toString().trim();
			}
		}
		return "";
	}

}
