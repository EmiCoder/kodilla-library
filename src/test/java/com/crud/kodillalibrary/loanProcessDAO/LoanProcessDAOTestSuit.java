package com.crud.kodillalibrary.loanProcessDAO;

import com.crud.kodillalibrary.domain.dao.LoanProcessDAO;
import com.crud.kodillalibrary.domain.dao.ReaderDAO;
import com.crud.kodillalibrary.domain.main.LoanProcess;
import com.crud.kodillalibrary.domain.main.Reader;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDate;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class LoanProcessDAOTestSuit {

    @Autowired
    private LoanProcessDAO loanProcessDAO;
    @Autowired
    ReaderDAO readerDAO;

    @Test
    public void testLoanProcessDaoSave() {
//        loanProcessDAO.deleteAll();
//        readerDAO.deleteAll();
//        Reader reader = new Reader();
//        reader.setFirstname("Thomas");
//        reader.setLastname("Carry");
//        reader.setAccountCreatingDate("2014.01.03");
//        readerDAO.save(reader);
//
//        LoanProcess loanProcess = new LoanProcess(5);
//                    loanProcess.setReader(reader);
//        loanProcessDAO.save(loanProcess);
//
////        Assert.assertEquals(1, loanProcessDAO.findByReader(reader.getId()).size());
//        readerDAO.deleteAll();
//        loanProcessDAO.deleteAll();
//        readerDAO.deleteById(reader.getId());
//        loanProcessDAO.deleteById(loanProcess.getId());
    }
}
