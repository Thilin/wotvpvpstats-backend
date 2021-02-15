package com.mxh.wotvpvpstats.controllers;

import com.mxh.wotvpvpstats.projections.dtos.FormationDTO;
import com.mxh.wotvpvpstats.projections.dtos.TopWinFormationsDTO;
import com.mxh.wotvpvpstats.services.FormationService;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/formations")
public class FormationController {

    @Autowired
    FormationService formationService;

    @ResponseStatus(HttpStatus.ACCEPTED)
    @PostMapping(value = "/{userId}", consumes = "application/json", produces = "application/json")
    @Operation(summary = "Create a new formation", description = "Create a new user's formation sending the characterbuiltId and the position that it belongs")
    public void createFormation(@PathVariable Long userId, @RequestBody @Valid FormationDTO dto){
        formationService.create(userId,dto);
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping(value = "/common/confrontationType/{typeId}", produces = "application/json")
    @Operation(summary = "Show most common Formations", description = "Show the most common formations by confrontationTypeId")
    public List<TopWinFormationsDTO> findMostCommonTeamsByConfrontationId(@PathVariable Long typeId){
        return formationService.findMostCommonTeamsByConfrontationId(typeId);
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping(value = "top/confrontationType/{typeId}", produces = "application/json")
    @Operation(summary = "Show top winrate formations", description = "List top winrate formations by confrontationTypeId")
    public List<TopWinFormationsDTO> findTopWinRateTeamsByConfrontationId(@PathVariable Long typeId){
        return formationService.findTopTeamsWinRateByConfrontationId(typeId);
    }
}
