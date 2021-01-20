package com.mxh.wotvpvpstats.repositories;

import com.mxh.wotvpvpstats.domains.Job;
import com.mxh.wotvpvpstats.projections.dtos.JobDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface JobRepository  extends JpaRepository<Job, Long> {

    @Query("SELECT new com.mxh.wotvpvpstats.projections.dtos.JobDTO(j.description, j.image) from Job j where j.id = :id")
    JobDTO getById(@Param("id") Long id);

    @Query("select new com.mxh.wotvpvpstats.projections.dtos.JobDTO(j.description, j.image) from Job j")
    List<JobDTO> findAllJobs();
}
