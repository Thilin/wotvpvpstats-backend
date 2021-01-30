package com.mxh.wotvpvpstats.repositories;

import com.mxh.wotvpvpstats.domains.Opponent;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OpponentRepository extends JpaRepository<Opponent, Long> {
}
