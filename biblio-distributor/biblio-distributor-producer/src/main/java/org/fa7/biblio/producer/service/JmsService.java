package org.fa7.biblio.producer.service;

import org.fa7.biblio.commons.bean.Order;

public interface JmsService {

    void purchaseOrderRequest(Order order);

}
