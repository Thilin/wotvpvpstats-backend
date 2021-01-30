package com.mxh.wotvpvpstats.repositories;

import com.mxh.wotvpvpstats.domains.OpponentFormationCharacter;
import com.mxh.wotvpvpstats.projections.dtos.OpponentsCharactersFoundDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface OpponentFormationCharacterRepository extends JpaRepository<OpponentFormationCharacter, Long> {

    @Query("""
        select new com.mxh.wotvpvpstats.projections.dtos.OpponentsCharactersFoundDTO(c.name, c.rarity, c.element.description, count(ofc.character.id)) from OpponentFormationCharacter  ofc
        inner join Character c on c.id = ofc.character.id
        inner join Element  e on e.id = c.element.id
        group by ofc.character.id
        order by count(ofc.character.id) desc
    """)
    List<OpponentsCharactersFoundDTO> countDistinct();
}
