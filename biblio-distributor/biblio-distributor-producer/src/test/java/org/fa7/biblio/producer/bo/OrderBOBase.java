package org.fa7.biblio.producer.bo;

import org.fa7.biblio.commons.bean.Item;
import org.fa7.biblio.commons.bean.Order;

import java.util.ArrayList;
import java.util.Collection;

public class OrderBOBase {

    Order createValidOrder(){
        Collection<Item> items = new ArrayList<>(1);
        items.add(new Item(1234567890L, 10));
        items.add(new Item(987654321L, 5));
        return new Order(1L, "http://callback-url.com", items);
    }

    Order createInvalidOrder(){
        Collection<Item> items = new ArrayList<>(1);
        return new Order(1L, "http://callback-url.com", items);
    }
}
