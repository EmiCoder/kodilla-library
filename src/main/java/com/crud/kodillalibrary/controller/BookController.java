package com.crud.kodillalibrary.controller;

import com.crud.kodillalibrary.domain.dto.BookDTO;
import com.crud.kodillalibrary.domain.main.Book;
import com.crud.kodillalibrary.mapper.BookMapper;
import com.crud.kodillalibrary.repository.BookRepository;
import com.crud.kodillalibrary.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping("/book")
public class BookController {

    @Autowired
    BookService bookService;
    @Autowired
    BookMapper bookMapper;
    @Autowired
    BookRepository bookRepository;

    @RequestMapping(method=RequestMethod.GET, value="getBooks")
    List<BookDTO> getBooks() {
        return bookMapper.mapToBookDTOList(bookService.getAllBooks());
    }

    @RequestMapping(method=RequestMethod.GET, value="getBookById")
    public BookDTO getBookById(@RequestParam Integer id){
        return bookMapper.mapToBookDTO(bookService.getBookById(id));
    }

    @RequestMapping(method=RequestMethod.GET, value="getBooksByTitle")
    public List<Book> findByTitle(@RequestParam String title) {
        return bookRepository.findByTitle(title);
    }

    @RequestMapping(method=RequestMethod.DELETE, value="deleteBookById")
    public void deleteBookById(@RequestParam Integer id) {
        bookService.deleteBookById(id);
    }

    @RequestMapping(method=RequestMethod.PUT, value="updateBook")
    public BookDTO updateBook(@RequestBody BookDTO bookDTO) {
        return bookMapper.mapToBookDTO(bookService.save(bookMapper.mapToBook(bookDTO)));
    }

    @RequestMapping(method=RequestMethod.POST, value="createBook", consumes=APPLICATION_JSON_VALUE)
    public void createBook(@RequestBody BookDTO bookDTO) {
        bookService.save(bookMapper.mapToBook(bookDTO));
    }



}
