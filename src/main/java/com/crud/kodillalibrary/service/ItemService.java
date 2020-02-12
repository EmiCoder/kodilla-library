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

    @Autowired
    BookService bookService;

    public List<Item> getItems() {
        return itemRepository.findAll();
    }

    public Item saveItem(Item item) {
        item.setBook(bookService.getBookById(item.getBook().getId()));
        return itemRepository.save(item);
    }


    public Item updateItemStatus(int itemId, String newStatus) {
        Item itemById=getItemById(itemId);
             itemById.setStatus(newStatus);
             saveItem(itemById);
        return itemById;
    }

    public Item getItemById(final Integer id) {
        return itemRepository.findById(id).get();
    }

    public void deleteItemById(final Integer id) {
        itemRepository.deleteById(id);
    }
}
