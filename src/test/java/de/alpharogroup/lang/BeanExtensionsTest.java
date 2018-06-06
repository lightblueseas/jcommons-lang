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
package de.alpharogroup.lang;

import static org.testng.AssertJUnit.assertEquals;

import org.meanbean.factories.ObjectCreationException;
import org.meanbean.test.BeanTestException;
import org.meanbean.test.BeanTester;
import org.testng.annotations.Test;

import de.alpharogroup.test.objects.Person;
import de.alpharogroup.test.objects.annotations.AnotherTestAnnotation;

/**
 * The unit test class for the class {@link BeanExtensions}.
 */
@AnotherTestAnnotation
public class BeanExtensionsTest
{

	/**
	 * Test method for {@link BeanExtensions#setPropertyQuietly(Object, String, Object)}
	 *
	 * @throws Exception
	 *             the exception
	 */
	@Test
	public final void testSetPropertyQuietly() throws Exception
	{
		String expected;
		String actual;
		final Person person = Person.builder().name("Alex").build();
		expected = "Leo";
		BeanExtensions.setPropertyQuietly(person, "name", "Leo");
		actual = person.getName();
		assertEquals(expected, actual);
		// provocation of InvocationTargetException...
		Foo foo = new Foo();
		BeanExtensions.setPropertyQuietly(foo, "bar", "bla");
	}

	/**
	 * Test method for {@link BeanExtensions}
	 */
	@Test(expectedExceptions = { BeanTestException.class, ObjectCreationException.class })
	public void testWithBeanTester()
	{
		final BeanTester beanTester = new BeanTester();
		beanTester.testBean(BeanExtensions.class);
	}

}
class Foo
{
    String bar;

    public String getBar() {
        return bar;
    }

    public void setBar(String bar) {
        this.bar = bar;
    }

}
