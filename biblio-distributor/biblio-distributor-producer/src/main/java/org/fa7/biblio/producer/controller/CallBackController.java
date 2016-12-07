package org.fa7.biblio.producer.controller;


import org.apache.commons.lang3.StringUtils;
import org.fa7.biblio.commons.bean.Order;
import org.fa7.biblio.producer.to.ResponseTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/biblio/ordercallback")
public class CallBackController {

    private static final Logger SYSTEM_LOGGER = LoggerFactory.getLogger("system");

    //FIXME delete after test - this call will be in biblio (Java EE) environment
    @RequestMapping(method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseTO purchaseOrderRequest(@RequestBody Order order) {
        SYSTEM_LOGGER.info("callbackUrl called - order[{}]", order);
        return new ResponseTO(true, "Success", StringUtils.EMPTY);
    }
}
