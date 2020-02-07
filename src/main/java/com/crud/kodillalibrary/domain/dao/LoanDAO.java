package com.crud.kodillalibrary.domain.dao;

import com.crud.kodillalibrary.domain.main.Loan;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.time.LocalDate;
import java.util.List;

@Repository
@Transactional
public interface LoanDAO extends CrudRepository<Loan, Integer> {
    List<Loan> findByLoanDate(LocalDate loanDate);
    List<Loan> findByReaderLastname(String lastname);
    List<Loan> findByItemStatus(String status);
}
