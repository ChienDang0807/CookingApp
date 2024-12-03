package com.chiendang.cooking.api.instruction.mapper;

import com.chiendang.cooking.api.instruction.dto.InstructionResponse;
import com.chiendang.cooking.api.instruction.entity.Instruction;
import org.springframework.stereotype.Component;


@Component
public class InstructionMapper {
    public InstructionResponse toInstructionResponse(Instruction instruction) {
        if (instruction == null) {
            return null;
        } else {
            InstructionResponse.InstructionResponseBuilder instructionResponse = InstructionResponse.builder();
            instructionResponse.id(instruction.getId());
            instructionResponse.stepNumber(instruction.getStepNumber());
            instructionResponse.description(instruction.getDescription());

            return instructionResponse.build();
        }
    }

}
