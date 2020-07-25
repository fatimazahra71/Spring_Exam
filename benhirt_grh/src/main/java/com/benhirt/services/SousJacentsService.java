package com.benhirt.services;

import com.benhirt.entities.SousJacents;
import com.benhirt.exceptions.ResourceNotFoundException;
import org.springframework.data.domain.Page;

import java.util.List;
import java.util.Optional;

public interface SousJacentsService {
    public Page<SousJacents> getAllSousJacents(Optional<Integer> pageNo, Integer pageSize, String sortBy);
    public List<SousJacents> getAllSousJacents();

    SousJacents findById(long id) throws ResourceNotFoundException;

    void save(SousJacents employee);

    void deleteById(long id);
}
