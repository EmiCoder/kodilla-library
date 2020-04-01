package com.crud.kodillalibrary.controller;

import com.crud.kodillalibrary.domain.dto.BookDTO;
import com.crud.kodillalibrary.mapper.BookMapper;
import com.crud.kodillalibrary.repository.BookRepository;
import com.crud.kodillalibrary.service.BookService;
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
@RequestMapping("/book")
public class BookController {

    @Autowired
    BookService service;
    @Autowired
    BookMapper mapper;

    @GetMapping
    public ResponseEntity<?> getBooks() throws NotFoundException {
        List<BookDTO> list = mapper.mapToBookDTOList(service.getAllBooks());
        if (!list.isEmpty()) {
            return ResponseEntity.ok().body(list);
        } else {
            throw new NotFoundException("List not found");
        }
    }

    @GetMapping("/getBookById/{id}")
    public ResponseEntity<?> getBookById(@PathVariable Integer id) {
        if (!service.getAllBooks().stream().anyMatch(book -> book.getId().equals(id))) {
            return ResponseEntity.badRequest().body("Book with given id does not exists.");
        }
        return new ResponseEntity<>(mapper.mapToBookDTO(service.getBookById(id)), HttpStatus.FOUND);
    }

    @GetMapping("/getBookByTitle/{title}")
    public ResponseEntity<?> getBookByTitle(@PathVariable String title) {
        if (!service.getAllBooks().stream().anyMatch(book -> book.getTitle().equals(title))) {
            return ResponseEntity.badRequest().body("Book with given title does not exists.");
        }
        return new ResponseEntity<>(mapper.mapToBookDTOList(service.findBooksByTitle(title)), HttpStatus.FOUND);
    }


    @PutMapping
    public ResponseEntity<?> updateBook(@RequestBody BookDTO bookDTO) {
        boolean exist = service.getAllBooks().stream().anyMatch(book -> book.getId().equals(bookDTO.getId()));
        if (bookDTO.getId() == null) {
            return ResponseEntity.badRequest().body("BookId was not specified.");
        }
        if (!exist) {
            return ResponseEntity.badRequest().body("Book with given id " + bookDTO.getId() + " does not exists.");
        }
        BookDTO result = mapper.mapToBookDTO(service.save(mapper.mapToBook(bookDTO)));
        return ResponseEntity.ok()
                .headers(HeaderUtil.createEntityUpdateAlert("KodillaLibraryApplication", false, "book", bookDTO.getId().toString()))
                .body(result);
    }

    @PostMapping
    public ResponseEntity<?> createBook(@RequestBody BookDTO bookDTO) throws URISyntaxException {
        if (service.getAllBooks().stream().anyMatch(book -> book.getId().equals(bookDTO.getId()))) {
            return ResponseEntity.badRequest().body("Book with given id already exists.");
        }

        BookDTO result = mapper.mapToBookDTO(service.save(mapper.mapToBook(bookDTO)));
        return ResponseEntity.created(new URI("/book/" + result.getId()))
                .headers(HeaderUtil.createEntityCreationAlert("KodillaLibraryApplication", false, "book", result.getId().toString()))
                .body(result);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteBook(@PathVariable Integer id) {
        if (!service.getAllBooks().stream().anyMatch(book -> book.getId().equals(id))) {
            return ResponseEntity.badRequest().body("Book with given id: " + id + " does not exists.");
        }
        service.deleteBookById(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(
                "KodillaLibraryApplication",
                false,
                "book",
                id.toString()))
                .build();
    }

}
