package com.mxh.wotvpvpstats.repositories;

import com.mxh.wotvpvpstats.domains.CharacterBuiltSupportAbility;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CharacterBuiltSupportAbilityRepository extends JpaRepository<CharacterBuiltSupportAbility, Long> {
    List<CharacterBuiltSupportAbility> findByCharacterBuiltId(Long id);
}
