package com.mxh.wotvpvpstats.services.impl;

import com.mxh.wotvpvpstats.domains.ConfrontationDetail;
import com.mxh.wotvpvpstats.domains.Confrontation;
import com.mxh.wotvpvpstats.domains.FormationCharacterBuilt;
import com.mxh.wotvpvpstats.repositories.FormationCharacterBuiltRepository;
import com.mxh.wotvpvpstats.services.ConfrontationDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ConfrontationDetailServiceImpl implements ConfrontationDetailService {

    @Autowired
    FormationCharacterBuiltRepository formationCharacterBuiltRepository;

    @Override
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
        });
    }
}
