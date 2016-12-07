package org.fa7.biblio.consumer.repository;


import org.fa7.biblio.consumer.bean.Book;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;


@RepositoryRestResource(collectionResourceRel = "book", path = "books")
public interface BookRepository extends PagingAndSortingRepository<Book, Long> {
    Book findByIsbn(@Param("isbn") Long isbn);
}



