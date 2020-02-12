package com.crud.kodillalibrary.controller;


import com.crud.kodillalibrary.domain.dto.ReaderDTO;
import com.crud.kodillalibrary.mapper.ReaderMapper;
import com.crud.kodillalibrary.service.ReaderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


import java.util.List;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping("/reader")
public class ReaderController {

    @Autowired
    ReaderService readerService;
    @Autowired
    ReaderMapper readerMapper;

    @RequestMapping(method=RequestMethod.GET, value="getReaders")
    List<ReaderDTO> getReaders() {
        return readerMapper.mapToReaderDTOList(readerService.getAllReaders());
    }

    @RequestMapping(method=RequestMethod.GET, value="getReaderById")
    public ReaderDTO getReaderById(@RequestParam Integer id) {
        return readerMapper.mapToReaderDTO(readerService.getReaderById(id));
    }

    @RequestMapping(method=RequestMethod.DELETE, value="deleteReaderById")
    public void deleteReaderById(@RequestParam Integer id) {
        readerService.deleteById(id);
    }

    @RequestMapping(method=RequestMethod.PUT, value="updateReader")
    public ReaderDTO updateReader(@RequestBody ReaderDTO readerDTO) {
        return readerMapper.mapToReaderDTO(readerService.save(readerMapper.mapToReader(readerDTO)));
    }

    @RequestMapping(method=RequestMethod.POST, value="createReader", consumes=APPLICATION_JSON_VALUE)
    public void createReader(@RequestBody ReaderDTO readerDTO) {
        readerService.save(readerMapper.mapToReader(readerDTO));
    }
}
