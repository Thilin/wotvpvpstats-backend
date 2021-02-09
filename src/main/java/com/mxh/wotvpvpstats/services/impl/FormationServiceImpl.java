package com.mxh.wotvpvpstats.services.impl;

import com.mxh.wotvpvpstats.domains.Formation;
import com.mxh.wotvpvpstats.domains.FormationCharacterBuilt;
import com.mxh.wotvpvpstats.projections.dtos.FormationDTO;
import com.mxh.wotvpvpstats.projections.dtos.TopWinFormationsDTO;
import com.mxh.wotvpvpstats.repositories.*;
import com.mxh.wotvpvpstats.services.FormationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

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
    public List<TopWinFormationsDTO> findTopFormationByConfrontationTypeId(Long id) {
        return confrontationCharacterFormationRepository.findTopFormationByConfrontationTypeId(id);
    }
}
