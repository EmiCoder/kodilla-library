package com.crud.kodillalibrary.domain.dao;

import com.crud.kodillalibrary.domain.main.Reader;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

@Repository
@Transactional
public interface ReaderDAO extends CrudRepository<Reader, Integer> {
    List<Reader> findByFirstname(String firstname);
    List<Reader> findByAccountCreatingDate(String date);
}
