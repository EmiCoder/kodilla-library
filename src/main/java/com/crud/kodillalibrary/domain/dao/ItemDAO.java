package com.crud.kodillalibrary.domain.dao;

import com.crud.kodillalibrary.domain.main.Book;
import com.crud.kodillalibrary.domain.main.Item;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

@Repository
@Transactional
public interface ItemDAO extends CrudRepository<Item, Integer> {
    @Query
    List<Item> findByTitle(@Param("TITLE") String title);
    List<Item> findByStatus(String status);
}
