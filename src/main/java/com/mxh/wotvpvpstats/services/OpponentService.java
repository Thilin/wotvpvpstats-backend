package com.mxh.wotvpvpstats.services;

import com.mxh.wotvpvpstats.projections.dtos.OpponentFormationDTO;
import com.mxh.wotvpvpstats.projections.dtos.OpponentsCharactersFoundDTO;

import java.util.List;

public interface OpponentService {
    OpponentFormationDTO createOpponentFormation(OpponentFormationDTO dto);

    List<OpponentsCharactersFoundDTO> listCharactersOpponentTotalFound();
}
