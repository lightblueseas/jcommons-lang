package de.alpharogroup.io;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.Date;

import org.apache.commons.io.FileUtils;
import org.testng.AssertJUnit;
import org.testng.annotations.Test;

import de.alpharogroup.date.CreateDateExtensions;

/**
 * The class {@link StringOutputStreamTest} is the test class for the class {@link StringOutputStream}.
 *
 * @version 1.0
 * @author Asterios Raptis
 */
public class StringOutputStreamTest
{

	/**
	 * Test method for {@link StringOutputStream#toString()}
	 *
	 * @throws IOException
	 *             Signals that an I/O exception has occurred.
	 */
	@Test
	public void testToString() throws IOException
	{
		final String expected = "Thu Apr 19 00:00:00 CEST 2012";
		final File writeInMe = new File(".", "testWriteBirthdayToFile.dat");
		FileUtils.writeStringToFile(writeInMe, expected);
		final InputStream inputStream = writeInMe.toURI().toURL().openStream();
		final StringOutputStream stringOutput = new StringOutputStream();

		final byte[] buffer = new byte[8192];
		int readLength;
		while ((readLength = inputStream.read(buffer, 0, buffer.length)) != -1)
		{
			stringOutput.write(buffer, 0, readLength);
		}

		final String actual = stringOutput.toString();
		stringOutput.close();
		AssertJUnit.assertTrue("", actual.startsWith(expected));
	}

}
