package com.mxh.wotvpvpstats.services.impl;

import com.mxh.wotvpvpstats.domains.Confrontation;
import com.mxh.wotvpvpstats.domains.ConfrontationCharacterFormation;
import com.mxh.wotvpvpstats.domains.FormationCharacterBuilt;
import com.mxh.wotvpvpstats.domains.OpponentFormationCharacter;
import com.mxh.wotvpvpstats.projections.dtos.*;
import com.mxh.wotvpvpstats.repositories.*;
import com.mxh.wotvpvpstats.services.ConfrontationDetailService;
import com.mxh.wotvpvpstats.services.ConfrontationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
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

    @Autowired
    ConfrontationCharacterFormationRepository confrontationCharacterFormationRepository;

    @Override
    @Transactional()
    public void create(Long userId, CreateConfrontationDTO dto) {

        var confrontation = new Confrontation();
        var user = userRepository.getOne(userId);
        var opponentFormation = opponentFormationRepository.getOne(dto.getOpponentFormationId());
        var map = mapRepository.getOne(dto.getMapId());
        var confrontationType = confrontationTypeRepository.getOne(dto.getConfrontationTypeId());
        var formation = formationRepository.getOne(dto.getUserFormationId());

        List<FormationCharacterBuilt> formationCharacterBuilts = formationCharacterBuiltRepository.findAllByFormationId(formation.getId());
        var name1 = formationCharacterBuilts.get(0).getCharacterBuilt().getCharacter().getName();
        var name2 = formationCharacterBuilts.get(1).getCharacterBuilt().getCharacter().getName();
        var name3 = formationCharacterBuilts.get(2).getCharacterBuilt().getCharacter().getName();
        var optionalConfrontationCharacterFormation = confrontationCharacterFormationRepository.findByName1AndName2AndName3(name1, name2, name3);
        if(optionalConfrontationCharacterFormation.isEmpty()){
            var confrontationCharacterFormation = new ConfrontationCharacterFormation();
            confrontationCharacterFormation.setName1(name1);
            confrontationCharacterFormation.setName2(name2);
            confrontationCharacterFormation.setName3(name3);
            confrontationCharacterFormationRepository.save(confrontationCharacterFormation);
            confrontation.setConfrontationCharacterFormation(confrontationCharacterFormation);
        }
        else{
            confrontation.setConfrontationCharacterFormation(optionalConfrontationCharacterFormation.get());
        }

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
        confrontationDetailService.createOpponentDetails(confrontation);
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
                dto.setEsper(built.getCharacterBuilt().getEsper().getName());
                dto.setVisionCard(built.getCharacterBuilt().getVisionCard().getName());
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
