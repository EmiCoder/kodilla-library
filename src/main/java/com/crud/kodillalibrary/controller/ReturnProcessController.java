package com.crud.kodillalibrary.controller;

import com.crud.kodillalibrary.domain.dto.ReturnProcessDTO;
import com.crud.kodillalibrary.domain.main.Loan;
import com.crud.kodillalibrary.mapper.ReturnProcessMapper;
import com.crud.kodillalibrary.service.*;
import io.github.jhipster.web.util.HeaderUtil;
import javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

@RestController
@RequestMapping("/returnProcess")
public class ReturnProcessController {

    private static Integer loanId;
    private static Integer itemId;

    @Autowired
    private LoanService loanService;
    @Autowired
    private ReaderService readerService;
    @Autowired
    private ItemService itemService;
    @Autowired
    private ReturnProcessMapper mapper;
    @Autowired
    private ReturnProcessService service;


    @GetMapping
    public ResponseEntity getReturnProcesses() throws NotFoundException {
        List<ReturnProcessDTO> list = mapper.mapToReturnProcessDTOList(service.getReturnProcesses());
        if (!list.isEmpty()) {
            return ResponseEntity.ok().body(list);
        } else {
            throw new NotFoundException("List not found");
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity getReturnProcessById(@PathVariable Integer id) {
        if (!service.getReturnProcesses().stream().anyMatch(returnProcess -> returnProcess.getId().equals(id))) {
            return ResponseEntity.badRequest().body("ReturnProcess with given id does not exists.");
        }
        return new ResponseEntity(mapper.mapToReturnProcessDTO(service.getReturnPrecessById(id)), HttpStatus.FOUND);
    }

    @PutMapping
    public ResponseEntity updateReturnProcess(@RequestBody ReturnProcessDTO returnProcessDTO) {
        boolean exist = service.getReturnProcesses().stream().anyMatch(returnProcess -> returnProcess.getId().equals(returnProcessDTO.getId()));
        if (returnProcessDTO.getId() == null) {
            return ResponseEntity.badRequest().body("ReturnProcess was not specified.");
        }
        if (!exist) {
            return ResponseEntity.badRequest().body("ReturnProcess with given id " + returnProcessDTO.getId() + " does not exists.");
        }
        ReturnProcessDTO result = mapper.mapToReturnProcessDTO(service.save(mapper.mapToReturnProcess(returnProcessDTO)));
        return ResponseEntity.ok()
                .headers(HeaderUtil.createEntityUpdateAlert("KodillaLibraryApplication", false, "loanProcess", returnProcessDTO.getId().toString()))
                .body(result);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deleteReturnProcess(@PathVariable Integer id) {
        if (!service.getReturnProcesses().stream().anyMatch(loanProcess -> loanProcess.getId().equals(id))) {
            return ResponseEntity.badRequest().body("ReturnProcess with given id: " + id + " does not exists.");
        }
        service.deleteReturnProcessById(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(
                "KodillaLibraryApplication",
                false,
                "returnProcess",
                id.toString()))
                .build();
    }

    @PostMapping
    public ResponseEntity create(@RequestBody ReturnProcessDTO returnProcessDTO) throws URISyntaxException {

        if (doesExistReader(returnProcessDTO)) {
            if (doesExistTheLoan(returnProcessDTO)) {
                if (checkingLoanDetails(returnProcessDTO)) {
                    databaseUpdating();
                    ReturnProcessDTO result = mapper.mapToReturnProcessDTO(service.save(mapper.mapToReturnProcess(returnProcessDTO)));
                    return ResponseEntity.created(new URI("/returnProcess/" + result.getId()))
                            .headers(HeaderUtil.createEntityCreationAlert("KodillaLibraryApplication",
                            false, "returnProcess", result.getId().toString()))
                            .body(result);
                }
            }
        } return ResponseEntity.badRequest().body("Can not to create the return process.");
    }


    private void databaseUpdating () {
        loanService.deleteLoanById(loanId);
        itemService.getItemById(itemId).setStatus("toRent");
    }

    private boolean checkingLoanDetails(ReturnProcessDTO returnProcessDTO) {
        Loan loan = loanService.getLoanById(loanId);
        if (loan.getReader().getId().equals(returnProcessDTO.getReaderId()) &&
            loan.getItem().getId().equals(returnProcessDTO.getItemId())) {
            return true;
        } return false;
    }

    private boolean doesExistTheLoan(ReturnProcessDTO returnProcessDTO) {
        List<Loan> loanList = loanService.getLoans();
        for (Loan loan : loanList) {
            if (loan.getReader().getId().equals(returnProcessDTO.getReaderId())) {
                loanId = loan.getId();
                itemId = loan.getItem().getId();
                return true;
            }
        } return false;
    }

    private boolean doesExistReader(ReturnProcessDTO returnProcessDTO) {
        if (readerService.getAllReaders().stream().anyMatch(reader -> reader.getId().equals(returnProcessDTO.getReaderId()))) {
            return true;
        } return false;
    }
}
