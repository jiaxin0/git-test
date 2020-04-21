package com.jpa.onetomany.repositories;

import com.jpa.onetomany.domains.Book;
import org.springframework.data.repository.CrudRepository;

public interface BookRepository extends CrudRepository<Book, Long> {

    //Book findByIsbn(String isbn);
}
