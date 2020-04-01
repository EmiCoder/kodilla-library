package com.crud.kodillalibrary.controller;


import com.crud.kodillalibrary.domain.dto.ReaderDTO;
import com.crud.kodillalibrary.mapper.ReaderMapper;
import com.crud.kodillalibrary.service.ReaderService;
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
@RequestMapping("/reader")
public class ReaderController {

    @Autowired
    ReaderService service;
    @Autowired
    ReaderMapper mapper;

    @GetMapping
    public ResponseEntity<?> getReaders() throws NotFoundException {
        List<ReaderDTO> list = mapper.mapToReaderDTOList(service.getAllReaders());
        if (!list.isEmpty()) {
            return ResponseEntity.ok().body(list);
        } else {
            throw new NotFoundException("List not found");
        }
    }

    @PostMapping
    public ResponseEntity<?> createReader(@RequestBody ReaderDTO readerDTO) throws URISyntaxException {
        if (readerDTO.getId() != null) {
            return ResponseEntity.badRequest().body("Reader with given id already exists.");
        }

        ReaderDTO result = mapper.mapToReaderDTO(service.save(mapper.mapToReader(readerDTO)));
        return ResponseEntity.created(new URI("/reader/" + result.getId()))
                .headers(HeaderUtil.createEntityCreationAlert("KodillaLibraryApplication", false, "reader", result.getId().toString()))
                .body(result);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getReaderById(@PathVariable Integer id) {
        if (!service.getAllReaders().stream().anyMatch(reader -> reader.getId().equals(id))) {
            return ResponseEntity.badRequest().body("Reader with given id does not exists.");
        }
        return new ResponseEntity<>(mapper.mapToReaderDTO(service.getReaderById(id)), HttpStatus.FOUND);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteReaderById(@PathVariable Integer id) {

        if (!service.getAllReaders().stream().anyMatch(reader -> reader.getId().equals(id))) {
            return ResponseEntity.badRequest().body("Reader with given id: " + id + " does not exists.");
        }
        service.deleteById(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(
                "KodillaLibraryApplication",
                false,
                "item",
                id.toString()))
                .build();
    }

    @PutMapping
    public ResponseEntity<?> updateReader(@RequestBody ReaderDTO readerDTO) {
        boolean exist = service.getAllReaders().stream().anyMatch(reader -> reader.getId().equals(readerDTO.getId()));

        if (readerDTO.getId() == null) {
            return ResponseEntity.badRequest().body("ReaderId was not specified.");
        }
        if (!exist) {
            return ResponseEntity.badRequest().body("Reader with given id does not exists.");
        }

        ReaderDTO result = mapper.mapToReaderDTO(service.save(mapper.mapToReader(readerDTO)));
        return ResponseEntity.ok()
                .headers(HeaderUtil.createEntityUpdateAlert("KodillaLibraryApplication", false, "reader", readerDTO.getId().toString()))
                .body(result);
    }


}
