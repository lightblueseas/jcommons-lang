package de.alpharogroup.log;

import lombok.experimental.UtilityClass;

import org.apache.log4j.FileAppender;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.apache.log4j.PatternLayout;

/**
 * Logger extensions for appenders.
 */
@UtilityClass
public class LoggerExtensions
{

	/**
	 * Adds the file appender to the given logger.
	 *
	 * @param logger
	 *            the logger
	 * @param fileAppender
	 *            the file appender
	 */
	public static void addFileAppender(final Logger logger, final FileAppender fileAppender)
	{
		logger.addAppender(fileAppender);
	}

	/**
	 * New file appender.
	 *
	 * @param logFilePath
	 *            the log file path
	 * @return the file appender
	 */
	public static FileAppender newFileAppender(final String logFilePath)
	{
		final FileAppender appender = new FileAppender();
		appender.setName("MyFileAppender");
		appender.setLayout(new PatternLayout("%d %-5p [%c{1}] %m%n"));
		appender.setFile(logFilePath);
		appender.setAppend(true);
		appender.setThreshold(Level.DEBUG);
		appender.activateOptions();
		return appender;
	}
}