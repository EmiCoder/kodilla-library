package com.crud.kodillalibrary.readerDAO;

import com.crud.kodillalibrary.domain.dao.ReaderDAO;
import com.crud.kodillalibrary.domain.main.Reader;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ReaderDaoTestSuit {

    @Autowired
    ReaderDAO readerDAO;

    @Test
    public void testReaderDaoSave() {

        readerDAO.deleteAll();
        Reader reader_1 = new Reader();
            reader_1.setFirstname("Thomas");
            reader_1.setLastname("Carry");
            reader_1.setAccountCreatingDate("2014.01.03");
            readerDAO.save(reader_1);

        Reader reader_2 = new Reader();
            reader_2.setFirstname("Thomas");
            reader_2.setLastname("Luck");
            reader_2.setAccountCreatingDate("2010.04.16");
            readerDAO.save(reader_2);

        Assert.assertEquals(2, readerDAO.findByFirstname("Thomas").size());
        Assert.assertEquals(1, readerDAO.findByAccountCreatingDate("2010.04.16").size());

        readerDAO.deleteAll();
    }
}
