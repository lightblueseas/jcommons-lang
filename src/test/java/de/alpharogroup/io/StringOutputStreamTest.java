package de.alpharogroup.io;

import java.io.IOException;
import java.io.InputStream;

import org.testng.AssertJUnit;
import org.testng.annotations.Test;

import de.alpharogroup.lang.ClassExtensions;

/**
 * The class {@link StringOutputStreamTest} is the test class for the class {@link StringOutputStream}.
 *
 * @version 1.0
 * @author Asterios Raptis
 */
/**
 * The class {@link StringOutputStreamTest}.
 */
public class StringOutputStreamTest
{
	/** The file. */
	final String propertiesFilename = "de/alpharogroup/lang/resources.properties";

	/**
	 * Instantiates a new {@link testToString}.
	 *
	 * @throws IOException
	 *             Signals that an I/O exception has occurred.
	 */
	@Test
	public void testToString() throws IOException
	{
		final InputStream inputStream = ClassExtensions.getResourceAsStream(propertiesFilename);
		final StringOutputStream stringOutput = new StringOutputStream();

		final byte[] buffer = new byte[8192];
		int readLen;
		while ((readLen = inputStream.read(buffer, 0, buffer.length)) != -1)
		{
			stringOutput.write(buffer, 0, readLen);
		}
		final String expected = "testkey3=testvalue3";
		final String actual = stringOutput.toString();
		stringOutput.close();
		AssertJUnit.assertTrue("", actual.startsWith(expected));
	}

}
