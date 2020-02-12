package com.crud.kodillalibrary.service;

import com.crud.kodillalibrary.domain.main.Loan;
import com.crud.kodillalibrary.repository.LoanRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class LoanService {

    @Autowired
    LoanRepository loanRepository;

    public List<Loan> getLoans() {
        return loanRepository.findAll();
    }

    public Loan save(Loan loan) {
        return loanRepository.save(loan);
    }

    public Loan getLoanById(Integer id) {
        return loanRepository.findById(id).get();
    }

    public Loan updateLoanDateDetails(Integer loanId, LocalDate loanDate, LocalDate returnDate) {
        Loan loanById=getLoanById(loanId);
        loanById.setLoanDate(loanDate);
        loanById.setReturnDate(returnDate);
        save(loanById);
        return loanById;
    }

    public void deleteLoanById(Integer id) {
        loanRepository.deleteById(id);
    }
}
