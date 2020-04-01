package com.crud.kodillalibrary;

import com.crud.kodillalibrary.domain.dao.BookDAO;
import com.crud.kodillalibrary.domain.dao.ItemDAO;
import com.crud.kodillalibrary.domain.main.Book;
import com.crud.kodillalibrary.domain.main.Item;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class QueriesTestSuite {

    @Autowired
    ItemDAO itemDAO;

    @Autowired
    BookDAO bookDAO;


    @Test
    public void doesItemNamedNativeQueryWorksCorrectly() {
        Book book1 = new Book();
                book1.setTitle("Title_1");
                book1.setAuthor("Author_1");
                book1.setPublished("Published_1");
                bookDAO.save(book1);
        Item item1 = new Item();
                item1.setStatus("rented");
                item1.setBook(book1);
                itemDAO.save(item1);
        Item item2 = new Item();
                item2.setStatus("destroyed");
                item2.setBook(book1);
                itemDAO.save(item2);
        Assert.assertEquals(2, itemDAO.findByTitle("Title_1").size());
        itemDAO.deleteAll();
        bookDAO.deleteAll();
    }
}
