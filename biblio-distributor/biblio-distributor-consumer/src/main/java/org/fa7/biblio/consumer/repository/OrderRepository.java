package org.fa7.biblio.consumer.repository;

import org.fa7.biblio.consumer.bean.Order;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.Collection;


@RepositoryRestResource(collectionResourceRel = "order", path = "orders")
public interface OrderRepository extends PagingAndSortingRepository<Order, Long> {

    Collection<Order> findByClientId(@Param("clientId") Long clientId);

    Collection<Order> findByClientOrderId(@Param("clientOrderId") String clientOrderId);
}



