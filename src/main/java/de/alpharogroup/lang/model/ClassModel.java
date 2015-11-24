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

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.lang.model.element.ElementKind;
import javax.lang.model.element.Modifier;

public class ClassModel
{

	private ElementKind kind = ElementKind.CLASS;
	private String packageName;
	private List<String> imports = new ArrayList<>();
	private List<ClassModel> classAnnotations;
	private List<Modifier> modifiers;
	private List<String> genericTypes;
	private String className;
	private String extendedClassName;
	private List<String> interfaceImplementations = new ArrayList<>();
	private Map<String, MethodModel> methods;

	public List<ClassModel> getClassAnnotations()
	{
		return classAnnotations;
	}

	public String getClassName()
	{
		return className;
	}

	public String getExtendedClassName()
	{
		return extendedClassName;
	}

	public List<String> getGenericTypes()
	{
		return genericTypes;
	}

	public List<String> getImports()
	{
		return imports;
	}

	public List<String> getInterfaceImplementations()
	{
		return interfaceImplementations;
	}

	public ElementKind getKind()
	{
		return kind;
	}

	public Map<String, MethodModel> getMethods()
	{
		return methods;
	}

	public List<Modifier> getModifiers()
	{
		return modifiers;
	}

	public String getPackageName()
	{
		return packageName;
	}

	public void setClassAnnotations(final List<ClassModel> classAnnotations)
	{
		this.classAnnotations = classAnnotations;
	}

	public void setClassName(final String className)
	{
		this.className = className;
	}

	public void setExtendedClassName(final String extendedClassName)
	{
		this.extendedClassName = extendedClassName;
	}

	public void setGenericTypes(final List<String> genericTypes)
	{
		this.genericTypes = genericTypes;
	}

	public void setImports(final List<String> imports)
	{
		this.imports = imports;
	}

	public void setInterfaceImplementations(final List<String> interfaceImplementations)
	{
		this.interfaceImplementations = interfaceImplementations;
	}

	public void setKind(final ElementKind kind)
	{
		this.kind = kind;
	}

	public void setMethods(final Map<String, MethodModel> methods)
	{
		this.methods = methods;
	}

	public void setModifiers(final List<Modifier> modifiers)
	{
		this.modifiers = modifiers;
	}

	public void setPackageName(final String packageName)
	{
		this.packageName = packageName;
	}

}
