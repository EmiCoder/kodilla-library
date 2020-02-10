package com.crud.kodillalibrary.controller;


import com.crud.kodillalibrary.domain.dto.ReaderDTO;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/reader")
public class ReaderController {

    @RequestMapping(method=RequestMethod.GET, value="getReaders")
    List<ReaderDTO> getReaders() {
        return new ArrayList<>();
    }

    @RequestMapping(method=RequestMethod.GET, value="getReaderById")
    public ReaderDTO getReaderById(Integer id) {
        return new ReaderDTO(1, "Emilia", "Traczyk", "01.01.2016");
    }

    @RequestMapping(method=RequestMethod.DELETE, value="deleteReaderById")
    public void deleteReaderById(Integer id) {

    }

    @RequestMapping(method=RequestMethod.PUT, value="updateReader")
    public ReaderDTO updateReader(ReaderDTO readerDTO) {
        return new ReaderDTO(1, "Updated firstname", "Updated lastname", "Updated account");
    }

    @RequestMapping(method=RequestMethod.POST, value="createReader")
    public void createReader(ReaderDTO ReaderDTO) {

    }
}
