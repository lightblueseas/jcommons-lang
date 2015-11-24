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
package de.alpharogroup.velocity;

import java.io.StringReader;
import java.io.StringWriter;

import org.apache.velocity.Template;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.Velocity;
import org.apache.velocity.app.VelocityEngine;
import org.apache.velocity.runtime.RuntimeConstants;
import org.apache.velocity.runtime.RuntimeServices;
import org.apache.velocity.runtime.RuntimeSingleton;
import org.apache.velocity.runtime.parser.ParseException;
import org.apache.velocity.runtime.parser.node.SimpleNode;
import org.apache.velocity.runtime.resource.loader.ClasspathResourceLoader;

/**
 * The Class VelocityUtils.
 */
public class VelocityUtils
{

	/** The Constant VELOCITY_TEMPLATE_FILE_EXTENSION. */
	public static final String VELOCITY_TEMPLATE_FILE_EXTENSION = ".vm";

	/**
	 * Gets the velocity engine that load resources from the class path.
	 * 
	 * @return the velocity engine
	 */
	public static VelocityEngine getClasspathResourceLoaderVelocityEngine()
	{
		final VelocityEngine ve = new VelocityEngine();
		ve.setProperty(RuntimeConstants.RESOURCE_LOADER, "classpath");
		ve.setProperty("classpath.resource.loader.class", ClasspathResourceLoader.class.getName());
		ve.init();
		return ve;
	}

	/**
	 * Gets a velocity template from the given String.
	 * 
	 * @param templateAsString
	 *            the template as string
	 * @return the template
	 * @throws ParseException
	 *             the parse exception
	 */
	public static Template getTemplate(final String templateAsString) throws ParseException
	{
		final RuntimeServices runtimeServices = RuntimeSingleton.getRuntimeServices();
		final StringReader reader = new StringReader(templateAsString);
		final SimpleNode node = runtimeServices.parse(reader, "Template name");
		final Template template = new Template();
		template.setRuntimeServices(runtimeServices);
		template.setData(node);
		template.initDocument();
		template.setEncoding("UTF-8");
		return template;

	}

	/**
	 * Gets the velocity template with default encoding "UTF-8".
	 * 
	 * @param ve
	 *            the ve
	 * @param path
	 *            the path
	 * @param templateName
	 *            the template name
	 * @return the template
	 */
	public static Template getTemplate(final VelocityEngine ve, final String path,
		final String templateName)
	{
		return getTemplate(ve, path, templateName, "UTF-8");
	}

	/**
	 * Gets the velocity template.
	 * 
	 * @param ve
	 *            the ve
	 * @param path
	 *            the path
	 * @param templateName
	 *            the template name
	 * @param encoding
	 *            the encoding
	 * @return the template
	 */
	public static Template getTemplate(final VelocityEngine ve, final String path,
		final String templateName, final String encoding)
	{
		final String templatePath = path + templateName + VELOCITY_TEMPLATE_FILE_EXTENSION;
		final Template template = ve.getTemplate(templatePath, encoding);
		return template;
	}

	/**
	 * Merges the given context with the given template that is a String object and returns the
	 * result.
	 * 
	 * @param context
	 *            the context
	 * @param templateAsString
	 *            the template as string
	 * @return the string
	 */
	public static String merge(final VelocityContext context, final String templateAsString)
	{
		return merge(context, "VelocityUtilsMergeMethod", templateAsString);
	}

	/**
	 * Merges the given context with the given template that is a String object and returns the
	 * result.
	 * 
	 * @param context
	 *            the context
	 * @param logTag
	 *            to be used as the template name for log messages
	 * @param templateAsString
	 *            the template as string
	 * @return the string
	 */
	public static String merge(final VelocityContext context, final String logTag,
		final String templateAsString)
	{
		final StringWriter writer = new StringWriter();
		Velocity.evaluate(context, writer, logTag, templateAsString);
		return writer.toString();
	}
}
