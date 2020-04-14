package com.crud.kodillalibrary.repository;

import com.crud.kodillalibrary.domain.main.LoanProcess;
import com.crud.kodillalibrary.domain.main.ReturnProcess;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface ReturnProcessRepository extends CrudRepository<ReturnProcess, Integer> {

    @Override
    List<ReturnProcess> findAll();

    @Override
    ReturnProcess save(ReturnProcess returnProcess);

    @Override
    Optional<ReturnProcess> findById(Integer id);

    @Override
    void deleteById(Integer id);
}
