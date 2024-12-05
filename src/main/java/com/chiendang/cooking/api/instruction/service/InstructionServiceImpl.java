package com.chiendang.cooking.api.instruction.service;

import com.chiendang.cooking.api.instruction.dto.InstructionRequest;
import com.chiendang.cooking.api.instruction.dto.InstructionResponse;
import com.chiendang.cooking.api.instruction.entity.Instruction;
import com.chiendang.cooking.api.instruction.mapper.InstructionMapper;
import com.chiendang.cooking.api.instruction.repository.InstructionRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class InstructionServiceImpl implements InstructionService{
    InstructionRepository instructionRepository;
    InstructionMapper instructionMapper;


    @Override
    public List<InstructionResponse> saveAllInstruction(List<InstructionRequest> requests) {
        if (requests == null || requests.isEmpty()) {
            throw new IllegalArgumentException("The list of ingredients is null or empty.");
        }

        List<Instruction> instructionSave = instructionMapper.toListInstruction(requests);

        List<Instruction> savedInstructions = instructionRepository.saveAll(instructionSave);

        return instructionMapper.toListInstructionResponse(savedInstructions);
    }

    @Override
    public List<InstructionResponse> findAllInstructionByRecipeId (Integer id){
        return instructionRepository.findAllByRecipeId(id).stream().map(instructionMapper::toInstructionResponse).toList();
    }


}
