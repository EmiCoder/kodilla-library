package com.crud.kodillalibrary.controller;

import com.crud.kodillalibrary.domain.dto.ItemDTO;
import com.crud.kodillalibrary.mapper.ItemMapper;
import com.crud.kodillalibrary.service.BookService;
import com.crud.kodillalibrary.service.ItemService;
import io.github.jhipster.web.util.HeaderUtil;
import javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

@RestController
@RequestMapping("/item")
public class ItemController {


    @Autowired
    ItemMapper itemMapper;
    @Autowired
    ItemService service;
    @Autowired
    private BookService bookService;


    @GetMapping
    public ResponseEntity<?> getItems() throws NotFoundException {
        List<ItemDTO> list = itemMapper.mapToItemDTOList(service.getItems());
        if (!list.isEmpty()) {
            return ResponseEntity.ok().body(list);
        } else {
            throw new NotFoundException("List not found");
        }
    }

    @PostMapping
    public ResponseEntity<?> createItem(@RequestBody ItemDTO itemDTO) throws URISyntaxException {
        if (service.getItems().stream().anyMatch(item -> item.getId().equals(itemDTO.getId()))) {
            return ResponseEntity.badRequest().body("Item with given id " + itemDTO.getId() + " already exists.");
        }

        ItemDTO result = itemMapper.mapToItemDTO(service.saveItem(itemMapper.mapToItem(itemDTO)));
        bookService.getBookById(result.getBookId()).getItems().add(itemMapper.mapToItem(result));
        return ResponseEntity.created(new URI("/item/" + result.getId()))
                .headers(HeaderUtil.createEntityCreationAlert("KodillaLibraryApplication", false, "item", result.getId().toString()))
                .body(result);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getItemById(@PathVariable Integer id) {
        if (!service.getItems().stream().anyMatch(item -> item.getId().equals(id))) {
            return ResponseEntity.badRequest().body("Item with given id " + id + " does not exists.");
        }
        return new ResponseEntity<>(itemMapper.mapToItemDTO(service.getItemById(id)), HttpStatus.FOUND);
    }

    @PutMapping
    public ResponseEntity<?> updateItem(@RequestBody ItemDTO itemDTO) {
        boolean exist = service.getItems().stream().anyMatch(item -> item.getId().equals(itemDTO.getId()));

        if (itemDTO.getId() == null) {
            return ResponseEntity.badRequest().body("ItemId was not specified.");
        }
        if (!exist) {
            return ResponseEntity.badRequest().body("Item with given id " + itemDTO.getId() + " does not exists.");
        }

        ItemDTO result = itemMapper.mapToItemDTO(service.saveItem(itemMapper.mapToItem(itemDTO)));
        return ResponseEntity.ok()
                .headers(HeaderUtil.createEntityUpdateAlert("KodillaLibraryApplication", false, "item", itemDTO.getId().toString()))
                .body(result);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteItemById(@PathVariable Integer id) {
        if (!service.getItems().stream().anyMatch(item -> item.getId().equals(id))) {
            return ResponseEntity.badRequest().body("Item with given id: " + id + " does not exists.");
        }
        service.deleteItemById(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(
                "KodillaLibraryApplication",
                false,
                "item",
                id.toString()))
                .build();
    }

}
