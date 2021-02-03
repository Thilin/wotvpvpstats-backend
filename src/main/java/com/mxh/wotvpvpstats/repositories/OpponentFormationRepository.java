package com.mxh.wotvpvpstats.repositories;

import com.mxh.wotvpvpstats.domains.OpponentFormation;
import com.mxh.wotvpvpstats.domains.OpponentFormationCharacter;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OpponentFormationRepository extends JpaRepository<OpponentFormation, Long> {
}
