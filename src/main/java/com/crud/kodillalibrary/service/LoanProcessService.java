package com.crud.kodillalibrary.service;

import com.crud.kodillalibrary.domain.main.Book;
import com.crud.kodillalibrary.domain.main.Item;
import com.crud.kodillalibrary.domain.main.LoanProcess;
import com.crud.kodillalibrary.repository.LoanProcessRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class LoanProcessService {

    @Autowired
    private LoanProcessRepository repository;

    public List<LoanProcess> getLoanProcesses() {
        return repository.findAll();
    }

    public LoanProcess save(LoanProcess loanProcess) {
        return repository.save(loanProcess);
    }

    public LoanProcess getLoanPrecessById(final Integer id) {
        return repository.findById(id).get();
    }

    public void deleteLoanPrcessById(Integer id) {
        repository.deleteById(id);
    }

    public List<LoanProcess> findLoanPrecessByReaderId(Integer readerId) {
        return repository.findAll().stream()
                .filter(loanProcess -> readerId.equals(loanProcess.getReader().getId())).collect(Collectors.toList());
    }
}
