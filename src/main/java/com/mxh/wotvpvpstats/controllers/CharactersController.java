package com.mxh.wotvpvpstats.controllers;

import com.mxh.wotvpvpstats.projections.dtos.CharacterBuildDTO;
import com.mxh.wotvpvpstats.projections.dtos.CharacterBuildResponseDTO;
import com.mxh.wotvpvpstats.projections.dtos.CharacterDTO;
import com.mxh.wotvpvpstats.services.CharacterService;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/characters")
public class CharactersController {

    @Autowired
    private CharacterService characterService;

    @ResponseStatus(HttpStatus.OK)
    @GetMapping(value = "/{id}", produces = "application/json")
    @Operation(summary = "find a character by id", description = "Show all informations of one character")
    public CharacterDTO findById(@PathVariable Long id){
        return characterService.findById(id);
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping(value = "/all", produces = "application/json")
    @Operation(summary = "show all Characters", description = "Show all characters informations")
    public List<CharacterDTO> findAll(){
        return characterService.findAll();
    }

    @ResponseStatus(HttpStatus.ACCEPTED)
    @PostMapping(value = "/build", consumes = "application/json", produces = "application/json")
    @Operation(summary = "Build a Character", description = "Build a character equipping him with equipments, visioncards, espers and jobs")
    public CharacterBuildResponseDTO build(@RequestBody @Valid CharacterBuildDTO dto){
        return characterService.build(dto);
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping(value = "/{id}/build", produces = "application/json")
    @Operation(summary = "Find character Built by Id", description = "Find a character that the user created by id")
    public CharacterBuildResponseDTO findCharacterBuiltById(@PathVariable Long id){
        return characterService.findCharacterBuiltById(id);
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping(value = "/{userId}/build/all", produces = "application/json")
    @Operation(summary = "List all", description = "List all character built that the user created")
    public List<CharacterBuildResponseDTO> findAllCharacterBuiltByUserId(@PathVariable Long userId){
        return characterService.findAllCharacterBuiltByUserId(userId);
    }
}
