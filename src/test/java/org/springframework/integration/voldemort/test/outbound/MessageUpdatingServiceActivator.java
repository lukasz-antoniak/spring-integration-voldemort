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

import org.springframework.integration.Message;
import org.springframework.integration.annotation.ServiceActivator;
import org.springframework.integration.voldemort.test.domain.Person;

/**
 * Sample service activator used to verify order of output adapters execution.
 *
 * @author Lukasz Antoniak
 * @since 1.0
 */
public class MessageUpdatingServiceActivator {
	@ServiceActivator
	public Message<Person> updateMessage(Message<Person> message) {
		updatePerson( message.getPayload() );
		return message;
	}

	protected void updatePerson(Person person) {
		person.setFirstName( "Robert" );
	}
}
