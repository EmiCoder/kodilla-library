package com.crud.kodillalibrary.loanProcessDAO;

import com.crud.kodillalibrary.domain.dao.LoanProcessDAO;
import com.crud.kodillalibrary.domain.dao.ReaderDAO;
import com.crud.kodillalibrary.domain.dto.LoanProcessDTO;
import com.crud.kodillalibrary.domain.main.LoanProcess;
import com.crud.kodillalibrary.domain.main.Reader;
import com.crud.kodillalibrary.mapper.LoanProcessMapper;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.transaction.Transactional;
import java.time.LocalDate;

@Transactional
@RunWith(SpringRunner.class)
@SpringBootTest
public class LoanProcessDAOTestSuit {

    @Autowired
    private LoanProcessDAO loanProcessDAO;
    @Autowired
    ReaderDAO readerDAO;
    @Autowired
    LoanProcessMapper mapper;

    @Test
    public void testLoanProcessDaoSave() {
        Reader reader = new Reader();
        reader.setFirstname("Thomas");
        reader.setLastname("Carry");
        reader.setAccountCreatingDate("2014.01.03");
        readerDAO.save(reader);

        LoanProcess loanProcess = new LoanProcess();
                    loanProcess.setUser(reader);
                    loanProcess.setLoanDate(LocalDate.now());
                    loanProcess.setReturnDate(LocalDate.now().plusDays(5));
                    loanProcess.setBookTitle("Maly Ksiaze");
        loanProcessDAO.save(loanProcess);

        Assert.assertEquals(1, loanProcessDAO.findByUser(reader).size());
        readerDAO.deleteById(reader.getId());
        loanProcessDAO.deleteById(loanProcess.getId());
    }

    @Test
    public void doesMapperWorksCorrectly() {
        Reader reader = new Reader();
        reader.setFirstname("Thomas");
        reader.setLastname("Carry");
        reader.setAccountCreatingDate("2014.01.03");
        readerDAO.save(reader);

        LoanProcess loan = new LoanProcess();
                        loan.setId(1);
                        loan.setUser(reader);
                        loan.setBookTitle("title");
                        loan.setLoanDate(LocalDate.now());
                        loan.setReturnDate(LocalDate.now().plusDays(2));
                        loanProcessDAO.save(loan);
        LoanProcessDTO loanProcessDTO =  mapper.mapToLoanPrecessDto(loan);
        Assert.assertEquals(loan.getUser().getId(), loanProcessDTO.getUser());
    }
}
