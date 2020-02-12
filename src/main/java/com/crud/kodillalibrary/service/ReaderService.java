package com.crud.kodillalibrary.service;

import com.crud.kodillalibrary.domain.main.Reader;
import com.crud.kodillalibrary.repository.ReaderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReaderService {

    @Autowired
    ReaderRepository readerRepository;

    public List<Reader> getAllReaders() {
        return readerRepository.findAll();
    }

    public Reader save(Reader reader) {
        return readerRepository.save(reader);
    }

    public Reader getReaderById(Integer id) {
        return readerRepository.findById(id).get();
    }

    public void deleteById(Integer id) {
        readerRepository.deleteById(id);
    }
}
