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
package org.springframework.integration.voldemort.test.outbound;

import junit.framework.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.integration.Message;
import org.springframework.integration.MessageChannel;
import org.springframework.integration.support.MessageBuilder;
import org.springframework.integration.voldemort.test.BaseFunctionalTestCase;
import org.springframework.integration.voldemort.test.domain.Person;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import voldemort.client.StoreClient;
import voldemort.versioning.Versioned;

/**
 * Voldemort outbound adapter tests.
 *
 * @author Lukasz Antoniak
 * @since 1.0
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "VoldemortOutboundAdapterTest-context.xml" })
public class VoldemortOutboundAdapterTest extends BaseFunctionalTestCase {
	@Autowired
	private MessageChannel voldemortOutboundChannel;

	@Autowired
	private StoreClient storeClient;

	@Test
	@SuppressWarnings("unchecked")
	public void testSendMessage() {
		// given
		final Person lukasz = new Person( "1", "Lukasz", "Antoniak" );

		// when
		Message<Person> message = MessageBuilder.withPayload( lukasz ).build();
		voldemortOutboundChannel.send( message );

		// then
		Versioned found = storeClient.get( lukasz.getId() );
		Assert.assertEquals( lukasz, found.getValue() );
	}
}
