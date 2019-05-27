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
package de.alpharogroup.lang.thread;

import static org.testng.AssertJUnit.assertNotNull;

import java.util.Map;

import org.meanbean.test.BeanTester;
import org.testng.annotations.Test;

import de.alpharogroup.collections.map.MapFactory;
import de.alpharogroup.evaluate.object.verifier.ContractVerifier;

/**
 * The unit test class for the class {@link ThreadFieldDataBean}.
 */
public class ThreadFieldDataBeanTest
{

	/**
	 * Test method for {@link ThreadFieldDataBean} constructors
	 */
	@Test
	public final void testConstructors()
	{
		ThreadFieldDataBean model = new ThreadFieldDataBean();
		assertNotNull(model);

		Map<String, Object> fields = MapFactory.newHashMap();

		model = new ThreadFieldDataBean(fields);
		assertNotNull(model);
		model = ThreadFieldDataBean.builder().build();
		assertNotNull(model);
	}

	/**
	 * Test method for {@link ThreadFieldDataBean}
	 */
	@Test
	public void testWithBeanTester()
	{
		final BeanTester beanTester = new BeanTester();
		beanTester.testBean(ThreadFieldDataBean.class);
	}

	/**
	 * Test method for {@link ThreadFieldDataBean#equals(Object)} ,
	 * {@link ThreadFieldDataBean#hashCode()} and {@link ThreadFieldDataBean#toString()}
	 */
	@Test
	public void verifyEqualsHashcodeAndToStringContracts()
	{
		ContractVerifier.of(ThreadFieldDataBean.class).verify();
	}
}
