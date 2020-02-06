package com.crud.kodillalibrary.domain.dao;

import com.crud.kodillalibrary.domain.main.Book;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

@Repository
@Transactional
public interface BookDAO extends CrudRepository<Book, Integer> {
    List<Book> findByTitle(String title);
}
