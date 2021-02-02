package com.mxh.wotvpvpstats.services.impl;

import com.mxh.wotvpvpstats.domains.Confrontation;
import com.mxh.wotvpvpstats.domains.FormationCharacterBuilt;
import com.mxh.wotvpvpstats.domains.OpponentFormationCharacter;
import com.mxh.wotvpvpstats.projections.dtos.CharacterBuiltFormationConfrontationDTO;
import com.mxh.wotvpvpstats.projections.dtos.CreateConfrontationDTO;
import com.mxh.wotvpvpstats.projections.dtos.ConfrontationResultDTO;
import com.mxh.wotvpvpstats.projections.dtos.OpponentCompositionDTO;
import com.mxh.wotvpvpstats.repositories.*;
import com.mxh.wotvpvpstats.services.ConfrontationDetailService;
import com.mxh.wotvpvpstats.services.ConfrontationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ConfrontationServiceImpl implements ConfrontationService {

    @Autowired
    UserRepository userRepository;
    @Autowired
    OpponentFormationRepository opponentFormationRepository;
    @Autowired
    MapRepository mapRepository;
    @Autowired
    ConfrontationTypeRepository confrontationTypeRepository;
    @Autowired
    FormationRepository formationRepository;
    @Autowired
    FormationCharacterBuiltRepository formationCharacterBuiltRepository;
    @Autowired
    OpponentFormationCharacterRepository opponentFormationCharacterRepository;

    @Autowired
    ConfrontationRepository confrontationRepository;
    @Autowired
    ConfrontationDetailService confrontationDetailService;

    @Override
    public void create(Long userId, CreateConfrontationDTO dto) {
        var user = userRepository.getOne(userId);
        var opponentFormation = opponentFormationRepository.getOne(dto.getOpponentFormationId());
        var map = mapRepository.getOne(dto.getMapId());
        var confrontationType = confrontationTypeRepository.getOne(dto.getConfrontationTypeId());
        var formation = formationRepository.getOne(dto.getUserFormationId());

        var confrontation = new Confrontation();
        confrontation.setConfrontationType(confrontationType);
        confrontation.setFormation(formation);
        confrontation.setOpponentFormation(opponentFormation);
        confrontation.setMap(map);
        confrontation.setDate(dto.getDate());
        confrontation.setLosses(dto.getLosses());
        confrontation.setDefeats(dto.getDefeats());
        confrontation.setWin(dto.isWin());
        confrontation.setUser(user);
        confrontationRepository.save(confrontation);

        confrontationDetailService.createDetails(confrontation);

    }

    @Override
    public List<ConfrontationResultDTO> findAllResultsByUserId(Long userId) {
        List<ConfrontationResultDTO> resultDTOS = new ArrayList<>();
        List<Confrontation> confrontations = confrontationRepository.findByUserId(userId);
        confrontations.forEach(confrontation -> {
            var result = new ConfrontationResultDTO();
            result.setDate(confrontation.getDate());
            result.setConfrontType(confrontation.getConfrontationType().getDescription());
            result.setDeaths(confrontation.getLosses());
            result.setKills(confrontation.getDefeats());
            result.setMap(confrontation.getMap().getName());
            result.setOpponentName(confrontation.getOpponentFormation().getOpponent().getName());

            var formation = confrontation.getFormation();
            List<CharacterBuiltFormationConfrontationDTO> dtosList = new ArrayList<>();
            List<FormationCharacterBuilt> formationCharacterBuilts = formationCharacterBuiltRepository.findAllByFormationId(formation.getId());
            formationCharacterBuilts.forEach(built ->{
                var dto = new CharacterBuiltFormationConfrontationDTO();
                dto.setName(built.getCharacterBuilt().getCharacter().getName());
                dto.setPosition(built.getOrder());
                dtosList.add(dto);
            });
            result.setComposition(dtosList);

            var opponentFormation = confrontation.getOpponentFormation();
            List<OpponentCompositionDTO> opponentCompositions = new ArrayList<>();
            List<OpponentFormationCharacter> opponentFormationCharacters = opponentFormationCharacterRepository.findAllByOpponentFormationId(opponentFormation.getId());
            opponentFormationCharacters.forEach(opponentCharacter ->{
                var opponentComposition = new OpponentCompositionDTO();
                opponentComposition.setName(opponentCharacter.getCharacter().getName());
                opponentComposition.setPosition(opponentCharacter.getOrder());
                opponentCompositions.add(opponentComposition);
            });
            result.setOpponentComposition(opponentCompositions);

            resultDTOS.add(result);
        });
        return resultDTOS;
    }
}
