package org.fa7.biblio.consumer.service.impl;

import org.fa7.biblio.commons.bean.Order;
import org.fa7.biblio.consumer.bo.OrderBO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

@Component
public class JmsServiceImpl {

    private static final Logger SYSTEM_LOGGER = LoggerFactory.getLogger("system");

    @Autowired
    private OrderBO orderBO;

    @JmsListener(destination = "distributor-inbox")
    public void processMessage(Order order) {
        SYSTEM_LOGGER.info("Received order [{}]", order);
        orderBO.processOrderRequest(order);
    }
}
