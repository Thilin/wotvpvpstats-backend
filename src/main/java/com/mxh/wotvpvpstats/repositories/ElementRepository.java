package com.mxh.wotvpvpstats.repositories;

import com.mxh.wotvpvpstats.domains.Element;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ElementRepository extends JpaRepository<Element, Long> {
}
