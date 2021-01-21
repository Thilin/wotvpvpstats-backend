package com.mxh.wotvpvpstats.controllers;

import com.mxh.wotvpvpstats.projections.dtos.JobDTO;
import com.mxh.wotvpvpstats.services.JobService;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/jobs")
public class JobController {

    @Autowired
    private JobService jobService;

    @ResponseStatus(HttpStatus.OK)
    @GetMapping(value = "/{id}", produces = "application/json")
    @Operation(description = "Find a job by Id", summary = "Find by Id")
    public JobDTO findById(@PathVariable Long id){
        return jobService.findById(id);
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping(value = "/all", produces = "application/json")
    @Operation(description = "Show all jobs", summary = "Find All")
    public List<JobDTO> listAllJobs(){
        return jobService.findAll();
    }

}
