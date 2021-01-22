package com.mxh.wotvpvpstats.controllers;

import com.mxh.wotvpvpstats.projections.dtos.UserDTO;
import com.mxh.wotvpvpstats.services.UserService;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserService userService;

    @ResponseStatus(HttpStatus.ACCEPTED)
    @PostMapping(consumes = "application/json", produces = "application/json")
    @Operation(summary = "Create user", description = "Create a new User of the application")
    public UserDTO create(@RequestBody @Valid UserDTO dto){
        return userService.create(dto);
    }

}
