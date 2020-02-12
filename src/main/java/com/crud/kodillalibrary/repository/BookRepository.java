package com.crud.kodillalibrary.repository;
import com.crud.kodillalibrary.domain.main.Book;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface BookRepository extends CrudRepository<Book, Integer> {
    @Override
    List<Book> findAll();

    @Override
    Book save(Book book);

    @Override
    Optional<Book> findById(Integer id);

    @Override
    void deleteById(Integer id);

    List<Book> findByTitle(String title);
}
