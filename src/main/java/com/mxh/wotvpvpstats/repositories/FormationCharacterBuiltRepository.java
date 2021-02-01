package com.mxh.wotvpvpstats.repositories;

import com.mxh.wotvpvpstats.domains.FormationCharacterBuilt;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface FormationCharacterBuiltRepository extends JpaRepository<FormationCharacterBuilt, Long> {
    List<FormationCharacterBuilt> findAllByFormationId(Long id);
}
