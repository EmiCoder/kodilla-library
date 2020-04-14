package com.crud.kodillalibrary.mapper;

import com.crud.kodillalibrary.domain.dto.LoanProcessDTO;
import com.crud.kodillalibrary.domain.dto.ReturnProcessDTO;
import com.crud.kodillalibrary.domain.main.LoanProcess;
import com.crud.kodillalibrary.domain.main.ReturnProcess;
import com.crud.kodillalibrary.service.LoanProcessService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class ReturnProcessMapper {

    @Autowired
    private LoanProcessService loanProcessService;

    public ReturnProcess mapToReturnProcess(final ReturnProcessDTO returnProcessDTO) {
        return new ReturnProcess(returnProcessDTO.getId(), returnProcessDTO.getReaderId(), returnProcessDTO.getItemId());
    }

    public ReturnProcessDTO mapToReturnProcessDTO(final ReturnProcess returnProcess) {
        return new ReturnProcessDTO(returnProcess.getId(), returnProcess.getReaderId(), returnProcess.getItemId());
    }

    public List<ReturnProcessDTO> mapToReturnProcessDTOList (final List<ReturnProcess> list) {
        return list.stream()
                .map(returnProcess -> new ReturnProcessDTO(
                        returnProcess.getId(),
                        returnProcess.getReaderId(),
                        returnProcess.getItemId()
                        )
                ).collect(Collectors.toList());
    }
}
