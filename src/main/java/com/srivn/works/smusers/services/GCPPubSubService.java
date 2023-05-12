package com.srivn.works.smusers.services;

import com.srivn.works.smusers.config.GcpPubSubConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class GCPPubSubService {

    @Autowired
    private GcpPubSubConfig.PubsubOutboundGateway messagingGateway;

    public void sendActivationEmail(String emailMsg) {
        messagingGateway.sendToPubsub(emailMsg);
    }
}
