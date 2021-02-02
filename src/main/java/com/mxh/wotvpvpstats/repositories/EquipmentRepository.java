package com.mxh.wotvpvpstats.repositories;

import com.mxh.wotvpvpstats.domains.Equipment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EquipmentRepository extends JpaRepository<Equipment, Long> {
}
