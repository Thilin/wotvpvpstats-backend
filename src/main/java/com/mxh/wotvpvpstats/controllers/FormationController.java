package com.mxh.wotvpvpstats.controllers;

import com.mxh.wotvpvpstats.projections.dtos.FormationDTO;
import com.mxh.wotvpvpstats.services.FormationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/formations")
public class FormationController {

    @Autowired
    FormationService formationService;

    @PostMapping("/{userId}")
    public void createFormation(@PathVariable Long userId, @RequestBody @Valid FormationDTO dto){
        formationService.create(userId,dto);
    }
}
