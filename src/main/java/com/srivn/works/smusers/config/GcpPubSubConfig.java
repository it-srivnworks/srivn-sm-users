package com.srivn.works.smusers.config;

import com.google.cloud.spring.pubsub.core.PubSubTemplate;
import com.google.cloud.spring.pubsub.integration.outbound.PubSubMessageHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.integration.annotation.MessagingGateway;
import org.springframework.integration.annotation.ServiceActivator;
import org.springframework.messaging.MessageHandler;


@Configuration
public class GcpPubSubConfig {

    @Value( "${sm-users.gcp.topic}" )
    private String topic;

    private static final Logger log = LoggerFactory.getLogger(GcpPubSubConfig.class);

    @Bean
    @ServiceActivator(inputChannel = "pubsubOutputChannel")
    public MessageHandler messageSender(PubSubTemplate pubsubTemplate) {
        PubSubMessageHandler adapter = new PubSubMessageHandler(pubsubTemplate, this.topic);
        adapter.setSuccessCallback(((ackId, message) -> log.info("Message was sent via the outbound channel adapter to ackId : "+ackId+" topic :"+this.topic)));
        adapter.setFailureCallback((cause, message) -> log.info("Error sending " + message + " due to " + cause));
        return adapter;
    }
    @MessagingGateway(defaultRequestChannel = "pubsubOutputChannel")
    public interface PubsubOutboundGateway {
        void sendToPubsub(String text);
    }

}
