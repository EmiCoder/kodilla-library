package com.crud.kodillalibrary.mapper;

import com.crud.kodillalibrary.domain.dto.ReaderDTO;
import com.crud.kodillalibrary.domain.main.Reader;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class ReaderMapper {

    public Reader mapToReader(final ReaderDTO readerDTO) {
        Reader reader = new Reader();
                reader.setId(readerDTO.getId());
                reader.setFirstname(readerDTO.getFirstname());
                reader.setLastname(readerDTO.getLastname());
                reader.setAccountCreatingDate(readerDTO.getAccountCreatingDate());

        return reader;
    }

    public ReaderDTO mapToReaderDTO(final Reader reader) {
        return new ReaderDTO(reader.getId(),
                             reader.getFirstname(),
                             reader.getLastname(),
                             reader.getAccountCreatingDate());
    }

    public List<ReaderDTO> mapToReaderDTOList(final List<Reader> readerList) {
        return readerList.stream().map(reader -> new ReaderDTO(reader.getId(),
                                                               reader.getFirstname(),
                                                               reader.getLastname(),
                                                               reader.getAccountCreatingDate())
                                       ).collect(Collectors.toList());
    }
}
