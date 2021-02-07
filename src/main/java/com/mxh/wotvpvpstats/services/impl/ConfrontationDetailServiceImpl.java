package com.mxh.wotvpvpstats.services.impl;

import com.mxh.wotvpvpstats.domains.*;
import com.mxh.wotvpvpstats.repositories.*;
import com.mxh.wotvpvpstats.services.ConfrontationDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class ConfrontationDetailServiceImpl implements ConfrontationDetailService {

    @Autowired
    FormationCharacterBuiltRepository formationCharacterBuiltRepository;

    @Autowired
    ConfrontationDetailRepository confrontationDetailRepository;
    @Autowired
    CharacterBuiltEquipmentRepository characterBuiltEquipmentRepository;
    @Autowired
    EquipmentConfrontationDetailRepository equipmentConfrontationDetailRepository;
    @Autowired
    CharacterBuiltSupportAbilityRepository characterBuiltSupportAbilityRepository;
    @Autowired
    SupportAbilityConfrontationDetailRepository supportAbilityConfrontationDetailRepository;
    @Autowired
    CharacterBuiltJobRepository characterBuiltJobRepository;
    @Autowired
    CharacterJobConfrontationDetailRepository characterJobConfrontationDetailRepository;
    @Autowired
    OpponentFormationCharacterRepository opponentFormationCharacterRepository;
    @Autowired
    OpponentConfrontationDetailRepository opponentConfrontationDetailRepository;
    @Autowired
    ConfrontationCharacterFormationRepository confrontationCharacterFormationRepository;

    @Override
    @Transactional
    public void createDetails(Confrontation confrontation) {

        List<FormationCharacterBuilt> formationCharacterBuilts = formationCharacterBuiltRepository.findAllByFormationId(confrontation.getFormation().getId());
        formationCharacterBuilts.forEach(formationCharacterBuilt ->{
            var detail = new ConfrontationDetail();
            detail.setConfrontation(confrontation);
            detail.setCharacter(formationCharacterBuilt.getCharacterBuilt().getCharacter());
            detail.setEsper(formationCharacterBuilt.getCharacterBuilt().getEsper());
            detail.setPosition(formationCharacterBuilt.getOrder());
            detail.setVisionCard(formationCharacterBuilt.getCharacterBuilt().getVisionCard());
            detail.setReaction(formationCharacterBuilt.getCharacterBuilt().getReaction());
            confrontationDetailRepository.save(detail);

            var characterBuilt = formationCharacterBuilt.getCharacterBuilt();

            List<CharacterBuiltEquipment> characterBuiltEquipments = characterBuiltEquipmentRepository.findByCharacterBuiltId(characterBuilt.getId());
            characterBuiltEquipments.forEach(characterBuiltEquipment -> {
                var equipmentConfrontationDetail = new EquipmentConfrontationDetail();
                equipmentConfrontationDetail.setConfrontationDetail(detail);
                equipmentConfrontationDetail.setEquipment(characterBuiltEquipment.getEquipment());
                equipmentConfrontationDetailRepository.save(equipmentConfrontationDetail);
            });

            List<CharacterBuiltSupportAbility> supportAbilitiesDetails = characterBuiltSupportAbilityRepository.findByCharacterBuiltId(characterBuilt.getId());
            supportAbilitiesDetails.forEach(abilityDetail ->{
                var supportAbilityConfrontationDetail = new SupportAbilityConfrontationDetail();
                supportAbilityConfrontationDetail.setConfrontationDetail(detail);
                supportAbilityConfrontationDetail.setSupportAbility(abilityDetail.getSupportAbility());
                supportAbilityConfrontationDetailRepository.save(supportAbilityConfrontationDetail);
            });

            List<CharacterBuiltJob> characterBuiltJobs = characterBuiltJobRepository.findByCharacterBuiltId(characterBuilt.getId());
            characterBuiltJobs.forEach(characterBuiltJob -> {
                var characterJobConfrontationDetail = new CharacterJobConfrontationDetail();
                characterJobConfrontationDetail.setConfrontationDetail(detail);
                characterJobConfrontationDetail.setCharacterJob(characterBuiltJob.getCharacterJob());
                characterJobConfrontationDetailRepository.save(characterJobConfrontationDetail);
            });
        });
    }

    @Override
    @Transactional
    public void createOpponentDetails(Confrontation confrontation) {

        var opponentFormation = confrontation.getOpponentFormation();
        List<OpponentFormationCharacter> opponentFormationCharacters = opponentFormationCharacterRepository.findByOpponentFormationId(opponentFormation.getId());
        opponentFormationCharacters.forEach(opponentFormationCharacter -> {
            var opponentConfrontationDetail = new OpponentConfrontationDetail();
            opponentConfrontationDetail.setConfrontation(confrontation);
            opponentConfrontationDetail.setPosition(opponentFormationCharacter.getOrder());
            opponentConfrontationDetail.setName(opponentFormationCharacter.getCharacter().getName());
            opponentConfrontationDetail.setElement(opponentFormationCharacter.getCharacter().getElement());
            opponentConfrontationDetailRepository.save(opponentConfrontationDetail);
        });
    }
}
