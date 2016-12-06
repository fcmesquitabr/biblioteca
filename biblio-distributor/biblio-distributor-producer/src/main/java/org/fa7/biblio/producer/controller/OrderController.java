package org.fa7.biblio.producer.controller;

import org.fa7.biblio.commons.bean.Order;
import org.fa7.biblio.producer.bo.OrderBO;
import org.fa7.biblio.producer.to.ResponseTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/order")
public class OrderController {

    @Autowired
    private OrderBO orderBO;

    @RequestMapping(method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseTO purchaseOrderRequest(@RequestBody Order order) {
        return orderBO.processAndSend(order);
    }

}
