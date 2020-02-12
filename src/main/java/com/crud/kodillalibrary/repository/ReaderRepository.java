package com.crud.kodillalibrary.repository;

import com.crud.kodillalibrary.domain.main.Reader;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface ReaderRepository extends CrudRepository<Reader, Integer> {

    @Override
    List<Reader> findAll();

    @Override
    Reader save(Reader reader);

    @Override
    Optional<Reader> findById(Integer id);

    @Override
    void deleteById(Integer id);
}
