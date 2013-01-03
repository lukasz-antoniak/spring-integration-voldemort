Spring Integration Voldemort Adapter
====================================

The Voldemort adapter for Spring Integration (SI) project includes inbound
and outbound channel adapters.

Inbound channel adapter:
-----------------------------------------------------------------------------
Inbound channel adapter is used to retrieve data out of Voldemort database
and transfer objects into Spring Integration's channel. Component expects
user to provide Voldemort store client, message converter and desired object
key.

### Example:
~~~~~xml
<int-voldemort:inbound-channel-adapter id="voldemortInKey" channel="voldemortInboundChannel" search-key="1ukasz"
                                       store-client="storeClient" message-converter="messageConverter">
    <int:poller fixed-rate="1000"/>
</int-voldemort:inbound-channel-adapter>
~~~~~

For more implementation details please review documentation and integration
test cases.

Outbound channel adapter:
-----------------------------------------------------------------------------
Outbound channel adapter is used to insert data into Voldemort database
from Spring Integration's channel. Component expects user to provide
Voldemort store client and message converter.

### Example:
~~~~~xml
<int-voldemort:outbound-channel-adapter id="voldemortOut" channel="voldemortOutboundChannel"
                                        store-client="storeClient" message-converter="messageConverter" />
~~~~~

For more implementation details please review documentation and integration
test cases.

Build
-----------------------------------------------------------------------------
For build instructions visit [Spring Integration on GitHub](https://github.com/SpringSource/spring-integration).