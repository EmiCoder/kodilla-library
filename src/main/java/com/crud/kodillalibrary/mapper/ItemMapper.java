package com.crud.kodillalibrary.mapper;

import com.crud.kodillalibrary.domain.dto.ItemDTO;
import com.crud.kodillalibrary.domain.main.Item;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class ItemMapper {

    public Item mapToItem(final ItemDTO itemDTO) {
        Item item = new Item();
                item.setId(itemDTO.getId());
                item.setStatus(itemDTO.getStatus());
                item.setBook(itemDTO.getBook());

        return item;
    }

    public ItemDTO mapToItemDTO(final Item item) {
        return new ItemDTO(item.getId(), item.getStatus(), item.getBook());
    }

    public List<ItemDTO> mapToItemDTOList(List<Item> itemList) {
        return itemList.stream()
                        .map(item -> new ItemDTO(item.getId(), item.getStatus(), item.getBook()))
                        .collect(Collectors.toList());
    }
}
