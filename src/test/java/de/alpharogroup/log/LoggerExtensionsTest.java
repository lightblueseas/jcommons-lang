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
		FileAppender fileAppender = LoggerExtensions.newFileAppender("./"+fileName);
		LoggerExtensions.addFileAppender(log, fileAppender);
		Appender appender = log.getAppender("MyFileAppender");
		assertNotNull(appender);
		assertEquals(fileAppender, appender);
		File logFile = new File(".", fileName);
		if(logFile.exists()) {
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
		FileAppender fileAppender = LoggerExtensions.newFileAppender("./"+fileName);
		
		assertNotNull(fileAppender);
		File logFile = new File(".", fileName);
		if(logFile.exists()) {
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
