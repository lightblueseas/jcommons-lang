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
package de.alpharogroup.file;

/**
 * The interface {@link FileSuffix} contains some file suffixes of class names.
 *
 * @deprecated will be removed in the next minor version
 */
@Deprecated
public interface FileSuffix
{

	/** The Constant TEST for the suffix of a test class. */
	static final String TEST = "Test";

	/** The Constant SERVICE for the suffix of a service interfaces. */
	static final String SERVICE = "Service";

	/** The Constant BUSINESS_SERVICE for the suffix of a business service class. */
	static final String BUSINESS_SERVICE = "Business" + SERVICE;

	/** The Constant DOMAIN_SERVICE for the suffix of a domain service class. */
	static final String DOMAIN_SERVICE = "Domain" + SERVICE;

	/** The Constant MAPPER for the suffix of a Mapper class. */
	static final String MAPPER = "Mapper";

	/** The Constant RESOURCE for the suffix of a rest resource interface. */
	static final String RESOURCE = "Resource";

	/** The Constant REST for the preffix of a rest resource class. */
	static final String REST = "Rest";

	/** The Constant REST_RESOURCE for the suffix of a rest resource class. */
	static final String REST_RESOURCE = REST + RESOURCE;

	/** The Constant REST_RESOURCE for the suffix of a rest resource class. */
	static final String REST_CLIENT = REST + "Client";

	/** The Constant DAO for the suffix of a dao class. */
	static final String DAO = "Dao";

	/** The Constant REPOSITORY for the suffix of a repository class. */
	static final String REPOSITORY = "Repository";
}
