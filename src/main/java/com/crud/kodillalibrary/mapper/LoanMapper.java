package com.crud.kodillalibrary.mapper;

import com.crud.kodillalibrary.domain.dto.LoanDTO;
import com.crud.kodillalibrary.domain.main.Loan;
import com.crud.kodillalibrary.service.ItemService;
import com.crud.kodillalibrary.service.ReaderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class LoanMapper {

    @Autowired
    ReaderService readerService;
    @Autowired
    ItemService itemService;

    public Loan mapToLoan(final LoanDTO loanDTO) {
        Loan loan = new Loan();
                loan.setId(loanDTO.getId());
                loan.setLoanDate(loanDTO.getLoanDate());
                loan.setReturnDate(loanDTO.getReturnDate());
                loan.setReader(readerService.getReaderById(loanDTO.getReaderId()));
                loan.setItem(itemService.getItemById(loanDTO.getItemId()));
        return loan;
    }

    public LoanDTO mapToLoanDTO(final Loan loan) {
        return new LoanDTO(loan.getId(),
                           loan.getLoanDate(),
                           loan.getReturnDate(),
                           loan.getReader().getId(),
                           loan.getItem().getId());
    }

    public List<LoanDTO> mapToLoanDTOList(List<Loan> loanList) {
        return loanList.stream()
                        .map(loan -> new LoanDTO(loan.getId(),
                                loan.getLoanDate(),
                                loan.getReturnDate(),
                                loan.getReader().getId(),
                                loan.getItem().getId()))
                        .collect(Collectors.toList());
    }
}
