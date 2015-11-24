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
package de.alpharogroup.exception;

import java.io.PrintWriter;
import java.io.StringWriter;

import de.alpharogroup.io.StreamUtils;

/**
 * The Class ExceptionUtils.
 *
 * @author Asterios Raptis
 * @version 1.0
 */
public class ExceptionUtils
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
		if (null == throwable)
		{
			return null;
		}
		StringWriter sw = null;
		PrintWriter pw = null;
		String stacktrace = null;
		try
		{
			sw = new StringWriter();
			pw = new PrintWriter(sw);
			throwable.printStackTrace(pw);
			stacktrace = sw.toString();
		}
		finally
		{
			StreamUtils.closeWriter(sw);
			StreamUtils.closeWriter(pw);
		}
		return stacktrace;
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
		StringWriter sw = null;
		PrintWriter pw = null;
		String stacktrace = "throwable is null...";
		if (throwable != null)
		{
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
				stacktrace = sw.toString();
			}
			finally
			{
				StreamUtils.closeWriter(sw);
				StreamUtils.closeWriter(pw);
			}
		}

		return stacktrace;
	}

}
