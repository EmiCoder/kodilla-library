package com.crud.kodillalibrary.service;

import com.crud.kodillalibrary.domain.main.Item;
import com.crud.kodillalibrary.repository.ItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ItemService {

    @Autowired
    ItemRepository itemRepository;

    public List<Item> getItems() {
        return itemRepository.findAll();
    }

    public Item saveItem(Item item) {
        return itemRepository.save(item);
    }

    public Item getItemById(final Integer id) {
        return itemRepository.findById(id).get();
    }

    public void deleteItemById(final Integer id) {
        itemRepository.deleteById(id);
    }
}
