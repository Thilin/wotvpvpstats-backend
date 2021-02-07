package com.mxh.wotvpvpstats.services;

import com.mxh.wotvpvpstats.projections.dtos.CreateConfrontationDTO;
import com.mxh.wotvpvpstats.projections.dtos.ConfrontationResultDTO;

import java.util.List;

public interface ConfrontationService {
    void create(Long userId, CreateConfrontationDTO dto);

    List<ConfrontationResultDTO> findAllResultsByUserId(Long userId);

    List<Long> findFormationTimes(List<Long> ids);
}
