package com.crud.kodillalibrary.controller;
import com.crud.kodillalibrary.domain.dto.LoanDTO;
import com.crud.kodillalibrary.domain.main.Book;
import com.crud.kodillalibrary.domain.main.Item;
import com.crud.kodillalibrary.domain.main.Reader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/loan")
public class LoanController {

    @RequestMapping(method=RequestMethod.GET, value="getLoans")
    List<LoanDTO> getLoans() {
        return new ArrayList<>();
    }

    @RequestMapping(method=RequestMethod.GET, value="getLoanById")
    public LoanDTO getLoanById(Integer id) {
        Reader reader = new Reader();
                reader.setFirstname("Emilia");
                reader.setLastname("Traczyk");
                reader.setAccountCreatingDate("2010.01.23");

        Item item = new Item();
                item.setStatus("Lost");
                Book book = new Book();
                        book.setTitle("Title");
                        book.setAuthor("Author");
                        book.setPublished("2012");
                item.setBook(book);

        return new LoanDTO(1,
                            LocalDate.of(2012, 01, 12),
                            LocalDate.of(2012, 01, 22),
                            reader,
                            item);
    }

    @RequestMapping(method=RequestMethod.DELETE, value="deleteLoanById")
    public void deleteLoanById(Integer id) {

    }

    @RequestMapping(method=RequestMethod.PUT, value="updateLoan")
    public LoanDTO updateLoan(LoanDTO loanDTO) {
        Reader reader = new Reader();
        reader.setFirstname("Emilia");
        reader.setLastname("Traczyk");
        reader.setAccountCreatingDate("2010.01.23");

        Item item = new Item();
        item.setStatus("Lost");
        Book book = new Book();
        book.setTitle("Title");
        book.setAuthor("Author");
        book.setPublished("2012");
        item.setBook(book);

        return new LoanDTO(1,
                LocalDate.of(2012, 01, 12),
                LocalDate.of(2012, 01, 22),
                reader,
                item);
    }

    @RequestMapping(method=RequestMethod.POST, value="createLoan")
    public void createLoan(LoanDTO loanDTO) {

    }
}
