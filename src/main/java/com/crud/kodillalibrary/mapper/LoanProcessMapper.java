package com.crud.kodillalibrary.mapper;

import com.crud.kodillalibrary.domain.dto.LoanProcessDTO;
import com.crud.kodillalibrary.domain.main.LoanProcess;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class LoanProcessMapper {

    public LoanProcess mapToLoan(final LoanProcessDTO loanProcessDTO) {
        LoanProcess loanProcess = new LoanProcess();
                    loanProcess.setId(loanProcessDTO.getId());
                    loanProcess.setReader(loanProcess.getReader());
                    loanProcess.setBookTitle(loanProcessDTO.getBookTitle());
                    loanProcess.setReturnDate(loanProcessDTO.getReturnDate());
                    loanProcess.setLoanDate(loanProcessDTO.getLoanDate());
                    return loanProcess;
    }

    public LoanProcessDTO mapToLoanPrecessDto(final LoanProcess loanProcess) {
        return new LoanProcessDTO(loanProcess.getId(),
                                    loanProcess.getReader().getId(),
                                    loanProcess.getBookTitle(),
                                    loanProcess.getLoanDate(),
                                    loanProcess.getReturnDate());
    }

    public List<LoanProcessDTO> mapToLoanProcessDtoList(final List<LoanProcess> list) {
        return list.stream()
                .map(loan -> new LoanProcessDTO(
                        loan.getId(),
                        loan.getReader().getId(),
                        loan.getBookTitle(),
                        loan.getLoanDate(),
                        loan.getReturnDate()
                ))
                .collect(Collectors.toList());
    }

}
