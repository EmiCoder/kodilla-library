package com.crud.kodillalibrary.mapper;

import com.crud.kodillalibrary.domain.dto.ItemDTO;
import com.crud.kodillalibrary.domain.dto.LoanProcessDTO;
import com.crud.kodillalibrary.domain.main.Book;
import com.crud.kodillalibrary.domain.main.Item;
import com.crud.kodillalibrary.domain.main.LoanProcess;
import com.crud.kodillalibrary.domain.main.Reader;
import com.crud.kodillalibrary.service.ReaderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class LoanProcessMapper {

    @Autowired
    private ReaderService readerService;

    public LoanProcess mapToLoan(final LoanProcessDTO loanProcessDTO) {
        LoanProcess loanProcess = new LoanProcess();
                    loanProcess.setId(loanProcessDTO.getId());
                    loanProcess.setBookTitle(loanProcessDTO.getBookTitle());
                    loanProcess.setReturnDate(loanProcessDTO.getReturnDate());
                    loanProcess.setLoanDate(loanProcessDTO.getLoanDate());
        Reader user = new Reader();
                    user.setId(readerService.getReaderById(loanProcessDTO.getUser()).getId());
                    user.setFirstname(readerService.getReaderById(loanProcessDTO.getUser()).getFirstname());
                    user.setLastname(readerService.getReaderById(loanProcessDTO.getUser()).getLastname());
                    user.setAccountCreatingDate(readerService.getReaderById(loanProcessDTO.getUser()).getAccountCreatingDate());
                    loanProcess.setUser(user);
        return loanProcess;
    }

    public LoanProcessDTO mapToLoanPrecessDto(final LoanProcess loanProcess) {
        return new LoanProcessDTO(loanProcess.getId(),
                                    loanProcess.getUser().getId(),
                                    loanProcess.getBookTitle(),
                                    loanProcess.getLoanDate(),
                                    loanProcess.getReturnDate());
    }

    public List<LoanProcessDTO> mapToLoanProcessDtoList(final List<LoanProcess> list) {
        return list.stream()
                .map(loan -> new LoanProcessDTO(
                        loan.getId(),
                        loan.getUser().getId(),
                        loan.getBookTitle(),
                        loan.getLoanDate(),
                        loan.getReturnDate()
                ))
                .collect(Collectors.toList());
    }

}
