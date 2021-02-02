package com.mxh.wotvpvpstats.services.impl;

import com.mxh.wotvpvpstats.domains.*;
import com.mxh.wotvpvpstats.domains.Character;
import com.mxh.wotvpvpstats.exceptions.ObjectNotFoundException;
import com.mxh.wotvpvpstats.projections.dtos.*;
import com.mxh.wotvpvpstats.repositories.*;
import com.mxh.wotvpvpstats.services.CharacterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CharacterServiceImpl implements CharacterService {

    @Autowired
    CharactersRepository charactersRepository;

    @Autowired
    CharacterJobRepository characterJobRepository;

    @Autowired
    UserRepository userRepository;
    @Autowired
    VisionCardRepository visionCardRepository;
    @Autowired
    EsperRepository esperRepository;
    @Autowired
    CharacterBuiltEquipmentRepository characterBuiltEquipmentRepository;
    @Autowired
    EquipmentRepository equipmentRepository;
    @Autowired
    ReactionRepository reactionRepository;
    @Autowired
    SupportAbilityRepository supportAbilityRepository;
    @Autowired
    CharacterBuiltSupportAbilityRepository characterBuiltSupportAbilityRepository;
    @Autowired
    JobRepository jobRepository;
    @Autowired
    CharacterBuiltJobRepository characterBuiltJobRepository;

    @Autowired
    CharacterBuiltRepository characterBuiltRepository;

    @Override
    public CharacterDTO findById(Long id) {
        var dto = new CharacterDTO();
        List<CharacterJobsDTO> jobsDTO = new ArrayList<>();
        var character = charactersRepository.findById(id).orElseThrow(() -> new ObjectNotFoundException("Character not found"));
        List<CharacterJob> characterJobs = characterJobRepository.findByCharacterId(character.getId());

        dto.setName(character.getName());
        dto.setImage(character.getImage());
        dto.setRarity(character.getRarity());
        dto.setElement(character.getElement().getDescription());
        characterJobs.forEach(characterJob -> {
            var job = new CharacterJobsDTO();
            job.setMain(characterJob.isMain());
            job.setName(characterJob.getJob().getDescription());
            jobsDTO.add(job);
        });
        dto.setJobs(jobsDTO);
        return dto;
    }

    @Override
    public List<CharacterDTO> findAll() {
        List<CharacterDTO> charactersDTO = new ArrayList<>();
        List<Character> characters = charactersRepository.findAll();

        characters.forEach(character -> {
            var characterDTO = new CharacterDTO();
            List<CharacterJobsDTO> jobsDTOS = new ArrayList<>();
            characterDTO.setElement(character.getElement().getDescription());
            characterDTO.setName(character.getName());
            characterDTO.setRarity(character.getRarity());
            characterDTO.setImage(character.getImage());
            List<CharacterJob> characterJobs = characterJobRepository.findByCharacterId(character.getId());
            characterJobs.forEach(characterJob -> {
                var jobDTO = new CharacterJobsDTO();
                jobDTO.setMain(characterJob.isMain());
                jobDTO.setName(characterJob.getJob().getDescription());
                jobsDTOS.add(jobDTO);
            });
            characterDTO.setJobs(jobsDTOS);
            charactersDTO.add(characterDTO);
        });
        return charactersDTO;
    }

    @Override
    public void build(CharacterBuildDTO dto) {
        var character = charactersRepository.findById(dto.getCharacterId()).orElseThrow(() -> new ObjectNotFoundException("Character not found"));
        var user = userRepository.findById(dto.getUserId()).orElseThrow(()-> new ObjectNotFoundException("User not found"));
        var visionCard = visionCardRepository.getOne(dto.getVisionCardId());
        var esper = esperRepository.getOne(dto.getEsperId());
        var reaction = reactionRepository.getOne(dto.getReactionId());

        var characterBuilt = new CharacterBuilt();
        characterBuilt.setCharacter(character);
        characterBuilt.setUser(user);
        characterBuilt.setName(dto.getName());
        characterBuilt.setVisionCard(visionCard);
        characterBuilt.setEsper(esper);
        characterBuilt.setReaction(reaction);
        characterBuiltRepository.save(characterBuilt);

        dto.getEquipmentsDTO().forEach(equipmentDTO ->{
            var characterBuiltEquipment = new CharacterBuiltEquipment();

            var equipment = equipmentRepository.getOne(equipmentDTO.getEquipmentId());
            characterBuiltEquipment.setCharacterBuilt(characterBuilt);
            characterBuiltEquipment.setPlus(equipmentDTO.getPlus());
            characterBuiltEquipment.setEquipment(equipment);

            characterBuiltEquipmentRepository.save(characterBuiltEquipment);
        });

        dto.getSupportAbilitiesId().forEach(id ->{
            var supportAbility = supportAbilityRepository.getOne(id);

            var characterBuiltSupportAbility = new CharacterBuiltSupportAbility();
            characterBuiltSupportAbility.setCharacterBuilt(characterBuilt);
            characterBuiltSupportAbility.setSupportAbility(supportAbility);
            characterBuiltSupportAbilityRepository.save(characterBuiltSupportAbility);
        });

        dto.getJobsId().forEach(id ->{
            var job = jobRepository.getOne(id);

            var characterBuiltJob = new CharacterBuiltJob();
            characterBuiltJob.setCharacterBuilt(characterBuilt);
            characterBuiltJob.setCharacterJob(characterJobRepository.findByJobId(id));
            characterBuiltJobRepository.save(characterBuiltJob);
        });
    }

    @Override
    public CharacterBuildResponseDTO findCharacterBuiltById(Long id) {
        var dto = new CharacterBuildResponseDTO();
        var characterBuilt = characterBuiltRepository.findById(id).orElseThrow(()-> new ObjectNotFoundException("Character Built not found"));
        dto.setName(characterBuilt.getName());
        dto.setCharacter(characterBuilt.getCharacter().getName());
        dto.setEsper(characterBuilt.getEsper().getName());
        dto.setVisionCard(characterBuilt.getVisionCard().getName());
        dto.setReaction(characterBuilt.getReaction().getDescription());

        List<CharacterBuiltEquipmentResponseDTO> equipmentsDTO = new ArrayList<>();
        List<CharacterBuiltEquipment> characterBuiltEquipments = characterBuiltEquipmentRepository.findByCharacterBuiltId(characterBuilt.getId());
        characterBuiltEquipments.forEach(characterBuiltEquipment -> {
            var equipmentDTO = new CharacterBuiltEquipmentResponseDTO();
            equipmentDTO.setName(characterBuiltEquipment.getEquipment().getName());
            equipmentDTO.setPlus(characterBuiltEquipment.getPlus());
            equipmentsDTO.add(equipmentDTO);
        });
        dto.setEquipments(equipmentsDTO);

        List<String> supportAbilities = new ArrayList<>();
        List<CharacterBuiltSupportAbility> characterBuiltSupportAbilities = characterBuiltSupportAbilityRepository.findByCharacterBuiltId(characterBuilt.getId());
        characterBuiltSupportAbilities.forEach(CharacterAbility ->{
            String ability = CharacterAbility.getSupportAbility().getDescription();
            supportAbilities.add(ability);
        });
        dto.setSupportAbilities(supportAbilities);
        return dto;
    }

//    @Override
//    public List<CharacterBuildResponseDTO> findAllCharacterBuiltByUserId(Long id) {
//        return characterBuiltRepository.findAllCharacterBuiltByUserId(id);
//    }
}
