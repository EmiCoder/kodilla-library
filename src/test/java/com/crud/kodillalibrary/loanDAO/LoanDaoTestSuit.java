package com.crud.kodillalibrary.loanDAO;

import com.crud.kodillalibrary.domain.dao.BookDAO;
import com.crud.kodillalibrary.domain.dao.ItemDAO;
import com.crud.kodillalibrary.domain.dao.LoanDAO;
import com.crud.kodillalibrary.domain.dao.ReaderDAO;
import com.crud.kodillalibrary.domain.main.Book;
import com.crud.kodillalibrary.domain.main.Item;
import com.crud.kodillalibrary.domain.main.Loan;
import com.crud.kodillalibrary.domain.main.Reader;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDate;

@RunWith(SpringRunner.class)
@SpringBootTest
public class LoanDaoTestSuit {

    @Autowired
    LoanDAO loanDAO;
    @Autowired
    ReaderDAO readerDAO;
    @Autowired
    BookDAO bookDAO;
    @Autowired
    ItemDAO itemDAO;

    @Test
    public void testLoanDaoSave() {

        Reader reader_1=new Reader();
        reader_1.setFirstname("Thomas");
        reader_1.setLastname("Carry");
        reader_1.setAccountCreatingDate("2014.01.03");
        readerDAO.save(reader_1);

        Book book_1=new Book();
        book_1.setTitle("Kubus Puchatek");
        book_1.setAuthor("Alan Alexander Milne");
        book_1.setPublished("1926");
        bookDAO.save(book_1);

        Item item_1=new Item();
        item_1.setStatus("InUse");
        item_1.setBook(book_1);
        itemDAO.save(item_1);


        Loan loan_1=new Loan();
        loan_1.setLoanDate(LocalDate.of(2010, 10, 15));
        loan_1.setReturnDate(LocalDate.of(2010, 10, 25));
        loan_1.setReader(reader_1);
        loan_1.setItem(item_1);
        loanDAO.save(loan_1);

        Assert.assertEquals(1, loanDAO.findByReaderLastname("Carry").size());
        Assert.assertEquals(1,loanDAO.findByItemStatus("InUse").size());
        Assert.assertEquals(0,loanDAO.findByItemStatus("Lost").size());

        bookDAO.deleteAll();
        itemDAO.deleteAll();
        loanDAO.deleteAll();
        readerDAO.deleteAll();
    }
}
