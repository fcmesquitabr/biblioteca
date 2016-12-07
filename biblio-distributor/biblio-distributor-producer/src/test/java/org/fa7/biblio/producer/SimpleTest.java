package org.fa7.biblio.producer;

import org.fa7.biblio.commons.bean.Item;
import org.fa7.biblio.commons.bean.Order;
import org.fa7.biblio.producer.bin.Producer;
import org.fa7.biblio.producer.service.JmsService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.ArrayList;
import java.util.Collection;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = Producer.class)
public class SimpleTest {

    @Autowired
    private JmsService jmsService;

    @Test
    public void sendItem() {
        Collection<Item> items = new ArrayList<>(1);
        items.add(new Item(1234567890L, 10));
        items.add(new Item(987654321L, 5));
        Order order = new Order(1L, "callBackUrl", items);
        jmsService.purchaseOrderRequest(order);
    }

}