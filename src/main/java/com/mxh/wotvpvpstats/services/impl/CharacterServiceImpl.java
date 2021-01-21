package com.mxh.wotvpvpstats.services.impl;

import com.mxh.wotvpvpstats.domains.Character;
import com.mxh.wotvpvpstats.domains.CharacterJob;
import com.mxh.wotvpvpstats.exceptions.ObjectNotFoundException;
import com.mxh.wotvpvpstats.projections.dtos.CharacterDTO;
import com.mxh.wotvpvpstats.projections.dtos.CharacterJobsDTO;
import com.mxh.wotvpvpstats.repositories.CharacterJobRepository;
import com.mxh.wotvpvpstats.repositories.CharactersRepository;
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
}
