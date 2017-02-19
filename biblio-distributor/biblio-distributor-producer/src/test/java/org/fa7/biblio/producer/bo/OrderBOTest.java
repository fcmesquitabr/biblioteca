package org.fa7.biblio.producer.bo;

import org.fa7.biblio.producer.bin.Producer;
import org.fa7.biblio.producer.to.ResponseTO;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.junit.Assert.*;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = Producer.class)
public class OrderBOTest extends OrderBOBase{

    @Autowired
    private OrderBO orderBO;

    @Test
    public void testProcessAndSend_validOrder_success(){
        ResponseTO responseTO = orderBO.processAndSend(createValidOrder());
        assertNotNull(responseTO);
        assertTrue(responseTO.getSuccess());
    }

    @Test(expected = RuntimeException.class)
    public void testProcessAndSend_invalidOrder_fail(){
        ResponseTO responseTO = orderBO.processAndSend(createInvalidOrder());
    }

}