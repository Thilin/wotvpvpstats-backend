package com.mxh.wotvpvpstats.repositories;

import com.mxh.wotvpvpstats.domains.Reaction;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReactionRepository extends JpaRepository<Reaction, Long> {
}
