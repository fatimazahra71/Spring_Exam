package com.benhirt.repositories;

import com.benhirt.entities.SousJacents;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SousJacentsRepository extends JpaRepository<SousJacents, Long> {

    Page<SousJacents> findAll(Pageable pageable);

}
