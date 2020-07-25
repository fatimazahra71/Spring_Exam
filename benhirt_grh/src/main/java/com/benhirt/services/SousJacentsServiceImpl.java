package com.benhirt.services;

import com.benhirt.entities.SousJacents;
import com.benhirt.exceptions.ResourceNotFoundException;
import com.benhirt.repositories.SousJacentsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.sql.Timestamp;
import java.util.List;
import java.util.Optional;

@Service
public class SousJacentsServiceImpl implements SousJacentsService{
    @Autowired
    private SousJacentsRepository sousJacentsRepository;

    @Override
    @Transactional
    public Page<SousJacents> getAllSousJacents(Optional<Integer> pageNo, Integer pageSize, String sortBy) {
        if(pageNo.isPresent()){
            Pageable paging = PageRequest.of(pageNo.get(), pageSize, Sort.by(sortBy));
            return sousJacentsRepository.findAll(paging);
        }else{
            Pageable paging = PageRequest.of(0, pageSize, Sort.by(sortBy));
            return sousJacentsRepository.findAll(paging);
        }
    }

    @Override
    public List<SousJacents> getAllSousJacents() {
        return sousJacentsRepository.findAll();
    }

    @Override
    @Transactional
    public SousJacents findById(long id) throws ResourceNotFoundException {
        return sousJacentsRepository.findById(id).orElseThrow(
                ()->new ResourceNotFoundException(id)
        );
    }

    @Override
    @Transactional
    public void save(SousJacents sousJacents) {
        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
        //sousJacents.setCreated(timestamp);
        sousJacentsRepository.save(sousJacents);
    }

    @Override
    @Transactional
    public void deleteById(long id) {
        sousJacentsRepository.deleteById(id);
    }
}
