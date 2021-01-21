package com.mxh.wotvpvpstats.repositories;

import com.mxh.wotvpvpstats.domains.CharacterJob;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CharacterJobRepository extends JpaRepository<CharacterJob, Long> {
    List<CharacterJob> findByCharacterId(Long id);
}
