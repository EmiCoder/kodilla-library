package com.crud.kodillalibrary.controller;
import com.crud.kodillalibrary.domain.dto.LoanDTO;
import com.crud.kodillalibrary.domain.dto.ReaderDTO;
import com.crud.kodillalibrary.mapper.LoanMapper;
import com.crud.kodillalibrary.service.LoanService;
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
@RequestMapping("/loan")
public class LoanController {

    @Autowired
    LoanMapper mapper;
    @Autowired
    LoanService service;

    @GetMapping
    public ResponseEntity<?> getLoans() {
        List<LoanDTO> list = mapper.mapToLoanDTOList(service.getLoans());
        if (!list.isEmpty()) {
            return ResponseEntity.ok().body(list);
        } else {
            return ResponseEntity.badRequest().body("List not found");
//            throw new NotFoundException("List not found");
        }
    }

    @PostMapping
    public ResponseEntity<?> createLoan(@RequestBody LoanDTO loanDTO) throws URISyntaxException {
        if (service.getLoans().stream().anyMatch(loan -> loan.getId().equals(loanDTO.getId()))) {
            return ResponseEntity.badRequest().body("Loan with given id already exists.");
        }

        LoanDTO result = mapper.mapToLoanDTO(service.save(mapper.mapToLoan(loanDTO)));
        return ResponseEntity.created(new URI("/loan/" + result.getId()))
                .headers(HeaderUtil.createEntityCreationAlert("KodillaLibraryApplication", false, "loan", result.getId().toString()))
                .body(result);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getLoanById(@PathVariable Integer id) {
        if (!service.getLoans().stream().anyMatch(loan -> loan.getId().equals(id))) {
            return ResponseEntity.badRequest().body("Loan with given id " + id +" does not exists.");
        }
        return new ResponseEntity<>(mapper.mapToLoanDTO(service.getLoanById(id)), HttpStatus.FOUND);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteLoanById(@PathVariable Integer id) {

        if (!service.getLoans().stream().anyMatch(loan -> loan.getId().equals(id))) {
            return ResponseEntity.badRequest().body("Loan with given id: " + id + " does not exists.");
        }
        service.deleteLoanById(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(
                "KodillaLibraryApplication",
                false,
                "loan",
                id.toString()))
                .build();
    }

    @PutMapping
    public ResponseEntity<?> updateLoan(@RequestBody LoanDTO loanDTO) {
        boolean exist = service.getLoans().stream().anyMatch(loan -> loan.getId().equals(loanDTO.getId()));

        if (loanDTO.getId() == null) {
            return ResponseEntity.badRequest().body("loanId was not specified.");
        }
        if (!exist) {
            return ResponseEntity.badRequest().body("Loan with given id " + loanDTO.getId() + " does not exists.");
        }

        LoanDTO result = mapper.mapToLoanDTO(service.save(mapper.mapToLoan(loanDTO)));
        return ResponseEntity.ok()
                .headers(HeaderUtil.createEntityUpdateAlert("KodillaLibraryApplication", false, "loan", loanDTO.getId().toString()))
                .body(result);
    }


}
