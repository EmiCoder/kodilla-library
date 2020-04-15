package com.crud.kodillalibrary.controller;

import com.crud.kodillalibrary.domain.dto.LoanProcessDTO;
import com.crud.kodillalibrary.domain.main.Item;
import com.crud.kodillalibrary.domain.main.Loan;
import com.crud.kodillalibrary.domain.main.LoanProcess;
import com.crud.kodillalibrary.domain.main.Reader;
import com.crud.kodillalibrary.mapper.LoanProcessMapper;
import com.crud.kodillalibrary.service.ItemService;
import com.crud.kodillalibrary.service.LoanProcessService;
import com.crud.kodillalibrary.service.LoanService;
import com.crud.kodillalibrary.service.ReaderService;
import io.github.jhipster.web.util.HeaderUtil;
import javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.net.URI;
import java.net.URISyntaxException;
import java.time.LocalDate;
import java.util.List;


@RestController
@RequestMapping("/loanProcess")
public class LoanProcessController {

    static int itemId;

    @Autowired
    private LoanProcessMapper mapper;
    @Autowired
    private LoanProcessService service;
    @Autowired
    private ReaderService readerService;
    @Autowired
    private ItemService itemService;
    @Autowired
    private LoanService loanService;


    @GetMapping
    public ResponseEntity getLoanProcesses() throws NotFoundException {
        List<LoanProcessDTO> list = mapper.mapToLoanProcessDtoList(service.getLoanProcesses());
        if (!list.isEmpty()) {
            return ResponseEntity.ok().body(list);
        } else {
            throw new NotFoundException("List not found");
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity getLoanProcessById(@PathVariable Integer id) {
        if (!service.getLoanProcesses().stream().anyMatch(loanProcess -> loanProcess.getId().equals(id))) {
            return ResponseEntity.badRequest().body("LoanProcess with given id does not exists.");
        }
        return new ResponseEntity(mapper.mapToLoanPrecessDto(service.getLoanPrecessById(id)), HttpStatus.FOUND);
    }

    @PutMapping
    public ResponseEntity updateLoanProcess(@RequestBody LoanProcessDTO loanProcessDTO) {
        boolean exist = service.getLoanProcesses().stream().anyMatch(loanProcess -> loanProcess.getId().equals(loanProcessDTO.getId()));
        if (loanProcessDTO.getId() == null) {
            return ResponseEntity.badRequest().body("LoanProcess was not specified.");
        }
        if (!exist) {
            return ResponseEntity.badRequest().body("LoanProcess with given id " + loanProcessDTO.getId() + " does not exists.");
        }
        LoanProcessDTO result = mapper.mapToLoanPrecessDto(service.save(mapper.mapToLoan(loanProcessDTO)));
        return ResponseEntity.ok()
                .headers(HeaderUtil.createEntityUpdateAlert("KodillaLibraryApplication", false, "loanProcess", loanProcessDTO.getId().toString()))
                .body(result);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deleteLoanProcess(@PathVariable Integer id) {
        if (!service.getLoanProcesses().stream().anyMatch(loanProcess -> loanProcess.getId().equals(id))) {
            return ResponseEntity.badRequest().body("LoanProcess with given id: " + id + " does not exists.");
        }
        service.deleteLoanProcessById(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(
                "KodillaLibraryApplication",
                false,
                "loanProcess",
                id.toString()))
                .build();
    }

    @PostMapping
    public ResponseEntity create(@RequestBody LoanProcessDTO loanProcessDTO) throws URISyntaxException {
        if (doesExistReader(loanProcessDTO.getUser())) {
            if (isPossibleToRentTheTitle(loanProcessDTO.getBookTitle())) {
                changeItemStatus(loanProcessDTO);
                LoanProcessDTO result = mapper.mapToLoanPrecessDto(service.save(mapper.mapToLoan(loanProcessDTO)));
                readerService.getReaderById(loanProcessDTO.getUser()).getLoanProcesses().add(mapper.mapToLoan(result));
                createNewLoan(result);
                return ResponseEntity.created(new URI("/lonaProcess/" + result.getId()))
                        .headers(HeaderUtil.createEntityCreationAlert("KodillaLibraryApplication", false, "loanProcess", result.getId().toString()))
                        .body(result);
            }
        } else {
            if (isPossibleToRentTheTitle(loanProcessDTO.getBookTitle())) {
                LoanProcess loanProcess = createLoanProcess(loanProcessDTO);
                changeItemStatus(loanProcessDTO);
                LoanProcessDTO result = mapper.mapToLoanPrecessDto(service.save(loanProcess));
                createNewLoan(result);
                return ResponseEntity.created(new URI("/loanProcess/" + result.getId()))
                        .headers(HeaderUtil.createEntityCreationAlert("KodillaLibraryApplication", false, "loanProcess", result.getId().toString()))
                        .body(result);
            }
        } return  ResponseEntity.badRequest().body("Can not to create loan.");
    }






    private LoanProcess createLoanProcess(LoanProcessDTO loanProcessDTO) {
        LoanProcess loanProcess = new LoanProcess();
        loanProcess.setBookTitle(loanProcessDTO.getBookTitle());
        loanProcess.setLoanDate(loanProcessDTO.getLoanDate());
        loanProcess.setReturnDate(loanProcessDTO.getReturnDate());
        loanProcess.setUser(createNewReader());
        return loanProcess;
    }

    private Reader createNewReader() {
        Reader reader = new Reader();
        reader.setFirstname("Susan");
        reader.setLastname("Sarandon");
        reader.setAccountCreatingDate(LocalDate.now().toString());
        readerService.save(reader);
        return reader;
    }

    private void createNewLoan(LoanProcessDTO loanProcessDTO) {
        Loan newLoan = new Loan();
        newLoan.setLoanDate(loanProcessDTO.getLoanDate());
        newLoan.setReturnDate(loanProcessDTO.getReturnDate());
        newLoan.setReader(readerService.getReaderById(loanProcessDTO.getUser()));
        newLoan.setItem(itemService.getItemById(itemId));
        loanService.save(newLoan);
    }

    private void changeItemStatus(LoanProcessDTO loanProcessDTO) {
        for (Item item : itemService.getItems()) {
            if (item.getBook().getTitle().equals(loanProcessDTO.getBookTitle()) && item.getStatus().equals("toRent")) {
                item.setStatus("rented");
            }
        }
    }

    private boolean isPossibleToRentTheTitle(String title) {
        for (Item item : itemService.getItems()) {
            if (item.getBook().getTitle().equals(title) && item.getStatus().equals("toRent")) {
                itemId = item.getId();
                return true;
            }
        } return false;
    }

    private boolean doesExistReader(Integer id) {
        if (readerService.getAllReaders().stream().anyMatch(reader -> reader.getId().equals(id))) {
            return true;
        } return false;
    }




}
