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

import lombok.experimental.UtilityClass;

/**
 * Utility class for getting the Memory status.
 * @deprecated moved to jobj-core repository
 *
 * @version 1.0
 * @author Asterios Raptis
 */
@UtilityClass
public final class MemoryExtensions
{
	/**
	 * Freeing memory that is available for the application by running the garbage collector.
	 */
	public static void disposeUnusedMemory()
	{
		Runtime.getRuntime().gc();
	}

	/**
	 * Gets the free memory in kilobytes is available for the application.
	 *
	 * @return Returns the free memory in kilobytes is available for the application.
	 */
	public static long getFreeMemoryForAppInKB()
	{
		return (Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory()) / 1024;
	}

	/**
	 * Gets the free memory in kilobytes is available.
	 *
	 * @return Returns the free memory in kilobytes is available.
	 */
	public static long getFreeMemoryInKB()
	{
		return Runtime.getRuntime().freeMemory() / 1024;
	}

	/**
	 * Gets the total memory in kilobytes is available.
	 *
	 * @return Returns the total memory in kilobytes is available.
	 */
	public static long getTotalMemoryInKB()
	{
		return Runtime.getRuntime().totalMemory() / 1024;
	}

}
