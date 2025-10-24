package com.vilas.hungerHub.service;

import com.vilas.hungerHub.entity.IdSequence;
import com.vilas.hungerHub.repository.IdSequenceRepository;
import com.vilas.hungerHub.serviceInterface.IdSequenceService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class IdSequenceServiceImpl implements IdSequenceService {

    @Autowired
    private final IdSequenceRepository idSequenceRepository;

    @Transactional
    @Override
    public int getSequence(String name){
        IdSequence sequence = idSequenceRepository.findBySeqNameForUpdate(name);
        int val = sequence.getNextVal();
        sequence.setNextVal(val + 1);
        idSequenceRepository.save(sequence);
        return val;
    }

}
