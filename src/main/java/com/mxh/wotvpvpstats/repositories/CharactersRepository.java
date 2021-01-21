package com.mxh.wotvpvpstats.repositories;

import com.mxh.wotvpvpstats.domains.Character;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CharactersRepository extends JpaRepository<Character, Long> {
}
