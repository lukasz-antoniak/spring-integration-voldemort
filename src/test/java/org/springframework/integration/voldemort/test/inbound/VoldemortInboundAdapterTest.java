/*
 * Copyright 2002-2012 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.springframework.integration.voldemort.test.inbound;

import junit.framework.Assert;
import org.junit.Test;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.integration.Message;
import org.springframework.integration.core.PollableChannel;
import org.springframework.integration.voldemort.test.BaseFunctionalTestCase;
import org.springframework.integration.voldemort.test.domain.Person;
import voldemort.client.StoreClient;

/**
 * Voldemort inbound adapter tests.
 *
 * @author Lukasz Antoniak
 * @since 1.0
 */
public class VoldemortInboundAdapterTest extends BaseFunctionalTestCase {
	/**
	 * Tests inbound adapter configured with 'search-key' attribute.
	 */
	@Test
	@SuppressWarnings("unchecked")
	public void testReceiveMessageKey() {
		ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext( "VoldemortInboundAdapterTest-context.xml", getClass() );
		StoreClient storeClient = context.getBean( "storeClient", StoreClient.class );
		PollableChannel inboundChannel = context.getBean( "voldemortInboundChannel", PollableChannel.class );

		// given
		final Person lukasz = new Person( "1ukasz", "Lukasz", "Antoniak" );
		storeClient.put( lukasz.getId(), lukasz );

		// when
		Message<Person> received = (Message<Person>) inboundChannel.receive();

		// then
		Assert.assertEquals( lukasz, received.getPayload() );

		context.close();
	}

	/**
	 * Tests inbound adapter configured with 'search-key-expression' attribute.
	 */
	@Test
	@SuppressWarnings("unchecked")
	public void testReceiveMessageExpr() {
		ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext( "VoldemortInboundAdapterTest-context.xml", getClass() );
		StoreClient storeClient = context.getBean( "storeClient", StoreClient.class );
		PollableChannel inboundChannel = context.getBean( "voldemortInboundChannel", PollableChannel.class );

		// given
		final Person kinga = new Person( "kinga", "Kinga", "Mroz" );
		storeClient.put( kinga.getId(), kinga );

		// when
		Message<Person> received = (Message<Person>) inboundChannel.receive();

		// then
		Assert.assertEquals( kinga, received.getPayload() );

		context.close();
	}
}
