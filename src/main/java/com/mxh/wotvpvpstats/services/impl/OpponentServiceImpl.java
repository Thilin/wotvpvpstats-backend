package com.mxh.wotvpvpstats.services.impl;

import com.mxh.wotvpvpstats.domains.Opponent;
import com.mxh.wotvpvpstats.domains.OpponentFormation;
import com.mxh.wotvpvpstats.domains.OpponentFormationCharacter;
import com.mxh.wotvpvpstats.projections.dtos.OpponentFormationDTO;
import com.mxh.wotvpvpstats.projections.dtos.OpponentsCharactersFoundDTO;
import com.mxh.wotvpvpstats.repositories.CharactersRepository;
import com.mxh.wotvpvpstats.repositories.OpponentFormationCharacterRepository;
import com.mxh.wotvpvpstats.repositories.OpponentFormationRepository;
import com.mxh.wotvpvpstats.repositories.OpponentRepository;
import com.mxh.wotvpvpstats.services.OpponentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OpponentServiceImpl implements OpponentService {

    @Autowired
    OpponentRepository opponentRepository;

    @Autowired
    OpponentFormationRepository opponentFormationRepository;

    @Autowired
    OpponentFormationCharacterRepository opponentFormationCharacterRepository;

    @Autowired
    CharactersRepository charactersRepository;

    @Override
    public OpponentFormationDTO createOpponentFormation(OpponentFormationDTO dto) {
        var opponent = new Opponent();
        opponent.setName(dto.getOpponentName());
        opponentRepository.save(opponent);

        var opponentFormation = new OpponentFormation();
        opponentFormation.setName(opponent.getName());
        opponentFormation.setOpponent(opponent);
        opponentFormationRepository.save(opponentFormation);

        dto.getCharactersPositions().forEach(characterPosition ->{
            var opponentFormationCharacter = new OpponentFormationCharacter();
            opponentFormationCharacter.setOpponentFormation(opponentFormation);
            opponentFormationCharacter.setCharacter(charactersRepository.getOne(characterPosition.getId()));
            opponentFormationCharacter.setOrder(characterPosition.getPosition());
            opponentFormationCharacterRepository.save(opponentFormationCharacter);
        });
        return null;
    }

    @Override
    public List<OpponentsCharactersFoundDTO> listCharactersOpponentTotalFound() {
        return opponentFormationCharacterRepository.countDistinct();
    }
}
