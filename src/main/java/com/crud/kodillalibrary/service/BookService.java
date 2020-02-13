package com.crud.kodillalibrary.service;

import com.crud.kodillalibrary.domain.main.Book;
import com.crud.kodillalibrary.domain.main.Item;
import com.crud.kodillalibrary.repository.BookRepository;
import com.crud.kodillalibrary.repository.ItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class BookService {

    @Autowired
    BookRepository bookRepository;


    public List<Book> getAllBooks() {
        return bookRepository.findAll();
    }

    public Book save(Book book) {
        return bookRepository.save(book);
    }

    public Book getBookById(final Integer id) {
        return bookRepository.findById(id).get();
    }

    public void deleteBookById(Integer id) {
        bookRepository.deleteById(id);
    }

    public List<Book> findBooksByTitle(String title) {
        return bookRepository.findAll().stream()
                .filter(book -> title.equals(book.getTitle())).collect(Collectors.toList());
    }


}
