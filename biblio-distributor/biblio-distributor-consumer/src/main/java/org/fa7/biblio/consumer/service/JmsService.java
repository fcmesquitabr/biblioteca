package org.fa7.biblio.consumer.service;

import org.fa7.biblio.commons.bean.Order;

public interface JmsService {

    void processMessage(Order order);

}
