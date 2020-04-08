package com.crud.kodillalibrary.domain.dao;

import com.crud.kodillalibrary.domain.main.LoanProcess;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

@Repository
@Transactional
public interface LoanProcessDAO extends CrudRepository<LoanProcess, Integer> {
    List<LoanProcess> findByReader(Integer id);
    List<LoanProcess> findAll();
}
