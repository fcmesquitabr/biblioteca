package org.fa7.biblio.producer.bo;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.Validate;
import org.fa7.biblio.commons.bean.Item;
import org.fa7.biblio.commons.bean.Order;
import org.fa7.biblio.producer.service.JmsService;
import org.fa7.biblio.producer.to.ResponseTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.UUID;

@Service
public class OrderBO {

    private static final Logger EXCEPTION_LOGGER = LoggerFactory.getLogger("exceptions");
    private static final Logger SYSTEM_LOGGER = LoggerFactory.getLogger("system");

    @Autowired
    private JmsService jmsService;

    public ResponseTO processAndSend(Order order) {
        ResponseTO response = new ResponseTO(false, "fail to process order", StringUtils.EMPTY);
        validate(order.getClientId(), order.getCallBackUrl(), order.getItems());

        try {
            order.setOrderId(UUID.randomUUID().toString());
            jmsService.purchaseOrderRequest(order);
            response = new ResponseTO(true, "process order on queue", order.getOrderId());
            SYSTEM_LOGGER.info("process order [{}] on queue", order);
        } catch (Exception e) {
            EXCEPTION_LOGGER.error("fail to process order [{}]", order, e);
        }
        return response;
    }

    private void validate(Long clientId, String callBackUrl, Collection<Item> itens) {
        Validate.notNull(callBackUrl);
        if (StringUtils.isBlank(callBackUrl)) {
            throw new RuntimeException("Empty CallBackUrl!");
        }
        Validate.notNull(clientId);
        Validate.inclusiveBetween(1L, Long.MAX_VALUE, clientId.longValue());
        validate(itens);
    }

    private void validate(Collection<Item> items) {
        if (CollectionUtils.isEmpty(items)) {
            throw new RuntimeException("Empty items!");
        }
        items.forEach(item -> {
            Validate.inclusiveBetween(1L, Long.MAX_VALUE, item.getIsbn().longValue());
            Validate.inclusiveBetween(1, Integer.MAX_VALUE, item.getAmount().intValue());
        });
    }
}
