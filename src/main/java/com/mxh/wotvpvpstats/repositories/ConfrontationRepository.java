package com.mxh.wotvpvpstats.repositories;

import com.mxh.wotvpvpstats.domains.Confrontation;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ConfrontationRepository extends JpaRepository<Confrontation, Long> {
    List<Confrontation> findByUserId(Long userId);
}
