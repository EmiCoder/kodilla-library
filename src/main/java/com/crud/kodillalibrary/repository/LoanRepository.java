package com.crud.kodillalibrary.repository;

import com.crud.kodillalibrary.domain.main.Loan;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface LoanRepository extends CrudRepository<Loan, Integer> {

    @Override
    List<Loan> findAll();

    @Override
    Loan save(Loan loan);

    @Override
    Optional<Loan> findById(Integer id);

    @Override
    void deleteById(Integer id);


}
