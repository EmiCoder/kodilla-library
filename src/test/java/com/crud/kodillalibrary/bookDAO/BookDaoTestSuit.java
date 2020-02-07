package com.crud.kodillalibrary.bookDAO;


import com.crud.kodillalibrary.domain.dao.BookDAO;
import com.crud.kodillalibrary.domain.main.Book;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.persistence.criteria.CriteriaBuilder;
import java.util.Optional;

@RunWith(SpringRunner.class)
@SpringBootTest
public class BookDaoTestSuit {

    @Autowired
    BookDAO bookDAO;

    @Test
    public void testBookDaoSave() {

        Book book = new Book();
            book.setTitle("Kubus Puchatek");
            book.setAuthor("Alan Alexander Milne");
            book.setPublished("1926");
        bookDAO.save(book);

        Book book_2 = new Book();
            book_2.setTitle("Kubus Puchatek");
            book_2.setAuthor("Alan Alexander Milne");
            book_2.setPublished("1926");
            bookDAO.save(book_2);

        Integer id = book.getId();
        Optional<Book> foundedBook = bookDAO.findById(id);
        Assert.assertTrue(foundedBook.isPresent());
        Assert.assertEquals(2, bookDAO.findByTitle("Kubus Puchatek").size());

        bookDAO.deleteById(id);
    }


}
