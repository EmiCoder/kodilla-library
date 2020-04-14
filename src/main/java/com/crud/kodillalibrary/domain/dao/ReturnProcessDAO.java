package com.crud.kodillalibrary.domain.dao;

import com.crud.kodillalibrary.domain.main.ReturnProcess;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

@Repository
@Transactional
public interface ReturnProcessDAO extends CrudRepository<ReturnProcess, Integer> {
}
