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
package de.alpharogroup.io;

import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.ObjectStreamClass;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.io.Reader;
import java.io.Serializable;
import java.io.Writer;

/**
 * Utility class for input/output operations.
 *
 * @version 1.0
 * @author Asterios Raptis
 */
public final class StreamUtils implements Serializable
{

	/**
	 * The serialVersionUID.
	 */
	private static final long serialVersionUID = 5042445056004440533L;


	/**
	 * Closes the given InputStream.
	 *
	 * @param in
	 *            The InputStream to close.
	 * @throws IOException
	 *             Signals that an I/O exception has occurred.
	 */
	public static void close(InputStream in) throws IOException
	{
		try
		{
			if (in != null)
			{
				in.close();
				in = null;
			}
		}
		catch (final IOException e)
		{
			throw e;
		}
		finally
		{
			if (in != null)
			{
				in.close();
			}

		}
	}

	/**
	 * Closes the given OutputStream.
	 *
	 * @param out
	 *            The OutputStream to close.
	 * @throws IOException
	 *             Signals that an I/O exception has occurred.
	 */
	public static void close(OutputStream out) throws IOException
	{
		try
		{
			if (out != null)
			{
				out.flush();
				out.close();
				out = null;
			}
		}
		catch (final IOException e)
		{
			throw e;
		}
		finally
		{
			if (out != null)
			{
				out.flush();
				out.close();
			}
		}
	}

	/**
	 * Closes the given Reader.
	 *
	 * @param reader
	 *            The Reader to close.
	 * @throws IOException
	 *             Signals that an I/O exception has occurred.
	 */
	public static void close(Reader reader) throws IOException
	{

		try
		{
			if (reader != null)
			{
				reader.close();
				reader = null;
			}
		}
		catch (final IOException e)
		{
			throw e;
		}
		finally
		{

			if (reader != null)
			{
				reader.close();
			}

		}
	}

	/**
	 * Closes the given Writer.
	 *
	 * @param writer
	 *            The Writer to close.
	 * @throws IOException
	 *             Signals that an I/O exception has occurred.
	 */
	public static void close(Writer writer) throws IOException
	{
		try
		{
			if (writer != null)
			{
				writer.flush();
				writer.close();
				writer = null;
			}
		}
		catch (final IOException e)
		{
			throw e;
		}
		finally
		{
			if (writer != null)
			{
				writer.flush();
				writer.close();
			}
		}
	}

	/**
	 * Closes the given InputStream.
	 *
	 * @param in
	 *            The InputStream to close.
	 * @return Returns true if the OutputStream is closed otherwise false.
	 */
	public static boolean closeInputStream(InputStream in)
	{
		boolean closed = true;
		try
		{
			if (in != null)
			{
				in.close();
				in = null;
			}
		}
		catch (final IOException e)
		{
			closed = false;
		}
		finally
		{
			try
			{
				if (in != null)
				{
					in.close();
				}
			}
			catch (final IOException e)
			{
				closed = false;
			}
		}
		return closed;
	}

	/**
	 * Closes the given OutputStream.
	 *
	 * @param out
	 *            The OutputStream to close.
	 * @return Returns true if the OutputStream is closed otherwise false.
	 */
	public static boolean closeOutputStream(OutputStream out)
	{
		boolean closed = true;
		try
		{
			if (out != null)
			{
				out.flush();
				out.close();
				out = null;
			}
		}
		catch (final IOException e)
		{
			closed = false;
		}
		finally
		{
			try
			{
				if (out != null)
				{
					out.flush();
					out.close();
				}
			}
			catch (final IOException e)
			{
				closed = false;
			}
		}
		return closed;
	}

	/**
	 * Closes the given Reader.
	 *
	 * @param reader
	 *            The Reader to close.
	 * @return Returns true if the Reader is closed otherwise false.
	 */
	public static boolean closeReader(Reader reader)
	{
		boolean closed = true;
		try
		{
			if (reader != null)
			{
				reader.close();
				reader = null;
			}
		}
		catch (final IOException e)
		{
			closed = false;
		}
		finally
		{
			try
			{
				if (reader != null)
				{
					reader.close();
				}
			}
			catch (final IOException e)
			{
				closed = false;
			}
		}
		return closed;
	}

	/**
	 * Closes the given Writer.
	 *
	 * @param writer
	 *            The Writer to close.
	 * @return Returns true if the Writer is closed otherwise false.
	 */
	public static boolean closeWriter(Writer writer)
	{
		boolean closed = true;
		try
		{
			if (writer != null)
			{
				writer.flush();
				writer.close();
				writer = null;
			}
		}
		catch (final IOException e)
		{
			closed = false;
		}
		finally
		{
			try
			{
				if (writer != null)
				{
					writer.flush();
					writer.close();
				}
			}
			catch (final IOException e)
			{
				closed = false;
			}
		}
		return closed;
	}

	/**
	 * Returns the given InputStream as a byte array.
	 *
	 * @param in
	 *            The InputStream.
	 * @return Returns the given InputStream as a byte array.
	 * @throws IOException
	 *             Signals that an I/O exception has occurred.
	 */
	public static byte[] getByteArray(final InputStream in) throws IOException
	{
		return getByteArray(in, new ByteArrayOutputStream(in.available()));
	}

	/**
	 * Gets the byte array.
	 *
	 * @param in
	 *            The InputStream.
	 * @param os
	 *            The ByteArrayOutputStream.
	 * @return the byte array
	 * @throws IOException
	 *             Signals that an I/O exception has occurred.
	 */
	public static byte[] getByteArray(final InputStream in, final ByteArrayOutputStream os)
		throws IOException
	{
		int byt;
		byte[] bytes = null;
		try
		{
			while ((byt = in.read()) != -1)
			{
				os.write(byt);
			}

		}
		catch (final IOException e)
		{
			throw e;
		}
		finally
		{
			bytes = os.toByteArray();
		}
		return bytes;
	}

	/**
	 * Gets the input stream from a File object.
	 *
	 * @param file
	 *            the file.
	 * @return the input stream.
	 * @throws IOException
	 *             Signals that an I/O exception has occurred.
	 */
	public static InputStream getInputStream(final File file) throws IOException
	{
		return getInputStream(file, false);
	}

	/**
	 * Gets the input stream from a File object.
	 *
	 * @param file
	 *            the file
	 * @param createFile
	 *            If true and the file does not exist it will be create a new file.
	 * @return the input stream
	 * @throws IOException
	 *             Signals that an I/O exception has occurred.
	 */
	public static InputStream getInputStream(final File file, final boolean createFile)
		throws IOException
	{
		InputStream is = null;
		if (file.exists())
		{
			is = file.toURI().toURL().openStream();
		}
		else
		{
			if (createFile)
			{
				file.createNewFile();
				is = file.toURI().toURL().openStream();
			}
			else
			{
				throw new FileNotFoundException("File " + file.getName() + " does not exist.");
			}
		}
		return is;
	}

	/**
	 * Gets the output stream from a File object.
	 *
	 * @param file
	 *            the file.
	 * @return the output stream.
	 * @throws IOException
	 *             Signals that an I/O exception has occurred.
	 */
	public static OutputStream getOutputStream(final File file) throws IOException
	{
		return getOutputStream(file, false);
	}


	/**
	 * Gets the output stream from a File object.
	 *
	 * @param file
	 *            the file
	 * @param createFile
	 *            If true and the file does not exist it will be create a new file.
	 * @return the output stream
	 * @throws IOException
	 *             Signals that an I/O exception has occurred.
	 */
	public static OutputStream getOutputStream(final File file, final boolean createFile)
		throws IOException
	{
		OutputStream os = null;
		BufferedOutputStream bos = null;
		if (file.exists())
		{
			os = new FileOutputStream(file);
		}
		else
		{
			if (createFile)
			{
				file.createNewFile();
				os = new FileOutputStream(file);
			}
			else
			{
				throw new FileNotFoundException("File " + file.getName() + " does not exist.");
			}
		}
		bos = new BufferedOutputStream(os);
		return bos;
	}

	/**
	 * Gets a Reader from the given file object.
	 *
	 * @param inputFile
	 *            the input file.
	 * @return the reader.
	 * @throws IOException
	 *             Signals that an I/O exception has occurred.
	 */
	public static Reader getReader(final File inputFile) throws IOException
	{
		return getReader(inputFile, null, false);
	}

	/**
	 * Gets a Reader from the given file object.
	 *
	 * @param inputFile
	 *            the input file
	 * @param encoding
	 *            The encoding from the file.
	 * @param createFile
	 *            If true and the file does not exist it will be create a new file.
	 * @return the reader
	 * @throws IOException
	 *             Signals that an I/O exception has occurred.
	 */
	public static Reader getReader(final File inputFile, final String encoding,
		final boolean createFile) throws IOException
	{
		FileInputStream fis = null;
		InputStreamReader isr = null;
		BufferedReader reader = null;
		if (inputFile.exists())
		{
			fis = new FileInputStream(inputFile);
		}
		else
		{
			if (createFile)
			{
				inputFile.createNewFile();
				fis = new FileInputStream(inputFile);
			}
			else
			{
				throw new FileNotFoundException("File " + inputFile.getName() + " does not exist.");
			}
		}
		if (null == encoding)
		{
			isr = new InputStreamReader(fis);
		}
		else
		{
			isr = new InputStreamReader(fis, encoding);
		}
		// create the bufferedreader
		reader = new BufferedReader(isr);

		return reader;
	}

	/**
	 * Gets the SerialVersionUID from the given Class.
	 *
	 * @param clazz
	 *            The class
	 * @return the serial version uid
	 */
	public static long getSerialVersionUID(final Class<? extends Serializable> clazz)
	{
		return ObjectStreamClass.lookup(clazz).getSerialVersionUID();
	}


	/**
	 * Gets a Writer from the given file object.
	 *
	 * @param file
	 *            the file.
	 * @return the Writer.
	 * @throws IOException
	 *             Signals that an I/O exception has occurred.
	 */
	public static Writer getWriter(final File file) throws IOException
	{
		return getWriter(file, null, false);
	}

	/**
	 * Gets a Writer from the given file object.
	 *
	 * @param file
	 *            the file
	 * @param encoding
	 *            The encoding from the file.
	 * @param createFile
	 *            the create file
	 * @return the Writer.
	 * @throws IOException
	 *             Signals that an I/O exception has occurred.
	 */
	public static Writer getWriter(final File file, final String encoding, final boolean createFile)
		throws IOException
	{
		PrintWriter printWriter = null;
		BufferedOutputStream bos = null;
		FileOutputStream fos = null;
		OutputStreamWriter osw = null;
		if (file.exists())
		{
			fos = new FileOutputStream(file);
		}
		else
		{
			if (createFile)
			{
				file.createNewFile();
				fos = new FileOutputStream(file);
			}
			else
			{
				throw new FileNotFoundException("File " + file.getName() + "does not exist.");
			}
		}
		bos = new BufferedOutputStream(fos);
		if (null == encoding)
		{
			osw = new OutputStreamWriter(bos);
		}
		else
		{
			osw = new OutputStreamWriter(bos, encoding);
		}
		printWriter = new PrintWriter(osw);

		return printWriter;

	}

	/**
	 * The Method writeInputStreamToOutputStream(InputStream, OutputStream, boolean) writes to the
	 * given OutputStream from an opened InputStream.
	 *
	 * @param inputStream
	 *            The opened InputStream.
	 * @param outputStream
	 *            The opened OutputStream.
	 * @param closeStream
	 *            If true then close the outputStream otherwise keep open.
	 * @throws IOException
	 *             Signals that an I/O exception has occurred.
	 */
	public static void writeInputStreamToOutputStream(final InputStream inputStream,
		final OutputStream outputStream, final boolean closeStream) throws IOException
	{
		int byt;
		try
		{
			while ((byt = inputStream.read()) != -1)
			{
				outputStream.write(byt);
			}
			if (closeStream)
			{
				inputStream.close();
				outputStream.close();
			}
		}
		catch (final IOException e)
		{
			throw e;
		}
		finally
		{
			if (closeStream)
			{
				StreamUtils.closeInputStream(inputStream);
				StreamUtils.closeOutputStream(outputStream);
			}
		}
	}

	/**
	 * Private constructor.
	 */
	private StreamUtils()
	{
	}

}
