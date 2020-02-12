package com.crud.kodillalibrary.controller;
import com.crud.kodillalibrary.domain.dto.LoanDTO;
import com.crud.kodillalibrary.mapper.LoanMapper;
import com.crud.kodillalibrary.service.LoanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping("/loan")
public class LoanController {

    @Autowired
    LoanMapper loanMapper;
    @Autowired
    LoanService loanService;

    @RequestMapping(method=RequestMethod.GET, value="getLoans")
    List<LoanDTO> getLoans() {
        return loanMapper.mapToLoanDTOList(loanService.getLoans());
    }

    @RequestMapping(method=RequestMethod.GET, value="getLoanById")
    public LoanDTO getLoanById(@RequestParam Integer id) {
        return loanMapper.mapToLoanDTO(loanService.getLoanById(id));
    }

    @RequestMapping(method=RequestMethod.DELETE, value="deleteLoanById")
    public void deleteLoanById(@RequestParam Integer id) {
        loanService.deleteLoanById(id);
    }

    @RequestMapping(method=RequestMethod.PUT, value="updateLoan")
    public LoanDTO updateLoan(@RequestBody LoanDTO loanDTO) {
       return loanMapper.mapToLoanDTO(loanService.updateLoanDateDetails(loanDTO.getId(), loanDTO.getLoanDate(), loanDTO.getReturnDate()));
    }

    @RequestMapping(method=RequestMethod.POST, value="createLoan", consumes=APPLICATION_JSON_VALUE)
    public void createLoan(@RequestBody LoanDTO loanDTO) {
        loanService.save(loanMapper.mapToLoan(loanDTO));

    }
}
