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
package de.alpharogroup.id.generator;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * The Class SystemTimeIdGenerator.
 */
public class SystemTimeIdGenerator implements IdGenerator
{


	/** The instance. */
	private static final SystemTimeIdGenerator instance = new SystemTimeIdGenerator();

	/**
	 * Gets the single instance of SystemTimeIdGenerator.
	 *
	 * @return single instance of SystemTimeIdGenerator
	 */
	public static SystemTimeIdGenerator getInstance()
	{
		return instance;
	}

	/** The atomic id. */
	private final AtomicInteger atomicId;

	/**
	 * Instantiates a new system time id generator.
	 */
	private SystemTimeIdGenerator()
	{
		super();
		atomicId = new AtomicInteger((int)System.currentTimeMillis());
	}

	/**
	 * {@inheritDoc}
	 *
	 * @return the next id
	 *
	 * @see de.alpharogroup.id.generator.IdGenerator#getNextId()
	 */
	@Override
	public int getNextId()
	{
		int nextId = atomicId.getAndIncrement();
		if (nextId < 0)
		{
			nextId *= -1;
		}
		return nextId;
	}
}