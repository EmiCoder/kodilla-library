package com.crud.kodillalibrary.itemDAO;

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
public class ItemDaoTestSuit {

    @Autowired
    private ItemDAO itemDAO;
    @Autowired
    BookDAO bookDAO;

    @Test
    public void testItemDaoSave() {

        Book book_1 = new Book();
            book_1.setTitle("Kubus Puchatek");
            book_1.setAuthor("Alan Alexander Milne");
            book_1.setPublished("1926");
            bookDAO.save(book_1);
            Integer book_1_id = book_1.getId();

        Item item_1 = new Item();
            item_1.setStatus("InUse");
            item_1.setBook(book_1);
            itemDAO.save(item_1);
        Integer book_1_id_in_itemsTable = item_1.getBook().getId();

        Book book_2 = new Book();
            book_2.setTitle("Kubus Puchatek");
            book_2.setAuthor("Alan Alexander Milne");
            book_2.setPublished("1926");
            bookDAO.save(book_2);

        Item item_2 = new Item();
            item_2.setStatus("Lost");
            item_2.setBook(book_2);
            itemDAO.save(item_2);

        Book book_3 = new Book();
            book_3.setTitle("Coyoties");
            book_3.setAuthor("Franc Tomas");
            book_3.setPublished("2017");
            bookDAO.save(book_3);

         Item item_3 = new Item();
            item_3.setStatus("InUse");
            item_3.setBook(book_3);
            itemDAO.save(item_3);

        Assert.assertEquals(2, itemDAO.findByStatus("InUse").size());
        Assert.assertEquals(1, itemDAO.findByStatus("Lost").size());
        Assert.assertEquals(book_1_id, book_1_id_in_itemsTable);
//        Assert.assertEquals(1, itemDAO.findByBookId(book_1_id).size());

        bookDAO.deleteAll();
        itemDAO.deleteAll();
    }
}
