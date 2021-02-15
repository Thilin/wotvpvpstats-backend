package com.mxh.wotvpvpstats.services.impl;

import com.mxh.wotvpvpstats.domains.Confrontation;
import com.mxh.wotvpvpstats.domains.ConfrontationCharacterFormation;
import com.mxh.wotvpvpstats.domains.Formation;
import com.mxh.wotvpvpstats.domains.FormationCharacterBuilt;
import com.mxh.wotvpvpstats.exceptions.NoConfrontationException;
import com.mxh.wotvpvpstats.projections.dtos.FormationDTO;
import com.mxh.wotvpvpstats.projections.dtos.TopWinFormationsDTO;
import com.mxh.wotvpvpstats.repositories.*;
import com.mxh.wotvpvpstats.services.FormationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class FormationServiceImpl implements FormationService {

    @Autowired
    FormationRepository formationRepository;
    @Autowired
    CharacterBuiltRepository characterBuiltRepository;
    @Autowired
    FormationCharacterBuiltRepository formationCharacterBuiltRepository;
    @Autowired
    ConfrontationCharacterFormationRepository confrontationCharacterFormationRepository;
    @Autowired
    ConfrontationRepository confrontationRepository;

    @Autowired
    UserRepository userRepository;

    @Override
    public void create(Long userId, FormationDTO dto) {
        var user = userRepository.getOne(userId);



        var formation = new Formation();
        formation.setAttack(dto.isAttack());
        formation.setName(dto.getName());
        formation.setUser(user);
        formationRepository.save(formation);

        dto.getCharactersPositions().forEach(characterPosition ->{
            var formationCharacterBuilt = new FormationCharacterBuilt();
            formationCharacterBuilt.setFormation(formation);
            formationCharacterBuilt.setOrder(characterPosition.getPosition());
            formationCharacterBuilt.setCharacterBuilt(characterBuiltRepository.getOne(characterPosition.getCharacterBuiltId()));
            formationCharacterBuiltRepository.save(formationCharacterBuilt);
        });
    }

    @Override
    public List<TopWinFormationsDTO> findMostCommonTeamsByConfrontationId(Long id) {
        return confrontationCharacterFormationRepository.findMostCommonTeamsByConfrontationId(id);
    }

    @Override
    public List<TopWinFormationsDTO> findTopTeamsWinRateByConfrontationId(Long id) {
        List<TopWinFormationsDTO> topWinFormations = new ArrayList<>();
        List<ConfrontationCharacterFormation> formations = confrontationCharacterFormationRepository.findAll();
        formations.forEach(formation ->{
            var formationDTO = new TopWinFormationsDTO();
            List<Confrontation> confrontations= confrontationRepository.findByConfrontationTypeIdAndConfrontationCharacterFormationId(id, formation.getId());
            List<Confrontation> confrontationsWon = confrontations.stream().filter(Confrontation::isWin).collect(Collectors.toList());
            if(confrontations.isEmpty())
                throw new NoConfrontationException("No confrontation registered");
            else
                formationDTO.setTotal((long) (confrontationsWon.size()/confrontations.size())*100);
            formationDTO.setName1(formation.getName1());
            formationDTO.setName2(formation.getName2());
            formationDTO.setName3(formation.getName3());
            topWinFormations.add(formationDTO);
        });
        return topWinFormations;
    }
}
