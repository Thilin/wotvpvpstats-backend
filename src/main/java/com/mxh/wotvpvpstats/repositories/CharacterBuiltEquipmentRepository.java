package com.mxh.wotvpvpstats.repositories;

import com.mxh.wotvpvpstats.domains.CharacterBuiltEquipment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CharacterBuiltEquipmentRepository extends JpaRepository<CharacterBuiltEquipment, Long> {
    List<CharacterBuiltEquipment> findByCharacterBuiltId(Long id);
}
