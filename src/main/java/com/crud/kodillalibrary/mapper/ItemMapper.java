package com.crud.kodillalibrary.mapper;

import com.crud.kodillalibrary.domain.dto.ItemDTO;
import com.crud.kodillalibrary.domain.main.Book;
import com.crud.kodillalibrary.domain.main.Item;
import com.crud.kodillalibrary.repository.ItemRepository;
import com.crud.kodillalibrary.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class ItemMapper {

    @Autowired
    BookService bookService;

    @Autowired
    ItemRepository itemRepository;

    public Item mapToItem(final ItemDTO itemDTO) {
        Item item = new Item();
                item.setStatus(itemDTO.getStatus());
                Book book = new Book();
                    book.setId(bookService.getBookById(itemDTO.getBookId()).getId());
                    book.setAuthor(bookService.getBookById(itemDTO.getBookId()).getAuthor());
                    book.setPublished(bookService.getBookById(itemDTO.getBookId()).getPublished());
                item.setBook(book);
        return item;
    }

    public ItemDTO mapToItemDTO(final Item item) {
        return new ItemDTO(item.getId(), item.getStatus(), item.getBook().getId());
    }


    public List<ItemDTO> mapToItemDTOList(List<Item> itemList) {
        return itemList.stream()
                        .map(item -> new ItemDTO(item.getId(), item.getStatus(), item.getBook().getId()))
                        .collect(Collectors.toList());
    }
}
