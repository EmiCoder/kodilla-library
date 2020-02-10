package com.crud.kodillalibrary.domain.dao;

import com.crud.kodillalibrary.domain.main.Item;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

@Repository
@Transactional
public interface ItemDAO extends CrudRepository<Item, Integer> {
    List<Item> findByStatus(String status);
    List<Item> findByBook_Id(Integer book_id);
    List<Item> findByBook(String title);
}
