package org.springframework.integration.voldemort.config.xml;

import org.springframework.beans.factory.support.AbstractBeanDefinition;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.beans.factory.xml.ParserContext;
import org.springframework.integration.config.xml.AbstractOutboundChannelAdapterParser;
import org.springframework.integration.config.xml.IntegrationNamespaceUtils;
import org.springframework.integration.voldemort.outbound.VoldemortStoringMessageHandler;
import org.w3c.dom.Element;

/**
 * Parses Voldemort outbound adapter XML definition.
 *
 * @author Lukasz Antoniak
 * @since 1.0
 */
public class VoldemortOutboundChannelAdapterParser extends AbstractOutboundChannelAdapterParser {
	/**
	 * Produces "int-voldemort:outbound-channel-adapter" bean definition.
	 * <p />
	 * {@inheritDoc}
	 */
	@Override
	protected AbstractBeanDefinition parseConsumer(Element element, ParserContext parserContext) {
		BeanDefinitionBuilder builder = BeanDefinitionBuilder.genericBeanDefinition( VoldemortStoringMessageHandler.class );
		VoldemortParserUtils.processCommonAttributes( element, builder );
		IntegrationNamespaceUtils.setValueIfAttributeDefined( builder, element, VoldemortParserUtils.PERSIST_MODE );
		return builder.getBeanDefinition();
	}
}
