package org.fa7.biblio.consumer.bo;

import org.apache.commons.collections.CollectionUtils;
import org.fa7.biblio.commons.bean.Item;
import org.fa7.biblio.commons.bean.Order;
import org.fa7.biblio.consumer.bean.Book;
import org.fa7.biblio.consumer.repository.BookRepository;
import org.fa7.biblio.consumer.repository.OrderRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class OrderBO {

    private static final Logger SYSTEM_LOGGER = LoggerFactory.getLogger("system");

    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private RestTemplate biblioRestTemplate;

    @Transactional(propagation = Propagation.REQUIRED)
    public void processOrderRequest(Order order) {
        SYSTEM_LOGGER.info("Processing order [{}]", order);
        if (CollectionUtils.isEmpty(order.getItems())) {
            SYSTEM_LOGGER.error("Not found items!");
            return;
        }
        if (CollectionUtils.isNotEmpty(orderRepository.findByClientOrderId(order.getClientOrderId()))){
            SYSTEM_LOGGER.error("Order already processed!");
            return;
        }

        List<Item> processedItems = new ArrayList<>(order.getItems().size());
        processOrder(order, processedItems);
        org.fa7.biblio.consumer.bean.Order orderEntity = saveOrder(order, processedItems);
        processCallBack(orderEntity);
    }

    private org.fa7.biblio.consumer.bean.Order saveOrder(Order order, List<Item> processedItems) {
        org.fa7.biblio.consumer.bean.Order orderEntity = new org.fa7.biblio.consumer.bean.Order(order);
        orderEntity.getItems().forEach(o -> {
            Optional<Item> item = processedItems.stream().filter(pi -> pi.getIsbn().longValue() == o.getIsbn().longValue()).findFirst();
            if (item.isPresent()){
                o.setAmountResponse(item.get().getAmount());
            }else{
                o.setAmountResponse(0);
            }
        });
        orderEntity.setRequestDate(new Date());
        orderRepository.save(orderEntity);
        return orderEntity;
    }

    private void processCallBack(org.fa7.biblio.consumer.bean.Order orderEntity) {
        HttpEntity<org.fa7.biblio.consumer.bean.Order> request = new HttpEntity<>(orderEntity);
        URI location = biblioRestTemplate.postForLocation(orderEntity.getCallBackUrl(), request);
        SYSTEM_LOGGER.info("Processed order [{}] - URI [{}]", orderEntity, location);
    }

    private void processOrder(Order order, Collection<Item> processedItems) {
        order.getItems().forEach(item -> {
            Book book = bookRepository.findByIsbn(item.getIsbn());
            if (book != null && book.getAmount() > item.getAmount()) {
                book.setAmount(book.getAmount() - item.getAmount());
                bookRepository.save(book);
                processedItems.add(item);
            }
        });
    }
}
