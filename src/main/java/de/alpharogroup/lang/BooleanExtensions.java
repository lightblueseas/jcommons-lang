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
 * The class {@link BooleanExtensions}.
 */
public class BooleanExtensions
{

	/**
	 * Decides over the given flags if the true-case or the false-case will be return.
	 *
	 * @param flags
	 *            the flags
	 * @return the false-case if all false or empty otherwise the true-case.
	 */
	public static <T> T trueOrFalse(final T trueCase, final T falseCase, final boolean... flags)
	{
		boolean interlink = false;
		for (int i = 0; i < flags.length; i++)
		{
			if (i == 0)
			{
				interlink = !flags[i];
				continue;
			}
			interlink &= !flags[i];
		}
		if (interlink)
		{
			return falseCase;
		}
		return trueCase;
	}

}
