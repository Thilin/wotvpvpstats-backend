package com.mxh.wotvpvpstats.repositories;

import com.mxh.wotvpvpstats.domains.CharacterBuiltJob;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CharacterBuiltJobRepository extends JpaRepository<CharacterBuiltJob, Long> {
    List<CharacterBuiltJob> findByCharacterBuiltId(Long id);
}
