package com.crud.kodillalibrary.controller;

import com.crud.kodillalibrary.domain.dto.ItemDTO;
import com.crud.kodillalibrary.mapper.ItemMapper;
import com.crud.kodillalibrary.repository.ItemRepository;
import com.crud.kodillalibrary.service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping("/item")
public class ItemController {

    @Autowired
    ItemRepository itemRepository;
    @Autowired
    ItemMapper itemMapper;
    @Autowired
    ItemService itemService;

    @RequestMapping(method=RequestMethod.GET, value="getItems")
    List<ItemDTO> getItems() {
        return itemMapper.mapToItemDTOList(itemService.getItems());
    }

    @RequestMapping(method=RequestMethod.GET, value="getItemById")
    public ItemDTO getItemById(@RequestParam Integer id) {
        return itemMapper.mapToItemDTO(itemService.getItemById(id));
    }

    @RequestMapping(method=RequestMethod.DELETE, value="deleteItemById")
    public void deleteItemById(@RequestParam Integer id) {
        itemService.deleteItemById(id);
    }

    @RequestMapping(method=RequestMethod.PUT, value="updateItem")
    public ItemDTO updateItem(@RequestBody ItemDTO itemDTO) {
        return itemMapper.mapToItemDTO(itemService.saveItem(itemMapper.mapToItem(itemDTO)));
    }

    @RequestMapping(method=RequestMethod.POST, value="createItem", consumes=APPLICATION_JSON_VALUE)
    public void createItem(@RequestBody ItemDTO itemDTO) {
        itemService.saveItem(itemMapper.mapToItem(itemDTO));
    }
}
