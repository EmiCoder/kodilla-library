package com.crud.kodillalibrary.domain.dao;

import com.crud.kodillalibrary.domain.main.LoanProcess;
import com.crud.kodillalibrary.domain.main.Reader;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

@Repository
@Transactional
public interface LoanProcessDAO extends CrudRepository<LoanProcess, Integer> {
    List<LoanProcess> findByUser(Reader reader);
}
