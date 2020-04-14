package com.crud.kodillalibrary.service;

import com.crud.kodillalibrary.domain.main.LoanProcess;
import com.crud.kodillalibrary.domain.main.ReturnProcess;
import com.crud.kodillalibrary.repository.ReturnProcessRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ReturnProcessService {

    @Autowired
    private ReturnProcessRepository repository;

    public List<ReturnProcess> getReturnProcesses() {
        return repository.findAll();
    }

    public ReturnProcess save(ReturnProcess returnProcess) {
        return repository.save(returnProcess);
    }

    public ReturnProcess getReturnPrecessById(final Integer id) {
        return repository.findById(id).get();
    }


    public void deleteReturnProcessById(Integer id) {
        repository.deleteById(id);
    }

    public ReturnProcess findReturnProcessByReaderId(Integer readerId) {
        return repository.findAll().stream()
                .filter(returnProcess -> readerId.equals(returnProcess.getReaderId())).findFirst().get();
    }
}
