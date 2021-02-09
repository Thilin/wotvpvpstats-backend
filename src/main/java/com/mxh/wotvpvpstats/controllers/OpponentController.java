package com.mxh.wotvpvpstats.controllers;

import com.mxh.wotvpvpstats.projections.dtos.OpponentFormationDTO;
import com.mxh.wotvpvpstats.projections.dtos.OpponentsCharactersFoundDTO;
import com.mxh.wotvpvpstats.services.OpponentService;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/opponents")
public class OpponentController {

    @Autowired
    private OpponentService opponentService;

    @ResponseStatus(HttpStatus.ACCEPTED)
    @PostMapping(value = "/formation", consumes = "application/json", produces = "application/json")
    @Operation(summary = "Create opponent formation", description = "Create an oponent formation to fight against")
    public OpponentFormationDTO createOpponentFormation (@RequestBody @Valid OpponentFormationDTO dto){
        return opponentService.createOpponentFormation(dto);
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping(value = "/characters/all", produces = "application/json")
    @Operation(summary = "List opponent teams fought", description = "List all times that a character was found when fighting a opponent team")
    public List<OpponentsCharactersFoundDTO> listCharactersOpponentTotalFound(){
        return opponentService.listCharactersOpponentTotalFound();
    }
}
