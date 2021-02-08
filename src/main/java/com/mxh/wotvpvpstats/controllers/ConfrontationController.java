package com.mxh.wotvpvpstats.controllers;

import com.mxh.wotvpvpstats.projections.dtos.CreateConfrontationDTO;
import com.mxh.wotvpvpstats.projections.dtos.ConfrontationResultDTO;
import com.mxh.wotvpvpstats.projections.dtos.TopWinFormationsDTO;
import com.mxh.wotvpvpstats.services.ConfrontationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/confrontations")
public class ConfrontationController {

    @Autowired
    ConfrontationService confrontationService;

    @ResponseStatus(HttpStatus.ACCEPTED)
    @PostMapping("/create/{userId}")
    public void createConfrontation(@PathVariable Long userId, @RequestBody @Valid CreateConfrontationDTO dto){
        confrontationService.create(userId, dto);
    }

    @GetMapping("/all/{userId}")
    public List<ConfrontationResultDTO> findAllResultsByUserId(@PathVariable Long userId){
        return confrontationService.findAllResultsByUserId(userId);
    }

    @GetMapping("/type/{typeId}/top/formations")
    public List<TopWinFormationsDTO> topWinFormationsByConfrontationType(@PathVariable Long typeId){
        return confrontationService.findTopFormationByConfrontationTypeId(typeId);
    }

}
