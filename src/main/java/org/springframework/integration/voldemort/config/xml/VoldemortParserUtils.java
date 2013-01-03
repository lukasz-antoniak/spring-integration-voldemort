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
package org.springframework.integration.voldemort.config.xml;

import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.w3c.dom.Element;

/**
 * Contains various utility methods for parsing Voldemort Adapter specific namesspace elements.
 *
 * @author Lukasz Antoniak
 * @since 1.0
 */
abstract class VoldemortParserUtils {
	static final String STORE_CLIENT_ATTRIBUTE = "store-client";
	static final String MESSAGE_CONVERTER_ATTRIBUTE = "message-converter";
	static final String SEARCH_KEY_ATTRIBUTE = "search-key";
	static final String SEARCH_KEY_EXPRESSION_ATTRIBUTE = "search-key-expression";

	/**
	 * Handles 'store-client' and 'message-converter' attributes.
	 */
	static void processCommonAttributes(Element element, BeanDefinitionBuilder builder) {
		String storeClient = element.getAttribute( VoldemortParserUtils.STORE_CLIENT_ATTRIBUTE );
		String messageConverter = element.getAttribute( VoldemortParserUtils.MESSAGE_CONVERTER_ATTRIBUTE );
		builder.addConstructorArgReference( storeClient );
		builder.addConstructorArgReference( messageConverter );
	}
}
