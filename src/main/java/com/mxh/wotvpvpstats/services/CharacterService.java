package com.mxh.wotvpvpstats.services;

import com.mxh.wotvpvpstats.projections.dtos.CharacterDTO;

import java.util.List;

public interface CharacterService {
    CharacterDTO findById(Long id);
    List<CharacterDTO> findAll();
}
