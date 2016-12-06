package org.fa7.biblio.producer.service.impl;

import org.fa7.biblio.commons.bean.Order;
import org.fa7.biblio.producer.service.JmsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Component;

@Component
public class JmsServiceImpl implements JmsService {

    @Value("${queue.name}")
    private String QUEUE;

    private final JmsTemplate jmsTemplate;

    @Autowired
    public JmsServiceImpl(JmsTemplate jmsTemplate) {
        this.jmsTemplate = jmsTemplate;
    }

    @Override
    public void purchaseOrderRequest(Order order) {
        jmsTemplate.convertAndSend(QUEUE, order);
    }
}
