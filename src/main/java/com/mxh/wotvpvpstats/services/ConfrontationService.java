package com.mxh.wotvpvpstats.services;

import com.mxh.wotvpvpstats.projections.dtos.CreateConfrontationDTO;
import com.mxh.wotvpvpstats.projections.dtos.ConfrontationResultDTO;
import com.mxh.wotvpvpstats.projections.dtos.TopWinFormationsDTO;

import java.util.List;

public interface ConfrontationService {
    void create(Long userId, CreateConfrontationDTO dto);

    List<ConfrontationResultDTO> findAllResultsByUserId(Long userId);

    List<TopWinFormationsDTO> findTopFormationByConfrontationTypeId(Long id);

}
