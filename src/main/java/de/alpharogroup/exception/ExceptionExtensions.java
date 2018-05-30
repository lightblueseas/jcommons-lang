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
package de.alpharogroup.exception;

import java.io.PrintWriter;
import java.io.StringWriter;

import org.apache.commons.lang3.builder.ReflectionToStringBuilder;

import de.alpharogroup.io.StreamExtensions;
import lombok.experimental.UtilityClass;

/**
 * The class {@link ExceptionExtensions}.
 *
 * @author Asterios Raptis
 * @version 1.0
 */
@UtilityClass
public final class ExceptionExtensions
{

	/**
	 * Gets the stacktrace as string.
	 *
	 * @param throwable
	 *            the throwable
	 * @return the stacktrace as string.
	 */
	public static String getStackTrace(final Throwable throwable)
	{
		StringBuilder stacktrace = new StringBuilder();
		if (null == throwable)
		{
			stacktrace.append("throwable is null...");
			return stacktrace.toString();
		}
		StringWriter sw = null;
		PrintWriter pw = null;
		try
		{
			sw = new StringWriter();
			pw = new PrintWriter(sw);
			throwable.printStackTrace(pw);
			stacktrace.append(sw.toString());
		}
		finally
		{
			StreamExtensions.closeWriter(sw);
			StreamExtensions.closeWriter(pw);
		}
		return stacktrace.toString();
	}

	/**
	 * Gets the stack trace elements from the given Throwable and returns a {@link String} object
	 * from it.
	 *
	 * @param throwable
	 *            the throwable
	 * @return the stack trace elements
	 */
	public static String getStackTraceElements(Throwable throwable)
	{
		StringBuilder stacktrace = new StringBuilder();
		if (null == throwable)
		{
			stacktrace.append("throwable is null...");
			return stacktrace.toString();
		}
		StringWriter sw = null;
		PrintWriter pw = null;
		try
		{
			sw = new StringWriter();
			pw = new PrintWriter(sw);
			pw.println(throwable.getClass().toString());
			while (throwable != null)
			{
				pw.println(throwable);
				final StackTraceElement[] stackTraceElements = throwable.getStackTrace();
				for (final StackTraceElement stackTraceElement : stackTraceElements)
				{
					pw.println("\tat " + stackTraceElement);
				}

				throwable = throwable.getCause();
				if (throwable != null)
				{
					pw.println("Caused by:\r\n");
				}
			}
			stacktrace.append(sw.toString());
		}
		finally
		{
			StreamExtensions.closeWriter(sw);
			StreamExtensions.closeWriter(pw);
		}

		return stacktrace.toString();
	}

	/**
	 * Prints the {@link Object#toString()} and if the given object is null a corresponding
	 * information.
	 *
	 * @param <T>
	 *            the generic type
	 * @param object
	 *            the object
	 * @return the string
	 * @deprecated use instead the same name method in class {@code StringExtensions}. Note: will be
	 *             removed in the next minor release
	 */
	public static <T> String toString(final T object)
	{
		if (object == null)
		{
			return "Given object is null!!!";
		}
		return ReflectionToStringBuilder.toString(object);
	}

}
