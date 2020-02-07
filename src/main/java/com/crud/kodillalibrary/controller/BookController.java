package com.crud.kodillalibrary.controller;

import com.crud.kodillalibrary.domain.dto.BookDTO;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/book")
public class BookController {

    @RequestMapping(method=RequestMethod.GET, value="getBooks")
    List<BookDTO> getBooks() {
        return new ArrayList<>();
    }

    @RequestMapping(method=RequestMethod.GET, value="getBookById")
    public BookDTO getBookById(Integer id) {
        return new BookDTO(1, "Title", "Author", "2012");
    }

    @RequestMapping(method=RequestMethod.DELETE, value="deleteBookById")
    public void deleteBookById(Integer id) {

    }

    @RequestMapping(method=RequestMethod.PUT, value="updateBook")
    public BookDTO updateBook(BookDTO bookDTO) {
        return new BookDTO(1, "Updated title", "Updated Author", "Updated published");
    }

    @RequestMapping(method=RequestMethod.POST, value="createBook")
    public void createBook(BookDTO bookDTO) {

    }
}
