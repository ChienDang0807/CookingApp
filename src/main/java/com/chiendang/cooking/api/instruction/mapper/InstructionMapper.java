package com.chiendang.cooking.api.instruction.mapper;

import com.chiendang.cooking.api.instruction.dto.InstructionRequest;
import com.chiendang.cooking.api.instruction.dto.InstructionResponse;
import com.chiendang.cooking.api.instruction.entity.Instruction;
import com.chiendang.cooking.api.recipe.entity.Recipe;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface InstructionMapper {
    InstructionResponse toInstructionResponse(Instruction instruction);


}
