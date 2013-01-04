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
package org.springframework.integration.voldemort.test.domain;

import org.springframework.integration.Message;
import org.springframework.integration.support.MessageBuilder;
import org.springframework.integration.voldemort.convert.VoldemortConverter;
import org.springframework.integration.voldemort.support.KeyValue;
import voldemort.versioning.Versioned;

/**
 * Converts {@link Person} message to key-value pair.
 *
 * @author Lukasz Antoniak
 * @since 1.0
 */
public class PersonMessageConverter implements VoldemortConverter<String, Person, Person> {
	@Override
	public KeyValue<String, Person> toKeyValue(Message<Person> message) {
		return KeyValue.make( message.getPayload().getId(), message.getPayload() );
	}

	@Override
	public Message<Person> toMessage(String key, Versioned<Person> versioned) {
		return MessageBuilder.withPayload( versioned.getValue() ).build();
	}
}
