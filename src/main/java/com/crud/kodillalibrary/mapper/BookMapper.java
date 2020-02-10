package com.crud.kodillalibrary.mapper;

import com.crud.kodillalibrary.domain.dto.BookDTO;
import com.crud.kodillalibrary.domain.main.Book;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class BookMapper {

    public Book mapToBook(final BookDTO bookDTO) {
        Book book = new Book();
                book.setId(bookDTO.getId());
                book.setTitle(bookDTO.getTitle());
                book.setAuthor(bookDTO.getAuthor());
                book.setPublished(bookDTO.getPublished());

                return book;
    }

    public BookDTO mapToBookDTO(final Book book) {
        return new BookDTO(book.getId(),
                        book.getTitle(),
                        book.getAuthor(),
                        book.getPublished());
    }

    public List<BookDTO> mapToBookDTOList(final List<Book> bookList) {
            return bookList.stream()
                    .map(book -> new BookDTO(book.getId(),
                                             book.getTitle(),
                                             book.getAuthor(),
                                             book.getPublished()
                                             ))
                    .collect(Collectors.toList());
    }

}
