package com.crud.kodillalibrary.returnProcessDAO;

import com.crud.kodillalibrary.domain.dao.LoanProcessDAO;
import com.crud.kodillalibrary.domain.dao.ReaderDAO;
import com.crud.kodillalibrary.domain.dao.ReturnProcessDAO;
import com.crud.kodillalibrary.domain.main.ReturnProcess;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class returnProcessDAOTestSuit {

    @Autowired
    private LoanProcessDAO loanProcessDAO;
    @Autowired
    private ReaderDAO readerDAO;
    @Autowired
    private ReturnProcessDAO returnProcessDAO;

    @Test
    public void testReturnProcessDaoSave() {
        ReturnProcess returnProcess1 = new ReturnProcess();
                        returnProcess1.setReaderId(1);
                        returnProcess1.setItemId(13);
                        returnProcessDAO.save(returnProcess1);

        ReturnProcess returnProcess2 = new ReturnProcess();
                        returnProcess2.setReaderId(2);
                        returnProcess2.setItemId(14);
                        returnProcessDAO.save(returnProcess2);

        Assert.assertEquals(returnProcess1.getId(), returnProcessDAO.findById(returnProcess1.getId()).get().getId());
        returnProcessDAO.deleteById(returnProcess1.getId());
        returnProcessDAO.deleteById(returnProcess2.getId());

    }
}
