package com.chiendang.cooking.api.instruction.service;

import com.chiendang.cooking.api.instruction.dto.InstructionResponse;
import com.chiendang.cooking.api.instruction.mapper.InstructionMapper;
import com.chiendang.cooking.api.instruction.repository.InstructionRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class InstructionService {
    InstructionRepository instructionRepository;
    InstructionMapper instructionMapper;



    public List<InstructionResponse> findAllInstructionByRecipeId (Integer id){
        return instructionRepository.findAllByRecipeId(id).stream().map(instructionMapper::toInstructionResponse).toList();
    }


}
