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
package de.alpharogroup.shell;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * The Class LinuxShellExecutor.
 */
public class LinuxShellExecutor
{

	/**
	 * Execute the given commands and return the result.
	 *
	 * @param withResponse
	 *            the with response
	 * @param command
	 *            the command(s)
	 * @return the string
	 * @throws IOException
	 *             Signals that an I/O exception has occurred.
	 * @throws InterruptedException
	 *             the interrupted exception
	 */
	public static String execute(final boolean withResponse, final String... command)
		throws IOException, InterruptedException
	{
		final List<String> commands = new ArrayList<>();
		commands.add("bash");
		commands.add("-c");
		commands.addAll(Arrays.asList(command));
		String response = "";

		final ProcessBuilder pb = new ProcessBuilder(commands);
		pb.redirectErrorStream(true);

		final Process shell = pb.start();

		if (withResponse)
		{
			try (InputStream shellIn = shell.getInputStream())
			{
				response = toString(shellIn);
			}
		}

		return response;
	}

	/**
	 * Converts the given InputStream to a string.
	 *
	 * @param is
	 *            the is
	 * @return the string
	 * @throws IOException
	 *             Signals that an I/O exception has occurred.
	 */
	public static String toString(final InputStream is) throws IOException
	{

		if (is != null)
		{
			final StringBuilder sb = new StringBuilder();
			final BufferedReader br = new BufferedReader(new InputStreamReader(is, "UTF-8"));
			String line;
			while ((line = br.readLine()) != null)
			{
				sb.append(line).append("\n");
			}
			return sb.toString();
		}
		else
		{
			return "";
		}
	}

}
