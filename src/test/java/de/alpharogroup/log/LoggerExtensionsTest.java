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
package de.alpharogroup.log;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertNotNull;

import java.io.File;

import org.apache.log4j.Appender;
import org.apache.log4j.FileAppender;
import org.meanbean.factories.ObjectCreationException;
import org.meanbean.test.BeanTestException;
import org.meanbean.test.BeanTester;
import org.testng.annotations.Test;

import lombok.extern.log4j.Log4j;

/**
 * The unit test class for the class {@link LoggerExtensions}.
 */
@Log4j
public class LoggerExtensionsTest
{

	/**
	 * Test method for {@link LoggerExtensions#addFileAppender(Logger, FileAppender)}.
	 */
	@Test
	public final void testAddFileAppender()
	{
		String fileName = "testLoggerExtensions.log";
		FileAppender fileAppender = LoggerExtensions.newFileAppender("./" + fileName);
		LoggerExtensions.addFileAppender(log, fileAppender);
		Appender appender = log.getAppender("MyFileAppender");
		assertNotNull(appender);
		assertEquals(fileAppender, appender);
		File logFile = new File(".", fileName);
		if (logFile.exists())
		{
			logFile.deleteOnExit();
		}
	}

	/**
	 * Test method for {@link LoggerExtensions#newFileAppender(String)}.
	 */
	@Test
	public final void testNewFileAppender()
	{
		String fileName = "testLoggerExtensions.log";
		FileAppender fileAppender = LoggerExtensions.newFileAppender("./" + fileName);

		assertNotNull(fileAppender);
		File logFile = new File(".", fileName);
		if (logFile.exists())
		{
			logFile.deleteOnExit();
		}
	}

	/**
	 * Test method for {@link LoggerExtensions}
	 */
	@Test(expectedExceptions = { BeanTestException.class, ObjectCreationException.class })
	public void testWithBeanTester()
	{
		final BeanTester beanTester = new BeanTester();
		beanTester.testBean(LoggerExtensions.class);
	}
}
