package com.crud.kodillalibrary.repository;

import com.crud.kodillalibrary.domain.main.Item;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface ItemRepository extends CrudRepository<Item, Integer> {

    @Override
    List<Item> findAll();

    @Override
    Item save(Item item);

    @Override
    Optional<Item> findById(Integer id);

    @Override
    void deleteById(Integer id);
}
