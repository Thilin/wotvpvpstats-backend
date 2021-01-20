package com.mxh.wotvpvpstats.services.impl;

import com.mxh.wotvpvpstats.projections.dtos.JobDTO;
import com.mxh.wotvpvpstats.repositories.JobRepository;
import com.mxh.wotvpvpstats.services.JobService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class JobServiceImpl implements JobService {

    @Autowired
    JobRepository jobRepository;

    @Override
    public JobDTO findById(Long id) {
        return jobRepository.getById(id);
    }

    @Override
    public List<JobDTO> findAll() {
        return jobRepository.findAllJobs();
    }
}
