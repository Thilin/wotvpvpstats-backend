package com.mxh.wotvpvpstats.services;

import com.mxh.wotvpvpstats.projections.dtos.JobDTO;
import com.mxh.wotvpvpstats.projections.view.JobView;

import java.util.List;

public interface JobService {

    JobDTO findById(Long id);
    List<JobDTO> findAll();
}
