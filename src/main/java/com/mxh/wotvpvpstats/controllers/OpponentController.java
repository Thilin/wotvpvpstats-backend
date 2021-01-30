package com.mxh.wotvpvpstats.controllers;

import com.mxh.wotvpvpstats.projections.dtos.OpponentFormationDTO;
import com.mxh.wotvpvpstats.projections.dtos.OpponentsCharactersFoundDTO;
import com.mxh.wotvpvpstats.services.OpponentService;
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
    public OpponentFormationDTO createOpponentFormation (@RequestBody @Valid OpponentFormationDTO dto){
        return opponentService.createOpponentFormation(dto);
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/characters/all")
    public List<OpponentsCharactersFoundDTO> listCharactersOpponentTotalFound(){
        return opponentService.listCharactersOpponentTotalFound();
    }
}
