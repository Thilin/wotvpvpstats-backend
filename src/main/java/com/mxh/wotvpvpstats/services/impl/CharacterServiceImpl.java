package com.mxh.wotvpvpstats.services.impl;

import com.mxh.wotvpvpstats.domains.Character;
import com.mxh.wotvpvpstats.domains.CharacterBuilt;
import com.mxh.wotvpvpstats.domains.CharacterJob;
import com.mxh.wotvpvpstats.exceptions.ObjectNotFoundException;
import com.mxh.wotvpvpstats.projections.dtos.CharacterBuildDTO;
import com.mxh.wotvpvpstats.projections.dtos.CharacterBuildResponseDTO;
import com.mxh.wotvpvpstats.projections.dtos.CharacterDTO;
import com.mxh.wotvpvpstats.projections.dtos.CharacterJobsDTO;
import com.mxh.wotvpvpstats.repositories.CharacterBuiltRepository;
import com.mxh.wotvpvpstats.repositories.CharacterJobRepository;
import com.mxh.wotvpvpstats.repositories.CharactersRepository;
import com.mxh.wotvpvpstats.repositories.UserRepository;
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
    public CharacterBuildResponseDTO build(CharacterBuildDTO dto) {
        var responseDTO = new CharacterBuildResponseDTO();
        var characterBuilt = new CharacterBuilt();
        var character = charactersRepository.findById(dto.getCharacterId()).orElseThrow(() -> new ObjectNotFoundException("Character not found"));
        var user = userRepository.findById(dto.getUserId()).orElseThrow(()-> new ObjectNotFoundException("User not found"));
        characterBuilt.setCharacter(character);
        characterBuilt.setUser(user);
        characterBuilt.setName(dto.getName());
        characterBuiltRepository.save(characterBuilt);
        responseDTO.setCharacter(character.getName());
        responseDTO.setUser(user.getNickName());
        responseDTO.setName(characterBuilt.getName());
        return responseDTO;
    }

    @Override
    public CharacterBuildResponseDTO findCharacterBuiltById(Long id) {
        var dto = new CharacterBuildResponseDTO();
        var characterBuilt = characterBuiltRepository.findById(id).orElseThrow(()-> new ObjectNotFoundException("Character Built not found"));
        dto.setName(characterBuilt.getName());
        dto.setCharacter(characterBuilt.getCharacter().getName());
        dto.setUser(characterBuilt.getUser().getNickName());
        return dto;
    }

    @Override
    public List<CharacterBuildResponseDTO> findAllCharacterBuiltByUserId(Long id) {
        return characterBuiltRepository.findAllCharacterBuiltByUserId(id);
    }
}
