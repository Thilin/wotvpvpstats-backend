package com.mxh.wotvpvpstats.services;

import com.mxh.wotvpvpstats.projections.dtos.FormationDTO;
import com.mxh.wotvpvpstats.projections.dtos.TopWinFormationsDTO;

import java.util.List;

public interface FormationService {
    void create(Long userId, FormationDTO dto);

    List<TopWinFormationsDTO> findMostCommonTeamsByConfrontationId(Long id);

    List<TopWinFormationsDTO> findTopTeamsWinRateByConfrontationId(Long id);
}
