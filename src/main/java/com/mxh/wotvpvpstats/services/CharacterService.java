package com.mxh.wotvpvpstats.services;

import com.mxh.wotvpvpstats.projections.dtos.CharacterBuildDTO;
import com.mxh.wotvpvpstats.projections.dtos.CharacterBuildResponseDTO;
import com.mxh.wotvpvpstats.projections.dtos.CharacterDTO;

import java.util.List;

public interface CharacterService {
    CharacterDTO findById(Long id);
    List<CharacterDTO> findAll();

    CharacterBuildResponseDTO build(CharacterBuildDTO dto);

    CharacterBuildResponseDTO findCharacterBuiltById(Long id);

    List<CharacterBuildResponseDTO> findAllCharacterBuiltByUserId(Long id);
}
