package com.mxh.wotvpvpstats.repositories;

import com.mxh.wotvpvpstats.domains.ConfrontationCharacterFormation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface ConfrontationCharacterFormationRepository extends JpaRepository<ConfrontationCharacterFormation, Long> {


    @Query("""
            select count(ccf.confrontation.id) from ConfrontationCharacterFormation ccf
            inner join Character  c on c.id = ccf.character.id
            where ccf.character.id in :ids group by ccf.confrontation.id
            """ )
    List<Long> findFormationsByCharacterIds(@Param("ids") List<Long> ids);

    Optional<ConfrontationCharacterFormation> findByName1AndName2AndName3(String name1, String name2, String name3);
}
