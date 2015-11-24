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
package de.alpharogroup.lang.model;


import java.util.List;
import java.util.Map;

import javax.lang.model.element.Modifier;

public class MethodModel
{

	private List<AnnotationModel> methodAnnotations;

	List<Modifier> modifiers;

	private List<String> genericTypes;

	private String returnType;

	private String methodName;

	private List<String> parameters;

	private Map<String, List<String>> parameterAnnotations;

	private boolean staticFlag = false;

	private boolean synchronizedFlag = false;

	public List<String> getGenericTypes()
	{
		return genericTypes;
	}

	public List<AnnotationModel> getMethodAnnotations()
	{
		return methodAnnotations;
	}

	public String getMethodName()
	{
		return methodName;
	}

	public Map<String, List<String>> getParameterAnnotations()
	{
		return parameterAnnotations;
	}

	public List<String> getParameters()
	{
		return parameters;
	}

	public String getReturnType()
	{
		return returnType;
	}

	public boolean isStaticFlag()
	{
		return staticFlag;
	}

	public boolean isSynchronizedFlag()
	{
		return synchronizedFlag;
	}

	public void setGenericTypes(final List<String> genericTypes)
	{
		this.genericTypes = genericTypes;
	}

	public void setMethodAnnotations(final List<AnnotationModel> methodAnnotations)
	{
		this.methodAnnotations = methodAnnotations;
	}

	public void setMethodName(final String methodName)
	{
		this.methodName = methodName;
	}

	public void setParameterAnnotations(final Map<String, List<String>> parameterAnnotations)
	{
		this.parameterAnnotations = parameterAnnotations;
	}

	public void setParameters(final List<String> parameters)
	{
		this.parameters = parameters;
	}

	public void setReturnType(final String returnType)
	{
		this.returnType = returnType;
	}

	public void setStaticFlag(final boolean staticFlag)
	{
		this.staticFlag = staticFlag;
	}

	public void setSynchronizedFlag(final boolean synchronizedFlag)
	{
		this.synchronizedFlag = synchronizedFlag;
	}

}
