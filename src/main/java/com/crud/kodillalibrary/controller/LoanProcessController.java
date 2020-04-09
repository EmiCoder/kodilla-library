package com.crud.kodillalibrary.controller;

import com.crud.kodillalibrary.domain.dto.BookDTO;
import com.crud.kodillalibrary.domain.dto.LoanProcessDTO;
import com.crud.kodillalibrary.domain.main.Book;
import com.crud.kodillalibrary.domain.main.Item;
import com.crud.kodillalibrary.domain.main.LoanProcess;
import com.crud.kodillalibrary.domain.main.Reader;
import com.crud.kodillalibrary.mapper.ItemMapper;
import com.crud.kodillalibrary.mapper.LoanProcessMapper;
import com.crud.kodillalibrary.mapper.ReaderMapper;
import com.crud.kodillalibrary.service.BookService;
import com.crud.kodillalibrary.service.ItemService;
import com.crud.kodillalibrary.service.LoanProcessService;
import com.crud.kodillalibrary.service.ReaderService;
import io.github.jhipster.web.util.HeaderUtil;
import javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.swing.*;
import java.net.URI;
import java.net.URISyntaxException;
import java.time.LocalDate;
import java.util.List;

import static javax.swing.JOptionPane.*;

@RestController
@RequestMapping("/loanProcess")
public class LoanProcessController {

    @Autowired
    private LoanProcessMapper mapper;
    @Autowired
    private LoanProcessService service;
    @Autowired
    private ReaderService readerService;
    @Autowired
    private ItemService itemService;



    @GetMapping
    public ResponseEntity getLoanProcesses() throws NotFoundException {
        List<LoanProcessDTO> list = mapper.mapToLoanProcessDtoList(service.getLoanProcesses());
        if (!list.isEmpty()) {
            return ResponseEntity.ok().body(list);
        } else {
            throw new NotFoundException("List not found");
        }
    }

    @PostMapping
    public ResponseEntity createLoanProcess(@RequestBody LoanProcessDTO loanProcessDTO) throws URISyntaxException {
        if (doesExistReader(loanProcessDTO.getReaderId())) {
            if (isPossibleToRentTheTitle(loanProcessDTO.getBookTitle())) {
                changeItemStatus(loanProcessDTO);
                LoanProcessDTO result = mapper.mapToLoanPrecessDto(service.save(mapper.mapToLoan(loanProcessDTO)));
                return ResponseEntity.created(new URI("/lonaProcess/" + result.getId()))
                        .headers(HeaderUtil.createEntityCreationAlert("KodillaLibraryApplication", false, "loanProcess", result.getId().toString()))
                        .body(result);
            }
        } else {
            if (isPossibleToRentTheTitle(loanProcessDTO.getBookTitle())) {
                Reader reader = createNewReader();
                LoanProcess loanProcess = new LoanProcess();
                loanProcess.setBookTitle(loanProcessDTO.getBookTitle());
                loanProcess.setLoanDate(loanProcessDTO.getLoanDate());
                loanProcess.setReturnDate(loanProcessDTO.getReturnDate());
                loanProcess.setReader(reader);
                changeItemStatus(loanProcessDTO);
                LoanProcessDTO result = mapper.mapToLoanPrecessDto(service.save(loanProcess));
                return ResponseEntity.created(new URI("/loanProcess/" + result.getId()))
                        .headers(HeaderUtil.createEntityCreationAlert("KodillaLibraryApplication", false, "loanProcess", result.getId().toString()))
                        .body(result);
            }
        } return  ResponseEntity.badRequest().body("Can not to create loan.");
    }



    private boolean doesExistReader(Integer id) {
        if (readerService.getAllReaders().stream().anyMatch(reader -> reader.getId().equals(id))) {
            return true;
        } return false;
    }

    private boolean isPossibleToRentTheTitle(String title) {
        for (Item item : itemService.getItems()) {
            if (item.getBook().getTitle().equals(title) && item.getStatus().equals("toRent")) {
                return true;
            }
        } return false;
    }

    private ResponseEntity createLoan(LoanProcessDTO loanProcessDTO) throws URISyntaxException {
        LoanProcessDTO result = mapper.mapToLoanPrecessDto(service.save(mapper.mapToLoan(loanProcessDTO)));
        return ResponseEntity.created((new URI("loanProcess" + result.getId())))
                .headers(HeaderUtil.createEntityCreationAlert("KodillaLibraryApplication", false, "loanProcess", result.getId().toString()))
                .body(result);
    }

    private Reader createNewReader() throws URISyntaxException {
        Reader reader = new Reader();
        reader.setFirstname("New FirstName");
        reader.setLastname("New LastName");
        reader.setAccountCreatingDate(LocalDate.now().toString());
        readerService.save(reader);
        return reader;
    }

    public void changeItemStatus(LoanProcessDTO loanProcessDTO) {
        for (Item item : itemService.getItems()) {
            if (item.getBook().getTitle().equals(loanProcessDTO.getBookTitle()) && item.getStatus().equals("toRent")) {
                item.setStatus("rented");
            }
        }
    }

}
