package com.mxh.wotvpvpstats.repositories;

import com.mxh.wotvpvpstats.domains.CharacterBuilt;
import com.mxh.wotvpvpstats.projections.dtos.CharacterBuildResponseDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface CharacterBuiltRepository extends JpaRepository<CharacterBuilt, Long> {
//    @Query("""
//            select new com.mxh.wotvpvpstats.projections.dtos.CharacterBuildResponseDTO(cb.name, us.nickName, c.name) from CharacterBuilt cb
//            inner join User us on cb.user.id = us.id
//            inner join Character c on cb.character.id = c.id
//            where us.id = :id
//            """)
//    List<CharacterBuildResponseDTO> findAllCharacterBuiltByUserId(@Param("id") Long id);
}
