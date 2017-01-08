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

/**
 * The enum {@link ClassType} defines the types a class can have.
 */
public enum ClassType
{

	/** The annotation class type. */
	ANNOTATION,

	/** The anonymous class type. */
	ANONYMOUS,

	/** The array class type. */
	ARRAY,

	/** The collection class type. */
	COLLECTION,

	/** The enum class type. */
	ENUM,

	/** The interface class type. */
	INTERFACE,

	/** The local class type. */
	LOCAL,

	/** The map class type. */
	MAP,

	/** The member class type. */
	MEMBER,

	/** The primitive class type. */
	PRIMITIVE,

	/** The synthetic class type. */
	SYNTHETIC,

	/** The default class type. */
	DEFAULT;
}
