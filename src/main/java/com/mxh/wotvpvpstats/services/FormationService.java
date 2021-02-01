package com.mxh.wotvpvpstats.services;

import com.mxh.wotvpvpstats.projections.dtos.FormationDTO;

public interface FormationService {
    void create(Long userId, FormationDTO dto);
}
