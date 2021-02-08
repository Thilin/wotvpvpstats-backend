package com.mxh.wotvpvpstats.repositories;

import com.mxh.wotvpvpstats.domains.ConfrontationCharacterFormation;
import com.mxh.wotvpvpstats.projections.dtos.TopWinFormationsDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface ConfrontationCharacterFormationRepository extends JpaRepository<ConfrontationCharacterFormation, Long> {

    Optional<ConfrontationCharacterFormation> findByName1AndName2AndName3(String name1, String name2, String name3);

    @Query("""
            select new com.mxh.wotvpvpstats.projections.dtos.TopWinFormationsDTO(count(c.id), ccf.name1, ccf.name2, ccf.name3)  from ConfrontationCharacterFormation ccf
            inner join Confrontation c on c.confrontationCharacterFormation.id = ccf.id
            where c.isWin = true and c.confrontationType.id = :id
            group by c.confrontationType.id order by count(c.id) desc 
            """)
    List<TopWinFormationsDTO> findTopFormationByConfrontationTypeId(@Param("id") Long id);
}
