package com.srivn.works.smusers.services;

import com.srivn.works.smusers.config.GcpPubSubConfig.PubsubOutboundGateway;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class GCPPubSubService {

    @Autowired
    private PubsubOutboundGateway messagingGateway;

    public void sendActivationEmail(String emailMsg) {
        messagingGateway.sendToPubsub(emailMsg);
    }
}
