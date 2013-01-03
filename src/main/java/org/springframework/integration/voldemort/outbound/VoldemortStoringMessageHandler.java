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
package org.springframework.integration.voldemort.outbound;

import org.springframework.integration.Message;
import org.springframework.integration.handler.AbstractMessageHandler;
import org.springframework.integration.voldemort.convert.VoldemortConverter;
import org.springframework.integration.voldemort.support.KeyValue;
import voldemort.client.StoreClient;

/**
 * Voldemort outbound adapter implementation.
 *
 * @author Lukasz Antoniak
 * @since 1.0
 */
public class VoldemortStoringMessageHandler extends AbstractMessageHandler {
	private final StoreClient client;
	private final VoldemortConverter converter;

	/**
	 * Creates new message sender.
	 *
	 * @param client Voldemort store client.
	 * @param converter Message converter.
	 */
	public VoldemortStoringMessageHandler(StoreClient client, VoldemortConverter converter) {
		this.client = client;
		this.converter = converter;
	}

	@Override
	@SuppressWarnings("unchecked")
	protected void handleMessageInternal(Message<?> message) throws Exception {
		final KeyValue pair = converter.toKeyValue( message );
		client.put( pair.getKey(), pair.getValue() );
	}

	@Override
	public String getComponentType() {
		return "voldemort:outbound-channel-adapter";
	}
}
