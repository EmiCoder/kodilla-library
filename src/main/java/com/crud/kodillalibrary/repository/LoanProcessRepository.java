package com.crud.kodillalibrary.repository;

import com.crud.kodillalibrary.domain.main.Book;
import com.crud.kodillalibrary.domain.main.LoanProcess;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface LoanProcessRepository extends CrudRepository<LoanProcess, Integer> {

    @Override
    List<LoanProcess> findAll();

    @Override
    LoanProcess save(LoanProcess loanProcess);

    @Override
    Optional<LoanProcess> findById(Integer id);

    @Override
    void deleteById(Integer id);
}
