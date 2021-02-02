package com.mxh.wotvpvpstats.controllers;

import com.mxh.wotvpvpstats.projections.dtos.CharacterBuildDTO;
import com.mxh.wotvpvpstats.projections.dtos.CharacterBuildResponseDTO;
import com.mxh.wotvpvpstats.services.CharacterService;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/character/builder")
public class CharacterBuilderController {

    @Autowired
    private CharacterService characterService;

    @ResponseStatus(HttpStatus.ACCEPTED)
    @PostMapping(value = "/build", consumes = "application/json", produces = "application/json")
    @Operation(summary = "Build a Character", description = "Build a character equipping him with equipments, visioncards, espers and jobs")
    public void build(@RequestBody @Valid CharacterBuildDTO dto){
        characterService.build(dto);
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping(value = "/{id}/build", produces = "application/json")
    @Operation(summary = "Find character Built by Id", description = "Find a character that the user created by id")
    public CharacterBuildResponseDTO findCharacterBuiltById(@PathVariable Long id){
        return characterService.findCharacterBuiltById(id);
    }
}
