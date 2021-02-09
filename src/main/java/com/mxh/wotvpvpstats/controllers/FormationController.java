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
    @GetMapping(value = "/top/confrontationType/{typeId}", produces = "application/json")
    @Operation(summary = "Show top Formations", description = "Show the top formations with most wins by confrontationTypeId")
    public List<TopWinFormationsDTO> topWinFormationsByConfrontationType(@PathVariable Long typeId){
        return formationService.findTopFormationByConfrontationTypeId(typeId);
    }
}
