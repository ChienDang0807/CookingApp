package com.chiendang.cooking.api.instruction.service;

import com.chiendang.cooking.api.instruction.dto.InstructionRequest;
import com.chiendang.cooking.api.instruction.dto.InstructionResponse;

import java.util.List;

public interface InstructionService {
    List<InstructionResponse> saveAllInstruction(List<InstructionRequest> requests);
    List<InstructionResponse> findAllInstructionByRecipeId (Integer id);
}
